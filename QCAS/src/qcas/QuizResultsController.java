/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
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

    @FXML
    private Button PrintToPDFButton;
    @FXML
    private Button returnHomeButton;
    @FXML
    private Label numberCorrectLabel;
    @FXML
    private Label numberIncorrectLabel;
    @FXML
    private BarChart<?, ?> barChartStudent;
    

    
    
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
            } ); {
         }       
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
        
        public void launchQuizResults(ArrayList<Question>correctQuestions, ArrayList<Question>incorrectQuestions) throws IOException, SQLException{
        Parent root;
        this.correctQuestions = correctQuestions;
        this.incorrectQuestions = incorrectQuestions;
        String questionDifficulty;
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
       
       
        
        }
}
