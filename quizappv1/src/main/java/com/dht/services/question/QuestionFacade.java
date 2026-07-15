/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionFacade {
    public static List<Question> getQuestions(QuestionQueryBuilder q) throws SQLException {
        Configs.questionService.setQuery(q);
        return Configs.questionService.list();
    }
    
    public static List<Question> getLazyQuestions(QuestionQueryBuilder q) throws SQLException {
        Configs.questionService.setQuery(q);
        return new QuestionServiceDecorator(Configs.questionService).list();
    }
}
