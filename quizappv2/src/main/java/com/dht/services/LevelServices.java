/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Level;
import com.dht.utils.MyConnSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelServices extends QueryServiceBase<Level> {

    @Override
    public PreparedStatement getStm() throws SQLException {
        String sql = "SELECT * FROM level";
        return MyConnSingleton.getInstance().connect().prepareCall(sql);
    }

    @Override
    public List<Level> getResults(ResultSet rs) throws SQLException {
        List<Level> levels = new ArrayList<>();
        while (rs.next()) {
            levels.add(new Level(rs.getInt("id"), rs.getString("name")));
        }
        
        return levels;
    }
}
