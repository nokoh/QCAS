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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class MAQuestionsController implements Initializable {
    
    ArrayList<Question>multipleAnswerQuestions = new ArrayList();
    ArrayList<Question>trueFalseQuestions = new ArrayList();

    
    Scene scene;
    String userId;
    
    @FXML
    private Pagination pagination;
    @FXML
    private Button nextButton;
    @FXML
    private TextField userAnswerField;
    @FXML
    private Label FIBQuestionDescriptionLabel;
    
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

    public void launchMA(ArrayList<Question>multipleAnswerQuestions, int size) throws IOException{
        Parent root;
       // Stage stage = (Stage) AButton.getScene().getWindow();
        if(size != 0){
            
        }
        
    }
    
}
