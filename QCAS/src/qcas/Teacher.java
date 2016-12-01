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
    
    String firstName;
    String lastName;
    String password;
    int teacherID;
    String status;

    /**
     *
     * @param firstName
     * @param lastName
     * @param password
     * @param teacherID
     */
    public Teacher(String firstName, String lastName, String password, int teacherID) {
        this.status = "Teacher";
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.teacherID = teacherID;
    }
    
    
    


    

    
  
}
