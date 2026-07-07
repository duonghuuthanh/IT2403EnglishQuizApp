/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv2;

import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.utils.Configs;
import com.dht.utils.MyAlertSingleton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {
    @FXML private TextArea txtContent;
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private ComboBox<Category> cbSearchCates;
    @FXML private ComboBox<Level> cbSearchLevels;
    @FXML private TableView<Question> tvQuestions;
    @FXML private VBox vChoices;
    @FXML private ToggleGroup toggle;
    @FXML private TextField txtSearchText;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadColumns();
        this.loadTableQuestions();
        try {
            this.cbCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(Configs.lvlService.getLevels()));
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.cateService.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableList(Configs.lvlService.getLevels()));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        this.txtSearchText.textProperty().addListener(e -> {
            this.loadTableQuestions();
        });
        this.cbSearchCates.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestions();
        });
        this.cbSearchLevels.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestions();
        });
    }    
    
    public void loadColumns() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(80);
        
        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(80);
        
        this.tvQuestions.getColumns().addAll(colId, colContent);
        
    }
    
    public void addChoice() {
        HBox h = new HBox();
        h.getStyleClass().add("Container");
        
        RadioButton rdo = new RadioButton();
        rdo.setToggleGroup(toggle);
        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        
        h.getChildren().addAll(rdo, txt);
        
        this.vChoices.getChildren().add(h);
    }
    
    public void addQuestion(ActionEvent e) {
        Question q = new Question.QuestionBuilder().setContent(txtContent.getText())
                .setCategory(cbCates.getSelectionModel().getSelectedItem())
                .setLevel(cbLevels.getSelectionModel().getSelectedItem()).build();
        
        List<Choice> choices = new ArrayList<>();
        
        for (var hbox: this.vChoices.getChildren()) {
            HBox h = (HBox) hbox;
            
            RadioButton rdo = (RadioButton)h.getChildren().get(0);
            TextField txt = (TextField)h.getChildren().get(1);
            
            choices.add(new Choice(txt.getText(), rdo.isSelected()));
        }
        
        try {
            Optional<ButtonType> b = MyAlertSingleton.getInstance().showAlert("Bạn chắc chắn thêm không?", Alert.AlertType.CONFIRMATION);
            if (b.isPresent() && b.get() == ButtonType.OK) {
                Configs.uQuestionService.addQuestion(q, choices);
                MyAlertSingleton.getInstance().showAlert("Thêm câu hỏi thành công!");
            }
        } catch (SQLException ex) {
            MyAlertSingleton.getInstance().showAlert("Thêm câu hỏi thất bại, do: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void loadTableQuestions() {
        try {
            QuestionQueryBuilder query = new QuestionQueryBuilder().widthKeywords(this.txtSearchText.getText())
                                                    .widthCategory(this.cbSearchCates.getSelectionModel().getSelectedItem())
                                                    .widthLevel(this.cbSearchLevels.getSelectionModel().getSelectedItem());
            Configs.questionService.setQuery(query);
            
            this.tvQuestions.setItems(FXCollections.observableList(Configs.questionService.getQuestions()));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
