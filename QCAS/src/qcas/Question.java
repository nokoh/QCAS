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
public class Question {
    
      String identifier;
      String difficulty;
      String description; 
      
      
      
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
    
}
