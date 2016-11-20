/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 */
public class LoginScreenController implements Initializable {

    Connection connection;
    Scene scene;
   

    @FXML
    private Button teacherLogin;

    @FXML
    private TextField userIDField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Text loginMessage = new Text();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void verifyLogin() {

            Stage primaryStage = (Stage) teacherLogin.getScene().getWindow();
            teacherLogin.setOnAction(e ->{
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

    public void authentication() throws SQLException, IOException {
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/UserDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            if (this.connection != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.connection.close();//closes connection resource
        } // end of try-with-resources 

        String userID = userIDField.getText();
        String userPassword = passwordField.getText();
        
        
        String loginQuery = "select count(*) as counts from (select studentid as id, password from Students"
                + " union select teacherid as id, password from Teachers) as login where id = ? and password = ?";
                
        
        PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
        preparedStatement.setString(1, userID);
        preparedStatement.setString(2, userPassword);
 
        
        ResultSet rset = preparedStatement.executeQuery();
        
        if (rset.next()) {
            if (rset.getInt(1) > 0){
                loginMessage.setFill(Color.FIREBRICK);
                loginMessage.setText("Count: " + rset.getInt(1) + ". user authenticated.");
                
                Parent selectDifficulty = FXMLLoader.load(getClass().getResource("scene2.fxml"));
                Scene selectDifficultyScene = new Scene(selectDifficulty);
                Stage screenStage = (Stage)teacherLogin.getScene().getWindow();
                screenStage.hide();
                screenStage.setScene(selectDifficultyScene);
                screenStage.show();
            }
            else {
                loginMessage.setFill(Color.FIREBRICK);
                loginMessage.setText("User ID or Password is incorrect!");
                
            }
            
        }

    }

}
