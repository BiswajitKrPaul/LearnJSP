package com.tech.dbHandlers;

import com.tech.Utils.DataBaseConstant;

import java.sql.*;

public class QueryProcessor {

    Connection connection;

    public QueryProcessor() {
        try {
            Class.forName(DataBaseConstant.DataBaseDriverClass);
            connection = DriverManager
                    .getConnection(DataBaseConstant.DataBaseConnectionURL,
                            DataBaseConstant.DataBaseUserName, DataBaseConstant.DataBaseUserPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DataSet getQueryResult(String sqlQuery) throws SQLException {
        ResultSet dsPrimary = null;
        Statement statement = connection.createStatement();
        dsPrimary = statement.executeQuery(sqlQuery);

        return new DataSet(dsPrimary).getDataSetFromResultSet();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
