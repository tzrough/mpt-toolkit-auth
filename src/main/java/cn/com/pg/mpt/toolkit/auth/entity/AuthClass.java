package cn.com.pg.mpt.toolkit.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 权限模块
 */
@Data
@TableName(value = "auth_class")
public class AuthClass extends BaseEntity implements Serializable {
    /**
     * 权限所属应用
     */
    @NotNull
    @TableField(value = "auth_service_name")
    private String authServiceName;

    /**
     * 权限类型 1: function  2: data
     */
    @NotNull
    @TableField(value = "auth_type")
    private Integer authType;

    /**
     * 权限作用范围
     */
    @NotNull
    @TableField(value = "authRange")
    private String authRange;

    /**
     * 权限中文名称
     */
    @NotNull
    @TableField(value = "auth_ch_name")
    private String authChName;

    /**
     * 权限版本
     */
    @NotNull
    @TableField(value = "version")
    private Integer version;

    /**
     * 权限点表名
     */
    @NotNull
    @TableField(value = "auth_table_name")
    private String authTableName;

}