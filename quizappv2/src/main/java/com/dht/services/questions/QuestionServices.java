/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.questions;

import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.services.QueryServiceBase;
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
    public List<Question> getResults(ResultSet rs) throws SQLException {
        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            questions.add(new Question.QuestionBuilder().setContent(rs.getString("content"))
                                      .setId(rs.getInt("id")).build());
        }
        
        return questions;
    }
    
    /**
     * @param sql the sql to set
     */
    public void setQuery(QuestionQueryBuilder query) {
        this.query = query;
    }

    
}
