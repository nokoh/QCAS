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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class MAQuestionsController implements Initializable {
    
    ArrayList<MultipleAnswer>multipleAnswerQuestions = new ArrayList();
    ArrayList<TrueFalse>trueFalseQuestions = new ArrayList();
    ArrayList<FillInTheBlanks>fillInTheBlanksQuestions = new ArrayList();

    
    Scene scene;
    String userId;
    Pane question = new Pane();
    

    
    @FXML 
    private Button AButton;
    @FXML 
    private Button BButton;
    @FXML 
    private Button CButton;
    @FXML 
    private Button DButton;

    
    @FXML 
    private Label MAQuestionDescriptionLabel; 
    
    @FXML 
    private Label MAOptionALabel; 
    @FXML 
    private Label MAOptionBLabel; 
    @FXML 
    private Label MAOptionCLabel; 
    @FXML 
    private Label MAOptionDLabel;  
    
    @FXML 
    private Pagination pagination;
    
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

    public void launchMA(ArrayList<MultipleAnswer>multipleAnswerQuestions, ArrayList<TrueFalse>trueFalseQuestions, 
            ArrayList<FillInTheBlanks>fillInTheBlanksQuestions,int size) throws IOException{
        Parent root;
        this.trueFalseQuestions = trueFalseQuestions;
        this.fillInTheBlanksQuestions = fillInTheBlanksQuestions;
        System.out.println(multipleAnswerQuestions.get(0).description);
       // Stage stage = (Stage) AButton.getScene().getWindow();
        if(size != 0){
            this.multipleAnswerQuestions = multipleAnswerQuestions;
            MAQuestionDescriptionLabel.setText(multipleAnswerQuestions.get(size-1).description);
            AButton.setText(multipleAnswerQuestions.get(size-1).answer1);
            BButton.setText(multipleAnswerQuestions.get(size-1).answer2);
            CButton.setText(multipleAnswerQuestions.get(size-1).answer3);
            DButton.setText(multipleAnswerQuestions.get(size-1).answer4);  
        }
        else{
            
        }
        
        
    }
    
}
