/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Banu Prakash
 */
public class McqtestscreenController {

    public Button button;
     private Label top;
    @FXML
    private Label questionid;

    public void initialize(URL url, ResourceBundle rb) {
        top.setText("This is my new Text");
        questionid.setText("what is my name");
    } 

    public void setTopText(String text) {
        // set text from another class
        top.setText(text);
    } 
    @FXML
    public void handlebuttonclick()
    {
        System.out.println("yes clicked");
        button.setText("yes clicked");
    }
    
   
}
