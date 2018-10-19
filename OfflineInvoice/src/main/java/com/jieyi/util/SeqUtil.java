package com.jieyi.util;

import java.text.SimpleDateFormat;

/**
 * Created by yuyong on 2018/06/01.
 */
public class SeqUtil {

    /**
     * 获取批次号
     *
     * @return
     */
    public static String getFPQQLSH() {
        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        //获取时间戳
        String time = dateFormat.format(now);
        int i = (int) (Math.random() * 900) + 100;

        return "RCT" + time + i;
    }
}
