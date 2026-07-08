/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Category;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
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
public class QuestionServices extends QuestionServicesBase {
    private QuestionQueryBuilder query;

    public QuestionServices() {
    }

    public QuestionServices(QuestionQueryBuilder query) {
        this.query = query;
    }
    
    @Override
    public List<Question> getQuestions() throws SQLException {
        PreparedStatement stm = this.query.build();
        
        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");

            questions.add(new Question.Builder().setId(id).setContent(content).build());
        }

        return questions;
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
