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
public class MAQuestionsController implements Initializable {

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
    private Button AButton;
    @FXML
    private Button BButton;
    @FXML
    private Button CButton;
    @FXML
    private Button DButton;
    @FXML
    private Label studentNameLabel;
    @FXML
    private Label userIDLabel;

    @FXML
    private Label MAQuestionDescriptionLabel;
    @FXML
    private Label questionNumberLabel;

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
    @FXML
    private TextArea outputTextArea;

    /**
     * Initialize method for Multiple Choice Questions. 
     * Sets initial parameters for user information. 
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
     * Setter method for userScore variable. 
     * @param num
     */
    public void setScore(int num) {
        userScore = num;
    }

    /**
     * Setter method for number of questions variable. 
     * @param num
     */
    public void setNumOfQuestions(int num) {
        numOfQuestions = num;
    }

    /**
     * Setter method for correct questions variable. 
     * @param num
     */
    public void setCorrect(int num) {
        numCorrect = num;
    }

    /**
     * Setter method for incorrect questions variable. 
     * @param num
     */
    public void setIncorrect(int num) {
        numIncorrect = num;
    }

    /**
     * Getter method for correct questions variable. 
     * @return
     */
    public ArrayList<Question> getCorrectQuestions() {
        return correctQuestions;
    }

