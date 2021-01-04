package com.hadoop.numsort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NumSortBean implements WritableComparable<NumSortBean> {
    private Long sort;
    private Long num;


    @Override
    public int compareTo(NumSortBean o) {
        //根据num的大小进行排序
        if (this.num > o.getNum()) {
            return -1;
        } else if (this.num < o.getNum()) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(sort);
        dataOutput.writeLong(num);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.sort = dataInput.readLong();
        this.num = dataInput.readLong();
    }

    public NumSortBean(Long sort, Long num) {
        this.sort = sort;
        this.num = num;
    }

    public NumSortBean() {
    }

    @Override
    public String toString() {
        return sort +
                "\t" + num;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
