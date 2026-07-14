/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.exam;

import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.services.questions.QuestionFacade;
import com.dht.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FixedStrategy implements ExamStrategy {

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        
        for (int i = 0; i < Configs.RATES.length; i++) {
            try {
                QuestionQueryBuilder q = new QuestionQueryBuilder().widthLevel(i + 1)
                        .setLimit((int)(Configs.RATES[i] * Configs.EXAM_NUM)).setOrderBy("rand()");
                questions.addAll(QuestionFacade.getLazyQuestions(q));
            } catch (SQLException ex) {
                Logger.getLogger(FixedStrategy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return questions;
    }
    
}
