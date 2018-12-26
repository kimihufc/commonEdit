package com.hjl.springboot.aspect;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author: HJL
 * @create: 2018-12-26 19:10
 */
public class MultiDataSource extends AbstractRoutingDataSource {

    private static ThreadLocal<String> dataSourceLocal = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = dataSourceLocal.get();
        return dataSource==null||dataSource.length()==0?"one":dataSource;
    }

    public static void setDataSource(String name){
        dataSourceLocal.set(name);
    }

    public static void remove(){
        dataSourceLocal.remove();
    }

}
