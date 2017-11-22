package ui.controller;

import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.chart.XYChart;
import application.MyDate;
import java.util.Arrays;

/**
 *
 * @author pompr_000
 */
public class HistoryViewController{
    @FXML
    private ComboBox<String> cb_month;

    @FXML
    private ComboBox<Integer> cb_year;

    @FXML
    private TextField tf_income;

    @FXML
    private TextField tf_outcome;

    @FXML
    private TextField tf_profit;

    @FXML
    private LineChart<Integer, Double> lc_graph;
    
    @FXML
    private NumberAxis lc_axis_value;
    private CategoryAxis lc_axis_date;
    
    //Database Summary cache
    private static XYChart.Series seriesIncome, seriesPayment;
    private static Double[][] dataCache = new Double[31][2];
    private static Integer oldestEntryYear = 2015;
    
    @FXML
    protected void initialize() {
        
        cb_year.getItems().setAll(Arrays.asList(MyDate.YEAR));
        cb_year.getSelectionModel().select(MyDate.getCurrentYearIndex());
        
        cb_month.getItems().setAll(Arrays.asList(MyDate.MONTH));
        cb_month.getSelectionModel().select(MyDate.getCurrentMonthIndex());
        
        lc_graph.setCreateSymbols(false);
        updateGraph();
        
    }
    
    @FXML
    private void updateGraph(){
        
        int year, maxDay = 30;
        double income = 0, payment = 0;
        year = cb_year.getValue();
        switch(cb_month.getValue()){
            case "February":
                if(year % 4 == 0)
                    maxDay = 29;
                else
                    maxDay = 28;
                break;
            case "January":
            case "March":
            case "May":
            case "July":
            case "August":
            case "October":
            case "December":
                maxDay = 31;
                break;
        }
        //refresh data in series
        fetchData();
        
        lc_graph.setData(FXCollections.observableArrayList());
        
        seriesIncome = new XYChart.Series();
        seriesIncome.setName("Income");
        
        seriesPayment = new XYChart.Series();
        seriesPayment.setName("Payment");
        
        for(int i = 0; i < maxDay; i++){
            seriesIncome.getData().add(new XYChart.Data(Integer.toString(i + 1), dataCache[i][0]));
            seriesPayment.getData().add(new XYChart.Data(Integer.toString(i + 1), dataCache[i][1]));
            
            income += dataCache[i][0];
            payment += dataCache[i][1];
        }
        
        tf_income.setText(Double.toString(income));
        tf_outcome.setText(Double.toString(payment));
        tf_profit.setText(Double.toString(income - payment));
        lc_graph.getData().add(seriesIncome);
        lc_graph.getData().add(seriesPayment);
        
        System.out.println("boop");
    }
    
    private void fetchData(){
        //dummy data
        for (int i = 0; i < 31; i++){
            dataCache[i][0] = 100.0 + 100.0 * i;
            dataCache[i][1] = 50.0 * i;
        }
    }
}

