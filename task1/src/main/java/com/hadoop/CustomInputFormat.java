package com.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

public class CustomInputFormat extends FileInputFormat<Text, BytesWritable> {
    //重写是否可切分的方法
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        //对于当前的需求不需要把文件切分，保证一个切片就是一个文件
        return false;
    }

    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        CustomInputReader customInputReader = new CustomInputReader();
        customInputReader.initialize(inputSplit, taskAttemptContext);
        return customInputReader;
    }
}
