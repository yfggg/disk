package com.leadal.netdisk.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum FileKind {

    // 顺序必须从0开始
    DOCUMENT("文档", "0"),
    IMAGE("图片", "1"),
    VIDEO("视频", "2"),
    AUDIO("音频", "3"),
    COMPRESSION("压缩", "4");

    @JsonValue
    private String value;
    @EnumValue
    private String code;

    FileKind(String value, String code) {
        this.value = value;
        this.code = code;
    }

    private static Map<String, FileKind> valueMap;
    static {
        valueMap = new HashMap<>(FileKind.values().length);
        for (FileKind type : FileKind.values()) {
            valueMap.put(type.getCode(), type);
        }
    }
    public static FileKind fromValue(String code) {
        return valueMap.get(code);
    }

}
