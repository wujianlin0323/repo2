package com.jieyi.util;

public class RandomUtil {

    public static String produce(long max) {
        return String.format("%0" + String.valueOf(max).length() + "d", (long) (Math.random() * (max + 1)));
    }

    public static String produce(long min, long max) {
        return String.format("%0" + String.valueOf(max).length() + "d", (long) (min + Math.random() * (max - min + 1)));
    }

    public static String produceByDigit(int digit) {
        long maxValue = 0;
        for (int i = 0; i < digit; i++) {
            maxValue += 9 * Math.pow(10, i);
        }
        return produce(maxValue);
    }

    public static String produceNumberLetterByDigit(int digit) {
        String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[digit];
        for (int i = 0; i < rands.length; i++) {
            int rand = (int) (Math.random() * a.length());
            rands[i] = a.charAt(rand);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rands.length; i++) {
            sb.append(rands[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(produceNumberLetterByDigit(9));
    }

}
