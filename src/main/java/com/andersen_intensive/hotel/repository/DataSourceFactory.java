package com.andersen_intensive.hotel.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static final String CONFIG_FILE = "/hikari.properties";

    private static final HikariConfig config = new HikariConfig(CONFIG_FILE);
    private static final HikariDataSource ds = new HikariDataSource(config);

    public static DataSource getDataSource() {
        return ds;
    }
}
