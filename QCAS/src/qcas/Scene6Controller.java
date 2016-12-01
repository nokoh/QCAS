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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

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
    private AnchorPane teacherDashboardPane;

    private ObservableList monthList = FXCollections.observableArrayList();
    private ObservableList yearList = FXCollections.observableArrayList();

    @FXML
    private BarChart<String, Integer> barChartTeacher;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    PieChart PieChartEasy;

    @FXML
    PieChart PieChartMedium;

    @FXML
    PieChart PieChartHard;

    @FXML
    private Label totalNumberQuestions;
    @FXML
    private Label totalNumberQuizzesMonth;
    @FXML
    private Label totalNumberQuestionsMonth;
    @FXML
    private ComboBox yearSelector;
    @FXML
    private Label avgQuizScoreLM;
    @FXML
    private Label avgEasyScoreLM;
    @FXML
    private Label avgMediumScoreLM;
    @FXML
    private Label avgHardScoreLM;
    @FXML
    private Label avgQuizScoreLQ;
    @FXML
    private Label avgEasyScoreLQ;
    @FXML
    private Label avgMediumScoreLQ;
    @FXML
    private Label avgHardScoreLQ;
    @FXML
    private Label avgQuizScoreLY;
    @FXML
    private Label avgEasyScoreLY;
    @FXML
    private Label avgMediumScoreLY;
    @FXML
    private Label avgHardScoreLY;

    /**
     * Method To Initialise Teacher Dashboard
     * Displays all charts and tables used to provide information about quizzes taken.
     * @param ID
     * @throws SQLException
     */

    public void launchReports(String ID) throws SQLException {

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
        monthList.add("January");
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
        yearList.add("2015");
        yearList.add("2016");

        displayTableData();
        drawCharts("November");
        drawLineChart("2016");
        countNumberOfQuizzes("November");
        countNoOfQuizzes();
        displayTables();

    }

    /**
     * Draw carts method is used for displaying different charts, Pie and Bar chart 
     * Draws all charts in dashboard for a given month
     * @param month
     * @throws SQLException
     */

    public void drawCharts(String month) throws SQLException {

        drawPieChart(month);
        drawBarChart(month);

        countNumberOfQuizzes(month);

        yearSelector.setItems(yearList); //selector list for different years
        yearSelector.setOnAction(e -> {
            if (yearSelector.getSelectionModel().getSelectedItem().equals("2015")) {
                try {
                    drawLineChart("2015");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (yearSelector.getSelectionModel().getSelectedItem().equals("2016")) {
                try {
                    drawLineChart("2016");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        monthSelector.setItems(monthList); //selector list for different months
        
        /* Event handling for different months */
        monthSelector.setOnAction(e -> {

            if (monthSelector.getSelectionModel().getSelectedItem().equals("January")) {
                try {
                    drawCharts("January");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("February")) {
                try {
                    drawCharts("February");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("March")) {
                try {
                    drawCharts("March");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("April")) {
                try {
                    drawCharts("April");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("May")) {
                try {
                    drawCharts("May");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("June")) {
                try {
                    drawCharts("June");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("July")) {
                try {
                    drawCharts("July");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("August")) {
                try {
                    drawCharts("August");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("September")) {
                try {
                    drawCharts("September");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("October")) {
                try {
                    drawCharts("October");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("November")) {
                try {
                    drawCharts("November");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (monthSelector.getSelectionModel().getSelectedItem().equals("December")) {
                try {
                    drawCharts("December");
                } catch (SQLException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    /**
     * Method to convert month integer to string representation of month.
     * @param year
     * @return
     */
    public String setMonth(int monthNum) {
        String yearName = "";
        if (monthNum == 1) {
            yearName = "January";
        } else if (monthNum == 2) {
            yearName = "February";
        } else if (monthNum == 3) {
            yearName = "March";
        } else if (monthNum == 4) {
            yearName = "April";
        } else if (monthNum == 5) {
            yearName = "May";
        } else if (monthNum == 6) {
            yearName = "June";
        } else if (monthNum == 7) {
            yearName = "July";
        } else if (monthNum == 8) {
            yearName = "August";
        } else if (monthNum == 9) {
            yearName = "September";
        } else if (monthNum == 10) {
            yearName = "October";
        } else if (monthNum == 11) {
            yearName = "November";
        } else if (monthNum == 12) {
            yearName = "December";
        }

        return yearName;
    }

    /**
     * DrawLineChart method is used for displaying Line charts based on the 
     * month and year the test was taken.
     * @param year
     * @throws SQLException
     */
    public void drawLineChart(String year) throws SQLException {

        int yearNum = Integer.parseInt(year);

        TestsLineChart.getData().clear();
        TestsLineChart.setTitle("Total Tests Passed / Failed Since: " + year);
        XYChart.Series series = new XYChart.Series();
        series.setName("Students Failing " + year);
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Students Passing " + year);
        int size = 12;

        String tableCount = "select substring(examDate,1,4), substring(examDate,6,2) as month, count(case when score >= 59 then score else NULL end) as pass, count(case when  score < 59 then score else NULL end) as fail from UserDB.vw_examResult where substring(examDate,1,4) = ? group by substring(examDate,1,4), substring(examDate,6,2)";
        PreparedStatement stmt = this.connection.prepareStatement(tableCount);
        stmt.setInt(1, yearNum); //applies random variable to select statement.
        ResultSet lineChartRS = stmt.executeQuery();//executes statement and returns value to resultset variable
        while (lineChartRS.next()) {
            series2.getData().add(new XYChart.Data(setMonth(Integer.parseInt(lineChartRS.getString(2))), Integer.parseInt(lineChartRS.getString(3))));
            series.getData().add(new XYChart.Data(setMonth(Integer.parseInt(lineChartRS.getString(2))), Integer.parseInt(lineChartRS.getString(4))));
        }
        TestsLineChart.getData().addAll(series, series2);
    }



    /**
     *Dashboard draw pie charts
     *Checks difficulty of returned results and sums total number of correct and incorrect answers
     * @param month
     * @throws SQLException
     */

    public void drawPieChart(String month) throws SQLException {
        PieChartEasy.getData().clear();
        PieChartMedium.getData().clear();
        PieChartHard.getData().clear();
        int monthNum = monthNumberConversion(month);

        int totalNumberOfQuestions = 0;
        int eNumCorrect = 0;
        int eNumInCorrect = 0;
        int mNumCorrect = 0;
        int mNumInCorrect = 0;
        int hNumCorrect = 0;
        int hNumInCorrect = 0;
        String tableCount = "SELECT status, answercheck, count(answercheck) as noQuestion FROM UserDB.ExamTable WHERE substring(examDate,6,2) = ? group by status, answercheck";
        PreparedStatement stmt = this.connection.prepareStatement(tableCount);
        stmt.setInt(1, monthNum); //applies random variable to select statement.
        ResultSet pieChartRS = stmt.executeQuery();//executes statement and returns value to resultset variable
        
        while (pieChartRS.next()) {
            if (pieChartRS.getString(1).equalsIgnoreCase("e") && pieChartRS.getString(2).equalsIgnoreCase("correct")) {
                totalNumberOfQuestions += Integer.parseInt(pieChartRS.getString(3));
                eNumCorrect = Integer.parseInt(pieChartRS.getString(3));
            } else if (pieChartRS.getString(1).equalsIgnoreCase("e") && pieChartRS.getString(2).equalsIgnoreCase("incorrect")) {
                totalNumberOfQuestions += Integer.parseInt(pieChartRS.getString(3));
                eNumInCorrect = Integer.parseInt(pieChartRS.getString(3));
            } else if (pieChartRS.getString(1).equalsIgnoreCase("m") && pieChartRS.getString(2).equalsIgnoreCase("correct")) {
                totalNumberOfQuestions += Integer.parseInt(pieChartRS.getString(3));
                mNumCorrect = Integer.parseInt(pieChartRS.getString(3));
            } else if (pieChartRS.getString(1).equalsIgnoreCase("m") && pieChartRS.getString(2).equalsIgnoreCase("incorrect")) {
                totalNumberOfQuestions += Integer.parseInt(pieChartRS.getString(3));
                mNumInCorrect = Integer.parseInt(pieChartRS.getString(3));
            } else if (pieChartRS.getString(1).equalsIgnoreCase("h") && pieChartRS.getString(2).equalsIgnoreCase("correct")) {
                totalNumberOfQuestions += Integer.parseInt(pieChartRS.getString(3));
                hNumCorrect = Integer.parseInt(pieChartRS.getString(3));
            } else if (pieChartRS.getString(1).equalsIgnoreCase("h") && pieChartRS.getString(2).equalsIgnoreCase("incorrect")) {
                totalNumberOfQuestions += Integer.parseInt(pieChartRS.getString(3));
                hNumInCorrect = Integer.parseInt(pieChartRS.getString(3));
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Correct", eNumCorrect),
                new PieChart.Data("Incorrect", eNumInCorrect));

        PieChartEasy.setTitle("Easy Questions");
        PieChartEasy.setData(pieChartData);

        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                new PieChart.Data("Correct", mNumCorrect),
                new PieChart.Data("Incorrect", mNumInCorrect));

        PieChartMedium.setTitle("Medium Questions");
        PieChartMedium.setData(pieChartData2);

        ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                new PieChart.Data("Correct", hNumCorrect),
                new PieChart.Data("Incorrect", hNumInCorrect));

        PieChartHard.setTitle("Hard Questions");
        PieChartHard.setData(pieChartData3);

    }

    /**
     * Method to count number of questions attempted in quiz system 
     * @throws SQLException
     */
    public void countNoOfQuizzes() throws SQLException {
        int totalNumberOfQuestions = 0;
        ResultSet pieChartRS = connection.createStatement().executeQuery("SELECT status, answercheck, count(answercheck) FROM UserDB.ExamTable group by status, answercheck");
        while (pieChartRS.next()) {
            totalNumberOfQuestions += Integer.parseInt(pieChartRS.getString(3));
        }

        totalNumberQuestions.setText(totalNumberOfQuestions + "");
    }

    

    /**
     * Takes month as string and returns integer representation of month.
     * @param monthName
     * @return
     */
    public int monthNumberConversion(String monthName) {
        int monthValue = 0;
        if (monthName.equalsIgnoreCase("January")) {
            monthValue = 1;
        } else if (monthName.equalsIgnoreCase("February")) {
            monthValue = 2;
        } else if (monthName.equalsIgnoreCase("March")) {
            monthValue = 3;
        } else if (monthName.equalsIgnoreCase("April")) {
            monthValue = 4;
        } else if (monthName.equalsIgnoreCase("May")) {
            monthValue = 5;
        } else if (monthName.equalsIgnoreCase("June")) {
            monthValue = 6;
        } else if (monthName.equalsIgnoreCase("July")) {
            monthValue = 7;
        } else if (monthName.equalsIgnoreCase("August")) {
            monthValue = 8;
        } else if (monthName.equalsIgnoreCase("September")) {
            monthValue = 9;
        } else if (monthName.equalsIgnoreCase("October")) {
            monthValue = 10;
        } else if (monthName.equalsIgnoreCase("November")) {
            monthValue = 11;
        } else if (monthName.equalsIgnoreCase("December")) {
            monthValue = 12;
        }
        return monthValue;
    }

    /**
     * Counts total number of quizzes attempted in system
     * Counts number of quizzes attempted in a month
     * Counts number of questions attempted in a month
     * @param month
     * @throws SQLException
     */
    public void countNumberOfQuizzes(String month) throws SQLException {
        int monthNum = monthNumberConversion(month);
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        String tableCount = "SELECT examDate, count(examID), sum(score), sum(score)/count(examID) FROM UserDB.vw_examResult WHERE substring(examDate,6,2) = ? group by examDate;";
        PreparedStatement stmt = this.connection.prepareStatement(tableCount);
        stmt.setInt(1, monthNum); 
        ResultSet dbQuery1 = stmt.executeQuery();
        ResultSet dbQuery2 = connection.createStatement().executeQuery("SELECT examDate, count(examID), sum(score), sum(score)/count(examID) FROM UserDB.vw_examResult group by examDate;");
        while (dbQuery1.next()) {
            count2 += (Integer.parseInt(dbQuery1.getString(2)));
        }
        while (dbQuery2.next()) {
            count += (Integer.parseInt(dbQuery2.getString(2)));
        }
        String tableCount2 = "select substring(examDate,6,2) as month, count(question) from UserDB.ExamTable WHERE substring(examDate,1,4) = 2016 and substring(examDate,6,2) = ? group by substring(examDate,6,2);";
        PreparedStatement stmt2 = this.connection.prepareStatement(tableCount2);
        stmt2.setInt(1, monthNum);
        ResultSet dbQuery3 = stmt2.executeQuery();
        while (dbQuery3.next()) {
            count3 = (Integer.parseInt(dbQuery3.getString(2)));
        }

        totalNumberQuestionsMonth.setText(count3 + "");
        totalNumberQuizzes.setText(count + "");
        totalNumberQuizzesMonth.setText(count2 + "");
    }

    /**
     * Draws Bar chart representation for a given month
     * @param month
     * @throws SQLException
     */

    public void drawBarChart(String month) throws SQLException {

        int monthNum = monthNumberConversion(month);
        barChartTeacher.getData().clear();

        String tableCount = "SELECT examDate, count(examID), sum(score), sum(score)/count(examID) FROM UserDB.vw_examResult WHERE substring(examDate,6,2) = ? group by examDate;";
        PreparedStatement stmt = this.connection.prepareStatement(tableCount);
        stmt.setInt(1, monthNum); //applies random variable to select statement.
        ResultSet dbQuery1 = stmt.executeQuery();

        xAxis.setLabel("Questions Difficulty");
        yAxis.setLabel("Average Score");

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        barChartTeacher.setTitle("Average Student Scores Per Question Difficulty");
        String tableCount2 = "select status, month, score from UserDB.vw_scoreStatus where month = ?";
        PreparedStatement stmt2 = this.connection.prepareStatement(tableCount2);
        stmt2.setInt(1, monthNum);
        ResultSet levelOfDifficulty = stmt2.executeQuery();

        while (levelOfDifficulty.next()) {
            if (levelOfDifficulty.getString(1).equalsIgnoreCase("e")) {
                series1.getData().add(new XYChart.Data("Easy Questions", Double.parseDouble(levelOfDifficulty.getString(2))));
                series1.setName("Easy");
            } else if (levelOfDifficulty.getString(1).equalsIgnoreCase("m")) {
                series2.getData().add(new XYChart.Data("Medium Questions", Double.parseDouble(levelOfDifficulty.getString(2))));
                series2.setName("Medium");
            } else if (levelOfDifficulty.getString(1).equalsIgnoreCase("h")) {
                series3.getData().add(new XYChart.Data("Hard Questions", Double.parseDouble(levelOfDifficulty.getString(2))));
                series3.setName("Hard");
            }
        }

        barChartTeacher.getData().addAll(series1, series2, series3);

    }

    /**
     * Displays data for tables in Report
     * @throws SQLException
     */

    public void displayTableData() throws SQLException {
        ResultSet passMonthNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalpass  FROM UserDB.vw_examResult where (score >= 59) and (substring(examDate,6,2) = 10);");
        while (passMonthNum.next()) {
            passingLastMonth.setText(passMonthNum.getString(2));
        }
        ResultSet failMonthNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalfail  FROM UserDB.vw_examResult where (score < 59) and (substring(examDate,6,2) = 10);");
        while (failMonthNum.next()) {
            nonPassingLastMonth.setText(failMonthNum.getString(2));
        }
        ResultSet passQuarterNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalpass  FROM UserDB.vw_examResult where (score >= 59) and (substring(examDate,6,2) between 7 and 9);");
        while (passQuarterNum.next()) {
            passingLastQuarter.setText(passQuarterNum.getString(2));
        }
        ResultSet failQuarterNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalfail  FROM UserDB.vw_examResult where (score < 59) and (substring(examDate,6,2) between 7 and 9);");
        while (failQuarterNum.next()) {
            nonPassingLastQuarter.setText(failQuarterNum.getString(2));
        }
        ResultSet passYearNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalpass  FROM UserDB.vw_examResult where (score >= 59) and (substring(examDate,1,4) = 2015);");
        while (passYearNum.next()) {
            passingLastYear.setText(passYearNum.getString(2));
        }
        ResultSet failYearNum = connection.createStatement().executeQuery("SELECT substring(examDate,6,2) as month, count(examID) as totalfail  FROM UserDB.vw_examResult where (score < 59) and (substring(examDate,1,4) = 2015);");
        while (failYearNum.next()) {
            nonPassingLastYear.setText(failYearNum.getString(2));
        }
    }

    /**
     * The returnHome() method switches back to the login screen.
     *
     * @throws java.io.IOException
     *
     */
    public void returnHome() throws IOException {
        returnHomeButton.setOnAction(h -> {

            try {
                returnHomeButtonClicked();
            } catch (IOException ex) {
                Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        {
        }
    }

    /**
     * 
     * @throws java.io.IOException
     */
    @FXML
    public void returnHomeButtonClicked() throws IOException {
        FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
        Parent scene6 = f.load();
        LoginScreenController ls = f.<LoginScreenController>getController();

        Scene LoginScreen = new Scene(scene6);
        homeStage = (Stage) returnHomeButton.getScene().getWindow();
        homeStage.hide();
        homeStage.setScene(LoginScreen);
        homeStage.show();
    }

    /**
     * Connects to database and sets connection variable for retrieval of information.
     * @throws SQLException
     */
    public void connectToDatabase() throws SQLException {

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
     * Display table values for teacher dashboard
     * @throws SQLException
     */
    public void displayTables() throws SQLException {
        String avgScoreLM = "";
        String avgScoreLQ = "";
        String avgScoreLY = "";
        String testTakenLM = "";
        String testTakenLQ = "";
        String testTakenLY = "";

        ResultSet averages = connection.createStatement().executeQuery("select count(examID) as lastmonthcount from UserDB.vw_examResult where substring(examDate,6,2) = substring(current_date,6,2)-1 and substring(examDate,1,4) = substring(current_date,1,4);");
        while (averages.next()) {
            testTakenLM = averages.getString(1); //sets number of tests taken in last month
        }
        ResultSet averages2 = connection.createStatement().executeQuery("select count(examID) as lastquartercount from UserDB.vw_examResult where substring(examDate,6,2) in ('07','08','09') and substring(examDate,1,4) = substring(current_date,1,4);");
        while (averages2.next()) {
            testTakenLQ = averages2.getString(1); //sets number of tests taken in last quarter
        }
        ResultSet averages3 = connection.createStatement().executeQuery("select count(examID) as lastyearcount from UserDB.vw_examResult where substring(examDate,1,4) = substring(current_date,1,4)-1;");
        while (averages3.next()) {
            testTakenLY = averages3.getString(1); //sets number of tests taken in last year
        }
        ResultSet averages4 = connection.createStatement().executeQuery("select AVG(score) as lastmonthavg from UserDB.vw_examResult where substring(examDate,6,2) = substring(current_date,6,2)-1 and substring(examDate,1,4) = substring(current_date,1,4);");
        while (averages4.next()) {
            avgScoreLM = averages4.getString(1); //sets average score of users taken in last month
        }
        ResultSet averages5 = connection.createStatement().executeQuery("select AVG(score) as lastquarteravg from UserDB.vw_examResult where substring(examDate,6,2) in ('07','08','09') and substring(examDate,1,4) = substring(current_date,1,4);");
        while (averages5.next()) {
            avgScoreLQ = averages5.getString(1); //sets average score of users taken in last quarter
        }
        ResultSet averages6 = connection.createStatement().executeQuery("select AVG(score) as lastyearavg from UserDB.vw_examResult where substring(examDate,1,4) = substring(current_date,1,4)-1;");
        while (averages6.next()) {
            avgScoreLY = averages6.getString(1); //sets average score of users taken in last year
        }

        testTakenLastMonth.setText(testTakenLM);
        testTakenLastQuarter.setText(testTakenLQ);
        testTakenLastYear.setText(testTakenLY);
        avgQuizScoreLM.setText(avgScoreLM);
        avgQuizScoreLQ.setText(avgScoreLQ);
        avgQuizScoreLY.setText(avgScoreLY);
    }

    /**
     * Exports PDF report of dashboard for a particular teacher.
     * @throws IOException
     * @throws DocumentException
     */
    @FXML
    public void exportToPdf() throws IOException, DocumentException {
        WritableImage image = teacherDashboardPane.snapshot(new SnapshotParameters(), null);
        File file = new File("Dashboard Report.png");

        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);   

        Document document = new Document();
        String input = "Dashboard Report.png";
        String output = "Dashboard Report.pdf";
        try {
            FileOutputStream fos = new FileOutputStream(output);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", byteOutput);

            com.itextpdf.text.Image graph;
            graph = com.itextpdf.text.Image.getInstance(byteOutput.toByteArray());
            graph.scaleToFit(500, 500);

            document.add((com.itextpdf.text.Element) graph);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        teacherDashboardPane.setVisible(true);

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
