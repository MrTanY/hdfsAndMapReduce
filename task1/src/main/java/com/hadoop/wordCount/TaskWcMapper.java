package com.hadoop.wordCount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TaskWcMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    Text k = new Text();
    LongWritable v = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String val = value.toString();
        k.set(val);
        v.set(1);
        context.write(k, v);
    }
}
