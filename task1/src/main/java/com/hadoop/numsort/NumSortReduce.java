package com.hadoop.numsort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class NumSortReduce extends Reducer<LongWritable, NullWritable, LongWritable, NumSortBean> {
    private Long index=0L;

    @Override
    protected void reduce(LongWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        NumSortBean bean = new NumSortBean();
        index++;
        bean.setSort(index);
        for (NullWritable value : values) {
            System.out.println(key.get());
            bean.setNum(key.get());
            context.write(key,bean);
        }
    }
}
