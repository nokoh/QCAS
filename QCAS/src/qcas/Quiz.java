/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Nnamdi
 */
public class Quiz {
    
    int testCount;
    ArrayList<Question>Questions;
    String [] difficulty = {"Easy", "Medium", "Hard", "Mixed"};
    String type;
    Connection con;
    
    public Quiz(int noOfQuestions, String type){
        
        
    }

    public int getTestCount() {
        return testCount;
    }

    public void setTestCount(int testCount) {
        this.testCount = testCount;
    }

    public ArrayList<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(ArrayList<Question> Questions) {
        this.Questions = Questions;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    

}
