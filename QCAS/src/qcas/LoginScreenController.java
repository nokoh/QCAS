/*
 * Login Screen - Scene 1 // Login Screen // Home // 
 */
package qcas;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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

    @FXML
    public void authentication() throws SQLException, IOException {
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

        userId = userIDField.getText();
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
//                Parent studentHomePage = FXMLLoader.load(getClass().getResource("studentProfileScene.fxml"));
                Scene studentHomeScene = new Scene(studentHomePage);
                homeStage = (Stage) login.getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(studentHomeScene);
                homeStage.show();

            } else {
                Parent teacherHomePage = FXMLLoader.load(getClass().getResource("scene3.fxml"));
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

}
