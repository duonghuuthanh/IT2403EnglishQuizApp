/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FlyweightFactory {
    private static final Map<String, List> cachedData = new HashMap<>();
    
    public static <E> List<E> getData(QueryServiceBase s, String key) {
        if (cachedData.containsKey(key) == false) {
            try {
                cachedData.put(key, s.list());
            } catch (SQLException ex) {
                Logger.getLogger(FlyweightFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return cachedData.get(key);
    }
}
