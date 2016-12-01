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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * The Sign up Controller allows a new user to create a profile for the QCAS
 * program.
 *
 *
 * @author Nnamdi
 */
public class SignUpQCASController implements Initializable {

    Connection connection;
    String userId;
    String userPassword;
    Stage homeStage;
    String selected;
    String fName;
    String lName;
    String passw;
    String status;

    @FXML
    private Button returnHomeButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField idNumberField;
    @FXML
    private Button signUpButton;
    @FXML
    private Text errorMessageLabel;

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField staffRole;

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
     * Authenticate user in system and adds user credentials if user does not
     * already exist.
     */
    @FXML
    public void signUp(){
    signUpButton.setOnAction(e -> {
        
        this.fName = firstNameField.getText();
        this.lName = lastNameField.getText();
        this.userId = idNumberField.getText();
        this.passw = passwordField.getText();
        this.status = staffRole.getText();
        
        System.out.println(this.fName);
            try {
                connectToDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(SignUpQCASController.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            addToDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(SignUpQCASController.class.getName()).log(Level.SEVERE, null, ex);
            errorMessageLabel.setText("User already exists, please log in.");
        } catch (IOException ex) {
            Logger.getLogger(SignUpQCASController.class.getName()).log(Level.SEVERE, null, ex);
        }

        });
    }

    /**
     * Connect to database to verify credentials
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
        } // end of try-with-resource

    }

    /**
     * Add new user information to database
     * @throws SQLException
     * @throws IOException
     */
    public void addToDatabase() throws SQLException, IOException {
        connectToDatabase();

        System.out.println("pls enter");
        String check = "insert into Users (userid, firstName, lastname, password, status) VALUES (?,?,?,?,?);";
        PreparedStatement stmt = connection.prepareStatement(check);
        stmt.setString(1, userId);
        stmt.setString(2, fName);
        stmt.setString(3, lName);
        stmt.setString(4, passw);
        stmt.setString(5, status);
        stmt.executeUpdate();

        FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        Parent loginPage = f.load();
        LoginScreenController sc = f.<LoginScreenController>getController();
        Scene loginScene = new Scene(loginPage);
        homeStage = (Stage) signUpButton.getScene().getWindow();
        homeStage.hide();
        homeStage.setScene(loginScene);
        homeStage.show();
    }

    @FXML
    private void returnHomeButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        Parent loginPage = f.load();
        LoginScreenController sc = f.<LoginScreenController>getController();
        Scene loginScene = new Scene(loginPage);
        homeStage = (Stage) signUpButton.getScene().getWindow();
        homeStage.hide();
        homeStage.setScene(loginScene);
        homeStage.show();
    }
}
