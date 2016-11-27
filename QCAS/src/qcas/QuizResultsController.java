/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
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
 * 
 * 
 * 
 * 
 */
public class QuizResultsController implements Initializable {
    
    Scene scene; 
    Stage homeStage;
    String userId;
    int userScore;
    int numOfQuestions;
    int numCorrect;
    int numIncorrect;
    ArrayList <Question> correctQuestions = new ArrayList();
    ArrayList <Question> incorrectQuestions = new ArrayList();
    ArrayList <Question> allAnsweredQuestions = new ArrayList();
    ArrayList <String> userAnswers = new ArrayList();
    ArrayList <String> userAnswerCheck = new ArrayList();
    Connection connection;
    Date currentDate;
    int examNumber;
    Double examScore;
   

    @FXML
    private Label studentNameLabel;
    @FXML 
    private Label userIDLabel;
    @FXML
    private Label quizDateLabel;
    
    
    @FXML
    private Button PrintToPDFButton;
    @FXML
    private Button returnHomeButton;
    @FXML
    private Label numberCorrectLabel;
    @FXML
    private Label numberIncorrectLabel;
    @FXML 
    private Label quizScoreLabel;
    @FXML
    private Label letterGradeLabel;
    @FXML
    private Label UA1,UA2,UA3,UA4,UA5,UA6,UA7,UA8,UA9,UA10,UA11,UA12,UA13,UA14,UA15,UA16;
    @FXML
    private Label UA17,UA18,UA19,UA20,UA21,UA22,UA23,UA24,UA25,UA26,UA27,UA28,UA29,UA30,UA31,UA32;        
    @FXML
    private Label CA1,CA2,CA3,CA4,CA5,CA6,CA7,CA8,CA9,CA10,CA11,CA12,CA13,CA14,CA15,CA16;
    @FXML
    private Label CA17,CA18,CA19,CA20,CA21,CA22,CA23,CA24,CA25,CA26,CA27,CA28,CA29,CA30,CA31,CA32;  
    
    
    
    @FXML
    private BarChart<String, Integer> barChartStudent;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    

    
    
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
    
    public void setUserAnswerCheck(ArrayList <String> userAnswerCheck) {
        this.userAnswerCheck = userAnswerCheck;
    }

