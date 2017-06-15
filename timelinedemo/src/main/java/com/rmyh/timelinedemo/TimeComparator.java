package com.rmyh.timelinedemo;

import java.util.Comparator;

/**
 * Created by wen on 2017/6/14.
 */

public class TimeComparator implements Comparator<TimeData> {
    @Override
    public int compare(TimeData td1, TimeData td2) {
        return td2.getPosttime().compareTo(td1.getPosttime());
    }
}
