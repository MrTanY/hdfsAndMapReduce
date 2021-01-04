package com.hadoop.numbersort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class NumberMap extends Mapper<LongWritable, Text, LongWritable, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Long v = Long.parseLong(value.toString());
        context.write(new LongWritable(v), NullWritable.get());

    }
}
