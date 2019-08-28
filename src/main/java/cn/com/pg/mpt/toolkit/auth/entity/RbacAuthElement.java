package cn.com.pg.mpt.toolkit.auth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class RbacAuthElement {

    @JSONField(ordinal = 1)
    private String name;

    @JSONField(ordinal = 2)
    private String type = "bool";

    @JSONField(ordinal = 3)
    private String key;

}
