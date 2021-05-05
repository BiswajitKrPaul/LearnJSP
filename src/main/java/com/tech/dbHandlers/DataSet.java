package com.tech.dbHandlers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSet extends ArrayList implements Serializable {

    private static ResultSet rs = null;
    private static ResultSetMetaData resultSetMetaData = null;
    private static DataSet ds;

    public DataSet() {

    }


    public DataSet(ResultSet resultSet) throws SQLException {
        rs = resultSet;
        resultSetMetaData = rs.getMetaData();
        ds = new DataSet();
    }


    public DataSet getDataSetFromResultSet() throws SQLException {
        HashMap hashMapTemp = new HashMap();
        DataSet dataSet = new DataSet();
        String[] columnArr = getColumnLabelValues().split(";");
        int[] columnType = getColumnTypes();
        while (rs.next()) {
            hashMapTemp = new HashMap();
            for (int i = 0; i < columnArr.length; i++) {
                switch (columnType[i]) {
                    case Types.VARCHAR:
                        hashMapTemp.put(columnArr[i], rs.getString(columnArr[i]));
                        break;
                    case Types.INTEGER:
                        hashMapTemp.put(columnArr[i], rs.getInt(columnArr[i]));
                        break;
                    case Types.DATE:
                        hashMapTemp.put(columnArr[i], rs.getDate(columnArr[i]));
                        break;
                    default:
                        hashMapTemp.put(columnArr[i], rs.getObject(columnArr[i]));
                        break;
                }
            }
            dataSet.add(hashMapTemp);
        }
        ds = dataSet;
        return dataSet;
    }

    private int[] getColumnTypes() throws SQLException {
        int[] columnsTypes = new int[resultSetMetaData.getColumnCount()];
        for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
            columnsTypes[i] = resultSetMetaData.getColumnType(i + 1);
        }
        return columnsTypes;
    }

    private String getColumnLabelValues() throws SQLException {
        StringBuilder columns = new StringBuilder();
        columns.append(";");
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            columns.append(resultSetMetaData.getColumnLabel(i));
            columns.append(";");
        }
        return columns.substring(1);
    }

    public String getValue(int position, String columnName) throws SQLException {
        StringBuilder columnValue = new StringBuilder();
        HashMap hmTemp = (HashMap) ds.get(position);
        columnValue.append(hmTemp.get(columnName).toString());
        return columnValue.toString();
    }

    public String getColumnValues(String columnName, String delimeter) {
        if (delimeter.isEmpty()) {
            delimeter = ";";
        }
        StringBuilder columnValue = new StringBuilder();
        HashMap hmTemp;
        for (int i = 0; i < ds.size(); i++) {
            hmTemp = (HashMap) ds.get(i);
            columnValue.append(hmTemp.get(columnName).toString());
            columnValue.append(delimeter);
        }
        return columnValue.substring(0, columnValue.length() - 1);
    }

}
