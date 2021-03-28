import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


import scala.Tuple2;
import java.util.Arrays;

public class WordCountSorted{

public static void main(String[] args) throws Exception {

	
Logger.getLogger("org").setLevel(Level.ERROR);
SparkConf conf = new SparkConf().setAppName("SortedWordCountSolution").setMaster("local[1]");
JavaSparkContext sc = new JavaSparkContext(conf);
JavaRDD<String> lines = sc.textFile("C:\\Users\\bilal.iqbal\\Downloads\\files\\README.txt");
JavaRDD<String> wordRdd = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

JavaPairRDD<String, Integer> wordPairRdd = wordRdd.mapToPair(word -> new Tuple2<>(word, 1));
JavaPairRDD<String, Integer> wordToCountPairs = wordPairRdd.reduceByKey((x, y) -> x + y);

JavaPairRDD<Integer, String> countToWordParis = wordToCountPairs.mapToPair(wordToCount -> new Tuple2<>(wordToCount._2(),wordToCount._1()));
JavaPairRDD<Integer, String> sortedCountToWordParis = countToWordParis.sortByKey(false);

JavaPairRDD<String, Integer> sortedWordToCountPairs = sortedCountToWordParis.mapToPair(countToWord -> new Tuple2<>(countToWord._2(), countToWord._1()));

//sortedWordToCountPairs.saveAsTextFile("/home/bee/Desktop/spark/output/sortedwordcount");
//for (Tuple2<String, Integer> wordToCount : sortedWordToCountPairs.collect()) {
//System.out.println(wordToCount._1() + " : " + wordToCount._2());


//filter out words with less than threshold occurrences
JavaPairRDD<String, Integer> filtered = sortedWordToCountPairs.filter(
new Function<Tuple2<String, Integer>, Boolean>() {
@Override
public Boolean call(Tuple2<String, Integer> tup) {
	Boolean res=tup._2() >= 2;
	if(res) {System.out.println(tup._1+","+tup._2());}else {}
return tup._2() >= 2;
}
}
);

//filtered.saveAsTextFile("C:\\Users\\bilal.iqbal\\Downloads\\files\\output");


}
}