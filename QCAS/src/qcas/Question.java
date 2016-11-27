/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Nnamdi
 */
public class Question {
    
      String difficulty;
      String description;
      int number;
      Connection con;

public void createQuestion() throws SQLException{   
}

  /**
     * Method establishes connection to database, and sets the connection variable for connecting to the database.
     * @throws SQLException
     */
    public void connectToDatabase() throws SQLException{
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/UserDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database
        
        try {
         this.con = DriverManager.getConnection(url, username, password);  
           if (this.con != null) {
             //  System.out.println("Conencted");
            }
           else{
               System.out.println("Not Conencted");
           }
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.con.close();//closes connection resource
        } // end of try-with-resources 
        
    }
    public void connectToLiveDatabase() throws SQLException{
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/LiveQuizDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database
        
        try {
         this.con = DriverManager.getConnection(url, username, password);  
           if (this.con != null) {
             //  System.out.println("Conencted");
            }
           else{
               System.out.println("Not Conencted");
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
 
      public MultipleChoice(String difficulty) throws SQLException{
          connectToDatabase();
        try {     
            Random random = new Random();
            String tableCount = "SELECT COUNT(?) FROM UserDB.MCQTable";
            PreparedStatement stmt = this.con.prepareStatement(tableCount);
            stmt.setString(1, "*"); //applies random variable to select statement.
            ResultSet countRS = stmt.executeQuery();//executes statement and returns value to resultset variable
            int count = 0;
            while(countRS.next()){
            count = countRS.getInt(1);
            }
            int selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number
            String select = "SELECT * FROM UserDB.MCQTable WHERE questionNumber = ?";//statement to select question from database.
            PreparedStatement stmt2 = this.con.prepareStatement(select);
         //   stmt2.setString(1, difficulty); //applies random variable to select statement.
            stmt2.setInt(1, selector);
            ResultSet rs = stmt2.executeQuery();//executes statement and returns value to resultset variable
            /* Assigns each variable from column values to the different variables*/
            while (rs.next()) {
            if(rs.getString("difficulty").equalsIgnoreCase(difficulty) == true){
            int num = rs.getInt("questionNumber");
            this.number = num;
            this.difficulty = rs.getString("difficulty");
            this.description = rs.getString("description");
            this.answer1 = rs.getString("choice1");
            this.correct1 = rs.getString("answer1");
            this.answer2 = rs.getString("choice2");
            this.correct2 = rs.getString("answer2");
            this.answer3 = rs.getString("choice3");
            this.correct3 = rs.getString("answer3");
            this.answer4 = rs.getString("choice4");
            this.correct4 = rs.getString("answer4");
                }
            else{
            selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number
            select = "SELECT * FROM UserDB.MCQTable WHERE questionNumber = ?";//statement to select question from database.
            stmt2 = this.con.prepareStatement(select);
            stmt2.setInt(1, selector);
            rs = stmt2.executeQuery();//executes statement and returns value to resultset variable
            }
            }
        rs.close(); //closes resultset resource.
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }        
      }
      
      @Override
      public void createQuestion() throws SQLException{
        
      }   
}

class MultipleAnswer extends Question {
    String answer1;
    String correct1;
    String answer2;
    String correct2;
    String answer3;
    String correct3;
    String answer4;
    String correct4;
    
    public MultipleAnswer(String difficulty) throws SQLException{
         connectToDatabase();
                 try {     
            ArrayList <String> listOfQuestions = new ArrayList<>();
            Random random = new Random();
            String tableCount = "SELECT COUNT(?) FROM UserDB.MATable";
            PreparedStatement stmt = this.con.prepareStatement(tableCount);
            stmt.setString(1, "*"); //applies random variable to select statement.
            ResultSet countRS = stmt.executeQuery();//executes statement and returns value to resultset variable
          int count = 0;
          while(countRS.next()){
            count = countRS.getInt(1);
          }
            int selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number

            String select = "SELECT * FROM UserDB.MATable WHERE questionNumber = ?";//statement to select question from database.
            PreparedStatement stmt2 = this.con.prepareStatement(select);
          //  stmt2.setString(1, difficulty); //applies random variable to select statement.
            stmt2.setInt(1, selector);
            ResultSet rs = stmt2.executeQuery();//executes statement and returns value to resultset variable.
            
            /* Assigns each variable from column values to the different variables*/
            while (rs.next()) {
            if(rs.getString("difficulty").equalsIgnoreCase(difficulty) == true){
            int num = rs.getInt("questionNumber");
            this.number = num;
            this.difficulty = rs.getString("difficulty");
            this.description = rs.getString("description");
            this.answer1 = rs.getString("choice1");
            this.correct1 = rs.getString("answer1");
            this.answer2 = rs.getString("choice2");
            this.correct2 = rs.getString("answer2");
            this.answer3 = rs.getString("choice3");
            this.correct3 = rs.getString("answer3");
            this.answer4 = rs.getString("choice4");
            this.correct4 = rs.getString("answer4");
            }
            else{
            selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number
            select = "SELECT * FROM UserDB.MATable WHERE questionNumber = ?";//statement to select question from database.
            stmt2 = this.con.prepareStatement(select);
            stmt2.setInt(1, selector);
            rs = stmt2.executeQuery();//executes statement and returns value to resultset variable
            }
        }
        rs.close(); //closes resultset resource.
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }
    
    
}

class TrueFalse extends Question {
    String correctAnswer;
    
    public TrueFalse(String difficulty) throws SQLException{
        connectToDatabase();
        try {     
           // ArrayList <String> listOfQuestions = new ArrayList<>();
            Random random = new Random();
            String tableCount = "SELECT COUNT(?) FROM UserDB.TFTable";
            PreparedStatement stmt = this.con.prepareStatement(tableCount);
            stmt.setString(1, "*"); //applies random variable to select statement.
            ResultSet countRS = stmt.executeQuery();//executes statement and returns value to resultset variable
          int count = 0;
          while(countRS.next()){
            count = countRS.getInt(1);
          }
            int selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number

            String select = "SELECT * FROM UserDB.TFTable WHERE questionNumber = ?";//statement to select question from database.
            PreparedStatement stmt2 = this.con.prepareStatement(select);
        //    stmt2.setString(1, difficulty); //applies random variable to select statement.
            stmt2.setInt(1, selector); //applies random variable to select statement.
          //  stmt2.setInt(2, count); //applies random variable to select statement.
            ResultSet rs = stmt2.executeQuery();//executes statement and returns value to resultset variable.
            
            /* Assigns each variable from column values to the different variables*/
            while (rs.next()) {
            if(rs.getString("difficulty").equalsIgnoreCase(difficulty) == true){
            int num = rs.getInt("questionNumber");
            this.number = num;
            this.difficulty = rs.getString("difficulty");
            this.description = rs.getString("description");
            this.correctAnswer = rs.getString("answer"); 
            }
            else{
            selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number
            select = "SELECT * FROM UserDB.TFTable WHERE questionNumber = ?";//statement to select question from database.
            stmt2 = this.con.prepareStatement(select);
            stmt2.setInt(1, selector);
            rs = stmt2.executeQuery();//executes statement and returns value to resultset variable
            }
            }
            
        rs.close(); //closes resultset resource.
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
          
       
    }
}

class FillInTheBlanks extends Question {
    String correctAnswer;
    
    public FillInTheBlanks(String difficulty) throws SQLException{
        connectToDatabase();
        try {     
           // ArrayList <String> listOfQuestions = new ArrayList<>();
            Random random = new Random();
            String tableCount = "SELECT COUNT(?) FROM UserDB.FIBTable";
            PreparedStatement stmt = this.con.prepareStatement(tableCount);
            stmt.setString(1, "*"); //applies random variable to select statement.
            ResultSet countRS = stmt.executeQuery();//executes statement and returns value to resultset variable
          int count = 0;
          while(countRS.next()){
            count = countRS.getInt(1);
          }
            int selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number

            String select = "SELECT * FROM UserDB.FIBTable WHERE questionNumber = ?";//statement to select question from database.
            PreparedStatement stmt2 = this.con.prepareStatement(select);
         //   stmt2.setString(1, difficulty); //applies random variable to select statement.
            stmt2.setInt(1, selector); //applies random variable to select statement.
        //    stmt2.setInt(2, count); //applies random variable to select statement.
            ResultSet rs = stmt2.executeQuery();//executes statement and returns value to resultset variable.
            
            /* Assigns each variable from column values to the different variables*/
            while (rs.next()) {
            if(rs.getString("difficulty").equalsIgnoreCase(difficulty) == true){
            int num = rs.getInt("questionNumber");
            this.number = num;
            this.difficulty = rs.getString("difficulty");
            this.description = rs.getString("description");
            this.correctAnswer = rs.getString("answer");
            }
            else{
            selector = random.nextInt(count - 1 + 1) + 1;//random variable used to generate random number
            select = "SELECT * FROM UserDB.FIBTable WHERE questionNumber = ?";//statement to select question from database.
            stmt2 = this.con.prepareStatement(select);
            stmt2.setInt(1, selector);
            rs = stmt2.executeQuery();//executes statement and returns value to resultset variable
            }
            
        }
        rs.close(); //closes resultset resource.
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
          
    }
}
    

