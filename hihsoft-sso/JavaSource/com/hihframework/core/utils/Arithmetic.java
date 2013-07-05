package com.hihframework.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p> Title:处理数值运算的辅助类：加法、减法、乘法、
 * 除数、四舍五入处理、格式化数值</p>
 * <p> Description:传入double值，再用BigDecimal来计算
 * float和double只能用来做科学计算或者是工程计算</p>
 * 在商业计算中我们要用 java.math.BigDecimal
 * 如果需要精确计算，非要用String来构造BigDecimal
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class Arithmetic {
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    private static final String[] NumberCnS = {"0", "一", "二", "三", "四", "五",
            "六", "七", "八", "九", "十"};

    private static final String[] NumberCnF = {"零", "壹", "贰", "叁", "肆", "伍",
            "陆", "柒", "捌", "玖"};

    private static final String[] unit = {"", "拾", "佰", "仟", "万", "亿", "角",
            "分", "元", "整"};

    // 这个类不能被外部实例化，只能自己实例化,外部通过静态方法得到该类的实例
    //通过构造函数达到控制实例化类的目的,外部类不能继承此类
    private Arithmetic() {
    }

    /**
     * 判断是否为数字，可为整数，也可为小数
     *
     * @param obj
     * @return
     */
    public static boolean isNumber(Object obj) {
        //boolean x=false;
        try {
            //System.out.println(obj);
            new BigDecimal(obj.toString());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 提供两个参数精确的和运算
     *
     * @param v1：被加数
     * @param v2：加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();

    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1:被减数
     * @param v2：减数
     * @return两个数的差
     */

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();

    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1:   被乘数
     * @param v2:乘数
     * @return两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();

    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入
     *
     * @param v1:被除数
     * @param v2:除数
     * @return两个参数的商
     */

    public static double div(double v1, double v2) {

        return div(v1, v2, DEF_DIV_SCALE);

    }

    /**
     * 提供（相对）精确的除法运算。
     * 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1:被除数
     * @param v2:除数
     * @param scale：表示表示需要精确到小数点以后几位
     * @return两个参数的商
     */

    public static double div(double v1, double v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v:需要四舍五入的数字
     * @param scale:小数点后保留几位
     * @return四舍五入后的结果
     */

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 方法一
     * 格式化数字：格式化成900,000,000方便财务数据对照
     *
     * @param v：被格式化的数值
     * @return格式化后的数值
     */
    public static String formateArithmetic(double v) {
        String foramt = "";
        DecimalFormat formater = new DecimalFormat();
        formater.applyPattern("##,###.00");
        foramt = String.valueOf(formater.format(v));
        return foramt;

    }

    /**
     * 方法二
     * 使用千分分隔符将金额分隔开000,000
     *
     * @param moneyValue
     * @return
     */
    public static final String separateMoney(String moneyValue) {
        if (moneyValue == null || moneyValue.trim().equals(""))
            return "";
        else {
            String prefix = moneyValue.indexOf("-") >= 0 ? "-" : "";
            String tempMoney = moneyValue.indexOf(".") > 0 ? moneyValue
                    .substring(0, moneyValue.indexOf(".")) : moneyValue;

            if (!prefix.equals(""))
                tempMoney = tempMoney.substring(1);
            String retValue = "";
            int i = 0;
            i = tempMoney.length();
            String tmp = tempMoney.substring((i - 3 >= 0 ? i - 3 : 0), i);
            while (!tmp.equals("")) {
                if (tmp.length() == 3)
                    retValue = "," + tmp + retValue;
                else
                    retValue = tmp + retValue;

                i = i - 3 >= 0 ? i - 3 : 0;

                tmp = tempMoney.substring((i - 3 >= 0 ? i - 3 : 0), i);
            }
            if (retValue.charAt(0) == ',')
                retValue = retValue.substring(1);

            retValue = moneyValue.indexOf(".") > 0 ? retValue + "."
                    + moneyValue.substring(moneyValue.indexOf(".") + 1)
                    : retValue;

            if (!prefix.equals(""))
                retValue = prefix + retValue;

            return retValue;
        }

    }

    /**
     * 将浮点数转换成两位小数点的字符串表现形式，如1234.7转换成"1,234.57"（四舍五入）
     *
     * @param d
     * @return
     */
    public static String toPrice(double d) {
        // 修正：处理如下情况1.385 -> 1.39（原为1.385 -> 1.38）
        double e = ((double) Math.round(d * 100)) / 100;
        DecimalFormat df = new DecimalFormat("#,##0.00");

        return df.format(e);
    }

    /**
     * 将浮点数转换成两位小数点的字符串表现形式，如1234.7转换成"1234.57"（四舍五入）
     *
     * @param d
     * @return
     */
    public static String toPrice(float d) {
        // 修正：处理如下情况1.385 -> 1.39（原为1.385 -> 1.38）
        float e = ((float) Math.round(d * 100)) / 100;
        DecimalFormat df = new DecimalFormat("##0.00");

        return df.format(e);
    }

    /**
     * 单精度转金额
     *
     * @param d
     * @param format
     * @return
     */
    public static String toPrice(float d, String format) {
        DecimalFormat df = new DecimalFormat(format);

        return df.format(d);
    }

    /**
     * 双精度转金额
     *
     * @param d
     * @param format
     * @return
     */
    public static String toPrice(double d, String format) {
        DecimalFormat df = new DecimalFormat(format);

        return df.format(d);
    }

    /**
     * 中文数字,如一，二，三
     *
     * @param num
     * @return
     */
    public static final String getNumberCnS(String num) {
        if (num.equals("10"))
            return NumberCnS[10];
        else {
            char[] temp = String.valueOf(num).toCharArray();
            String a = new String();
            for (int i = 0; i < temp.length; i++) {
                String b = String.valueOf(temp[i]);
                try {
                    Integer.parseInt(b);
                    a = a + NumberCnS[Integer.parseInt(b)];
                } catch (Exception e) {
                    a = a + b;
                }

            }
            return a;
        }

    }

    /**
     * 中文大写数字，如壹，贰，叁
     *
     * @param num
     * @return
     */
    public static final String getNumberCnF(String num) {
        if (num.equals("10"))
            return NumberCnF[10];
        else {
            char[] temp = String.valueOf(num).toCharArray();
            String a = new String();
            for (int i = 0; i < temp.length; i++) {
                String b = String.valueOf(temp[i]);
                try {
                    Integer.parseInt(b);
                    a = a + NumberCnF[Integer.parseInt(b)];
                } catch (Exception e) {
                    a = a + b;
                }

            }
            return a;
        }
    }

    /**
     * 转成中文金额，如壹佰壹拾壹元整
     *
     * @param money
     * @return
     */
    public static final String moneyCn(String money) {
        String integer = "";
        String fraction = "";
        String b = "";
        int index = money.indexOf(".");
        if (index > 0) {
            integer = money.substring(0, index);
            fraction = money.substring(index + 1, money.length());
        } else {
            integer = money;
        }
        char[] temp = integer.toCharArray();

        int length = temp.length;
        for (int i = 0; i < length; i++) {
            int d = (length - 1 - i) / 4;
            int c = (length - 1 - i) % 4;
            if (temp[i] != '0') {
                b = b + NumberCnF[Integer.parseInt(String.valueOf(temp[i]))]
                        + unit[c];
            } else if (i != length - 1 && temp[i + 1] != '0') {
                b = b + NumberCnF[0];
            }
            if (c == 0) {
                if (d != 0 && d % 2 == 0) {
                    b = b + unit[5];
                } else if (d != 0 && d % 2 != 0) {
                    b = b + unit[4];
                }

            }
        }
        b = b + unit[8];
        if (!fraction.equals("")) {
            while (fraction.length() < 2) {
                fraction = fraction + "0";
            }
            char[] e = fraction.toCharArray();
            if (e.length > 2) {
                int j = Integer.parseInt(String.valueOf(e[2]));
                if (j > 4) {
                    e[1] = (char) (Integer.parseInt(String.valueOf(e[1])) + 1);
                }
            }
            if (e[0] == '0' && e[1] != '0')
                b = b + NumberCnF[Integer.parseInt(String.valueOf(e[0]))];
            else if (e[0] != '0')
                b = b + NumberCnF[Integer.parseInt(String.valueOf(e[0]))]
                        + unit[6];
            if (e[1] != '0')
                b = b + NumberCnF[Integer.parseInt(String.valueOf(e[1]))]
                        + unit[7];
        }
        return b + unit[9];
    }

    public static void main(String[] args) {
        // 两个小数相加：
        double v1 = 1.9899999;
        // double v2 = 2.0;
        String v3 = Arithmetic.formateArithmetic(v1 / 10000 * 10000);
        System.out.println(Arithmetic.moneyCn("123400700."));
        System.out.println("v3===============================>>" + v3);

    }
}

