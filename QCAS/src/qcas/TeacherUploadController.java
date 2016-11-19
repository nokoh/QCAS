/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;


import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nnamdi
 */
public class TeacherUploadController implements Initializable {
    
    
    Scene scene;
    Connection con;
    String fileName;
    
    @FXML
    private Button uploadButton; 
    
    @FXML 
    private TextField uploadField;
    
    @FXML
    private Label textLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void uploadFile() throws IOException, SQLException{
        
        Stage primaryStage = (Stage)uploadButton.getScene().getWindow();

        uploadButton.setOnAction(e -> {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println(file);
            this.fileName = file.toString().trim();
   
            try {
                connectToDatabase();
                readFile();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
}
    
public void connectToDatabase() throws SQLException{
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/QuizDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database
        
        try {
         this.con = DriverManager.getConnection(url, username, password);  
           if (this.con != null) {
               System.out.println("Conencted");
            }
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.con.close();//closes connection resource
        } // end of try-with-resources 
        
    }

private void addToDatabase(String[] questionArray) throws SQLException {
    
        switch (questionArray[0]) {
            case "MC":
            try {
            String set = "INSERT INTO QuizDB.MCQTable VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//insert statement to add the updated valuse in the database.
            PreparedStatement stmt2 = this.con.prepareStatement(set);
            
            String difficulty = questionArray[1];
            String description = questionArray[2]; 
            String answer1 = questionArray[3];
            String correct1 = questionArray[4];
            String answer2 = questionArray[5];
            String correct2 = questionArray[6];
            String answer3 = questionArray[7];
            String correct3 = questionArray[8];
            String answer4 = questionArray[9];
            String correct4 = questionArray[10];

            stmt2.setString(1, difficulty);
            stmt2.setString(2, description);
            stmt2.setString(3, answer1);
            stmt2.setString(4, correct1);
            stmt2.setString(5, answer2);
            stmt2.setString(6, correct2);
            stmt2.setString(7, answer3);
            stmt2.setString(8, correct3);
            stmt2.setString(9, answer4);
            stmt2.setString(10, correct4);
            stmt2.executeUpdate();//executes statement to insert values
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } // end of try-with-resources 
            break;
            
            case "MA":
            try {
            String set = "INSERT INTO QuizDB.MATable VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//insert statement to add the updated valuse in the database.
            PreparedStatement stmt2 = this.con.prepareStatement(set);
            
            String difficulty = questionArray[1];
            String description = questionArray[2]; 
            String answer1 = questionArray[3];
            String correct1 = questionArray[4];
            String answer2 = questionArray[5];
            String correct2 = questionArray[6];
            String answer3 = questionArray[7];
            String correct3 = questionArray[8];
            String answer4 = questionArray[9];
            String correct4 = questionArray[10];

            stmt2.setString(1, difficulty);
            stmt2.setString(2, description);
            stmt2.setString(3, answer1);
            stmt2.setString(4, correct1);
            stmt2.setString(5, answer2);
            stmt2.setString(6, correct2);
            stmt2.setString(7, answer3);
            stmt2.setString(8, correct3);
            stmt2.setString(9, answer4);
            stmt2.setString(10, correct4);
            stmt2.executeUpdate();//executes statement to insert values
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } // end of try-with-resources 
            break;
            
            case "TF":
                try {
            String set = "INSERT INTO QuizDB.TFTable VALUES(?, ?, ?)";//insert statement to add the updated valuse in the database.
            PreparedStatement stmt2 = this.con.prepareStatement(set);
            /* Loop takes the value of each line in the sailorsList array, 
            splits the values at every comma and adds the values to the database. */
            String difficulty = questionArray[1];
            String description = questionArray[2]; 
            String answer = questionArray[3];


            stmt2.setString(1, difficulty);
            stmt2.setString(2, description);
            stmt2.setString(3, answer);

            stmt2.executeUpdate();//executes statement to insert values
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } // end of try-with-resources 
            break;
            
            case "FIB":
            try {
            String set = "INSERT INTO QuizDB.FIBTable VALUES(?, ?, ?)";//insert statement to add the updated valuse in the database.
            PreparedStatement stmt2 = this.con.prepareStatement(set);

            String difficulty = questionArray[1];
            String description = questionArray[2]; 
            String answer = questionArray[3];

            stmt2.setString(1, difficulty);
            stmt2.setString(2, description);
            stmt2.setString(3, answer);

            stmt2.executeUpdate();//executes statement to insert values
        }
            
        catch (SQLException e) {
            
            System.out.println("SQLException: " + e);
            
        } // end of try-with-resources 
            break;
            
            default:
            break;
            
        }
}


/*Reads file and slices text*/
 public void readFile() throws FileNotFoundException, IOException, SQLException{
        int length = getFileLength();
        ArrayList <String[]> MCArray = new ArrayList<>();
        ArrayList <String[]> MAArray = new ArrayList<>();
        ArrayList <String[]> TFArray = new ArrayList<>();
        ArrayList <String[]> FIBArray = new ArrayList<>();
        CSVReader readerCSV = new CSVReader(new FileReader(this.fileName),',', '"', 0);
    
      //Read CSV line by line and use the string array as you want
      String[] nextLine;
      while ((nextLine = readerCSV.readNext()) != null) {
         if (nextLine != null) {
             switch (nextLine[0]) {
                 case "MC":
                     MCArray.add(nextLine);
                     break;
                 case "MA":
                     MAArray.add(nextLine);
                     break;
                 case "TF":
                     TFArray.add(nextLine);
                     break;
                 case "FIB":
                     FIBArray.add(nextLine);
                     break;
                 default:
                     break;
             }
         }
       }
      
      for(String [] quest:MCArray){
          String [] tester = new String[quest.length];
          tester = quest;
          addToDatabase(tester);
      }
      
      for(String [] quest:MAArray){
          String [] tester = new String[quest.length];
          tester = quest;
          addToDatabase(tester);
      }
      
      for(String [] quest:TFArray){
          String [] tester = new String[quest.length];
          tester = quest;
          addToDatabase(tester);
      }
      
      for(String [] quest:FIBArray){
          String [] tester = new String[quest.length];
          tester = quest;
          addToDatabase(tester);
      }
      
      
    }
    
    public int getFileLength() {
		int length = 0;
		BufferedReader reader = null;
		try {
	reader = new BufferedReader(new FileReader(new File("sample.txt")));//Reads text file and stores information in a buffer.

		} catch (FileNotFoundException err) {
			System.out.println("The system cannot find the inputfile specified");
			System.exit(-1); // exit if file not found
		}
		try {
		while (reader.readLine() != null) length++;
		reader.close();
		}
		catch (IOException err){
			System.out.println(err);
			System.exit(-1); // exit if file not found
		}
        return length;			
    }
}
