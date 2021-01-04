package com.hadoop.numsort;

import com.hadoop.TaskDervier;
import com.hadoop.TaskMap;
import com.hadoop.TaskReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class NumSortDervier {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"/Users/y/Workspace/study/第一期/习题/taskData/", "/Users/y/Workspace/study/第一期/习题/taskOut"};
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        //设置jar
        job.setJarByClass(TaskDervier.class);


        job.setMapperClass(NumSortMapper.class);
        job.setReducerClass(NumSortReduce.class);


        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(NullWritable.class);


        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(NumSortBean.class);


        //自定义inputformat
        //job.setInputFormatClass(CustomInputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        boolean b = job.waitForCompletion(true);
        System.out.println("执行结果" + b);
        System.exit(b ? 0 : 1);
    }
}
