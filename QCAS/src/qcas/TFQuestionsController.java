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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class TFQuestionsController implements Initializable {
    
    ArrayList<MultipleChoice>multipleChoiceQuestions = new ArrayList();
    ArrayList<MultipleAnswer>multipleAnswerQuestions = new ArrayList();
    ArrayList<TrueFalse>trueFalseQuestions = new ArrayList();
    ArrayList<FillInTheBlanks>fillInTheBlanksQuestions = new ArrayList();
    ArrayList <Question> correctQuestions = new ArrayList();
    ArrayList <Question> incorrectQuestions = new ArrayList();
    ArrayList <String> userAnswers = new ArrayList();

    @FXML
    private Button trueButton;
    @FXML
    private Button falseButton;
    @FXML
    private Label TFStatementLabel;
    @FXML
    private Button nextButton;
    @FXML
    private Pagination pagination;
    
    Scene scene;
    String userId;
    Pane question = new Pane();
    int userScore;
    int numOfQuestions;
    int numCorrect;
    int numIncorrect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

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
    
    public void launchTF(ArrayList<MultipleChoice>multipleChoiceQuestions, ArrayList<MultipleAnswer>multipleAnswerQuestions, 
            ArrayList<TrueFalse>trueFalseQuestions, ArrayList<FillInTheBlanks>fillInTheBlanksQuestions,int size) throws IOException, SQLException{
        Parent root;
        this.multipleAnswerQuestions = multipleAnswerQuestions;
        this.multipleChoiceQuestions = multipleChoiceQuestions;
        this.fillInTheBlanksQuestions = fillInTheBlanksQuestions;
       // Stage stage = (Stage) AButton.getScene().getWindow();
        if(size != 0){
            this.trueFalseQuestions = trueFalseQuestions;
            TFStatementLabel.setText(trueFalseQuestions.get(size-1).description);
            
            trueButton.setOnAction(e -> {
                if(trueFalseQuestions.get(size-1).correctAnswer.equals("true")){
                    this.numCorrect++;
                    this.correctQuestions.add(trueFalseQuestions.get(size-1));
                    this.userAnswers.add(trueFalseQuestions.get(size-1).correctAnswer);
                }
                else{
                    this.numIncorrect++;
                    this.incorrectQuestions.add(trueFalseQuestions.get(size-1));
                    this.userAnswers.add(trueFalseQuestions.get(size-1).correctAnswer);
                }
                int m = size - 1;
            try {
                launchTF(this.multipleChoiceQuestions, this.multipleAnswerQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (SQLException ex) {
                    Logger.getLogger(TFQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            falseButton.setOnAction(e -> {
                if(trueFalseQuestions.get(size-1).correctAnswer.equals("false")){
                    this.numCorrect++;
                    this.correctQuestions.add(trueFalseQuestions.get(size-1));
                    this.userAnswers.add(trueFalseQuestions.get(size-1).correctAnswer);
                }
                else{
                    this.numIncorrect++;
                    this.incorrectQuestions.add(trueFalseQuestions.get(size-1));
                    this.userAnswers.add(trueFalseQuestions.get(size-1).correctAnswer);
                }
                int m = size - 1;
            try {
                launchTF(this.multipleChoiceQuestions, this.multipleAnswerQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (SQLException ex) {
                    Logger.getLogger(TFQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }
        else{
            Stage stage = (Stage) nextButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("QuizResults.fxml"));
            root = f.load();
            QuizResultsController sc = f.<QuizResultsController>getController();
            sc.initID(this.userId);
            sc.setUserAnswers(this.userAnswers);
            sc.launchQuizResults(this.correctQuestions, this.incorrectQuestions);
            sc.setNumOfQuestions(this.numOfQuestions);
            sc.setCorrect(this.numCorrect);
            sc.setIncorrect(this.numIncorrect);
            sc.setCorrectQuestions(this.correctQuestions);
            sc.setIncorrectQuestions(this.incorrectQuestions);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        
        
    }
    
}
