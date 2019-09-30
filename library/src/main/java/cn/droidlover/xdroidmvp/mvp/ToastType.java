package cn.droidlover.xdroidmvp.mvp;

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
 * toast枚举
 */
public enum ToastType {
    NORMAL(0),//普通
    SUCCESS(1),//成功
    WARNING(2),//警告
    INFO(3),//信息
    CUSTOM(4),//自定义
    ERROR(5);//错误

    private int type;

    ToastType(int type) {
        this.type = type;
    }

    public int code() {
        return type;
    }
}