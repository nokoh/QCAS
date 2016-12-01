/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import com.itextpdf.text.DocumentException;
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
 *  StartQuizPageController class is used to display the quiz options that can be 
 * taken by the user. Depending on the difficulty, the student is given a list of 
 * questions according to their varying difficulty level.
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
    
    /**
     * Setter Method for number of questions
     * @param num
     */
    public void setNumOfQuestions(int num){ 
        numberOfQuestions = num;
    }
    
    /**
     * This method pulls from the database the first and last name of the student 
     * and also pulls up the user id and name.
     * @param ID
     * @throws SQLException
     */
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
    
    /** 
     * startQuiz method depending on the button (easy,medium, hard, mixed) clicked, sets a display of questions 
     * to be displayed on the screen. 
     */
    public void startQuiz(){
        int num = this.numberOfQuestions;
        
        /*Action event handler for easy diffidulty button*/
        easyButton.setOnAction(e -> {

        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("E");//calls MCQ constructor to generate questions.
                this.multipleChoiceQuestions.add(mcq); //adds multiple choice questions to array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("E");//calls MA constructor to generate questions.
                this.multipleAnswerQuestions.add(ma);//adds multiple answer questions array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("E");//calls TF constructor to generate questions.
                this.trueFalseQuestions.add(tf);//adds true false questions array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("E"); //calls FIB constructor to generate questions.
                this.fillInTheBlanksQuestions.add(fib); //calls MCQ constructor to generate questions.
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
            } catch (DocumentException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        /*Action event handler for medium diffidulty button*/
        mediumButton.setOnAction(e -> {
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("M"); //calls MA constructor to generate questions.
                this.multipleChoiceQuestions.add(mcq);//adds to multiple choice array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("M");//calls MA constructor to generate questions.
                this.multipleAnswerQuestions.add(ma);//adds to multiple answer array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("M");//calls TF constructor to generate questions.
                this.trueFalseQuestions.add(tf); //adds to true false array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("M");//calls FIB constructor to generate questions.
                this.fillInTheBlanksQuestions.add(fib);//adds to fill in the blanks array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)mediumButton.getScene().getWindow();
            try {
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
            } catch (DocumentException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        /*Action event handler for hard diffidulty button*/
        hardButton.setOnAction(e -> {
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("H");//calls MCQ constructor to generate questions.
                this.multipleChoiceQuestions.add(mcq);//adds to multiple choice array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("H"); //calls MA constructor to generate questions.
                this.multipleAnswerQuestions.add(ma);//adds to multiple answer array list 
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                TrueFalse tf = new TrueFalse("H"); //calls TF constructor to generate questions.
                this.trueFalseQuestions.add(tf); //adds to true false in the array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/4; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("H"); //calls FIB constructor to generate questions.
                this.fillInTheBlanksQuestions.add(fib); //adds to fill in the blanks array list
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
            } catch (DocumentException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        /*Action event handler for mixed diffidulty button*/
        mixedButton.setOnAction(e -> {
            for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("E");//calls MCQ constructor to generate questions.
                this.multipleChoiceQuestions.add(mcq); //adds to multiple choice array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleChoice mcq = new MultipleChoice("M");//calls MCQ constructor to generate questions.
                this.multipleChoiceQuestions.add(mcq); //adds to multiple choice array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("H");//calls MA constructor to generate questions.
                this.multipleAnswerQuestions.add(ma); //adds to multiple answer array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                MultipleAnswer ma = new MultipleAnswer("M");//calls MA constructor to generate questions.
                this.multipleAnswerQuestions.add(ma); //adds to multiple answer array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                TrueFalse tf = new TrueFalse("M");//calls TF constructor to generate questions.
                this.trueFalseQuestions.add(tf); //adds to true false array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                TrueFalse tf = new TrueFalse("E"); //calls TF constructor to generate questions.
                this.trueFalseQuestions.add(tf); //adds to true false array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("H"); //calls FIB constructor to generate questions.
                this.fillInTheBlanksQuestions.add(fib); //adds to fill in the blanks array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int i = 0; i < this.numberOfQuestions/8; i++){
            try {
                FillInTheBlanks fib = new FillInTheBlanks("E"); //calls FIB constructor to generate questions.
                this.fillInTheBlanksQuestions.add(fib); //adds to fill in the blanks array list
            } catch (SQLException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Stage stage;
            Parent root;
            stage = (Stage)easyButton.getScene().getWindow();
            try {
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
            } catch (DocumentException ex) {
                Logger.getLogger(StartQuizPageController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        });
    
    }
    
    /**
     * Method to establish database connection to USerDB.
     * @throws SQLException
     */
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
