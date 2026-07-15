/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.utils;

import com.dht.services.CategoryServices;
import com.dht.services.LevelServices;
import com.dht.services.question.QuestionServices;
import com.dht.services.question.UpdateQuestionServices;

/**
 *
 * @author admin
 */
public class Configs {
    public static final CategoryServices cateService = new CategoryServices();
    public static final QuestionServices questionService = new QuestionServices();
    public static final LevelServices lvlService = new LevelServices();
    public static final UpdateQuestionServices uQuestionService = new UpdateQuestionServices();
    public static final String CATE_KEY = "categories";
    public static final String LVL_KEY = "levels";
    public static final int EXAM_NUM = 10;
    public static final double[] RATES = {0.4, 0.4, 0.2};
}
