package cn.com.pg.mpt.toolkit.auth.entity.enums;

public enum AuthType {

    FUNC("func",1),
    DATA("data",2);

    // rbac json 中的 key
    private String key;
    private Integer code;

    AuthType(String key, Integer code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public Integer getCode() {
        return code;
    }
}
