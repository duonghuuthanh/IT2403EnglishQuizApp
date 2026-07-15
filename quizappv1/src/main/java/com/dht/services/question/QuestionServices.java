/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Category;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.services.QueryServiceBase;
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
public class QuestionServices extends QueryServiceBase<Question> implements QuestionServicesBase {
    private QuestionQueryBuilder query;

    public QuestionServices() {
    }

    public QuestionServices(QuestionQueryBuilder query) {
        this.query = query;
    }
    
    @Override
    public PreparedStatement getStm() throws SQLException {
        return this.query.build();
    }

    @Override
    public Question getObject(ResultSet rs) throws SQLException {
        return new Question.Builder().setId(rs.getInt("id")).setContent(rs.getString("content")).build();
    }
    
    /**
     * @return the query
     */
    public QuestionQueryBuilder getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(QuestionQueryBuilder query) {
        this.query = query;
    }

    
}
