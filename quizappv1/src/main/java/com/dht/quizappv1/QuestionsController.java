/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dht.quizappv1;

import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.Level;
import com.dht.pojo.Question;
import com.dht.pojo.QuestionQueryBuilder;
import com.dht.services.FlyweightFactory;
import com.dht.services.question.QuestionFacade;
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

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<Level> cbSearchLevels;
    @FXML
    private TableView<Question> tvQuestions;
    @FXML
    private VBox vChoices;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggle;
    @FXML
    private TextField txtKeywords;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.loadColumns();

        this.cbCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateService, Configs.CATE_KEY)));
        this.cbLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.lvlService, Configs.LVL_KEY)));
        this.cbSearchCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateService, Configs.CATE_KEY)));
        this.cbSearchLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.lvlService, Configs.LVL_KEY)));

        loadTableQuestions();

        this.txtKeywords.textProperty().addListener(e -> {
            this.loadTableQuestions();
        });
        this.cbSearchCates.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestions();
        });
        this.cbSearchLevels.getSelectionModel().selectedItemProperty().addListener(e -> {
            this.loadTableQuestions();
        });
    }

    private void loadColumns() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);

        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(300);

        this.tvQuestions.getColumns().addAll(colId, colContent);
    }

    public void addChoice(ActionEvent e) {
        HBox h = new HBox();
        h.getStyleClass().add("Container");

        RadioButton r = new RadioButton();
        r.setToggleGroup(toggle);
        TextField txt = new TextField();
        txt.getStyleClass().add("Input");

        h.getChildren().addAll(r, txt);

        this.vChoices.getChildren().add(h);
    }

    public void addQuestion(ActionEvent e) {
        Question q = new Question.Builder().setCate(this.cbCates.getSelectionModel().getSelectedItem())
                .setContent(this.txtContent.getText())
                .setLevel(this.cbLevels.getSelectionModel().getSelectedItem()).build();

        List<Choice> choices = new ArrayList<>();
        for (var hbox : this.vChoices.getChildren()) {
            HBox h = (HBox) hbox;
            RadioButton rdo = (RadioButton) h.getChildren().get(0);
            TextField txt = (TextField) h.getChildren().get(1);

            choices.add(new Choice(txt.getText(), rdo.isSelected()));
        }

        try {
            Optional<ButtonType> b = MyAlertSingleton.getInstance().showMsg("Bạn chắc chắn thêm không?", Alert.AlertType.CONFIRMATION);
            if (b.isPresent() && b.get() == ButtonType.OK) {
                Configs.uQuestionService.addQuestion(q, choices);

                MyAlertSingleton.getInstance().showMsg("Thêm câu hỏi thành công!");
                loadTableQuestions();
            }

        } catch (SQLException ex) {
            MyAlertSingleton.getInstance().showMsg("Thêm câu hỏi thất bại, do: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void loadTableQuestions() {
        QuestionQueryBuilder query = new QuestionQueryBuilder()
                .withCategory(this.cbSearchCates.getSelectionModel().getSelectedItem())
                .withKeywords(this.txtKeywords.getText())
                .withLevel(this.cbSearchLevels.getSelectionModel().getSelectedItem());

        try {
            this.tvQuestions.setItems(FXCollections.observableList(QuestionFacade.getQuestions(query)));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
