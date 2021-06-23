package com.rj.bd.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wxy
 * @desc DateUtils 获取当前时间
 * @time 2021-05-07-10:22
 */

@Component
public class DateUtils {
    /**
     * 获得当前日期 yyyy-MM-dd HH:mm:ss
     *
     * @return 2021-05-07 12:35:12
     */
    public static String getCurrentTime() {
        // 小写的hh取得12小时，大写的HH取的是24小时
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return df.format(date);
    }

    /**
     * 获得当前日期 yyyy-MM-dd
     *
     * @return 2021-05-07 12:35:12
     */
    public static String getCurrentTimeYMD() {
        // 小写的hh取得12小时，大写的HH取的是24小时
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return df.format(date);
    }

    /**
     * 根据传入 开始时间 结束时间 得到相差多少分钟
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public static long getTimeDifference(String startTime, String endTime) throws Exception {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff;
        //获得两个时间的毫秒时间差异
        diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        long day = diff/nd;//计算差多少天
        long hour = diff%nd/nh;//计算差多少小时
        long min = diff%nd%nh/nm;//计算差多少分钟
        long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
        System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
        return min ;
    }



}
