package cn.com.lynx.commonutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * validation utils
 *
 * @author Lynx
 * @data 2019/04/26
 */
public class ValidationUtils {

    /**
     * 特殊字符 special character sequence regx
     */
    private static final String SPECIAL_CHAR_SEQUENCE =
        "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    /**
     * 中文字符 chines character sequence regx
     */
    private static final String CHINESE_CHAR_SEQUENCE =
        "[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]";
    /**
     * 数字 number character sequence regx
     */
    private static final String NUMBER_SEQUENCE = "[0-9]*";

    // ensure non-instantiation abilities
    private ValidationUtils() {}

    /**
     * validate is a string contains invalid special characters
     * <p>
     * 后端校验是否位特殊字符，将其过滤掉
     *
     * @param stringToValidate
     *            String
     * @return boolean
     */
    public static boolean isContainsSpecialChar(String stringToValidate) {
        // 命中规则通配符字符数组
        final Pattern p = Pattern.compile(SPECIAL_CHAR_SEQUENCE);
        Matcher m = p.matcher(stringToValidate);
        return m.find();
    }

    /**
     * validate if a string contains Chinese character, Chinese Characters is not allowed in ups
     * <p>
     * need to validate that 验证是否包含中文字符， 对于ups,目前来说,对中文的支持不太友好，需要过滤中文
     *
     * @param stringToValidate
     *            String
     * @return boolean
     */
    public static boolean isContainChinese(String stringToValidate) {
        if (StringUtils.isEmpty(stringToValidate)) {
            return false;
        }
        final Pattern p = Pattern.compile(CHINESE_CHAR_SEQUENCE);
        Matcher m = p.matcher(stringToValidate);
        return m.find();
    }

    /**
     * 判断是否为数字（包括小数）
     *
     * @param str
     * @return
     * @author LeoSong
     * @date 2019/07/08
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        final Pattern pattern = Pattern.compile(NUMBER_SEQUENCE);
        if (str.indexOf('.') >= 0) {// 判断是否有小数点
            if (str.indexOf('.') == str.lastIndexOf('.') && str.split("\\.").length == 2) { // 判断是否只有一个小数点
                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }
    }
}
