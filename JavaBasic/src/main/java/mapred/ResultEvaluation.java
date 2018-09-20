package mapred;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class ResultEvaluation {

    public static class AnswerMapper
            extends Mapper<LongWritable, Text, Text, DoubleWritable>{

        private Map<String, String> answerSheet  = new java.util.HashMap<String, String>();
        private static TreeMap<String, Double> toRecordMap = new TreeMap<String, Double>();


        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            String answerSheetLocation  = context.getConfiguration().get("answer.sheet.location");
            System.out.println(context.getConfiguration().get("answer.sheet.location"));
            answerSheet = getAnswerSheet(context.getConfiguration(), answerSheetLocation);
        }

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            if (line !=null) {
                String[] lineSplit = line.split(",");
                if (lineSplit != null && lineSplit.length >= 3) {
                    String userId  = lineSplit[0];
                    String questionId = lineSplit[1];
                    String answerId = lineSplit[2];
                    if (answerSheet.containsKey(questionId) && answerSheet.get(questionId).equals(answerId)) {
                        System.out.println(line);
//                        context.write(new Text(userId), new DoubleWritable(1.0));
                        double userScore = toRecordMap.get(userId) == null ? 1.0 : toRecordMap.get(userId) + 1.0;
                        toRecordMap.put(userId, userScore);
                    }
                    System.out.println(toRecordMap);
                    Iterator<Map.Entry<String , Double>> iter = toRecordMap.entrySet().iterator();
                    Map.Entry<String , Double> entry = null;

                    while(toRecordMap.size()>5){
                        entry = iter.next();
                        iter.remove();
                    }
                }
            }
        }

        protected void cleanup(Context context) throws IOException, InterruptedException {
            for (String userId :toRecordMap.keySet()) {
                context.write(new Text(userId), new DoubleWritable(toRecordMap.get(userId)));
            }
        }

        private Map<String, String> getAnswerSheet(Configuration conf, String answerSheetLocation) throws IOException {
            Map<String, String> answerSheet = new HashMap<String, String>();
            if (answerSheetLocation == null) {
                throw new IllegalArgumentException("AnswerSheet location is null");
            }

            Path path=new Path(answerSheetLocation);
            FileSystem fs = FileSystem.get(conf);

            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(path)));
            try {
                String line;
                line=br.readLine();
                while (line != null){
                    if (line != null ) {
                        String[] lineSplit = line.split(",");
                        if (lineSplit != null && lineSplit.length == 2) {
                            answerSheet.put(lineSplit[0], lineSplit[1]);
                        }
                    }
                    line = br.readLine();
                }
            } finally {
                br.close();
            }
            return answerSheet;
        }
    }

    public static class SumReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private static TreeMap<String, Double> toRecordMap = new TreeMap<String, Double>();

        public void reduce(Text key, Iterable<DoubleWritable> values,
                           Context context) throws IOException, InterruptedException {
            System.out.println("CCCCC");
            double totalScore = 0;
            for (DoubleWritable val : values) {
                totalScore += val.get();
            }
            toRecordMap.put(key.toString(), totalScore);

            Iterator<Map.Entry<String , Double>> iter = toRecordMap.entrySet().iterator();
            Map.Entry<String , Double> entry = null;

            while(toRecordMap.size()>5){
                entry = iter.next();
                iter.remove();
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for (String userId :toRecordMap.keySet()) {
                context.write(new Text(userId), new DoubleWritable(toRecordMap.get(userId)));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("answer.sheet.location","/Users/pragya.mittal/workspace/inmobi/JavaBasic/src/main/java/mapred/AnswerSheet");
        Job job = Job.getInstance(conf, "Result Evaluator");
        job.setJarByClass(ResultEvaluation.class);
        job.setMapperClass(AnswerMapper.class);
        job.setReducerClass(SumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job, new Path("/Users/pragya.mittal/workspace/inmobi/JavaBasic/src/main/java/mapred/input"));
        String outputPath = "/Users/pragya.mittal/workspace/inmobi/JavaBasic/src/main/java/mapred/output";
        Path path = new Path(outputPath);
        FileSystem fs = FileSystem.get(conf);
        job.setNumReduceTasks(0);
        if (fs.exists(path)) {
            fs.delete(path);
        }
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}