    public void setAllAnsweredQuestions(ArrayList<Question> allAnsweredQuestions) {
        this.allAnsweredQuestions = allAnsweredQuestions;
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
                    Logger.getLogger(QuizResultsController.class.getName()).log(Level.SEVERE, null, ex);
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
            //    System.out.println("Conencted");
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
        
       // Stage stage = (Stage) AButton.getScene().getWindow();
       MultipleChoice mc = new MultipleChoice(0);
       MultipleAnswer ma = new MultipleAnswer(0);
       TrueFalse tf = new TrueFalse(0);
       FillInTheBlanks fib = new FillInTheBlanks(0);
       
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
       
           
          plotResultsGraph();
           
            
        
            connectToDatabase();
            String dbQuery = "Select firstname, lastname, userid from Users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(dbQuery);
            preparedStatement.setString(1, userId);
            ResultSet rset = preparedStatement.executeQuery();
            if (rset.next()) {
                barChartStudent.setTitle("Reports for Student: " + rset.getString("firstname") + " " + 
                        rset.getString("firstname") + " " + rset.getString("userid"));
            }
            
        ResultSet maxexamid = connection.createStatement().executeQuery("SELECT MAX(examID) FROM UserDB.ExamTable");
        if (maxexamid.next()) {
            examNumber = maxexamid.getInt(1) + 1;
        }
        
        this.examScore = (double)this.correctQuestions.size() / (double)this.allAnsweredQuestions.size()*100;
        int finalScore = this.examScore.intValue();
        quizScoreLabel.setText(finalScore + "%");
        String grade = "";
        
        if(this.examScore >= 97){
            grade = "A+";
        }
        else if(this.examScore >= 93 && this.examScore <= 96){
            grade = "A";
        }
        else if(this.examScore >= 90 && this.examScore <= 92){
            grade = "A-";
        }
        else if(this.examScore >= 87 && this.examScore <= 89){
            grade = "B+";
        }
        else if(this.examScore >= 83 && this.examScore <= 86){
            grade = "B";
        }
        else if(this.examScore >= 80 && this.examScore <= 82){
            grade = "B-";
        }
        else if(this.examScore >= 77 && this.examScore <= 79){
            grade = "C+";
        }
        else if(this.examScore >= 73 && this.examScore <= 76){
            grade = "C";
        }
        else if(this.examScore >= 70 && this.examScore <= 72){
            grade = "C-";
        }
        else if(this.examScore >= 67 && this.examScore <= 69){
            grade = "D+";
        }
        else if(this.examScore >= 63 && this.examScore <= 66){
            grade = "D";
        }
        else if(this.examScore >= 60 && this.examScore <= 62){
            grade = "D-";
        }
        else if(this.examScore >=0 && this.examScore <= 59){
            grade = "F";
        }
        
        letterGradeLabel.setText(grade);
            long time = System.currentTimeMillis();
            String s = convertTime(time);
            quizDateLabel.setText(s);
            String storeInDB = "INSERT INTO UserDB.ExamTable (examID, studentid, question, answerchoice, status, questionNo, examDate, correctAnswer, answercheck)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement storeDBExecute = this.connection.prepareStatement(storeInDB);
        connection.prepareStatement("SET foreign_key_checks = 0").executeUpdate();
        
        for (int t = 0;t < this.allAnsweredQuestions.size(); t++) {
            if(this.allAnsweredQuestions.get(t).getClass() == mc.getClass()){
            mc = (MultipleChoice)this.allAnsweredQuestions.get(t);
            storeDBExecute.setInt(1, examNumber);
            storeDBExecute.setInt(2, Integer.parseInt(userId));
            storeDBExecute.setString(3, mc.description);
            storeDBExecute.setString(4, this.userAnswers.get(t));
            storeDBExecute.setString(5, mc.difficulty);
            storeDBExecute.setInt(6, mc.number);
            storeDBExecute.setString(7, s);
            storeDBExecute.setString(8, this.userAnswerCheck.get(t));
            if(this.userAnswers.get(t).equals(this.userAnswerCheck.get(t))){
                storeDBExecute.setString(9, "correct");
            }
            else{
                storeDBExecute.setString(9, "incorrect");
            }
            storeDBExecute.executeUpdate();
            }
            else if(this.allAnsweredQuestions.get(t).getClass() == ma.getClass()){
            ma = (MultipleAnswer)this.allAnsweredQuestions.get(t);
            storeDBExecute.setInt(1, examNumber);
            storeDBExecute.setInt(2, Integer.parseInt(userId));
            storeDBExecute.setString(3, ma.description);
            storeDBExecute.setString(4, this.userAnswers.get(t));
            storeDBExecute.setString(5, ma.difficulty);
            storeDBExecute.setInt(6, ma.number);
            storeDBExecute.setString(7, s);
            storeDBExecute.setString(8, this.userAnswerCheck.get(t));
            if(this.userAnswers.get(t).equals(this.userAnswerCheck.get(t))){
                storeDBExecute.setString(9, "correct");
            }
            else{
                storeDBExecute.setString(9, "incorrect");
            }
            storeDBExecute.executeUpdate();
            }
            else if(this.allAnsweredQuestions.get(t).getClass() == fib.getClass()){
            fib = (FillInTheBlanks)this.allAnsweredQuestions.get(t);
            storeDBExecute.setInt(1, examNumber);
            storeDBExecute.setInt(2, Integer.parseInt(userId));
            storeDBExecute.setString(3, fib.description);
            storeDBExecute.setString(4, this.userAnswers.get(t));
            storeDBExecute.setString(5, fib.difficulty);
            storeDBExecute.setInt(6, fib.number);
            storeDBExecute.setString(7, s);
            storeDBExecute.setString(8, this.userAnswerCheck.get(t));
            if(this.userAnswers.get(t).equals(this.userAnswerCheck.get(t))){
                storeDBExecute.setString(9, "correct");
            }
            else{
                storeDBExecute.setString(9, "incorrect");
            }
            storeDBExecute.executeUpdate();
            }
            else if(this.allAnsweredQuestions.get(t).getClass() == tf.getClass()){
            tf = (TrueFalse)this.allAnsweredQuestions.get(t);
            storeDBExecute.setInt(1, examNumber);
            storeDBExecute.setInt(2, Integer.parseInt(userId));
            storeDBExecute.setString(3, tf.description);
            storeDBExecute.setString(4, this.userAnswers.get(t));
            storeDBExecute.setString(5, tf.difficulty);
            storeDBExecute.setInt(6, tf.number);
            storeDBExecute.setString(7, s);
            storeDBExecute.setString(8, this.userAnswerCheck.get(t));
            if(this.userAnswers.get(t).equals(this.userAnswerCheck.get(t))){
                storeDBExecute.setString(9, "correct");
            }
            else{
                storeDBExecute.setString(9, "incorrect");
            }
            storeDBExecute.executeUpdate();
            }
        }
           
        }
        
        public String convertTime(long time){
        
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd"); 
        return sdf.format(date);
}
    public void plotResultsGraph(){
        int [] difficultyCorrectScores = new int[3];
        int [] difficultyInCorrectScores = new int[3];
        for(Question question:this.correctQuestions){
           if(question.difficulty.equals("E")){
               difficultyCorrectScores[0]++;        
            }
           else if(question.difficulty.equals("M")){
               difficultyCorrectScores[1]++;        
            }
           else if(question.difficulty.equals("H")){
               difficultyCorrectScores[2]++;        
            }
           }
          
           for(Question question:this.incorrectQuestions){
           if(question.difficulty.equals("E")){
               difficultyInCorrectScores[0]++;        
            }
           else if(question.difficulty.equals("M")){
               difficultyInCorrectScores[1]++;        
            }
           else if(question.difficulty.equals("H")){
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
}
}
