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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class StartQuizPageController implements Initializable {
    Scene scene;
    Connection connection;
    int numberOfQuestions;
    String userId;
    
    ArrayList <MultipleChoice> multipleChoiceQuestions = new ArrayList();
    ArrayList <MultipleAnswer> multipleAnswerQuestions = new ArrayList();
    ArrayList <TrueFalse> trueFalseQuestions = new ArrayList();
    ArrayList <FillInTheBlanks> fillInTheBlanksQuestions = new ArrayList();
    ArrayList <Question> questionsAnswered = new ArrayList();

    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;
    @FXML
    private Button mixedButton;
    @FXML 
    private Label studentNameLabel;
    @FXML
    private Label userIDLabel;
    
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setNumOfQuestions(int num){ 
        numberOfQuestions = num;
    }
    
    public void initID(String ID) throws SQLException{ 
        userIDLabel.setText(ID);
        userId = ID;
        connectToDatabase();
        
        String dbQuery = "Select firstname, lastname, userid from Users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(dbQuery);
            preparedStatement.setString(1, userId);
            ResultSet rset = preparedStatement.executeQuery();
            if (rset.next()) {
                studentNameLabel.setText(rset.getString("firstname") + " " + rset.getString("lastname"));
            }
    }
    
    public void startQuiz(){
        int num = this.numberOfQuestions;
        easyButton.setOnAction(e -> {

        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("E");
                this.multipleChoiceQuestions.add(mcq);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("E");
                this.multipleAnswerQuestions.add(ma);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("E");
                this.trueFalseQuestions.add(tf);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("E");
                this.fillInTheBlanksQuestions.add(fib);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)easyButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("MCQuestions.fxml"));
                root = f.load();
                MCQuestionsController sc = f.<MCQuestionsController>getController();
                sc.initID(this.userId);
                sc.setNumOfQuestions(this.numberOfQuestions);
                sc.setPageNumber(0);
                sc.launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , this.numberOfQuestions/4);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        
        mediumButton.setOnAction(e -> {
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("M");
                this.multipleChoiceQuestions.add(mcq);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("M");
                this.multipleAnswerQuestions.add(ma);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("M");
                this.trueFalseQuestions.add(tf);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("M");
                this.fillInTheBlanksQuestions.add(fib);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)mediumButton.getScene().getWindow();
            try {
               
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("MCQuestions.fxml"));
                root = f.load();
                MCQuestionsController sc = f.<MCQuestionsController>getController();
                sc.initID(userId);
                sc.setNumOfQuestions(this.numberOfQuestions);
                sc.setPageNumber(0);
                sc.launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , this.numberOfQuestions/4);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        
        hardButton.setOnAction(e -> {
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("H");
                this.multipleChoiceQuestions.add(mcq);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("H");
                this.multipleAnswerQuestions.add(ma);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("H");
                this.trueFalseQuestions.add(tf);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("H");
                this.fillInTheBlanksQuestions.add(fib);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)easyButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("MCQuestions.fxml"));
                root = f.load();
                MCQuestionsController sc = f.<MCQuestionsController>getController();
                sc.multipleAnswerQuestions = this.multipleAnswerQuestions;
                sc.initID(userId);
                sc.setNumOfQuestions(this.numberOfQuestions);
                sc.setPageNumber(0);
                sc.launchMCQ(this.multipleAnswerQuestions, this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions, this.numberOfQuestions/4);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        
        mixedButton.setOnAction(e -> {
            for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("E");
                this.multipleChoiceQuestions.add(mcq);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("M");
                this.multipleChoiceQuestions.add(mcq);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("H");
                this.multipleAnswerQuestions.add(ma);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("M");
                this.multipleAnswerQuestions.add(ma);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                TrueFalse tf = new TrueFalse("M");
                this.trueFalseQuestions.add(tf);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                TrueFalse tf = new TrueFalse("E");
                this.trueFalseQuestions.add(tf);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("H");
                this.fillInTheBlanksQuestions.add(fib);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("E");
                this.fillInTheBlanksQuestions.add(fib);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)easyButton.getScene().getWindow();
            try {
                //load up OTHER FXML document
                FXMLLoader f = new FXMLLoader(getClass().getResource("MCQuestions.fxml"));
                root = f.load();
                MCQuestionsController sc = f.<MCQuestionsController>getController();
                sc.multipleAnswerQuestions = this.multipleAnswerQuestions;                
                sc.initID(userId);
                sc.setNumOfQuestions(this.numberOfQuestions);
                sc.setPageNumber(0);
                sc.launchMCQ(this.multipleAnswerQuestions, this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions, this.numberOfQuestions/4);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        });
    
    }
    
    public void connectToDatabase() throws SQLException{
        
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/UserDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            if (this.connection != null) {
              //  System.out.println("Conencted");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.connection.close();//closes connection resource
        } // end of try-with-resourc
        }
}
