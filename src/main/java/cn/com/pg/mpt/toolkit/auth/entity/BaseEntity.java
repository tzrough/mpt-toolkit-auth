package cn.com.pg.mpt.toolkit.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "updated_at")
    private Date updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "deleted_at")
    private Date deletedAt;

    @TableField(value = "created_by")
    private String createdBy;

    @TableField(value = "updated_by")
    private String updatedBy;

    @TableField(value = "deleted_by")
    private String deletedBy;

    /**
     * 是否删除 1: 删除 0: 未删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
