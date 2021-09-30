package com.springboot.demo.util;

/**
 * 脱敏工具类
 */
public class DesensitizationUtil {

    /**
     * 自定义脱敏规则
     * @param value
     * @param prefixLen
     * @param suffixLen
     * @param maskStr
     * @return
     */
    public static String desValue(String value, int prefixLen, int suffixLen, String maskStr) {
        if (value == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = value.length(); i < n; i++) {
            if (i < prefixLen) {
                sb.append(value.charAt(i));
                continue;
            }
            if (i > (n - suffixLen - 1)) {
                sb.append(value.charAt(i));
                continue;
            }
            sb.append(maskStr);
        }
        return sb.toString();
    }

    /**
     * 中文姓名，如张三丰--》张**
     * @param value
     * @return
     */
    public static String chineseName(String value) {
        if (value == null) {
            return null;
        }
        return value.replaceAll("(.)(.*)","$1**");
    }

    /**
     * 身份证号，前三位后四位，511*****1234
     * @param value
     * @return
     */
    public static String idCardNum(String value) {
        //return desValue(id, 6, 4, "*");
        return value.replaceAll("(\\d{3}(\\d{11})(\\d{4}))","$1******$2");
    }

    /**
     * 固定电话，后四位，028-12345678--》*****5678
     * @param value
     * @return
     */
    public static String telePhone(String value) {
        return desValue(value, 0, 4, "*");
    }

    /**
     * 手机号，前三位后四位，158****1234
     * @param value
     * @return
     */
    public static String mobilePhone(String value) {
        //return desValue(num, 3, 4, "*");
        return value.replaceAll("(\\d{3})(\\d{4})(\\d{4})","$1****$2");
    }

    /**
     * 【地址】只显示到地区，不显示详细地址，比如：北京市海淀区****
     * @param value
     * @return
     */
    public static String address(String value) {
        return desValue(value, 6, 0, "*");
    }

    /**
     * 电子邮箱 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示，比如：d**@126.com
     * @param value
     * @return
     */
    public static String email(String value) {
        if (value == null) {
            return null;
        }
        int index = value.lastIndexOf("@");
        if (index <= 1) {
            return value;
        }
        String preEmail = desValue(value.substring(0, index), 1, 0, "*");
        return preEmail + value.substring(index);

    }

    /**
     * 【银行卡号】前六位，后四位，其他用星号隐藏每位1个星号，比如：622260**********1234
     * @param value
     * @return
     */
    public static String bankCard(String value) {
        return desValue(value, 6, 4, "*");
    }

    /**
     * 【密码】密码的全部字符都用*代替，比如：******
     * @param value
     * @return
     */
    public static String password(String value) {
        if (value == null) {
            return null;
        }
        return "******";
    }

    /**
     * 【密钥】密钥除了最后三位，全部都用*代替，比如：***xdS 脱敏后长度为6，如果明文长度不足三位，则按实际长度显示，剩余位置补*
     *
     * @param key 密钥
     * @return 结果
     */
    public static String key(String key) {
        if (key == null) {
            return null;
        }
        int viewLength = 6;
        StringBuilder tmpKey = new StringBuilder(desValue(key, 0, 3, "*"));
        if (tmpKey.length() > viewLength) {
            return tmpKey.substring(tmpKey.length() - viewLength);
        } else if (tmpKey.length() < viewLength) {
            int buffLength = viewLength - tmpKey.length();
            for (int i = 0; i < buffLength; i++) {
                tmpKey.insert(0, "*");
            }
            return tmpKey.toString();
        } else {
            return tmpKey.toString();
        }
    }

}
