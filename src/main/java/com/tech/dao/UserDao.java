package com.tech.dao;

import com.tech.dbHandlers.DataSet;
import com.tech.dbHandlers.QueryProcessor;
import com.tech.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    public ArrayList<User> getAllUser() {
        User user;
        ArrayList<User> allUsers = new ArrayList<>();
        try {

            QueryProcessor queryProcessor = new QueryProcessor();
            String sqlquery = "select * from user";
            DataSet ds = queryProcessor.getQueryResult(sqlquery);
            String check = ds.getColumnValues("username", "#");
            for (int i = 0; i < ds.size(); i++) {
                user = new User();
                user.setUserid(Integer.parseInt(ds.getValue(i, "userid")));
                user.setUsername(ds.getValue(i, "username"));
                user.setPassword(ds.getValue(i, "userid"));
                allUsers.add(user);
            }
            queryProcessor.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

}
