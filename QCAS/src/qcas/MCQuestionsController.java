/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class MCQuestionsController implements Initializable {
    
    ArrayList<MultipleChoice>multipleChoiceQuestions = new ArrayList();
    ArrayList<Question>multipleAnswerQuestions = new ArrayList();
    ArrayList<Question>trueFalseQuestions = new ArrayList();
    ArrayList<Question>fillInTheBlanksQuestions = new ArrayList();
    
    Scene scene;
    String userId;

    @FXML
    private Pagination questionNumber;
    @FXML
    private Label MCOptionA;
    @FXML
    private Label MCOptionB;
    @FXML
    private Label MCOptionC;
    @FXML
    private Label MCOptionD;
    @FXML
    private Button AButton;
    @FXML
    private Button BButton;
    @FXML
    private Button CButton;
    @FXML
    private Button DButton;
    @FXML
    private Label MCQuestionDescriptionLabel;
    
    public void initID(String ID){ 
        userId = ID;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    public void launchMCQ(ArrayList<MultipleChoice>multipleChoiceQuestions, int size) throws IOException{
        Parent root;
       // Stage stage = (Stage) AButton.getScene().getWindow();
        if(size != 0){
        this.multipleChoiceQuestions = multipleChoiceQuestions;
        MCQuestionDescriptionLabel.setText(multipleChoiceQuestions.get(size-1).description);
        MCOptionA.setText(multipleChoiceQuestions.get(size-1).answer1);
        MCOptionB.setText(multipleChoiceQuestions.get(size-1).answer2);
        MCOptionC.setText(multipleChoiceQuestions.get(size-1).answer3);
        MCOptionD.setText(multipleChoiceQuestions.get(size-1).answer4);
        
        
        AButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleChoiceQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleChoiceQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        CButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleChoiceQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        DButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleChoiceQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
        else{
            Stage stage = (Stage) AButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("MAQuestions.fxml"));
            root = f.load();
            MAQuestionsController sc = f.<MAQuestionsController>getController();
            sc.launchMA(this.multipleAnswerQuestions, this.multipleChoiceQuestions.size());
            sc.initID(userId);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    /*
        FXMLLoader f = new FXMLLoader(getClass().getResource("scene10.fxml"));
        root = f.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   */
    
    
}
