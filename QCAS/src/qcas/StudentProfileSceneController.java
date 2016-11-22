/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shay
 * 
 * 
 * ** Student Logged In Scene // Student Chooses to view Report or Take New Quiz
 */
public class StudentProfileSceneController implements Initializable {
    
    Scene scene;
    Stage homeStage;
    
    @FXML
    private Button takeQuizButton;      
    
    @FXML 
    private Button viewReportButton;
 
    @FXML
    private Label UserIDLabel;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initID(String ID){ 
        UserIDLabel.setText(ID);
        
    }
    
    public void startQuiz(){
        
        takeQuizButton.setOnAction(e -> {
            Stage stage;
            Parent root;
            stage = (Stage)takeQuizButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("SelectQuestions.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
        });
        
    }
}