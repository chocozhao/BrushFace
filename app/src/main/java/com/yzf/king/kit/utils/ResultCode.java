package com.yzf.king.kit.utils;

/**
 * ClaseName：ResultCode
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/1/25 13:54
 * Modified By：
 * Fixtime：2018/1/25 13:54
 * FixDescription：
 */

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    PROCESSING(444),//处理中
    SMSfAIL(445),//短信发送失败
    INVALIDCODE(446),//无效验证码
    PAYfAIL(447),//支付失败
    TOKENfAIL(448),//token失效
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}