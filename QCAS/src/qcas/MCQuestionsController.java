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
import javafx.scene.control.Pagination;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class MCQuestionsController implements Initializable {
    
    ArrayList <MultipleChoice> multipleChoiceQuestions = new ArrayList();
    ArrayList <MultipleAnswer> multipleAnswerQuestions = new ArrayList();
    ArrayList <TrueFalse> trueFalseQuestions = new ArrayList();
    ArrayList <FillInTheBlanks> fillInTheBlanksQuestions = new ArrayList();
    ArrayList <Question> correctQuestions = new ArrayList();
    ArrayList <Question> incorrectQuestions = new ArrayList();
    ArrayList <String> userAnswers = new ArrayList();
    ArrayList <String> userAnswerCheck = new ArrayList();
    ArrayList <Question> allAnsweredQuestions = new ArrayList();
    
    Scene scene;
    String userId;
    int userScore;
    int currentQuestion;
    int numOfQuestions;
    int numCorrect = 0;
    int numIncorrect = 0;
    Connection connection;
    int pageNumber;

    @FXML
    private Label questionNumberLabel;
    @FXML
    private Label MCOptionA;
    @FXML
    private Label MCOptionB;
    @FXML
    private Label MCOptionC;
    @FXML
    private Label MCOptionD;
    @FXML
    private Button AButton;
    @FXML
    private Button BButton;
    @FXML
    private Button CButton;
    @FXML
    private Button DButton;
    @FXML
    private Label MCQuestionDescriptionLabel;
    @FXML 
    private Label studentNameLabel;
    @FXML
    private Label userIDLabel;

    
    
    public void initID(String ID) throws SQLException{ 
        userId = ID;
        userIDLabel.setText(ID);
        connectToDatabase();
        
        String dbQuery = "Select firstname, lastname, userid from Users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(dbQuery);
            preparedStatement.setString(1, userId);
            ResultSet rset = preparedStatement.executeQuery();
            if (rset.next()) {
                studentNameLabel.setText(rset.getString("firstname") + " " + rset.getString("lastname"));
            }
            
    }
    
    public void setScore(int num){ 
        userScore = num;
    }
    
    public void setNumOfQuestions(int num){ 
        numOfQuestions = num;
    }
    
    public void setCorrect(int num){ 
        numCorrect = num;
    }
    
    public void setIncorrect(int num){ 
        numIncorrect = num;
    }
    
    public void setUserAnswers(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void setAllAnsweredQuestions(ArrayList<Question> allAnsweredQuestions) {
        this.allAnsweredQuestions = allAnsweredQuestions;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    public void launchMCQ(ArrayList<MultipleAnswer>multipleAnswerQuestions, ArrayList<MultipleChoice>multipleChoiceQuestions, 
            ArrayList<TrueFalse>trueFalseQuestions, ArrayList<FillInTheBlanks>fillInTheBlanksQuestions, int size) throws IOException, SQLException{
        Parent root;
    //    System.out.println("This is sizw: " + size);
        this.multipleAnswerQuestions = multipleAnswerQuestions;
        this.trueFalseQuestions = trueFalseQuestions;
        this.fillInTheBlanksQuestions = fillInTheBlanksQuestions;
        
        if(size != 0){
        this.multipleChoiceQuestions = multipleChoiceQuestions;
        MCQuestionDescriptionLabel.setText(multipleChoiceQuestions.get(size-1).description);
        MCOptionA.setText(multipleChoiceQuestions.get(size-1).answer1);
        MCOptionB.setText(multipleChoiceQuestions.get(size-1).answer2);
        MCOptionC.setText(multipleChoiceQuestions.get(size-1).answer3);
        MCOptionD.setText(multipleChoiceQuestions.get(size-1).answer4);
        this.allAnsweredQuestions.add(multipleChoiceQuestions.get(size-1));
//        System.out.println("MC "+this.allAnsweredQuestions.size());
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                this.userAnswerCheck.add(multipleChoiceQuestions.get(size - 1).answer1);
            }
            else if(multipleChoiceQuestions.get(size-1).correct2.equals("correct")){
                this.userAnswerCheck.add(multipleChoiceQuestions.get(size - 1).answer2);
            }
            else if(multipleChoiceQuestions.get(size-1).correct3.equals("correct")){
                this.userAnswerCheck.add(multipleChoiceQuestions.get(size - 1).answer3);
            }
            else if(multipleChoiceQuestions.get(size-1).correct4.equals("correct")){
                this.userAnswerCheck.add(multipleChoiceQuestions.get(size - 1).answer4);
            }
        pageNumber+=1;
        questionNumberLabel.setText(pageNumber + "/" + this.numOfQuestions + "");
        
        AButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer1);
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer1);
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleAnswerQuestions, this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct2.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer2);
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer2);
            }
            int m = size - 1;
            
            try {
                launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        CButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct3.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer3);
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer3);
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        DButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct4.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer4);
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
                this.userAnswers.add(multipleChoiceQuestions.get(size-1).answer4);
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
        else{
            Stage stage = (Stage) AButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("MAQuestions.fxml"));
            root = f.load();
            MAQuestionsController sc = f.<MAQuestionsController>getController();
            
            sc.initID(this.userId);
            sc.setUserAnswerCheck(this.userAnswerCheck);
            sc.setNumOfQuestions(this.numOfQuestions);
            sc.setCorrect(this.numCorrect);
            sc.setIncorrect(this.numIncorrect);
            sc.setCorrectQuestions(this.correctQuestions);
            sc.setIncorrectQuestions(this.incorrectQuestions);
            sc.setUserAnswers(this.userAnswers);
            sc.setPageNumber(this.pageNumber);
            sc.setAllAnsweredQuestions(this.allAnsweredQuestions);
            sc.launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions, this.trueFalseQuestions, 
                    this.fillInTheBlanksQuestions, this.multipleAnswerQuestions.size());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
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
