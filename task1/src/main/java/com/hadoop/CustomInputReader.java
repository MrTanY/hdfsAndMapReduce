package com.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class CustomInputReader extends RecordReader<Text, BytesWritable> {


    private FileSplit split;
    //hadoop配置对象
    private Configuration configuration;


    //定义key，value的成员变量
    private Text key = new Text();
    private BytesWritable value = new BytesWritable();


    private Boolean flag = true;

    /**
     * 初始化方法，把切片以及上下文提升为全局
     * @param inputSplit
     * @param taskAttemptContext
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        split = (FileSplit) inputSplit;
        configuration = taskAttemptContext.getConfiguration();
    }

    /**
     * 读取数据
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        //对于当前solit来说只需要读取一次即可，因为一次就把整个文件全读取了
        if (flag) {
            //准备一个数组存放读取得到的数据
            byte[] bytes = new byte[(int) split.getLength()];
            Path path = split.getPath();//获取切牌呢的path信息
            FileSystem fileSystem = path.getFileSystem(configuration);//获取文件对象
            FSDataInputStream dataInputStream = fileSystem.open(path);
            //读取数据并把数据放入byte
            IOUtils.readFully(dataInputStream, bytes, 0, bytes.length);
            //封装key，value
            key.set(path.toString());
            value.set(bytes, 0, bytes.length);
            IOUtils.closeStream(dataInputStream);
            //把再次读取的开关设置为false
            flag = false;
            return true;
        }

        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }


    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
