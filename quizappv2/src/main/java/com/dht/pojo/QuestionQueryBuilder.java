/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.pojo;

import com.dht.utils.MyConnSingleton;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionQueryBuilder {
    private StringBuilder query;
    private StringBuilder where;
    private String orderBy = "id DESC";
    private List<Object> params;

    public QuestionQueryBuilder() {
        this.query = new StringBuilder("SELECT * FROM question WHERE 1=1 %s ORDER BY %s");
        this.where = new StringBuilder();
        this.params = new ArrayList<>();
    }
    
    public QuestionQueryBuilder widthKeywords(String kw) {
        if (kw != null && !kw.isEmpty()) {
            this.where.append(" AND content like concat('%', ?, '%')");
            this.params.add(kw);
        }
        
        return this;
    }
    
    public QuestionQueryBuilder widthCategory(Category c) {
        if (c != null) {
            this.where.append(" AND category_id = ?");
            this.params.add(c.getId());
        }
        
        return this;
    }
    
    public QuestionQueryBuilder widthLevel(Level lvl) {
        if (lvl != null) {
            this.where.append(" AND level_id = ?");
            this.params.add(lvl.getId());
        }
        
        return this;
    }
    
    public QuestionQueryBuilder widthLevel(int lvlId) {
        if (lvlId > 0) {
            this.where.append(" AND level_id = ?");
            this.params.add(lvlId);
        }
        
        return this;
    }
    
    public QuestionQueryBuilder setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
    
    public QuestionQueryBuilder setLimit(int limit) {
        if (!this.query.toString().toLowerCase().contains("limit")) {
            this.query.append(" LIMIT ?");
            this.params.add(limit);
        }
        
        return this;
    }
    
    public QuestionQueryBuilder setLimit(String limit) {
        this.setLimit(Integer.parseInt(limit));
        
        return this;
    }
    
    public PreparedStatement build() throws SQLException {
        String sql = String.format(this.query.toString(), this.where.toString(), this.orderBy);
        PreparedStatement stm = MyConnSingleton.getInstance().connect().prepareCall(sql);
        for (int i = 0; i < params.size(); i++)
            stm.setObject(i + 1, this.params.get(i));
        
        return stm;
    }
}
