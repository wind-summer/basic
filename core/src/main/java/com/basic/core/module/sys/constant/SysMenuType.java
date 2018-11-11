package com.basic.core.module.sys.constant;

/**
 * 菜单类型
 * @author wenlongfei
 * @Date 18-10-14
 */
public enum SysMenuType {
    MENU(0,"菜单"),
    BUTTON(1,"按钮");

    private SysMenuType(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    private SysMenuType valueOf(Integer value){
        return values()[value];
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
