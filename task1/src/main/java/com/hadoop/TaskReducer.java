package com.hadoop;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TaskReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    Long count = 0L;
    Text val = new Text();
    Text k = new Text();
    LongWritable v=new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        for (LongWritable value : values) {
            count+=value.get();
        }
        k.set(key);
        context.write(k,new LongWritable(count));
    }

    //    @Override
//    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {
//        for (BytesWritable value : values) {
//            System.out.println(value);
//            val.set(value.toString());
//            count++;
//        }
//        k.set(count.toString());
//        context.write(k, val);
//    }
}
