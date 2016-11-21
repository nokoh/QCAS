/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Shay
 * 
 * 
 * ** Student Logged In Scene // Student Chooses to view Report or Take New Quiz
 */
public class StudentProfileSceneController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    Scene scene;
    
    @FXML
    private Button takeQuizButton;      
    
    @FXML 
    private Button viewReportButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}