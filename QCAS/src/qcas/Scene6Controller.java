package qcas;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Shay McDowell // Scene 6 // Teacher Report Scene
 */
public class Scene6Controller implements Initializable {

    
    Scene scene; 
    Stage homeStage;
    String userId;
    int examNumber;
    Connection connection;
    
    @FXML 
    private Button printToPDFButton;
    @FXML 
    private Button returnHomeButton;
    @FXML
    private Label TeacherIDLabel;
    @FXML 
    private Label TeacherNameLabel;
    @FXML
    private LineChart<String, Integer> TestsLineChart;
    @FXML
    private NumberAxis numberOfTests;
    @FXML
    private CategoryAxis timePeriod;
    @FXML
    private Label testTakenLastYear;
    @FXML
    private Label testTakenLastMonth;
    @FXML
    private Label testTakenLastQuarter;
    @FXML
    private Label totalNumberQuizzes;
    @FXML
    private Label passingLastMonth;
    @FXML
    private Label nonPassingLastMonth;
    @FXML
    private Label passingLastQuarter;
    @FXML
    private Label nonPassingLastQuarter;
    @FXML
    private Label passingLastYear;
    @FXML
    private Label nonPassingLastYear;
    @FXML
    private ComboBox monthSelector;
    
    @FXML
    private ObservableList monthList = FXCollections.observableArrayList();
    
    @FXML
    private BarChart<String, Integer> barChartTeacher;
    
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
    
    /* Method To Initialise Teacher Dashboard */
    public void launchReports(String ID) throws SQLException{
        
        
        
        
        TeacherIDLabel.setText(ID);
        connectToDatabase();
        String dbQuery = "Select firstname, lastname from Users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(dbQuery);
            preparedStatement.setString(1, ID);
            ResultSet rset = preparedStatement.executeQuery();
            if (rset.next()) {
                TeacherNameLabel.setText(rset.getString("firstname").toUpperCase() + " " + rset.getString("lastname").toUpperCase());
            }
            int count = 0;
            drawCharts("November");
            ResultSet dbQuery2 = connection.createStatement().executeQuery("SELECT examDate, count(examID), sum(score), sum(score)/count(examID) FROM UserDB.vw_examResult group by examDate;");
            while(dbQuery2.next()){
                count += (Integer.parseInt(dbQuery2.getString(2)));
            }
            ResultSet dbQuery3 = connection.createStatement().executeQuery("SELECT examDate, count(examID), sum(score), sum(score)/count(examID) FROM UserDB.vw_examResult group by examDate;");
            ArrayList <String> averageDateScore = new ArrayList();
            while(dbQuery3.next()){
                averageDateScore.add(dbQuery3.getString(4));
            }
            ResultSet passMonthNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalpass  FROM UserDB.vw_examResult where (score >= 59) and (substring(examDate,6,2) = 10);");
            while(passMonthNum.next()){
                passingLastMonth.setText(passMonthNum.getString(2));
            }
            ResultSet failMonthNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalfail  FROM UserDB.vw_examResult where (score < 59) and (substring(examDate,6,2) = 10);");
            while(failMonthNum.next()){
                nonPassingLastMonth.setText(failMonthNum.getString(2));
            }
            ResultSet passQuarterNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalpass  FROM UserDB.vw_examResult where (score >= 59) and (substring(examDate,6,2) between 7 and 9);");
            while(passQuarterNum.next()){
                passingLastQuarter.setText(passQuarterNum.getString(2));
            }
            ResultSet failQuarterNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalfail  FROM UserDB.vw_examResult where (score < 59) and (substring(examDate,6,2) between 7 and 9);");
            while(failQuarterNum.next()){
                nonPassingLastQuarter.setText(failQuarterNum.getString(2));
            }
            ResultSet passYearNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalpass  FROM UserDB.vw_examResult where (score >= 59) and (substring(examDate,1,4) = 2015);");
            while(passYearNum.next()){
                passingLastYear.setText(passYearNum.getString(2));
            }
            ResultSet failYearNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalfail  FROM UserDB.vw_examResult where (score < 59) and (substring(examDate,1,4) = 2015);");
            while(failYearNum.next()){
                nonPassingLastYear.setText(failYearNum.getString(2));
            }
            String correct = "Correct";
           String inCorrect = "Incorrect";
  
           xAxis.setLabel("Questions Difficulty");       
           yAxis.setLabel("Average Score");
            XYChart.Series series1 = new XYChart.Series();
            XYChart.Series series2 = new XYChart.Series();
            XYChart.Series series3 = new XYChart.Series();
            barChartTeacher.setTitle("Average Student Scores Per Question Difficulty");
            
            ResultSet levelOfDifficulty = connection.createStatement().executeQuery("select * from UserDB.vw_scoreStatus");
            while(levelOfDifficulty.next()){
                if(levelOfDifficulty.getString(1).equalsIgnoreCase("e")){
                    series1.getData().add(new XYChart.Data("Easy Questions", Double.parseDouble(levelOfDifficulty.getString(2))));
                    series1.setName("Easy");
                }
                else if(levelOfDifficulty.getString(1).equalsIgnoreCase("m")){
                    series2.getData().add(new XYChart.Data("Medium Questions", Double.parseDouble(levelOfDifficulty.getString(2))));
                    series2.setName("Medium");
                }
                else if(levelOfDifficulty.getString(1).equalsIgnoreCase("h")){
                    series3.getData().add(new XYChart.Data("Hard Questions", Double.parseDouble(levelOfDifficulty.getString(2))));
                    series3.setName("Hard");
                }
            }

           barChartTeacher.getData().addAll(series1, series2, series3);
            
           totalNumberQuizzes.setText(count+"");
            
    }
    
