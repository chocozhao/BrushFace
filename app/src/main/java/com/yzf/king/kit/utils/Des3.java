package com.yzf.king.kit.utils;

import android.util.Base64;

import com.yzf.king.kit.AppUser;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import cn.droidlover.xdroidmvp.kit.Codec;
import cn.droidlover.xdroidmvp.log.Logger;

/**
 * ClaseName：Des3
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/2/28 16:59
 * Modified By：
 * Fixtime：2017/2/28 16:59
 * FixDescription：
 */

public class Des3 {

    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey;
        String secretKey = AppUser.getInstance().getKey();
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        Logger.v("加密数据：" + plainText);
        Logger.v("加密秘钥：" + secretKey);
        Logger.v("加密结果：" + Codec.BASE64.encode(encryptData));
        return Base64.encodeToString(encryptData, Base64.DEFAULT);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        Key deskey;
        String secretKey = AppUser.getInstance().getKey();
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText, Base64.DEFAULT));
        Logger.v("解密数据：" + encryptText);
        Logger.v("解密秘钥：" + secretKey);
        Logger.v("解密结果：" + new String(decryptData, encoding));

        return new String(decryptData, encoding);
    }
}
