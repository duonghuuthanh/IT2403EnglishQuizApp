/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv1;

import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.exam.ExamStrategy;
import com.dht.services.exam.ExamTypes;
import com.dht.services.exam.FixedExam;
import com.dht.services.exam.SpecificExam;
import com.dht.utils.MyAlertSingleton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ExamController implements Initializable {

    @FXML
    private ComboBox<ExamTypes> cbExamTypes;
    @FXML
    private TextField txtNum;
    @FXML
    private ListView<Question> lvQuestions;
    private List<Question> questions;
    private Map<Integer, Choice> answers = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        
        this.txtNum.setVisible(false);
        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
                this.txtNum.setVisible(true);
            else
                this.txtNum.setVisible(false);
        });
        
        this.lvQuestions.setCellFactory(callback -> new ListCell<>() {
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                
                if (q == null || empty == true)
                    setGraphic(null);
                else {
                    VBox v = new VBox(5);
                    
                    v.setStyle("-fx-border-width: 2; -fx-border-color:blue; -fx-padding: 5");
                    
                    Text t = new Text(q.getContent());
                    v.getChildren().add(t);
                    
                    ToggleGroup g = new ToggleGroup();
                    for (var c: q.getChoices()) {
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(g);
                        
                        // xử lý giao diện
                        if (answers.get(q.getId()) == c)
                            r.setSelected(true);
                        
                        r.setOnAction(e -> {
                            if (r.isSelected() == true)
                                answers.put(q.getId(), c);
                        });
                        
                        v.getChildren().add(r);
                    }
                    
                    setGraphic(v);
                }
            }
            
        });
    }

    public void start(ActionEvent e) {
        this.answers = new HashMap<>();
        switch (this.cbExamTypes.getSelectionModel().getSelectedItem()) {
            case SPECIFIC:
                ExamStrategy s = new SpecificExam(this.txtNum.getText());
                this.questions = s.getQuestions();
                break;
            default:
                ExamStrategy s1 = new FixedExam();
                this.questions = s1.getQuestions();
        }

        this.lvQuestions.setItems(FXCollections.observableList(this.questions));

    }
    
    public void grading(ActionEvent e) {
        int count = 0;
        
        for (var ans: answers.values())
            if (ans.isCorrect() == true)
                count++;
        
        MyAlertSingleton.getInstance().showMsg(String.format("Bạn làm đúng %d/%d!", count, this.questions.size()), Alert.AlertType.INFORMATION);
    }
}
