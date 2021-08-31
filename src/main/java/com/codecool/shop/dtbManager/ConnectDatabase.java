package com.codecool.shop.dtbManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;

public class ConnectDatabase {
    DatabaseInfo dbsInfo = new DatabaseInfo();
    DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(dbsInfo.getDbsName());
        dataSource.setUser(dbsInfo.getUsername());
        dataSource.setPassword(dbsInfo.getPassword());

        dataSource.getConnection().close();
        return dataSource;
    }
}
