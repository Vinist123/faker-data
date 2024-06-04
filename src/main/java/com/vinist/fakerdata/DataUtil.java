package com.vinist.fakerdata;

/**
 * @title: DataUtil
 * @description: <TODO Description>
 * @author: hd
 * @date: 2024/6/4 09:43
 */
public class DataUtil {

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
}
