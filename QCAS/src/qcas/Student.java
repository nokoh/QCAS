/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

/**
 *
 * @author Nnamdi
 */
public class Student {
    
    String firstName;
    String lastName;
    String password;
    String studentID;
    String status;

    public Student(String firstName, String lastName, String password, String studentID) {
        
        this.status = "Student";
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.studentID = studentID;
        
    }
    
    
    
    
   
}
