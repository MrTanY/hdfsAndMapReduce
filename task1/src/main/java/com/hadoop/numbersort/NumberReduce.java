package com.hadoop.numbersort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class NumberReduce extends Reducer<LongWritable, NullWritable, LongWritable, LongWritable> {
    Long index=0L;
    @Override
    protected void reduce(LongWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        index++;
        for (NullWritable value : values) {
            context.write(new LongWritable(index),key);
        }
    }
}
