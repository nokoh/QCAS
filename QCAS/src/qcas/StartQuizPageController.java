/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class StartQuizPageController implements Initializable {
    Scene scene;
    Connection con;
    int numberOfQuestions;
    
    ArrayList<MultipleChoice>multipleChoiceQuestions = new ArrayList();
    ArrayList<Question>multipleAnswerQuestions = new ArrayList();
    ArrayList<Question>trueFalseQuestions = new ArrayList();
    ArrayList<Question>fillInTheBlanksQuestions = new ArrayList();

    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;
    @FXML
    private Button mixedButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /*
    public void initID(String ID){ 
        UserIDLabel.setText(ID);
        
    }*/
    public void startQuiz(){
        
        easyButton.setOnAction(e -> {

        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("E");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("E");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("E");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("E");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)easyButton.getScene().getWindow();
            try {
                connectToLiveDatabase();
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("scene9.fxml"));
                root = f.load();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
             //   Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        mediumButton.setOnAction(e -> {
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("M");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("M");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("M");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("M");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)easyButton.getScene().getWindow();
            try {
                connectToLiveDatabase();
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("scene9.fxml"));
                root = f.load();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
             //   Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        hardButton.setOnAction(e -> {
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("H");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("H");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("H");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("H");
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)easyButton.getScene().getWindow();
            try {
                connectToLiveDatabase();
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("scene9.fxml"));
                root = f.load();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
             //   Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        mixedButton.setOnAction(e -> {});
    
    }
    public void connectToLiveDatabase() throws SQLException{
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/LiveQuizDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database
        
        try {
         this.con = DriverManager.getConnection(url, username, password);  
           if (this.con != null) {
             //  System.out.println("Conencted");
            }
           else{
               System.out.println("Not Conencted");
           }
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.con.close();//closes connection resource
        } // end of try-with-resources 
        
    }
}