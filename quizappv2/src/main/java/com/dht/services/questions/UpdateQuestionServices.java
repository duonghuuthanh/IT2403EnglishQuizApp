/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.services.questions;

import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.utils.MyConnSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class UpdateQuestionServices {

    public void addQuestion(Question question, List<Choice> choices) throws SQLException {
        Connection conn = MyConnSingleton.getInstance().connect();
        
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, category_id, level_id) VALUES(?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, question.getContent());
        stm.setInt(2, question.getCategory().getId());
        stm.setInt(3, question.getLevel().getId());

        int r = stm.executeUpdate();
        if (r > 0) {
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                int qId = rs.getInt(1);

                sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
                stm = conn.prepareCall(sql);

                for (var c : choices) {
                    stm.setString(1, c.getContent());
                    stm.setBoolean(2, c.isCorrect());
                    stm.setInt(3, qId);
                    
                    stm.executeUpdate();
                }
                
                conn.commit();
            }
        }
    }
}
