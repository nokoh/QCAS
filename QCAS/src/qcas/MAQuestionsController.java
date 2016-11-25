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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class MAQuestionsController implements Initializable {
    
    ArrayList<MultipleChoice>multipleChoiceQuestions = new ArrayList();
    ArrayList<MultipleAnswer>multipleAnswerQuestions = new ArrayList();
    ArrayList<TrueFalse>trueFalseQuestions = new ArrayList();
    ArrayList<FillInTheBlanks>fillInTheBlanksQuestions = new ArrayList();
    ArrayList <Question> correctQuestions = new ArrayList();
    ArrayList <Question> incorrectQuestions = new ArrayList();

    
    Scene scene;
    String userId;
    Pane question = new Pane();
    int userScore;
    int numCorrect;
    int numIncorrect;
    int numOfQuestions;
   
    @FXML 
    private Button AButton;
    @FXML 
    private Button BButton;
    @FXML 
    private Button CButton;
    @FXML 
    private Button DButton;

    
    @FXML 
    private Label MAQuestionDescriptionLabel; 
    
    @FXML 
    private Label MAOptionALabel; 
    @FXML 
    private Label MAOptionBLabel; 
    @FXML 
    private Label MAOptionCLabel; 
    @FXML 
    private Label MAOptionDLabel;  
    
    @FXML 
    private Pagination pagination;
    
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

    public void launchMA(ArrayList<MultipleChoice>multipleChoiceQuestions, ArrayList<MultipleAnswer>multipleAnswerQuestions, ArrayList<TrueFalse>trueFalseQuestions, 
        ArrayList<FillInTheBlanks>fillInTheBlanksQuestions,int size) throws IOException, SQLException{
        
        Parent root;
        this.multipleChoiceQuestions = multipleChoiceQuestions;
        this.trueFalseQuestions = trueFalseQuestions;
        this.fillInTheBlanksQuestions = fillInTheBlanksQuestions;
        System.out.println(multipleAnswerQuestions.get(0).description);
       // Stage stage = (Stage) AButton.getScene().getWindow();
        if(size != 0){
            this.multipleAnswerQuestions = multipleAnswerQuestions;
            MAQuestionDescriptionLabel.setText(multipleAnswerQuestions.get(size-1).description);
            MAOptionALabel.setText(multipleAnswerQuestions.get(size-1).answer1);
            MAOptionBLabel.setText(multipleAnswerQuestions.get(size-1).answer2);
            MAOptionCLabel.setText(multipleAnswerQuestions.get(size-1).answer3);
            MAOptionDLabel.setText(multipleAnswerQuestions.get(size-1).answer4);
            
            
            
            AButton.setOnAction(e -> {
            if(multipleAnswerQuestions.get(size-1).correct1.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleAnswerQuestions.get(size-1));
            }else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleAnswerQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        
        BButton.setOnAction(e -> {
            if(multipleAnswerQuestions.get(size-1).correct2.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleAnswerQuestions.get(size-1));
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleAnswerQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        });
        
        CButton.setOnAction(e -> {
            if(multipleAnswerQuestions.get(size-1).correct3.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleAnswerQuestions.get(size-1));
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleAnswerQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        
        DButton.setOnAction(e -> {
            if(multipleAnswerQuestions.get(size-1).correct4.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleAnswerQuestions.get(size-1));
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleAnswerQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        }
        else{
            Stage stage = (Stage) AButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("FIBQuestions.fxml"));
            root = f.load();
            FIBQuestionsController sc = f.<FIBQuestionsController>getController();
            sc.launchFIB(this.multipleChoiceQuestions, this.multipleAnswerQuestions, this.trueFalseQuestions, 
                    this.fillInTheBlanksQuestions, this.multipleAnswerQuestions.size());
            sc.initID(this.userId);
            System.out.println(userId);
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
