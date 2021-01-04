package com.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TaskDervier {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"/Users/y/Workspace/study/第一期/习题/taskData/", "/Users/y/Workspace/study/第一期/习题/taskOut"};
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        //设置jar
        job.setJarByClass(TaskDervier.class);


        job.setMapperClass(TaskMap.class);
        job.setReducerClass(TaskReducer.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);


        //自定义inputformat
        //job.setInputFormatClass(CustomInputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        boolean b = job.waitForCompletion(true);
        System.out.println("执行结果" + b);
        System.exit(b ? 0 : 1);
    }
}
