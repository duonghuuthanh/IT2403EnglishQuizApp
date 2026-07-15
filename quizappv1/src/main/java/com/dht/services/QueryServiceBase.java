/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Level;
import com.dht.utils.MyConnSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class QueryServiceBase<T> {
    public List<T> list() throws SQLException { // template method
        PreparedStatement stm = this.getStm();
        ResultSet rs = stm.executeQuery();

        List<T> results = new ArrayList<>();
        while (rs.next()) {
            results.add(this.getObject(rs));
        }

        return results;
    }
    
    public abstract PreparedStatement getStm() throws SQLException;
    public abstract T getObject(ResultSet rs) throws SQLException;
}
