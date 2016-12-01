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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static qcas.SelectQuestionsController.secs;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 * 
 *
 *
 */
public class TFQuestionsController implements Initializable {

    ArrayList<MultipleChoice> multipleChoiceQuestions = new ArrayList();
    ArrayList<MultipleAnswer> multipleAnswerQuestions = new ArrayList();
    ArrayList<TrueFalse> trueFalseQuestions = new ArrayList();
    ArrayList<FillInTheBlanks> fillInTheBlanksQuestions = new ArrayList();
    ArrayList<Question> correctQuestions = new ArrayList();
    ArrayList<Question> incorrectQuestions = new ArrayList();
    ArrayList<String> userAnswers = new ArrayList();
    ArrayList<String> userAnswerCheck = new ArrayList();
    ArrayList<Question> allAnsweredQuestions = new ArrayList();

    @FXML
    private Button trueButton;
    @FXML
    private Button falseButton;
    @FXML
    private Label TFStatementLabel;
    @FXML
    private Button nextButton;
    @FXML
    private Label questionNumberLabel;

    @FXML
    private Label studentNameLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextArea outputTextArea;

    Scene scene;
    String userId;
    Pane question = new Pane();
    int userScore;
    int numOfQuestions;
    int numCorrect;
    int numIncorrect;
    Connection connection;
    int pageNumber;

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
     * 
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
     * 
     * @param num
     */
    public void setScore(int num) {
        userScore = num;
    }

    /**
     *
     * @param num
     */
    public void setNumOfQuestions(int num) {
        numOfQuestions = num;
    }

    /**
     *
     * @param num
     */
    public void setCorrect(int num) {
        numCorrect = num;
    }

    /**
     *
     * @param num
     */
    public void setIncorrect(int num) {
        numIncorrect = num;
    }

    /**
     *
     * @return
     */
    public ArrayList<Question> getCorrectQuestions() {
        return correctQuestions;
    }

    /**
     *
     * @param correctQuestions
     */
    public void setCorrectQuestions(ArrayList<Question> correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    /**
     *
     * @return
     */
    public ArrayList<Question> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    /**
     *
     * @param incorrectQuestions
     */
    public void setIncorrectQuestions(ArrayList<Question> incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }

    /**
     *
     * @param userAnswers
     */
    public void setUserAnswers(ArrayList<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     *
     * @param userAnswerCheck
     */
    public void setUserAnswerCheck(ArrayList<String> userAnswerCheck) {
        this.userAnswerCheck = userAnswerCheck;
    }

    /**
     *
     * @param allAnsweredQuestions
     */
    public void setAllAnsweredQuestions(ArrayList<Question> allAnsweredQuestions) {
        this.allAnsweredQuestions = allAnsweredQuestions;
    }

    /**
     *
     * @param pageNumber
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Launch method to display true / false questions generated for quiz.
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
    public void launchTF(ArrayList<MultipleChoice> multipleChoiceQuestions, ArrayList<MultipleAnswer> multipleAnswerQuestions,
            ArrayList<TrueFalse> trueFalseQuestions, ArrayList<FillInTheBlanks> fillInTheBlanksQuestions, int size) throws IOException, SQLException, DocumentException {
        Parent root;
        this.multipleAnswerQuestions = multipleAnswerQuestions;
        this.multipleChoiceQuestions = multipleChoiceQuestions;
        this.fillInTheBlanksQuestions = fillInTheBlanksQuestions;
        pageNumber += 1;
        questionNumberLabel.setText(pageNumber + "/" + this.numOfQuestions + "");
        //    System.out.println("TF "+this.allAnsweredQuestions.size());
        // Stage stage = (Stage) AButton.getScene().getWindow();
        if (size != 0) {

            this.trueFalseQuestions = trueFalseQuestions;
            this.allAnsweredQuestions.add(this.trueFalseQuestions.get(size - 1));
            TFStatementLabel.setText(trueFalseQuestions.get(size - 1).description);
            this.userAnswerCheck.add(trueFalseQuestions.get(size - 1).correctAnswer);

            trueButton.setOnAction(e -> {
                if (trueFalseQuestions.get(size - 1).correctAnswer.equals("true")) {
                    this.numCorrect++;
                    this.correctQuestions.add(trueFalseQuestions.get(size - 1));
                    this.userAnswers.add(trueFalseQuestions.get(size - 1).correctAnswer);
                } else {
                    this.numIncorrect++;
                    this.incorrectQuestions.add(trueFalseQuestions.get(size - 1));
                    this.userAnswers.add(trueFalseQuestions.get(size - 1).correctAnswer);
                }
                int m = size - 1;
                try {
                    launchTF(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                            this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
                } catch (IOException ex) {
                    Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TFQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(TFQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            falseButton.setOnAction(e -> {
                if (trueFalseQuestions.get(size - 1).correctAnswer.equals("false")) {
                    this.numCorrect++;
                    this.correctQuestions.add(trueFalseQuestions.get(size - 1));
                    this.userAnswers.add(trueFalseQuestions.get(size - 1).correctAnswer);
                } else {
                    this.numIncorrect++;
                    this.incorrectQuestions.add(trueFalseQuestions.get(size - 1));
                    this.userAnswers.add(trueFalseQuestions.get(size - 1).correctAnswer);
                }
                int m = size - 1;
                this.userAnswerCheck.add(this.trueFalseQuestions.get(m).correctAnswer);

                try {
                    launchTF(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                            this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
                } catch (IOException ex) {
                    Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TFQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(TFQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } else {
            Stage stage = (Stage) trueButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("QuizResults.fxml"));
            root = f.load();
            QuizResultsController sc = f.<QuizResultsController>getController();
            sc.initID(this.userId);
            sc.setUserAnswerCheck(this.userAnswerCheck);
            sc.setUserAnswers(this.userAnswers);
            sc.setNumOfQuestions(this.numOfQuestions);
            sc.setCorrect(this.numCorrect);
            sc.setIncorrect(this.numIncorrect);
            sc.setCorrectQuestions(this.correctQuestions);
            sc.setIncorrectQuestions(this.incorrectQuestions);
            sc.setAllAnsweredQuestions(this.allAnsweredQuestions);
            sc.launchQuizResults(this.correctQuestions, this.incorrectQuestions);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
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
                    outputTextArea.setText("     ");
                    service.shutdownNow();
                }

            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
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
                //  System.out.println("Conencted");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.connection.close();//closes connection resource
        } // end of try-with-resourc
    }

}
