/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Category;
import com.dht.utils.MyConnSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryServices extends QueryServiceBase<Category> {

    @Override
    public PreparedStatement getStm() throws SQLException {
        String sql = "SELECT * FROM category";
        return MyConnSingleton.getInstance().connect().prepareCall(sql);
    }
    

    @Override
    public List<Category> getResults(ResultSet rs) throws SQLException {
        List<Category> cates = new ArrayList<>();
        while (rs.next()) {
            cates.add(new Category(rs.getInt("id"), rs.getString("name")));
        }
        
        return cates;
    }
}
