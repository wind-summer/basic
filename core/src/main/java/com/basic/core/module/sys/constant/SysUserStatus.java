package com.basic.core.module.sys.constant;

/**
 * 用户状态
 * @author wenlongfei
 * @Date 18-10-14
 */
public enum SysUserStatus {
    DISABLE(0,"禁用"),
    ENABLE(1,"启用");

    private SysUserStatus(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
