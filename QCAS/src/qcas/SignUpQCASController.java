/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Text errorMessageLabel;
    @FXML
    private Stage homeStage;

    
    
    
    
    
    
    
    
    
    
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
     *  The returnHome method is an additional handler to the returnHomeButtonClicker() MEthod.
     * @throws IOException 
     */
     public void returnHome() throws IOException {
        returnHomeButton.setOnAction(h -> {
            try {
                returnHomeButtonClicked();
            } catch (IOException ex) {
                Logger.getLogger(SignUpQCASController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        {
        }
    }
    /**
     * Returns the User to Login Screen after the user clicks on the button.
     * @throws java.io.IOException
     */
    @FXML
    public void returnHomeButtonClicked() throws IOException {
        FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        Parent signUp = f.load();
        LoginScreenController ls = f.<LoginScreenController>getController();

        Scene LoginScreen = new Scene(signUp);
        homeStage = (Stage) returnHomeButton.getScene().getWindow();
        homeStage.hide();
        homeStage.setScene(LoginScreen);
        homeStage.show();

    }
}