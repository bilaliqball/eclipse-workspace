import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Rating implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int userId;
  private int movieId;
  private float rating;
  private long timestamp;

  public Rating() {}

  public Rating(int userId, int movieId, float rating, long timestamp) {
    this.userId = userId;
    this.movieId = movieId;
    this.rating = rating;
    this.timestamp = timestamp;
  }

  public int getUserId() {
    return userId;
  }

  public int getMovieId() {
    return movieId;
  }

  public float getRating() {
    return rating;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public static Rating parseRating(String str) {
    String[] fields = str.split("::");
    if (fields.length != 4) {
      throw new IllegalArgumentException("Each line must contain 4 fields");
    }
    int userId = Integer.parseInt(fields[0]);
    int movieId = Integer.parseInt(fields[1]);
    float rating = Float.parseFloat(fields[2]);
    long timestamp = Long.parseLong(fields[3]);
    return new Rating(userId, movieId, rating, timestamp);
  }

public static void main(String args[]) {
SparkSession spark = SparkSession.builder().master("local").appName("JavaRandomForestRegressorExample").getOrCreate();
JavaRDD<Rating> ratingsRDD = spark.read().textFile("C:\\Users\\bilal.iqbal\\Downloads\\files\\rating.txt").javaRDD()
.map(new Function<String, Rating>() {public Rating call(String str) {return Rating.parseRating(str);}});

Dataset<Row> ratings = spark.createDataFrame(ratingsRDD, Rating.class);
Dataset<Row>[] splits = ratings.randomSplit(new double[]{0.8, 0.2});
Dataset<Row> training = splits[0];
Dataset<Row> test = splits[1];

// Build the recommendation model using ALS on the training data
ALS als = new ALS()
  .setMaxIter(3)
  .setRegParam(0.01)
  .setUserCol("userId")
  .setItemCol("movieId")
  .setRatingCol("rating");
ALSModel model = als.fit(training);

// Evaluate the model by computing the RMSE on the test data
Dataset<Row> predictions = model.transform(test);

RegressionEvaluator evaluator = new RegressionEvaluator()
  .setMetricName("rmse")
  .setLabelCol("rating")
  .setPredictionCol("prediction");
Double rmse = evaluator.evaluate(predictions);
System.out.println("Root-mean-square error = " + rmse);


//// Generate top 10 movie recommendations for each user
//Dataset<Row> userRecs = model.recommendForAllUsers(10);
//
//// Generate top 10 user recommendations for each movie
//Dataset<Row> movieRecs = model.recommendForAllItems(10);

// Generate top 10 movie recommendations for a specified set of users
Dataset<Row> users = ratings.select(als.getUserCol()).distinct().limit(3);
Dataset<Row> userSubsetRecs = model.recommendForUserSubset(users, 10);

//// Generate top 10 user recommendations for a specified set of movies
//Dataset<Row> movies = ratings.select(als.getItemCol()).distinct().limit(3);
//Dataset<Row> movieSubSetRecs = model.recommendForItemSubset(movies, 10);
// $example off$
//userRecs.show();
//movieRecs.show();

//userSubsetRecs.show();
//movieSubSetRecs.show();
predictions.show();
spark.stop();

}}