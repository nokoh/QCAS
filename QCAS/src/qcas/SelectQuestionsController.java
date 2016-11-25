/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class SelectQuestionsController implements Initializable {
    
    int numOfQuestions;
    Scene scene;
    String userId;
    
    public void initID(String ID){ 
        userId = ID;
    }
    

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }
    
    @FXML
    private Button eightQuestionsButton;
    @FXML
    private Button sixteenQuestionsButton;
    @FXML
    private Button twentyfourQuestionsButton;
    @FXML
    private Button thirtytwoQuestionsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void getNumberOfQuestions(){
        
        eightQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(8);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 8;
                sc.initID(userId);
                System.out.println(userId);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
        sixteenQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(16);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 16;
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        twentyfourQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(24);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 24;
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        thirtytwoQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(32);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 32;
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        
    }  
}
