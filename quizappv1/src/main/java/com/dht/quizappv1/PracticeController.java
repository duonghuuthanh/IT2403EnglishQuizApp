/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv1;

import com.dht.pojo.Category;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.services.question.QuestionServiceDecorator;
import com.dht.utils.Configs;
import com.dht.utils.MyAlertSingleton;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<Level> cbSearchLevels;
    @FXML
    private TextField txtNum;
    @FXML
    private Label lblContent;
    @FXML
    private VBox vChoices;
    private List<Question> questions;
    private int currentIdx = -1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableList(Configs.lvlService.getLevels()));

        } catch (SQLException ex) {

        }
    }

    public void start(ActionEvent e) {
        QuestionQueryBuilder query = new QuestionQueryBuilder()
                .withCategory(this.cbSearchCates.getSelectionModel().getSelectedItem())
                .withLevel(this.cbSearchLevels.getSelectionModel().getSelectedItem())
                .setOrderBy(" rand() ")
                .setLimit(this.txtNum.getText());

        Configs.questionService.setQuery(query);
        try {
            this.questions = new QuestionServiceDecorator(Configs.questionService).getQuestions();
            this.showQuestion(1);
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void next(ActionEvent e) {
        this.showQuestion(1);
    }

    public void previous(ActionEvent e) {
        this.showQuestion(-1);
    }

    public void checkAnswer(ActionEvent e) {
        Question q= this.questions.get(this.currentIdx);
        for (int i = 0; i < this.vChoices.getChildren().size(); i++) {
            RadioButton r = (RadioButton) this.vChoices.getChildren().get(i);
            if (r.isSelected()) {
                if (q.getChoices().get(i).isCorrect() == true)
                    MyAlertSingleton.getInstance().showMsg("CHÍNH XÁC!!!", Alert.AlertType.INFORMATION);
                else
                    MyAlertSingleton.getInstance().showMsg("SAI RỒI!!!", Alert.AlertType.ERROR);
                
                break;
            }
        }
    }

    private void showQuestion(int step) {
        this.currentIdx += step;

        if (this.currentIdx >= 0 && this.currentIdx < this.questions.size()) {
            Question q = this.questions.get(currentIdx);
            this.lblContent.setText(q.getContent());

            this.vChoices.getChildren().clear();
            ToggleGroup t = new ToggleGroup();
            for (var c : q.getChoices()) {
                RadioButton r = new RadioButton(c.getContent());
                r.setToggleGroup(t);
                this.vChoices.getChildren().add(r);
            }
        }
    }
}
