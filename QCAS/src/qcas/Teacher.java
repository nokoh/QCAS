/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Nnamdi
 */
public class Teacher {
    
    String name;
    String password;
    int teacherID;
    String fileName;
    Connection con;
    ArrayList fileData;


    /*This method will allow the teacher to import a text file to the system.*/
    public void uploadQuestions() throws FileNotFoundException, IOException{
        
        String nameOfFile;
        Scanner readInput = new Scanner(System.in);
        System.out.println("Please enter the name of the file to be uploaded. e.g. sample.txt");
        nameOfFile = readInput.next();
        File file = new java.io.File(nameOfFile);//assigns file name specified in constructor.
        
        /* Checks if file exists in project folder */
        if (file.exists()){
        }
        else {
	System.out.println("The system cannot find the inputfile specified, please check the CSV file");
	System.exit(-1);//exit if file not found
        }
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
           this.fileData  = new ArrayList<>();//uses the length of file to set the size of arrayList.
                for (int i = 0; i < getFileLength(); i++){
                    this.fileData.add(reader.readLine()); //adds each line in text file to quiz questions arrayList.
                }          
     
    }
    }
    
     /**
     * Method establishes connection to database, and sets the connection variable for connecting to the database.
     * @throws SQLException
     */
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
    
    public void readerTest() throws FileNotFoundException, IOException{
        int length = getFileLength();
        ArrayList <String[]> MCArray = new ArrayList<>();
        ArrayList <String[]> MAArray = new ArrayList<>();
        ArrayList <String[]> TFArray = new ArrayList<>();
        ArrayList <String[]> FIBArray = new ArrayList<>();
        CSVReader readerCSV = new CSVReader(new FileReader("sample.txt"),',', '"', 0);
    
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
      
      String identifier;
      String difficulty;
      String description; 
      String answer1;
      String correct1;
      String answer2;
      String correct2;
      String answer3;
      String correct3;
      String answer4;
      String correct4;
      
      

      for(String [] quest:MCArray){
          String [] tester = new String[quest.length];
          
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
    
    /* Method will add the questions from the text file to the database. */
    public void addToDatabase(){
    
    }
    
    /* Method will create reports to include number of tests taken during the last month, last quarter and over the last year. */
    public void viewTestsOverPeriod(){

    }
    
    /* Average student scores over last month, quarter and year. */
    public void viewStudentReports(){
    
    }
    
    /* Get information about students that have taken quizzes and print reports to pdf file. */
    public void printPDFReport(){
    
    }
    
    /* Return information on students passing or failing over different periods. */
    public void generateAverage(){
    
    }   
}
