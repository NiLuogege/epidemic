package com.songyuan.epidemic.utils.regex;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by LuoChen on 2017/10/20.
 * 正则url
 */

public class RegexUtils {
    /**
     * 正则：URL
     * [^\s]* : 除过空格外任意字符
     */
    public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";


    /**
     * 验证URL
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isURL(final CharSequence input) {
        if (input != null) {
            return isMatch(REGEX_URL, input);
        } else {
            return false;
        }
    }


    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }


    private RegexUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * If u want more please visit http://toutiao.com/i6231678548520731137/
     */

    /**
     * 验证人民币
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isRMB(String string) {
        return isMatch(ConstUtils.REGEX_RMB, string);
    }

    /**
     * 验证手机号（简单）
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isMobileSimple(String string) {
        return isMatch(ConstUtils.REGEX_MOBILE_SIMPLE, string);
    }

    /**
     * 验证手机号（精确）
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isMobileExact(String string) {
        return isMatch(ConstUtils.REGEX_MOBILE_EXACT, string);
    }

    /**
     * 验证4位验证码
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isVerifyCode(String string) {
        return isMatch(ConstUtils.REGEX_CERIFY_CODE, string);
    }

    /**
     * 验证电话号码
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isTel(String string) {
        return isMatch(ConstUtils.REGEX_TEL, string);
    }

    /**
     * 验证身份证号码15位
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isIDCard15(String string) {
        return isMatch(ConstUtils.REGEX_IDCARD15, string);
    }

    /**
     * 验证身份证号码18位
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isIDCard18(String string) {
        return isMatch(ConstUtils.REGEX_IDCARD18, string);
    }

    /**
     * 验证邮箱
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isEmail(String string) {
        return isMatch(ConstUtils.REGEX_EMAIL, string);
    }

    /**
     * 验证汉字
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isChz(String string) {
        return isMatch(ConstUtils.REGEX_CHZ, string);
    }

    /**
     * 验证用户名
     * <p>取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位</p>
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isUsername(String string) {
        return isMatch(ConstUtils.REGEX_USERNAME, string);
    }

    /**
     * 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isDate(String string) {
        return isMatch(ConstUtils.REGEX_DATE, string);
    }

    /**
     * 验证IP地址
     *
     * @param string 待验证文本
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isIP(String string) {
        return isMatch(ConstUtils.REGEX_IP, string);
    }

    /**
     * string是否匹配regex
     *
     * @param regex  正则表达式字符串
     * @param string 要匹配的字符串
     * @return true: 匹配<br>false: 不匹配
     */
    public static boolean isMatch(String regex, String string) {
        return !TextUtils.isEmpty(string) && Pattern.matches(regex, string);
    }

    /**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean isBankAccount(String cardId) {
        if (cardId.length() < 16) return false;
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        return bit != 'N' && cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }
}
