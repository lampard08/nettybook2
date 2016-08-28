package com.lampard.netty3.time;

import java.util.Date;

/**
 * Created by lampard on 16/8/28.
 */
public class UnixTime {
    private final int value;

    public UnixTime(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new Date(value * 1000L).toString();
    }
}
