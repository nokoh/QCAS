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
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class QuizResultsController implements Initializable {
    
    Scene scene; 
    Stage homeStage;
    String userId;
    Pane question = new Pane();
    int userScore;
    int numOfQuestions;
    int numCorrect;
    int numIncorrect;
    ArrayList <Question> correctQuestions = new ArrayList();
    ArrayList <Question> incorrectQuestions = new ArrayList();
    ArrayList <Question> allAnsweredQuestions = new ArrayList();
    ArrayList <String> userAnswers = new ArrayList();
    Connection connection;

    @FXML
    private Button PrintToPDFButton;
    @FXML
    private Button returnHomeButton;
    @FXML
    private Label numberCorrectLabel;
    @FXML
    private Label numberIncorrectLabel;
    @FXML
    private BarChart<String, Integer> barChartStudent;
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label question1, question2, question3, question4, question17, question18;
    private ObservableList<String> difficultyNames = FXCollections.observableArrayList();

    

    
    
    public void initID(String ID){ 
        userId = ID;
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
    
    public ArrayList<Question> getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(ArrayList<Question> correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public ArrayList<Question> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    public void setIncorrectQuestions(ArrayList<Question> incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }
    
    public void setUserAnswers(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     /**
        *  The returnHome() method switches back to the login screen.
        * 
     * @throws java.io.IOException
        **/
    @FXML
        public void returnHome() throws IOException{
            returnHomeButton.setOnAction(h ->{
                
                try {
                    returnHomeButtonClicked();
                } catch (IOException ex) {
                    Logger.getLogger(Scene13Controller.class.getName()).log(Level.SEVERE, null, ex);
                }                 
            } ); 
                
        }
     /**
     * 
     * @throws java.io.IOException
      */
    @FXML
    public void returnHomeButtonClicked() throws IOException{
            FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
                Parent scene13 = f.load();
                LoginScreenController ls = f.<LoginScreenController>getController();
                
                Scene LoginScreen = new Scene(scene13);
                homeStage = (Stage) returnHomeButton.getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(LoginScreen);
                homeStage.show();
    }
        
        public void connectToDatabase() throws SQLException{
        
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/UserDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            if (this.connection != null) {
                System.out.println("Conencted");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.connection.close();//closes connection resource
        } // end of try-with-resourc
        }
        
        public void launchQuizResults(ArrayList<Question>correctQuestions, ArrayList<Question>incorrectQuestions) throws IOException, SQLException{
        Parent root;
        this.correctQuestions = correctQuestions;
        this.incorrectQuestions = incorrectQuestions;
        
        String questionDifficulty;
        int [] difficultyCorrectScores = new int[3];
        int [] difficultyInCorrectScores = new int[3];
       // Stage stage = (Stage) AButton.getScene().getWindow();
       MultipleChoice mc = new MultipleChoice("");
       MultipleAnswer ma = new MultipleAnswer("");
       TrueFalse tf = new TrueFalse("");
       FillInTheBlanks fib = new FillInTheBlanks("");
       
       numberCorrectLabel.setText(this.correctQuestions.size()+"");
       numberIncorrectLabel.setText(this.incorrectQuestions.size()+"");
       
       for(int i = 0; i < this.correctQuestions.size(); i++){
           if(this.correctQuestions.get(i).getClass() == mc.getClass()){
               mc = (MultipleChoice)this.correctQuestions.get(i);
               questionDifficulty = mc.difficulty;
           }
           else if(this.correctQuestions.get(i).getClass() == ma.getClass()){
               ma = (MultipleAnswer)this.correctQuestions.get(i);
               questionDifficulty = ma.difficulty;
           }
           else if(this.correctQuestions.get(i).getClass() == tf.getClass()){
               tf = (TrueFalse)this.correctQuestions.get(i);
               questionDifficulty = tf.difficulty;
               int num = tf.number;
           }
           else if(this.correctQuestions.get(i).getClass() == fib.getClass()){
               fib = (FillInTheBlanks)this.correctQuestions.get(i);
               questionDifficulty = fib.difficulty;
           }
       }
           for(int i = 0; i < this.incorrectQuestions.size(); i++){
           if(this.incorrectQuestions.get(i).getClass() == mc.getClass()){
               mc = (MultipleChoice)this.incorrectQuestions.get(i);
           }
           else if(this.incorrectQuestions.get(i).getClass() == ma.getClass()){
               ma = (MultipleAnswer)this.incorrectQuestions.get(i);
           }
           else if(this.incorrectQuestions.get(i).getClass() == tf.getClass()){
               tf = (TrueFalse)this.incorrectQuestions.get(i);
           }
           else if(this.incorrectQuestions.get(i).getClass() == fib.getClass()){
               fib = (FillInTheBlanks)this.incorrectQuestions.get(i);
           }
       }
           for(Question question:this.correctQuestions){
           if(question.difficulty.equals("E")){
               difficultyCorrectScores[0]++;        
            }
           }
           
           for(Question question:this.correctQuestions){
           if(question.difficulty.equals("M")){
               difficultyCorrectScores[1]++;        
            }
           }
           
           for(Question question:this.correctQuestions){
           if(question.difficulty.equals("H")){
               difficultyCorrectScores[2]++;        
            }
           }
           
           for(Question question:this.incorrectQuestions){
           if(question.difficulty.equals("E")){
               difficultyInCorrectScores[0]++;        
            }
           }
           
           for(Question question:this.incorrectQuestions){
           if(question.difficulty.equals("M")){
               difficultyInCorrectScores[1]++;        
            }
           }
           
           for(Question question:this.incorrectQuestions){
           if(question.difficulty.equals("H")){
               difficultyInCorrectScores[2]++;        
            }
           }
                      
           String correct = "Correct";
           String inCorrect = "Incorrect";
  
           xAxis.setLabel("Difficulty Levels");       
           yAxis.setLabel("Number");
            
           /* Easy questions correct and incorrect */
           XYChart.Series series1 = new XYChart.Series();
           series1.setName("Easy");       
           series1.getData().add(new XYChart.Data(correct, difficultyCorrectScores[0]));
           series1.getData().add(new XYChart.Data(inCorrect, difficultyInCorrectScores[0]));
           
           /* Medium questions correct and incorrect */
           XYChart.Series series2 = new XYChart.Series();
           series2.setName("Medium");       
           series2.getData().add(new XYChart.Data(correct, difficultyCorrectScores[1]));
           series2.getData().add(new XYChart.Data(inCorrect, difficultyInCorrectScores[1]));
           
           /* Hard questions correct and incorrect */
           XYChart.Series series3 = new XYChart.Series();
           series3.setName("Hard");       
           series3.getData().add(new XYChart.Data(correct, difficultyCorrectScores[2]));
           series3.getData().add(new XYChart.Data(inCorrect, difficultyInCorrectScores[2]));

           barChartStudent.getData().addAll(series1, series2, series3);
           
           
            
            for(int t = 0; t < this.correctQuestions.size(); t++){
                this.allAnsweredQuestions.add(this.correctQuestions.get(t));
            }
            for(int t = 0; t < this.incorrectQuestions.size(); t++){
                this.allAnsweredQuestions.add(this.incorrectQuestions.get(t));
            }
            
            connectToDatabase();
            String dbQuery = "Select firstname, lastname, userid from Users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(dbQuery);
            preparedStatement.setString(1, userId);
            ResultSet rset = preparedStatement.executeQuery();
            if (rset.next()) {
                barChartStudent.setTitle("Reports for Student: " + rset.getString("firstname") + " " + 
                        rset.getString("firstname") + " " + rset.getString("userid"));
            }
            String storeInDB = "INSERT INTO UserDB.ExamTable (examID, studentid, question, anwerchoice, status, questionNo)" +
                        "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement storeDBExecute = this.connection.prepareStatement(storeInDB);
            
            for(int i = 0; i < this.numOfQuestions; i++){
            storeDBExecute.setInt(1, 1);
            storeDBExecute.setInt(2, Integer.parseInt(userId));
            storeDBExecute.setString(3, this.allAnsweredQuestions.get(i).description);
            storeDBExecute.setString(4, this.userAnswers.get(i));
            storeDBExecute.setString(5, this.allAnsweredQuestions.get(i).difficulty);
            storeDBExecute.setInt(6, this.allAnsweredQuestions.get(i).number);
            storeDBExecute.executeQuery();
            }
            
            
            
        }
}
