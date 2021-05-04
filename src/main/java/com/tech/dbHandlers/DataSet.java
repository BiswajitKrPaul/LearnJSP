package com.tech.dbHandlers;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSet extends ArrayList implements Serializable {

    private ResultSet rs = null;
    private ResultSetMetaData resultSetMetaData = null;


    public DataSet(ResultSet resultSet) throws SQLException {
        this.rs = resultSet;
        this.resultSetMetaData = rs.getMetaData();
    }


    public ArrayList getDataSetFromResultSet() throws SQLException {
        HashMap hashMapTemp = new HashMap();
        ArrayList dataSet = new ArrayList<Object>();
        String[] columnArr = getColumnValues().split(";");
        int[] columnType = getColumnTypes();
        while (rs.next()) {
            hashMapTemp.clear();
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
        return dataSet;
    }

    private int[] getColumnTypes() throws SQLException {
        int[] columnsTypes = new int[resultSetMetaData.getColumnCount()];
        for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
            columnsTypes[i] = resultSetMetaData.getColumnType(i+1);
        }
        return columnsTypes;
    }

    private String getColumnValues() throws SQLException {
        StringBuilder columns = new StringBuilder();
        columns.append(";");
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            columns.append(resultSetMetaData.getColumnLabel(i));
            columns.append(";");
        }
        return columns.substring(1);
    }
}
