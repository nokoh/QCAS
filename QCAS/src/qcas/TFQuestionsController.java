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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class TFQuestionsController implements Initializable {
    
    ArrayList<MultipleChoice>multipleChoiceQuestions = new ArrayList();
    ArrayList<MultipleAnswer>multipleAnswerQuestions = new ArrayList();
    ArrayList<TrueFalse>trueFalseQuestions = new ArrayList();
    ArrayList<FillInTheBlanks>fillInTheBlanksQuestions = new ArrayList();

    @FXML
    private Button trueButton;
    @FXML
    private Button falseButton;
    @FXML
    private Label TFStatementLabel;
    @FXML
    private Button nextButton;
    @FXML
    private Pagination pagination;
    
    Scene scene;
    String userId;
    Pane question = new Pane();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initID(String ID){ 
        userId = ID;
    }
    
    public void launchTF(ArrayList<MultipleChoice>multipleChoiceQuestions, ArrayList<MultipleAnswer>multipleAnswerQuestions, ArrayList<TrueFalse>trueFalseQuestions, 
        ArrayList<FillInTheBlanks>fillInTheBlanksQuestions,int size) throws IOException{
        Parent root;
       // Stage stage = (Stage) AButton.getScene().getWindow();
        if(size != 0){
            
            
        }
        else{
            Stage stage = (Stage) nextButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("QuizResults.fxml"));
            root = f.load();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        
        
    }
    
}
