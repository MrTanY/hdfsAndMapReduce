package com.hadoop.numsort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 设置map输入的kv以及map输出的kv
 */
public class NumSortMapper extends Mapper<LongWritable, Text, LongWritable, NullWritable> {
    LongWritable val = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        long v = Long.parseLong(value.toString());
        val.set(v);
        context.write(val, NullWritable.get());
    }
}
