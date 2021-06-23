package com.rj.bd.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.DigestUtils;

/**
 * @desc 验证生成 工具类
 */
public class VerificationUtil {

    //用于md5混淆
    private static final String slat = "&%52wy***&&%%$$#@";

    /**
     * @desc  生成四位随机码
     * @return
     */
    public static String generateVerificationCode() {
        return RandomStringUtils.random(4, "2345678wertyuipasdfghjkzxcvbnm");
    }



    /**
     * 生成md5
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
