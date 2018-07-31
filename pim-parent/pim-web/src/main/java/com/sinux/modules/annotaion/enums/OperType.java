package com.sinux.modules.annotaion.enums;

/**
 * @author lf
 * @Title: OperType
 * @Description: TODO
 * @date 2018/7/26 11:13
 */
public enum OperType {
    SAVE("save"),
    UPDATA("update"),
    DELETE("delete"),
    SELECT("select");

    private final String name;

    OperType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
