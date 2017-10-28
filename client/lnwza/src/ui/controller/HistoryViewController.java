package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 *
 * @author pompr_000
 */
public class HistoryViewController {
    @FXML
    private ComboBox<?> cb_month;

    @FXML
    private ComboBox<?> cb_year;

    @FXML
    private TextField tf_income;

    @FXML
    private TextField tf_outcome;

    @FXML
    private TextField tf_profit;

    @FXML
    private LineChart<?, ?> lc_graph;
}
