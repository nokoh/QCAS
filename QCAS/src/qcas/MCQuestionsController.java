/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
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
    
    Scene scene;
    String userId;
    int userScore;
    int numOfQuestions;
    int numCorrect = 0;
    int numIncorrect = 0;

    @FXML
    private Pagination questionNumber;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    public void launchMCQ(ArrayList<MultipleAnswer>multipleAnswerQuestions, ArrayList<MultipleChoice>multipleChoiceQuestions, 
            ArrayList<TrueFalse>trueFalseQuestions, ArrayList<FillInTheBlanks>fillInTheBlanksQuestions, int size) throws IOException{
        Parent root;
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
        
        
        AButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        CButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        DButton.setOnAction(e -> {
            if(multipleChoiceQuestions.get(size-1).correct1.equals("correct")){
                this.numCorrect++;
                this.correctQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            else{
                this.numIncorrect++;
                this.incorrectQuestions.add(multipleChoiceQuestions.get(size-1));
            }
            int m = size - 1;
            try {
                launchMCQ(this.multipleAnswerQuestions,   this.multipleChoiceQuestions, 
                        this.trueFalseQuestions, this.fillInTheBlanksQuestions , m);
            } catch (IOException ex) {
                Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
        else{
            Stage stage = (Stage) AButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("MAQuestions.fxml"));
            root = f.load();
            MAQuestionsController sc = f.<MAQuestionsController>getController();
            sc.launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions, this.trueFalseQuestions, 
                    this.fillInTheBlanksQuestions, this.multipleAnswerQuestions.size());
            sc.initID(userId);
            sc.setNumOfQuestions(this.numOfQuestions);
            sc.setCorrect(this.numCorrect);
            sc.setIncorrect(this.numIncorrect);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    /*
        FXMLLoader f = new FXMLLoader(getClass().getResource("scene10.fxml"));
        root = f.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   */
    
    
}
