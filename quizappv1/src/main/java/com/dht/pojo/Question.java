/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.pojo;

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
    private Category category;
    private Level level;
    private List<Choice> choices;
    
    private Question(Builder b) {
        this.id = b.id;
        this.content = b.content;
        this.hint = b.hint;
        this.image = b.image;
        this.category = b.category;
        this.level = b.level;
        this.choices = b.choices;
    }

    @Override
    public String toString() {
        return this.content; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public static class Builder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private Category category;
        private Level level;
        private List<Choice> choices;
        
        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }
        
        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }
        
        public Builder addChoice(Choice c) {
            this.choices.add(c);
            return this;
        }
        
        public Builder setCate(Category c) {
            this.category = c;
            return this;
        }
        
        public Builder setLevel(Level lvl) {
            this.level = lvl;
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
         * @return the content
         */
        public String getContent() {
            return content;
        }

        /**
         * @return the hint
         */
        public String getHint() {
            return hint;
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
