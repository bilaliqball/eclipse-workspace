import scala.Tuple2;

import org.apache.spark.api.java.*;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.SparkConf;
public class CollaborativeFiltering {

	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setAppName("Java Collaborative Filtering Example").setMaster("local[1]");
		JavaSparkContext jsc = new JavaSparkContext(conf);

		// Load and parse the data
		String path = "C:\\Users\\bilal.iqbal\\Downloads\\files\\ratings.csv";
		JavaRDD<String> data = jsc.textFile(path);
		JavaRDD<Rating> ratings = data.map(s -> {
		  String[] sarray = s.split(",");
		  return new Rating(
		    Integer.parseInt(sarray[0]),
		    Integer.parseInt(sarray[1]),
		    Double.parseDouble(sarray[2]));
		});

		// Build the recommendation model using ALS
		int rank = 3;
		int numIterations = 1;
		MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(ratings), rank, numIterations, 0.01);
	

		// Evaluate the model on rating data
		JavaRDD<Tuple2<Object, Object>> userProducts =
		  ratings.map(r -> new Tuple2<>(r.user(), r.product()));
		JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = JavaPairRDD.fromJavaRDD(
		  model.predict(JavaRDD.toRDD(userProducts)).toJavaRDD()
		      .map(r -> new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating()))
		);
		JavaRDD<Tuple2<Double, Double>> ratesAndPreds = JavaPairRDD.fromJavaRDD(
		    ratings.map(r -> new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating())))
		  .join(predictions).values();
		double MSE = ratesAndPreds.mapToDouble(pair -> {
		  double err = pair._1() - pair._2();
		  return err * err;
		}).mean();
		System.out.println("Mean Squared Error = " + MSE);
		System.out.println(jsc.sc().toString());

		// Save and load model
		//model.save(jsc.sc(), "target/tmp/myCollaborativeFilter");
		//MatrixFactorizationModel sameModel = MatrixFactorizationModel.load(jsc.sc(),"target/tmp/myCollaborativeFilter");


	}
}