    /**
     * Setter method for correct questions variable
     * @param correctQuestions
     */
    public void setCorrectQuestions(ArrayList<Question> correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    /**
     * Getter method for incorrect questions variable
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
     * Setter method for correct answers variable
     * @param userAnswerCheck
     */
    public void setUserAnswerCheck(ArrayList<String> userAnswerCheck) {
        this.userAnswerCheck = userAnswerCheck;
    }

    /**
     * Setter method for all user answered questions.
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
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *Launch method to display multiple answer questions generated for quiz.
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
    public void launchMA(ArrayList<MultipleChoice> multipleChoiceQuestions, ArrayList<MultipleAnswer> multipleAnswerQuestions, ArrayList<TrueFalse> trueFalseQuestions,
            ArrayList<FillInTheBlanks> fillInTheBlanksQuestions, int size) throws IOException, SQLException, DocumentException {

        Parent root;
        this.multipleChoiceQuestions = multipleChoiceQuestions; //sets multiple choice questions to be displayed in results page
        this.trueFalseQuestions = trueFalseQuestions; //sets true / false questions to be displayed in results page
        this.fillInTheBlanksQuestions = fillInTheBlanksQuestions; //sets fill in the blanks questions to be displayed in results page

        if (size != 0) {
            this.multipleAnswerQuestions = multipleAnswerQuestions; //assigns multiple answer questions generated for quiz
            MAQuestionDescriptionLabel.setText(multipleAnswerQuestions.get(size - 1).description); //sets question description on display
            MAOptionALabel.setText(multipleAnswerQuestions.get(size - 1).answer1); //sets options on display
            MAOptionBLabel.setText(multipleAnswerQuestions.get(size - 1).answer2); //sets options on display
            MAOptionCLabel.setText(multipleAnswerQuestions.get(size - 1).answer3); //sets options on display
            MAOptionDLabel.setText(multipleAnswerQuestions.get(size - 1).answer4); //sets options on display
            this.allAnsweredQuestions.add(multipleAnswerQuestions.get(size - 1)); //add questions to answered questions arrayList

            if (multipleAnswerQuestions.get(size - 1).correct1.equals("correct")) {
                this.userAnswerCheck.add(this.multipleAnswerQuestions.get(size - 1).answer1);
            } else if (multipleAnswerQuestions.get(size - 1).correct2.equals("correct")) {
                this.userAnswerCheck.add(this.multipleAnswerQuestions.get(size - 1).answer2);
            } else if (multipleAnswerQuestions.get(size - 1).correct3.equals("correct")) {
                this.userAnswerCheck.add(this.multipleAnswerQuestions.get(size - 1).answer3);
            } else if (multipleAnswerQuestions.get(size - 1).correct4.equals("correct")) {
                this.userAnswerCheck.add(this.multipleAnswerQuestions.get(size - 1).answer4);
            }
            pageNumber += 1;
            questionNumberLabel.setText(pageNumber + "/" + this.numOfQuestions + "");

            AButton.setOnAction(e -> {
                if (multipleAnswerQuestions.get(size - 1).correct1.equals("correct")) {
                    this.numCorrect++; //increase score count
                    this.correctQuestions.add(multipleAnswerQuestions.get(size - 1)); //add correct questions to correctQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer1); //add user answers userAnswers arrayList
                } else {
                    this.numIncorrect++; //increase score count
                    this.incorrectQuestions.add(multipleAnswerQuestions.get(size - 1)); //add incorrect questions to incorrectQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer1); //add user answers userAnswers arrayList
                }
                int m = size - 1;
                try {
                    launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                            this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
                } catch (IOException ex) {
                    Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            BButton.setOnAction(e -> {
                if (multipleAnswerQuestions.get(size - 1).correct2.equals("correct")) {
                    this.numCorrect++; //increase score count
                    this.correctQuestions.add(multipleAnswerQuestions.get(size - 1)); //add correct questions to correctQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer2); //add user answers userAnswers arrayList
                } else {
                    this.numIncorrect++; //increase score count
                    this.incorrectQuestions.add(multipleAnswerQuestions.get(size - 1)); //add incorrect questions to incorrectQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer2); //add user answers userAnswers arrayList
                }
                int m = size - 1;
                try {
                    launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                            this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
                } catch (IOException ex) {
                    Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            CButton.setOnAction(e -> {
                if (multipleAnswerQuestions.get(size - 1).correct3.equals("correct")) {
                    this.numCorrect++; //increase score count
                    this.correctQuestions.add(multipleAnswerQuestions.get(size - 1)); //add correct questions to correctQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer3); //add user answers userAnswers arrayList
                } else {
                    this.numIncorrect++; //increase score count
                    this.incorrectQuestions.add(multipleAnswerQuestions.get(size - 1)); //add incorrect questions to incorrectQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer3); //add user answers userAnswers arrayList
                }
                int m = size - 1;
                try {
                    launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                            this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
                } catch (IOException ex) {
                    Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            DButton.setOnAction(e -> {
                if (multipleAnswerQuestions.get(size - 1).correct4.equals("correct")) {
                    this.numCorrect++;//increase score count
                    this.correctQuestions.add(multipleAnswerQuestions.get(size - 1)); //add correct questions to correctQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer4); //adds user answers to userAnswers arrayList

                } else {
                    this.numIncorrect++; //increase score count
                    this.incorrectQuestions.add(multipleAnswerQuestions.get(size - 1)); //add incorrect questions to incorrectQuestions arrayList
                    this.userAnswers.add(multipleAnswerQuestions.get(size - 1).answer4); //adds user answers to userAnswers arrayList
                }
                int m = size - 1;

                try {
                    launchMA(this.multipleChoiceQuestions, this.multipleAnswerQuestions,
                            this.trueFalseQuestions, this.fillInTheBlanksQuestions, m);
                } catch (IOException ex) {
                    Logger.getLogger(MCQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(MAQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } else {
            Stage stage = (Stage) AButton.getScene().getWindow();
            FXMLLoader f = new FXMLLoader(getClass().getResource("FIBQuestions.fxml"));
            root = f.load();
            FIBQuestionsController sc = f.<FIBQuestionsController>getController(); //creating fill in the blanks controller for next scene
            
            /* Sets variables to be used in fill in the blanks controller */
            
            sc.initID(this.userId);
            sc.setNumOfQuestions(this.numOfQuestions);
            sc.setUserAnswerCheck(this.userAnswerCheck);
            sc.setCorrect(this.numCorrect);
            sc.setIncorrect(this.numIncorrect);
            sc.setCorrectQuestions(this.correctQuestions);
            sc.setIncorrectQuestions(this.incorrectQuestions);
            sc.setUserAnswers(this.userAnswers);
            sc.setPageNumber(this.pageNumber);
            sc.startTimer(secs);
            sc.setAllAnsweredQuestions(this.allAnsweredQuestions);
            sc.launchFIB(this.multipleChoiceQuestions, this.multipleAnswerQuestions, this.trueFalseQuestions,
                    this.fillInTheBlanksQuestions, this.multipleAnswerQuestions.size());

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
        secs = remaining;
        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                secs -= 1;

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
