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
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import static qcas.SelectQuestionsController.secs;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class SelectQuestionsController implements Initializable {
    
    int numOfQuestions;
    Scene scene;
    String userId;
    Connection connection;

    /**
     *
     */
    public static int secs;

    /**
     *
     */
    public static int maxTime;
    
    
    @FXML
    private Label studentNameLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextArea outputTextArea;
    
    /**
     * Method to set user variables in Select Questions controller
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
     * Getter method for number of questions
     * @return
     */
    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    /**
     * Setter method for number of questions
     * @param numOfQuestions
     */
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Gets the number of questions for a particular user and 
     * sets the number of questions to be answered in the quiz. 
     */
    public void getNumberOfQuestions(){
        
        /* Event handling for selecting questions button */
        eightQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(8);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));//sets scene for start of quiz
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 8;
                sc.initID(userId);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SelectQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
        
        /* Event handling for selecting questions button */
        sixteenQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(16);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));//sets scene for start of quiz
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 16;
                sc.initID(userId);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SelectQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        
        /* Event handling for selecting questions button */
        twentyfourQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(24);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));//sets scene for start of quiz
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 24;
                sc.initID(userId);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SelectQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });
        
        /* Event handling for selecting questions button */
        thirtytwoQuestionsButton.setOnAction(e -> {
            setNumOfQuestions(32);
            Stage stage;
            Parent root;
            stage = (Stage)eightQuestionsButton.getScene().getWindow();
            try {
                FXMLLoader f = new FXMLLoader(getClass().getResource("StartQuizPage.fxml"));//sets scene for start of quiz
                root = f.load();
                StartQuizPageController sc = f.<StartQuizPageController>getController();
                sc.numberOfQuestions = 32;
                sc.initID(userId);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             //   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SelectQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
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
          //      System.out.println("Conencted");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.connection.close();//closes connection resource
        } // end of try-with-resourc
        }
}
