package com.basic.core.module.sys.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *  角色下拉列表实体
 * </p>
 *
 * @author wenlongfei
 * @since 2019/4/13
 */
@Data
@Accessors(chain = true)
public class RoleDrop {
    private Long id;
    private String name;
}
