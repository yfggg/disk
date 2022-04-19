package com.leadal.netdisk.disk.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum TableKind {

    // 顺序必须从0开始
    FOLDER("文件夹", "0"),
    FILE("文件", "1");

    @JsonValue
    private String value;
    @EnumValue
    private String code;

    TableKind(String value, String code) {
        this.value = value;
        this.code = code;
    }

    private static Map<String, TableKind> valueMap;
    static {
        valueMap = new HashMap<>(TableKind.values().length);
        for (TableKind type : TableKind.values()) {
            valueMap.put(type.getCode(), type);
        }
    }
    public static TableKind fromValue(String code) {
        return valueMap.get(code);
    }

}
