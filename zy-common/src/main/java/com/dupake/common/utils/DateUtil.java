package com.dupake.common.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @ClassName DateUtil
 * @Description 日期工具类
 * @Author dupake
 * @Date 2020/6/10 14:28
 */
public class DateUtil {
    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

}
