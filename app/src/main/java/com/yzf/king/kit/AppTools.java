package com.yzf.king.kit;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.droidlover.xdroidmvp.kit.Codec;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;

/**
 * ClaseName：AppTools
 * Description：APP工具方法
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/17 17:23
 * Modified By：
 * Fixtime：2017/3/17 17:23
 * FixDescription：
 */

public class AppTools {

    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 检查是否存在汉字
     *
     * @param countname
     * @return 存在为true
     */
    public static boolean checkChinese(String countname) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 获取app地址
     *
     * @return
     */
    public static String getBaseUrl() {
        if (AppConfig.TEST) {
            return AppConfig.BASE_URL_TEST;
        } else {
            return AppConfig.BASE_URL_PROD;
        }
    }

    /**
     * 获取H5地址
     *
     * @return
     */
    public static String getH5Url() {
        if (AppConfig.TEST) {
            return AppConfig.H5_URL_TEST;
        } else {
            return AppConfig.H5_URL_PROD;
        }
    }


    public static String formatStatus(String status) {
        if (!AppTools.isEmpty(status)) {
            switch (status) {
                case "00":
                    status = "已实名认证";
                    break;
                case "01":
                    status = "已冻结";
                    break;
                case "02":
                    status = "认证图片不全";
                    break;
                case "03":
                    status = "未认证";
                    break;
                case "10":
                    status = "已冻结";
                    break;
                case "30":
                    status = "审核中";
                    break;
            }
        }
        return status;
    }

    /**
     * 格式化卡号（卡号脱敏）
     *
     * @param card
     * @return
     */
    public static String formatCard(String card) {
        if (AppTools.isEmpty(card) || card.length() <= 8) {
            return card;
        }
        return replaceString(card, "****", 4, card.length() - 4);
    }

    public static String formatPhone(String phone) {
        if (AppTools.isEmpty(phone) || phone.length() != 11) {
            return phone;
        }
        return replaceString(phone, "****", 3, 7);
    }

    /**
     * 格式化卡号（卡号脱敏）
     *
     * @param card
     * @return
     */
    public static String getLastCardId(String card) {
        if (AppTools.isEmpty(card) || card.length() <= 8) {
            return card;
        }
        return card.substring(card.length() - 4, card.length());
    }


    /**
     * 格式化名字（名字脱敏）
     *
     * @param name
     * @return
     */
    public static String formatName(String name) {
        if (AppTools.isEmpty(name)) {
            return "";
        }
        if (name.length() == 2) {
            return replaceString(name, "*", 1, 2);
        }
        if (name.length() == 3) {
            return replaceString(name, "*", 1, 2);
        }
        if (name.length() == 11) {
            return formatPhone(name);
        }
        return name.length() >= 4 ? replaceString(name, "*", name.length() - 1, name.length()) : name;
    }

    public static String replaceString(String content, String replaceStr, int begin, int end) {
        if (isEmpty(content)) {
            return "";
        }
        if (isEmpty(replaceStr)) {
            return content;
        }
        if (begin < 0) {
            return content;
        }
        if (end > content.length()) {
            return content;
        }
        StringBuilder sb = new StringBuilder(content);
        sb.replace(begin, end, replaceStr);
        return sb.toString();
    }


    public static boolean strToBool(String s) {
        if (!AppTools.isEmpty(s) && s.equals("true")) {
            return true;
        }
        return false;
    }


    /**
     * 读取assets下的txt文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTextReturnStr(Context context, String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName + ".txt");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取字符串中连续字符串的最大个数
     *
     * @param s
     * @return
     */
    public static int getContinuousCount(String s) {
        String b[] = s.split("\\.");
        int maxSucc = 1;
        int temp = 1;
        if (b[0].length() >= 3) {
            char array[] = b[0].toCharArray();
            for (int i = 1; i < array.length; i++) {
                if (array[i] == array[i - 1]) {
                    temp++;
                } else {
                    if (temp > maxSucc) {
                        maxSucc = temp;
                    }
                    temp = 1;
                }
            }
            maxSucc = maxSucc > temp ? maxSucc : temp;
        }
        return maxSucc;
    }

