import org.apache.hadoop.hive.ql.exec.spark.session.SparkSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;

import scala.Tuple2;


public class RecomendationEngine {

	public static void main(String[] args) {
		
		// Turn off unnecessary logging
		Logger.getLogger("org").setLevel(Level.OFF);
		Logger.getLogger("akka").setLevel(Level.OFF);
		
		// Create Java spark context
		//SparkSession spark = SparkSession.builder().master("local").appName("JavaRandomForestRegressorExample").getOrCreate();
		//SparkConf conf = new SparkConf().setAppName("Collaborative Filtering Example");
		SparkConf conf=new SparkConf().setAppName("SOME APP NAME").setMaster("local[1]");
		JavaSparkContext sc = new JavaSparkContext(conf);

		System.out.println("Read user-item rating file. format - userId,itemId,rating");
		JavaRDD<String> userItemRatingsFile = sc.textFile("C:\\Users\\bilal.iqbal\\Downloads\\files\\ratings.csv");
		
		// Read item description file. format - itemId, itemName, Other Fields,..
		JavaRDD<String> itemDescritpionFile = sc.textFile("C:\\Users\\bilal.iqbal\\Downloads\\files\\movies.csv");
		
		System.out.println("Map file to Ratings(user,item,rating) tuples");
		JavaRDD<Rating> ratings = userItemRatingsFile.map(new Function<String, Rating>() {
			public Rating call(String s) {
				String[] sarray = s.split(",");
				return new Rating(Integer.parseInt(sarray[0]), Integer
						.parseInt(sarray[1]), Double.parseDouble(sarray[2]));
			}
		});
		
		System.out.println("Create tuples(itemId,ItemDescription), will be used later to get names of item from itemId");
		JavaPairRDD<Integer,String> itemDescritpion = itemDescritpionFile.mapToPair(
				new PairFunction<String, Integer, String>() {
			@Override
			public Tuple2<Integer, String> call(String t) throws Exception {
				String[] s = t.split(",");
				return new Tuple2<Integer,String>(Integer.parseInt(s[0]), s[1]);
			}
		});

		// Build the recommendation model using ALS
		
		int rank = 10; // 10 latent factors
		int numIterations = Integer.parseInt("1"); // number of iterations
		
		System.out.println("MatrixFactorizationModel model = ALS.trainImplicit(JavaRDD.toRDD(ratings),rank, numIterations)");
		//MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(ratings), rank, numIterations, 0.01);
		MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(ratings), rank, numIterations);

		// Create user-item tuples from ratings
		JavaRDD<Tuple2<Object, Object>> userProducts = ratings
				.map(new Function<Rating, Tuple2<Object, Object>>() {
					public Tuple2<Object, Object> call(Rating r) {
						return new Tuple2<Object, Object>(r.user(), r.product());
					}
				});
		
		System.out.println("Calculate the itemIds not rated by a particular user, say user with userId = 1");
		JavaRDD<Integer> notRatedByUser = userProducts.filter(new Function<Tuple2<Object,Object>, Boolean>() {
			@Override
			public Boolean call(Tuple2<Object, Object> v1) throws Exception {
				if (((Integer) v1._1).intValue() != 0) {
					return true;
				}
				return false;
			}
		}).map(new Function<Tuple2<Object,Object>, Integer>() {
			@Override
			public Integer call(Tuple2<Object, Object> v1) throws Exception {
				return (Integer) v1._2;
			}
		});
		
		System.out.println("Create user-item tuples for the items that are not rated by user, with user id 1");
		JavaRDD<Tuple2<Object, Object>> itemsNotRatedByUser = notRatedByUser
				.map(new Function<Integer, Tuple2<Object, Object>>() {
					public Tuple2<Object, Object> call(Integer r) {
						return new Tuple2<Object, Object>(0, r);
					}
		});
		
		System.out.println("Predict the ratings of the items not rated by user for the user");
		JavaRDD<Rating> recomondations = model.predict(itemsNotRatedByUser.rdd()).toJavaRDD().distinct();
				
		System.out.println("Sort the recommendations by rating in descending order");
		recomondations = recomondations.sortBy(new Function<Rating,Double>(){
			@Override
			public Double call(Rating v1) throws Exception {
				return v1.rating();
			}
			
		}, false, 1);	
		
		System.out.println("Get top 10 recommendations");
		JavaRDD<Rating> topRecomondations = sc.parallelize(recomondations.take(3));
		
		// Join top 10 recommendations with item descriptions
		JavaRDD<Tuple2<Rating, String>> recommendedItems = topRecomondations.mapToPair(
				new PairFunction<Rating, Integer, Rating>() {
			@Override
			public Tuple2<Integer, Rating> call(Rating t) throws Exception {
				return new Tuple2<Integer,Rating>(t.product(),t);
			}
		}).join(itemDescritpion).values();
		
		
		System.out.println("Print the top recommendations for user 1.");
		recommendedItems.foreach(new VoidFunction<Tuple2<Rating,String>>() {
			@Override
			public void call(Tuple2<Rating, String> t) throws Exception {
				System.out.println(t._1.product() + "\t" + t._1.rating() + "\t" + t._2);
			}
		});
		
	}

}