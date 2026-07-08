/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.question;

import com.dht.pojo.Question;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionServiceDecorator extends QuestionServicesBase {
    private QuestionServicesBase q;

    public QuestionServiceDecorator(QuestionServicesBase q) {
        this.q = q;
    }
    
    

    @Override
    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = this.q.getQuestions();
        
        ChoiceServices se = new ChoiceServices();
        for (var ques: questions) {
            ques.setChoices(se.getChoicesByQuestionId(ques.getId()));
        }
        
        return questions;
    }
    
}
