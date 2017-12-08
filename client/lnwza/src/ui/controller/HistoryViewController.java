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
import application.entity.Transaction;
import application.handler.TransactionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javafx.geometry.Side;

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
    private static double[][] dataCache = new double[31][2];
    private static Integer oldestEntryYear = 2015;
    
    @FXML
    protected void initialize() {
        
        cb_year.getItems().setAll(Arrays.asList(MyDate.YEAR));
        cb_year.getSelectionModel().select(MyDate.getCurrentYearIndex());
        
        cb_month.getItems().setAll(Arrays.asList(MyDate.MONTH));
        cb_month.getSelectionModel().select(MyDate.getCurrentMonthIndex());
        
        lc_graph.setLegendSide(Side.RIGHT);
        lc_graph.setCreateSymbols(false);
        updateGraph();
        
    }
    
    @FXML
    private void updateGraph(){
        double income = 0, payment = 0;
        int year = cb_year.getValue();
        int month = 0;
        for(int i = 0; i < 12; i++){
            if(cb_month.getValue() == MyDate.MONTH[i]){
                month = i + 1;
                break;
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        //refresh data in series
        fetchData(month, year);
        lc_graph.setData(FXCollections.observableArrayList());
        
        seriesIncome = new XYChart.Series();
        seriesIncome.setName("Income");
        
        seriesPayment = new XYChart.Series();
        seriesPayment.setName("Cost");
        
        for(int i = 0; i < maxDay; i++){
            seriesIncome.getData().add(new XYChart.Data(Integer.toString(i + 1), dataCache[i][0]));
            seriesPayment.getData().add(new XYChart.Data(Integer.toString(i + 1), -dataCache[i][1]));
            
            income += dataCache[i][0];
            payment += dataCache[i][1];
        }
        payment = Math.abs(payment);
        tf_income.setText(Double.toString(income));
        tf_outcome.setText(Double.toString(payment));
        tf_profit.setText(Double.toString(income - payment));
        lc_graph.getData().add(seriesIncome);
        lc_graph.getData().add(seriesPayment);
    }
    
    private void clearCache(){
        //clear out
        for (int i = 0; i < 31; i++){
            dataCache[i][0] = 0.0;
            dataCache[i][1] = 0.0;
        }
    }
    
    private void fetchData(int month, int year){
        clearCache();
        Calendar cal = Calendar.getInstance();
        int date;
        ArrayList<Transaction> transaction = TransactionHandler.getDataFromMonth(month, year);
        for(Transaction entry : transaction){
            cal.setTime(entry.getTranDate());
            date = cal.get(Calendar.DAY_OF_MONTH);
            if(entry.getAmount() >= 0)
                dataCache[date-1][0] += entry.getAmount();
            else
                dataCache[date-1][1] += entry.getAmount();
        }
    }
}