    /**
     * 将以分转为百分数
     *
     * @param num
     * @return
     */
    public static String F2Percentformat(Object num) {
        double d = Double.valueOf(num.toString());
        d = d / 10000;
        DecimalFormat df = new DecimalFormat("0.00%");
        String result = df.format(d);
        return result;
    }

    /**
     * 格式化金额(加上最后两位00) 15->15.00
     *
     * @param amt
     * @return
     */
    public static String formatAmt(Object amt) {
        return new DecimalFormat("0.00").format(new BigDecimal(amt.toString()));
    }

    /**
     * 格式化金额(加上最后两位00) 15->15.000
     *
     * @param amt
     * @return
     */
    public static String formatLAmt(Object amt) {
        return new DecimalFormat("0.000").format(new BigDecimal(amt.toString()));
    }

    /**
     * 将以分为单位的金额转为元
     *
     * @param s
     * @return
     */
    public static String formatF2Y(Object s) {
        if (s != null && !AppTools.isEmpty(s.toString())) {
            return new BigDecimal(s.toString()).setScale(4, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
        return "0.00";
    }

    /**
     * 将以里为单位的金额转为元
     *
     * @param s
     * @return
     */
    public static String formatL2Y(Object s) {
        if (s != null && !AppTools.isEmpty(s.toString())) {
            return new BigDecimal(s.toString()).setScale(4, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(1000)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
        }
        return "0.00";
    }

    /**
     * 将以元为单位的金额转为分
     *
     * @param s
     * @return
     */
    public static String formatY2F(Object s) {
        if (s != null && !AppTools.isEmpty(s.toString())) {
            return new BigDecimal(s.toString()).setScale(4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).toString();
        }
        return "0";
    }


    /**
     * 获取微信分享url
     *
     * @param upMerchId
     * @param branchId
     * @return
     */
    public static String getWxUrl(String upMerchId, String branchId) {
        String url = AppConfig.WX_URL + "upMerchId=" + upMerchId + "&branchId=" + branchId;
        return url;
    }

    /**
     * 判断是否为邮箱
     *
     * @param strInput
     * @return
     */

    public static boolean isEmail(String strInput) {
        if (strInput != null && !strInput.trim().equalsIgnoreCase("")) {
            String Regular = "^([a-z0-9A-Z]+[-|\\_.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern pattern = Pattern.compile(Regular, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(strInput);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * 功能：判断是不是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        return mobile.length() == 11 && Pattern.matches("^(1[3|4|5|6|7|8|9])\\d{9}$", mobile);
    }

    /**
     * 如果字符串为null,或空串，或全为空格，返回true;否则返回false
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return !((str != null) && (str.trim().length() > 0));
    }

    /**
     * APP加密算法
     *
     * @param centent
     * @return
     */
    public static String appEncrypt(String centent) {
        String sign = centent;
        Logger.i("sign:" + sign);
        sign = Codec.MD5.getMD5(sign, 1).toUpperCase();
        Logger.i("sign1:" + sign);
        sign = Codec.MD5.getMD5((sign + AppUser.getInstance().getKey()), 0).toUpperCase();
        Logger.i("key:" + AppUser.getInstance().getKey());
        Logger.i("sign2:" + sign);
        return sign;
    }

    /**
     * 以当前时间数+四位随机数作为订单号
     *
     * @return
     */
    public static String craeateOrderId() {
        String temp;
        String time = Kits.Date.getyyyyMMddHHmmss();
        temp = "Android" + time + Kits.Random.getRandomNumbers(4);
        return temp;
    }


    /**
     * string类型转换为long类型
     *
     * @param strTime    要转换的String类型的时间
     * @param formatType 时间格式 strTime的时间格式和formatType的时间格式必须相同
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * string类型转换为long类型
     *
     * @param strTime    要转换的String类型的时间
     * @param formatType 时间格式 strTime的时间格式和formatType的时间格式必须相同
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * date类型转换为long类型
     *
     * @param date 要转换的date类型的时间
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 获取星期
     *
     * @param time       时间字符串    time的时间格式必须要与formatType的时间格式相同
     * @param formatType 日期格式 yyyy-MM-dd HH:mm:ss
     * @return 0-周日,1-周一，2-周二，3-周三，4-周四，5-周五，6-周六
     */
    public static String getWeek(String time, String formatType) {
        try {
            long mills;
            mills = stringToLong(time, formatType);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mills);
            int temp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            switch (temp) {
                case 0:
                    time = "周日";
                    break;
                case 1:
                    time = "周一";
                    break;
                case 2:
                    time = "周二";
                    break;
                case 3:
                    time = "周三";
                    break;
                case 4:
                    time = "周四";
                    break;
                case 5:
                    time = "周五";
                    break;
                case 6:
                    time = "周六";
                    break;
                default:
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

//    /**
//     * 包含大小写字母及数字且在6-20位
//     * 是否包含
//     *
//     * @param str
//     * @return
//     */
//    public static boolean isLetterDigit(String str) {
//        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
//        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
//        for (int i = 0; i < str.length(); i++) {
//            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
//                isDigit = true;
//            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
//                isLetter = true;
//            }
//        }
//        String regex = "^[a-zA-Z0-9]{6,20}$";
//        boolean isRight = isDigit && isLetter && str.matches(regex);
//        return isRight;
//    }

    /**
     * 功能：验证密码
     * 包含大小写字母及数字且在6-20位
     */
    public static boolean isPassWord(String password) {
        if (AppTools.isEmpty(password)) {
            return false;
        }
        if (password.trim().length() < 6 || password.trim().length() > 20) {
            return false;
        }
        return true;
    }



    /***
     * 保留两位小数
     * @param editText
     * @param length 整数数字长度
     */
    /*** 小数点后的位数 */
    private static final int POINTER_LENGTH = 2;

    private static final String POINTER = ".";

    private static final String ZERO = "0";

    private static String number;
    private static int curSelection;
    public static boolean keepTwoDecimals(EditText editText, int length) {

        number = editText.getText().toString();
        //第一位不能输入小点
        if (number.length() == 1 && TextUtils.equals(number.substring(0, 1), POINTER)) {
            editText.setText("");
            return false;
        }

        //第一位0时，后续不能输入其他数字
        if (number.length() > 1 && TextUtils.equals(number.substring(0, 1), ZERO) &&
                !TextUtils.equals(number.substring(1, 2), POINTER)) {
            editText.setText(number.substring(0, 1));
            editText.setSelection(editText.length());
            return false;
        }

        String[] numbers = number.split("\\.");
        //已经输入小数点的情况下
        if (numbers.length == 2) {
            //小数处理
            int decimalsLength = numbers[1].length();
            if (decimalsLength > POINTER_LENGTH) {
                curSelection = editText.getSelectionEnd();
                editText.setText(number.substring(0, numbers[0].length() + 1 + POINTER_LENGTH));
                editText.setSelection(curSelection > number.length() ?
                        number.length() :
                        curSelection);
            }
            //整数处理
            if (numbers[0].length() > length) {
                curSelection = editText.getSelectionEnd();
                editText.setText(number.substring(0, length) + number.substring(length + 1));
                editText.setSelection(curSelection > length ?
                        length :
                        curSelection);
            }
        } else {
            //整数处理
            if (editText.length() > length) {
                if (number.contains(POINTER)) return false;
                curSelection = editText.getSelectionEnd();
                editText.setText(number.substring(0, length));
                editText.setSelection(curSelection > length ?
                        length :
                        curSelection);
            }
        }
        return false;
    }

}
