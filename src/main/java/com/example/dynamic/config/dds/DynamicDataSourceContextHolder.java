package com.example.dynamic.config.dds;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: lij
 * @create: 2020-02-14 20:55
 */
public class DynamicDataSourceContextHolder {

    private static final String INIT_DATA_SOURCE_KEY = DataSourceEnum.MASTER.getKey();

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return INIT_DATA_SOURCE_KEY;
        }
    };

    public static Set<String> dataSourceKeys = new HashSet<>();

    /**切换数据源*/
    public static void setDataSourceKey(String key){
        CONTEXT_HOLDER.set(key);
    }

    /**获得数据源**/
    public static String getDataSourceKey(){
        return CONTEXT_HOLDER.get();
    }

    /**获得数据源**/
    public static void clearDataSourceKey(){
        CONTEXT_HOLDER.remove();
    }

    /**是否包含数据源**/
    public static boolean containDataSourceKey(String key){
        return dataSourceKeys.contains(key);
    }

    /**添加数据源keys*/
    public static void addDataSourceKeys(Map dataSourcesMap){
        dataSourceKeys.addAll(dataSourcesMap.keySet());
    }
}
