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
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static qcas.SelectQuestionsController.secs;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class FIBQuestionsController implements Initializable {

    ArrayList<MultipleChoice> multipleChoiceQuestions = new ArrayList();
    ArrayList<MultipleAnswer> multipleAnswerQuestions = new ArrayList();
    ArrayList<TrueFalse> trueFalseQuestions = new ArrayList();
    ArrayList<FillInTheBlanks> fillInTheBlanksQuestions = new ArrayList();
    ArrayList<Question> correctQuestions = new ArrayList();
    ArrayList<Question> incorrectQuestions = new ArrayList();
    ArrayList<String> userAnswers = new ArrayList();
    ArrayList<String> userAnswerCheck = new ArrayList();
    ArrayList<Question> allAnsweredQuestions = new ArrayList();

    Scene scene;
    String userId;
    Pane question = new Pane();
    int userScore;
    int numCorrect;
    int numIncorrect;
    int numOfQuestions;
    Connection connection;
    int pageNumber;

    @FXML
    private Button nextButton;
    @FXML
    private TextField userAnswerField;
    @FXML
    private Label FIBQuestionDescriptionLabel;
    @FXML
    private Label studentNameLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private Label questionNumberLabel;
    @FXML
    private TextArea outputTextArea;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Method to set user variables in Fill in the blanks controller
     * @param ID
     * @throws SQLException
     */
    public void initID(String ID) throws SQLException {
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

    /**
     * Setter method for quiz score variable
     * @param num
     */
    public void setScore(int num) {
        userScore = num;
    }

    /**
     * Setter method for number of questions variable
     * @param num
     */
    public void setNumOfQuestions(int num) {
        numOfQuestions = num;
    }

    /**
     * Setter method for correct count variable
     * @param num
     */
    public void setCorrect(int num) {
        numCorrect = num;
    }

    /**
     * Setter method for incorrect count variable
     * @param num
     */
    public void setIncorrect(int num) {
        numIncorrect = num;
    }

    /**
     * Getter method for correctQuestions arraylist variable
     * @return
     */
    public ArrayList<Question> getCorrectQuestions() {
        return correctQuestions;
    }

    /**
     * Setter method for correct questions arraylist variable
     * @param correctQuestions
     */
    public void setCorrectQuestions(ArrayList<Question> correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    /**
     * Getter method for incorrect questions arraylist variable
     * @return
     */
    public ArrayList<Question> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    /**
     * Setter method for incorrect questions variable
     * @param incorrectQuestions
     */
    public void setIncorrectQuestions(ArrayList<Question> incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }

    /**
     * Setter method for user answers variable
     * @param userAnswers
     */
    public void setUserAnswers(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     * Setter method correct answer variable
     * @param userAnswerCheck
     */
    public void setUserAnswerCheck(ArrayList<String> userAnswerCheck) {
        this.userAnswerCheck = userAnswerCheck;
    }

    /**
     * Setter method for answered questions variable
     * @param allAnsweredQuestions
     */
    public void setAllAnsweredQuestions(ArrayList<Question> allAnsweredQuestions) {
        this.allAnsweredQuestions = allAnsweredQuestions;
    }

    /**
     * Setter method for page number variable
     * @param pageNumber
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Launch method to display fill in the blank questions generated for quiz.
     * 
     * @param multipleChoiceQuestions
     * @param multipleAnswerQuestions
     * @param trueFalseQuestions
     * @param fillInTheBlanksQuestions
     * @param size
     * @throws IOException
     * @throws SQLException
     * @throws DocumentException
     */
    public void launchFIB(ArrayList<MultipleChoice> multipleChoiceQuestions, ArrayList<MultipleAnswer> multipleAnswerQuestions,
            ArrayList<TrueFalse> trueFalseQuestions, ArrayList<FillInTheBlanks> fillInTheBlanksQuestions, int size) throws IOException, SQLException, DocumentException {
        Parent root;
        this.multipleAnswerQuestions = multipleAnswerQuestions; //sets multiple answer questions to be displayed in results page
        this.multipleChoiceQuestions = multipleChoiceQuestions; //sets multiple choice questions to be displayed in results page
        this.trueFalseQuestions = trueFalseQuestions; //sets true / false questions to be displayed in results page

        if (size != 0) {
            pageNumber += 1;
            questionNumberLabel.setText(pageNumber + "/" + this.numOfQuestions + "");
            this.fillInTheBlanksQuestions = fillInTheBlanksQuestions; //assigns fill in the blank questions generated for quiz
            FIBQuestionDescriptionLabel.setText(fillInTheBlanksQuestions.get(size - 1).description); //sets question description on display

            nextButton.setOnAction(e -> {
                if (userAnswerField.getText() == null) {
                    String answer = "  ";
                    this.numIncorrect++; //increase score count
                    this.incorrectQuestions.add(fillInTheBlanksQuestions.get(size - 1)); //add incorrect questions to incorrectQuestions arrayList
                    this.userAnswers.add(answer); //adds answers to userAnswers arrayList
                } else {
                    String answer = userAnswerField.getText().trim();
                    if (answer.equalsIgnoreCase(fillInTheBlanksQuestions.get(size - 1).correctAnswer)) {
                        this.numCorrect++; //increase score count
                        this.correctQuestions.add(fillInTheBlanksQuestions.get(size - 1)); //add correct questions to correctQuestions arrayList
                        this.userAnswers.add(answer); //adds user answer option to userAnswers arrayList
                    } else {
                        this.numIncorrect++; //increase score count
                        this.incorrectQuestions.add(fillInTheBlanksQuestions.get(size - 1));
                        this.userAnswers.add(answer); //adds user answer option to userAnswers arrayList
                    }
                    userAnswerField.setText(null);
                }
                int m = size - 1;
                this.userAnswerCheck.add(this.fillInTheBlanksQuestions.get(m).correctAnswer);
                this.allAnsweredQuestions.add(this.fillInTheBlanksQuestions.get(m));

                try {
                    launchFIB(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                            this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
                } catch (IOException ex) {
                    Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FIBQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(FIBQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } else {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("TFQuestions.fxml"));
            root = f.load();
            TFQuestionsController sc = f.<TFQuestionsController>getController(); //creating true / false controller for next scene
            
            /* Sets variables to be used in true / false controller */
            
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
            sc.launchTF(this.multipleChoiceQuestions, this.multipleAnswerQuestions, this.trueFalseQuestions,
                    this.fillInTheBlanksQuestions, this.multipleAnswerQuestions.size());

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Method
     *
     * @param remaining
     */
    public void startTimer(int remaining) {
        secs = (remaining);
        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                secs--;

                outputTextArea.setText((Integer.toString(secs / 60)) + " : " + Integer.toString(secs % 60));

                if (secs == 0) {
                    outputTextArea.setText("Time Up!!");
                    service.shutdownNow();
                }

            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Method to establish database connection to USerDB.
     *
     * @throws SQLException
     */
    public void connectToDatabase() throws SQLException {

        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/UserDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            if (this.connection != null) {
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.connection.close();//closes connection resource
        } // end of try-with-resourc
    }

}
