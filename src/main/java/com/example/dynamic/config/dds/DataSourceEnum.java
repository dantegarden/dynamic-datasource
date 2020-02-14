package com.example.dynamic.config.dds;

import lombok.Getter;

/**
 * @description:
 * @author: lij
 * @create: 2020-02-14 23:07
 */
@Getter
public enum DataSourceEnum {
    MASTER("master"), SLAVE("slave");

    private String key;

    DataSourceEnum(String key) {
        this.key = key;
    }
}
