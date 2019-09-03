package cn.com.pg.mpt.toolkit.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限点
 */
@Data
@TableName(value = "auth_class_element")
public class AuthClassElement extends BaseEntity implements Serializable {

    @NotNull
    @TableField(value = "auth_class_id")
    private Long authClassId;

    /**
     * 权限点元素名称
     */
    @NotNull
    @TableField(value = "auth_element_name")
    private String authElementName;

    /**
     * 权限点元素
     */
    @NotNull
    @TableField(value = "auth_element")
    private String authElement;

    /**
     * 权限点表名
     */
    @NotNull
    private String tableName;
}