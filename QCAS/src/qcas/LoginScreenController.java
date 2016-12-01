/*
 * Login Screen - Scene 1 // Login Screen // Home // 
 */
package qcas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * 
 * @author Shay
 *
 * Home Screen // Login Screen - Scene 1 // For Teacher and Student
 */
public class LoginScreenController implements Initializable {

    Connection connection = null;
    Scene scene;
    String userId = "";
    String userPassword = "";
    Stage homeStage;
    ImageView imageView;

    @FXML
    private Button login;

    @FXML
    private TextField userIDField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text loginMessage = new Text();

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
     * Verifies the credentials entered by a user and logs in the user to respective profile page.
     */
    @FXML
    public void loginVerify() {
        login.setOnAction(e -> {
            try {
                try {
                    authentication();
                } catch (IOException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Authenticates user information with variables stored in database.
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    public void authentication() throws SQLException, IOException {
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
        } // end of try-with-resource

        userId = userIDField.getText(); //
        userPassword = passwordField.getText();

        String loginQuery = "Select userid, Status from Users WHERE userid = ? and password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);

        preparedStatement.setString(1, userId);
        preparedStatement.setString(2, userPassword);
        ResultSet rset = preparedStatement.executeQuery();

        if (rset.next()) {
            if (rset.getString("Status").equals("Student")) {
                FXMLLoader f = new FXMLLoader(getClass().getResource("studentProfileScene.fxml"));
                Parent studentHomePage = f.load();
                StudentProfileSceneController sc = f.<StudentProfileSceneController>getController();
                sc.initID(rset.getString("userID"));
                Scene studentHomeScene = new Scene(studentHomePage);
                homeStage = (Stage) login.getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(studentHomeScene);
                homeStage.show();

            } else {
                FXMLLoader f2 = new FXMLLoader(getClass().getResource("scene3.fxml"));
                Parent teacherHomePage = f2.load();
                Scene3Controller sc3 = f2.<Scene3Controller>getController();
                sc3.setTeacherDetails(rset.getString("userID"));
                Scene teacherHomeScene = new Scene(teacherHomePage);
                homeStage = (Stage) login.getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(teacherHomeScene);
                homeStage.show();

            }
        } else {
            loginMessage.setText("User ID or Password is incorrect!");
            loginMessage.setFill(Color.FIREBRICK);

            userIDField.clear();
            passwordField.clear();
        }
    
    }
    
    public void signUp(){
        
        
    
    }


}     
