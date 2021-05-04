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
            ResultSet dsPrimary = queryProcessor.getQueryResult(sqlquery);
            DataSet ds = new DataSet(dsPrimary).getDataSetFromResultSet();
            while (dsPrimary.next()) {
                user = new User();
                user.setUserid(dsPrimary.getInt("userid"));
                user.setUsername(dsPrimary.getString("username"));
                user.setPassword(dsPrimary.getString("userid"));
                allUsers.add(user);
            }
            queryProcessor.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

}
