/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Question {
    private int id;
    private String content;
    private String hint;
    private String image;
    private Level level;
    private Category category;
    private List<Choice> choices;

    @Override
    public String toString() {
        return this.content; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    private Question(QuestionBuilder b) {
        this.id = b.id;
        this.content = b.content;
        this.hint = b.hint;
        this.image = b.image;
        this.level = b.level;
        this.category = b.category;
        this.choices = b.choices;
    }
    
    public static class QuestionBuilder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private Level level;
        private Category category;
        private List<Choice> choices;
        
        public QuestionBuilder() {
            this.choices = new ArrayList<>();
        }
        
        public QuestionBuilder setId(int id) {
            this.id = id;
            return this;
        }
        
        public QuestionBuilder setContent(String content) {
            this.content = content;
            return this;
        }
        
        public QuestionBuilder setCategory(Category c) {
            this.category = c;
            return this;
        }
        
        public QuestionBuilder setLevel(Level lvl) {
            this.level = lvl;
            return this;
        }
        
        public QuestionBuilder addChoice(Choice c) {
            this.choices.add(c);
            return this;
        }
        
        public QuestionBuilder setChoices(List<Choice> choices) {
            this.setChoices(choices);
            return this;
        }
        
        public Question build() {
            return new Question(this);
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
