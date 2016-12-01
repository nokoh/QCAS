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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class SignUpQCASController implements Initializable {

    Connection connection;
    String userId;
    String userPassword;
    Stage homeStage;

    @FXML
    private Button returnHomeButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField idNumberField;
    @FXML
    private ComboBox statusSelector;
    @FXML
    private Button signUpButton;
    @FXML
    private Label errorMessageLabel;


    private ObservableList statusList = FXCollections.observableArrayList();

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
     *
     * @throws SQLException
     * @throws IOException
     */
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

        statusList.add("Student");
        statusList.add("Teacher");
        signUpButton.setOnAction(e -> {
            try {
                userId = idNumberField.getText();
                //     userPassword = passwordField.getText();

                String loginQuery = "Select userid from Users WHERE userid = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);

                preparedStatement.setString(1, userId);
                ResultSet rset = preparedStatement.executeQuery();
                while(rset.next()){
                if (userId == rset.getString(1)) {
                    errorMessageLabel.setText(" User already exists in system, proceed to login page. ");

                } else {
                    firstNameField.getText();
                    lastNameField.getText();
                    idNumberField.getText();
                    
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
            } catch (SQLException ex) {
                Logger.getLogger(SignUpQCASController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SignUpQCASController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

}
