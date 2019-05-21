package com.basic.core.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.basic.core.mvc.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 文件
 * </p>
 *
 * @author wenlongfei
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_file")
public class SysFile extends BaseEntity<SysFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 文件Base64字符串
     */
    @TableField("base64_file")
    private String base64File;
    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

}
