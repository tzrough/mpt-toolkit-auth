package cn.com.pg.mpt.toolkit.auth.entity.enums;

public enum AuthType {

    FUNC(1),
    DATA(2);

    private Integer code;

    AuthType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
