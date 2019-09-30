package com.yzf.king.kit.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.droidlover.xdroidmvp.log.Logger;

/**
 * ProjectName：Mall
 * Description：
 * Author：Weijunxing
 * CreateTime：2016/6/3015:54
 * QQ：2188307188
 * FixTime：
 * FixContent：
 */
public class AesUtil {
    /**
     * 加密/解密算法/工作模式/填充方法
     */
    private static final String transformation = "AES/CBC/PKCS5Padding";
    private static final String password = "1107yiyoupay1107";

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @return
     */
    public static String encrypt(String content) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(password.getBytes());
            SecretKeySpec key1 = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, key1, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes());
            String encryptResultStr = parseByte2HexStr(encryptedData);
            return encryptResultStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @return
     */
    public static String decrypt(String content) {
        try {

            byte[] decryptFrom = parseHexStr2Byte(content);
            IvParameterSpec zeroIv = new IvParameterSpec(password.getBytes());
            SecretKeySpec key1 = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, key1, zeroIv);
            byte decryptedData[] = cipher.doFinal(decryptFrom);
            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 3DES加密
     *
     * @param content
     * @return
     */
    public static String encrypt3DES(String content) {
        String secretKey = "0123456789ABCDEF0123456789ABCDEF";
        // 加解密统一使用的编码方式
        String encoding = "utf-8";
        try {
            // 生成密钥
            byte[] keybyte = secretKey.getBytes(encoding);
            byte[] temp = content.getBytes(encoding);
            SecretKey deskey = new SecretKeySpec(keybyte, "desede");
            // 加密
            Cipher c1 = Cipher.getInstance("desede/CBC/NoPadding");
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] temp1 = c1.doFinal(temp);
            return toHexString(temp1);
        } catch (NoSuchAlgorithmException e1) {
            Logger.e(e1.toString());
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            Logger.e(e2.toString());
            e2.printStackTrace();
        } catch (Exception e3) {
            Logger.e(e3.toString());
            e3.printStackTrace();
        }
        return null;
    }

    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }

        return hexString.toString();
    }


    /**
     * md5加密
     *
     * @param strSrc
     * @param length
     * @return
     */
    public static String encryptMD5(String strSrc, String length) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(strSrc.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
//      System.out.println("32位：result: " + buf.toString());//32位的加密
//      System.out.println("16位：result: " + buf.toString().substring(8,24));//16位的加密

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (length.equals("32")) {
            return buf.toString();
        } else {
            return buf.toString().substring(8, 24);
        }
    }

    public static void main(String[] args) {
        System.out.printf("3DES加密结果:" + encrypt3DES("123456"));
    }


}