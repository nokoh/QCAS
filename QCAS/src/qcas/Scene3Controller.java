package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author Shay
 * 
 *      // Scene 3//         Select Difficulty Screen for Student 
 * 
 * Student Will be allowed to select the difficulty level of questions.
 */
public class Scene3Controller implements Initializable {

    Scene scene;
    Connection con;
    String fileName;
    Stage homeStage;
    
    @FXML
    private Button importFileButton;   
    @FXML 
    private Button viewReportsButton;
    @FXML 
    private Button returnHomeButton;
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     **/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }    
        @FXML
    private void uploadFile() throws IOException, SQLException{
        Stage primaryStage = (Stage)importFileButton.getScene().getWindow();

        importFileButton.setOnAction(e -> {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        System.out.println(file);
            this.fileName = file.toString().trim();
   
            try {
                connectToDatabase();
                readFile();
            } catch (SQLException | IOException ex) {
                Logger.getLogger(Scene3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }
    
public void connectToDatabase() throws SQLException{
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/UserDB"; //creates network connection to database for application   
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
            String set = "INSERT INTO UserDB.MCQTable (difficulty, description, choice1, answer1, choice2, answer2, choice3, answer3, choice4, answer4)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";//insert statement to add the updated valuse in the database
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
         //   stmt2.setInt(11, 0);
            stmt2.executeUpdate();//executes statement to insert values
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } // end of try-with-resources 
            break;
            
            case "MA":
            try {
            String set = "INSERT INTO UserDB.MATable (difficulty, description, choice1, answer1, choice2, answer2, choice3, answer3, choice4, answer4)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";//insert statement to add the updated valuse in the database
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
         //   stmt2.setInt(11, 0);
            stmt2.executeUpdate();//executes statement to insert values
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } // end of try-with-resources 
            break;
            
            case "TF":
                try {
            String set = "INSERT INTO UserDB.TFTable (difficulty, description, answer)" +
                        "VALUES (?, ?, ?);";//insert statement to add the updated valuse in the database.
            PreparedStatement stmt2 = this.con.prepareStatement(set);
            /* Loop takes the value of each line in the sailorsList array, 
            splits the values at every comma and adds the values to the database. */
            String difficulty = questionArray[1];
            String description = questionArray[2]; 
            String answer = questionArray[3];


            stmt2.setString(1, difficulty);
            stmt2.setString(2, description);
            stmt2.setString(3, answer);
         //   stmt2.setInt(4, this.questionId++);

            stmt2.executeUpdate();//executes statement to insert values
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } // end of try-with-resources 
            break;
            
            case "FIB":
            try {
            String set = "INSERT INTO UserDB.FIBTable (difficulty, description, answer)" +
                        "VALUES (?, ?, ?);";//insert statement to add the updated valuse in the database.
            PreparedStatement stmt2 = this.con.prepareStatement(set);

            String difficulty = questionArray[1];
            String description = questionArray[2]; 
            String answer = questionArray[3];

            stmt2.setString(1, difficulty);
            stmt2.setString(2, description);
            stmt2.setString(3, answer);
         //   stmt2.setInt(4, this.questionId++);

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
    /**
 * The viewReports() method switches the scene to the Teacher Report Scene 
 * The Teacher Report Scene is **Scene Number 6**
     * @throws java.io.IOException
 */         
        public void viewReports() throws IOException{
            viewReportsButton.setOnAction(r ->{
                try {
                    Stage stage;
                    Parent root;
                    stage = (Stage)viewReportsButton.getScene().getWindow();
                    FXMLLoader ff = new FXMLLoader(getClass().getResource("scene6.fxml"));
                    root = ff.load();
                    Scene6Controller s6 = ff.<Scene6Controller>getController();
//                    s6.launchReports();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    try {
                        viewReportsButtonClicked();
                    } catch (IOException ex) {
                        Logger.getLogger(Scene3Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Scene3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }); {
        }         
        }
      /**
        *  The returnHome() method switches back to the login screen.
        * 
     * @throws java.io.IOException
        **/
        public void returnHome() throws IOException{
            returnHomeButton.setOnAction(h ->{
                
                try {
                    returnHomeButtonClicked();
                } catch (IOException ex) {
                    Logger.getLogger(Scene3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }                 
            } ); {
         }       
        }
        /**
         * 
         * 
     * @throws java.io.IOException
         */

    @FXML
        public void viewReportsButtonClicked() throws IOException {
            Parent scene3;
            Stage stage = (Stage) viewReportsButton.getScene().getWindow();
            FXMLLoader ff = new FXMLLoader(getClass().getResource("scene6.fxml"));
                scene3 = ff.load();
                Scene6Controller s6 = ff.<Scene6Controller>getController();
                s6.launchReports();
                scene = new Scene(scene3);
                stage.setScene(scene);
                stage.show();
    }
     /**
     * 
     * @throws java.io.IOException
      */
            @FXML
        public void returnHomeButtonClicked() throws IOException{
            FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
                Parent scene3 = f.load();
                LoginScreenController ls = f.<LoginScreenController>getController();
                
                Scene LoginScreen = new Scene(scene3);
                homeStage = (Stage) returnHomeButton.getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(LoginScreen);
                homeStage.show();
       
    }


}