    public void drawCharts(String month){
    //defining the axes
        TestsLineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Students Passing " + month);
        int size = monthNumberConversion(month);
       
        for(int i = 0; i < size; i++){
            series.getData().add(new XYChart.Data(i+"", i));
        }
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Students Failing " + month);
        for(int i = 0; i < size; i++){
            series2.getData().add(new XYChart.Data(i+"", i++));
        }
        
           
        
        monthList.add("Januarary");
        monthList.add("February");
        monthList.add("March");
        monthList.add("April");
        monthList.add("May");
        monthList.add("June");
        monthList.add("July");
        monthList.add("August");
        monthList.add("September");
        monthList.add("October");
        monthList.add("November");
        monthList.add("December");
        
        monthSelector.setItems(monthList);
        monthSelector.setOnAction(e -> {
            
        if(monthSelector.getSelectionModel().getSelectedItem().equals("January")){
            
            drawCharts("January");
           
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("February")){
            drawCharts("February");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("March")){
            drawCharts("March");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("April")){
            drawCharts("April");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("May")){
           drawCharts("May");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("June")){
            drawCharts("June");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("July")){
            drawCharts("July");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("August")){
            drawCharts("August");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("September")){
            drawCharts("September");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("October")){
            drawCharts("October");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("November")){
            drawCharts("November");
        }
        else if(monthSelector.getSelectionModel().getSelectedItem().equals("December")){
            drawCharts("December");
        }
        });
        
        
  /*      series.getData().add(new XYChart.Data("1", 23));
        series.getData().add(new XYChart.Data("2", 14));
        series.getData().add(new XYChart.Data("3", 15));
        series.getData().add(new XYChart.Data("4", 24));
        series.getData().add(new XYChart.Data("5", 34));
        series.getData().add(new XYChart.Data("6", 36));
        series.getData().add(new XYChart.Data("7", 22));
        series.getData().add(new XYChart.Data("8", 45));
        series.getData().add(new XYChart.Data("9", 43));
        series.getData().add(new XYChart.Data("10", 17));
        series.getData().add(new XYChart.Data("11", 23));
        series.getData().add(new XYChart.Data("12", 14));
        series.getData().add(new XYChart.Data("13", 15));
        series.getData().add(new XYChart.Data("14", 24));
        series.getData().add(new XYChart.Data("15", 34));
        series.getData().add(new XYChart.Data("16", 36));
        series.getData().add(new XYChart.Data("17", 22));
        series.getData().add(new XYChart.Data("18", 45));
        series.getData().add(new XYChart.Data("19", 43));
        series.getData().add(new XYChart.Data("20", 17));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Students Failing" + month);
           
        series2.getData().add(new XYChart.Data("1", 13));
        series2.getData().add(new XYChart.Data("2", 20));
        series2.getData().add(new XYChart.Data("3", 1));
        series2.getData().add(new XYChart.Data("4", 12));
        series2.getData().add(new XYChart.Data("5", 4));
        series2.getData().add(new XYChart.Data("6", 20));
        series2.getData().add(new XYChart.Data("7", 56));
        series2.getData().add(new XYChart.Data("8", 10));
        series2.getData().add(new XYChart.Data("9", 10));
        series2.getData().add(new XYChart.Data("10", 5));
        series2.getData().add(new XYChart.Data("11", 9));
        series2.getData().add(new XYChart.Data("12", 20));
        series2.getData().add(new XYChart.Data("13", 30));
        series2.getData().add(new XYChart.Data("14", 2));
        series2.getData().add(new XYChart.Data("15", 13));
        series2.getData().add(new XYChart.Data("16", 0));
        series2.getData().add(new XYChart.Data("17", 22));
        series2.getData().add(new XYChart.Data("18", 45));
        series2.getData().add(new XYChart.Data("19", 43));
        series2.getData().add(new XYChart.Data("20", 17));
        */
        TestsLineChart.getData().addAll(series, series2);
        
    }
    
    
    public int getNumberofDaysInMonth(int Month){
        int iYear = 2016;
        int iMonth = Month; // 1 (months begin with 0)
        int iDay = 1;

        // Create a calendar object and set year and month
        Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);

        // Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        return daysInMonth;
    }
    
    public int monthNumberConversion(String monthName){
        int monthValue = 0;
        if(monthName.equalsIgnoreCase("January")){
            monthValue = 1;
        }else if(monthName.equalsIgnoreCase("February")){
            monthValue = 2;
        }else if(monthName.equalsIgnoreCase("March")){
            monthValue = 3;
        }else if(monthName.equalsIgnoreCase("April")){
            monthValue = 4;
        }else if(monthName.equalsIgnoreCase("May")){
            monthValue = 5;
        }else if(monthName.equalsIgnoreCase("June")){
            monthValue = 6;
        }else if(monthName.equalsIgnoreCase("July")){
            monthValue = 7;
        }else if(monthName.equalsIgnoreCase("August")){
            monthValue = 8;
        }else if(monthName.equalsIgnoreCase("September")){
            monthValue = 9;
        }else if(monthName.equalsIgnoreCase("October")){
            monthValue = 10;
        }else if(monthName.equalsIgnoreCase("November")){
            monthValue = 11;
        }else if(monthName.equalsIgnoreCase("December")){
            monthValue = 12;
        }
        return monthValue;
    }
    
   
     /**
     * printToPDF() prints a PDF of the Report if the Print To PDF Button is clicked
     * 
     */
//    
//    
//        @FXML
//    public  void printToPDF(){
//        PrinterJob printToPDF = PrinterJob.createPrinterJob();
//             if(printToPDF != null){
//                printToPDF.showPrintDialog(scene.getWindow()); // Window must be your main Stage
//                printToPDF.printPage(homeStage);
//                printToPDF.endJob();
 

        
    
    /**
        *  The returnHome() method switches back to the login screen.
        * 
     * @throws java.io.IOException
        **/
            @FXML
        public void returnHome() throws IOException{
            returnHomeButton.setOnAction(h ->{
                
                try {
                    returnHomeButtonClicked();
                } catch (IOException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }                 
            } ); {
         }       
        }
     /**
     * 
     * @throws java.io.IOException
      */
            @FXML
        public void returnHomeButtonClicked() throws IOException{
            FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
                Parent scene6 = f.load();
                LoginScreenController ls = f.<LoginScreenController>getController();
                
                Scene LoginScreen = new Scene(scene6);
                homeStage = (Stage) returnHomeButton.getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(LoginScreen);
                homeStage.show();
    }
        
        public void connectToDatabase() throws SQLException{
        
        String url = "jdbc:mysql://adelaide-mysql-qcas1.caswkasqdmel.ap-southeast-2.rds.amazonaws.com:3306/UserDB"; //creates network connection to database for application   
        String username = "qcastest";//username for accessing database
        String password = "qcastest";//password for accessing database

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            if (this.connection != null) {
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            this.connection.close();//closes connection resource
        } // end of try-with-resourc
        }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}