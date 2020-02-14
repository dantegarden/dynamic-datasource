package com.example.dynamic.config.dds;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @description:
 * @author: lij
 * @create: 2020-02-14 20:51
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(Map<Object, Object> dataSourceMap) {
        super.setTargetDataSources(dataSourceMap);
        if(!dataSourceMap.containsKey("master")){
            throw new RuntimeException("没有配置默认的数据源");
        }
        //配置默认的数据源
        super.setDefaultTargetDataSource(dataSourceMap.get(DataSourceEnum.MASTER.getKey()));
        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.addDataSourceKeys(dataSourceMap);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

}
