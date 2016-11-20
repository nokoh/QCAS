/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nnamdi
 */
public class Question {
    
      String identifier;
      String difficulty;
      String description; 
      Connection con;
      
      public Question(){
          
      }
/*
    **
     * Create question method defines the methods for initialising variables of objects of the question class
     * Random Variable is used to select random question from quiztable in database.
     
    public void createQuestion(){     
        
        try {     
            ArrayList <String> listOfQuestions = new ArrayList<>();
            Random random = new Random();
            int selector = random.nextInt(getFileLength() - 1 + 1) + 1;//random variable used to generate random number

            String select = "SELECT * FROM APP.QUIZTABLE WHERE questionNumber = ?";//statement to select question from database.
            PreparedStatement stmt2 = this.con.prepareStatement(select);
            stmt2.setInt(1, selector); //applies random variable to select statement.
            ResultSet rs = stmt2.executeQuery();//executes statement and returns value to resultset variable.
            
            * Assigns each variable from column values to the different variables*
            while (rs.next()) {
            int num = rs.getInt("questionNumber");
            
            this.number = num + "";
            this.description = rs.getString("description");
            this.answerChoices[0] = rs.getString("choice1");
            this.answerChoices[1] = rs.getString("choice2");
            this.answerChoices[2] = rs.getString("choice3");
            this.answerChoices[3] = rs.getString("choice4");
            this.correctChoice = rs.getString("answer");
        }

        rs.close(); //closes resultset resource.
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        
    }      
*/      

public void createQuestion() throws SQLException{
    
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


}
      
class MultipleChoice extends Question {
    
      String answer1;
      String correct1;
      String answer2;
      String correct2;
      String answer3;
      String correct3;
      String answer4;
      String correct4;
      
}



class MultipleAnswer extends Question {
    String[]answerChoices;
    String[]correctAnswers;
}

class TrueFalse extends Question {
    String[]answerChoices;
    Boolean correctAnswer;
}

class FillInTheBlanks extends Question {
    String correctAnswer;
}
    

