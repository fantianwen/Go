package com.study.radasm.go.Sina;

import java.util.ArrayList;

/**
 * Created by RadAsm on 15/5/25.
 */
public class Fans {
    public int next_cursor;
    public int previous_cursor;
    public int total_number;
    public ArrayList<com.study.radasm.go.Sina.User> users;

    @Override
    public String toString() {
        return "Fans{" +
                "next_cursor=" + next_cursor +
                ", previous_cursor=" + previous_cursor +
                ", total_number=" + total_number +
                ", users=" + users +
                '}';
    }
}
