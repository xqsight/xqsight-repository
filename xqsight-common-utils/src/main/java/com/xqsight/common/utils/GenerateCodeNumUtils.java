package com.xqsight.common.utils;

import java.util.UUID;

/**
 * 生成编码、字符串等工具类
 * @author wangshujin
 * */
public class GenerateCodeNumUtils {

    public static String getSysJournalNo(int length) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (uuid.length() > length) {
            uuid = uuid.substring(0, length);
        } else if (uuid.length() < length) {
            for (int i = 0; i < length - uuid.length(); i++) {
                uuid = uuid + Math.round(Math.random() * 9);
            }
        }
        return uuid;
    }
}
