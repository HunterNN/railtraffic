package ch.railTraffic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class HeaderController implements Initializable{

	// SceneBuilder Attribute
	@FXML 
	public ComboBox<String> max_rows;
	@FXML
	public TableColumn column_time;
	@FXML 
	public TableColumn column_date;
	@FXML 
	public TableView<RailTrafficDatasetFields> table;
	@FXML 
	public TableColumn<RailTrafficDatasetFields, String> column_description;
	
	
	// ComboBox Attribute
	private String maxRowsValue = "10";
	private int maxRowsCreate = 210;
	
	// Date Time Zelle Formatierung
	private SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm");
	private SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
	
	// 10er Schritt für Anzahl Meldungen erstellen Observable List
	private ObservableList<String> obsListComboBox = FXCollections.observableList(this.createList());
	
	// Tabelle Observable List
	private ObservableList<RailTrafficDatasetFields> obsListTable = FXCollections.observableArrayList();
	
	// SBB API Daten
	private APIDatenSBB data;
	
	
	// *** Initialize Methode ***
	@SuppressWarnings("unchecked")
	@Override
	public void initialize (URL location, ResourceBundle resources) {

		// Combobox mit observable Liste verknüpfen und Standartwert setzten
		max_rows.setItems(obsListComboBox);
		max_rows.setValue(maxRowsValue);

		// Daten erstmalig via SBB API abfragen
		data = new APIDatenSBB(maxRowsValue);
		for (RailTrafficDatasetFields temp_data : data.actRecords) {
			obsListTable.add(temp_data);
		}

		// Tabelle mit Werten in der Observable Liste verknüpfen
		table.setItems(obsListTable);
		column_description.setCellValueFactory(new PropertyValueFactory<RailTrafficDatasetFields, String>("property_description"));
		column_time.setCellValueFactory(new PropertyValueFactory<RailTrafficDatasetFields, String>("property_date"));
		column_date.setCellValueFactory(new PropertyValueFactory<RailTrafficDatasetFields, String>("property_date"));
		
		
	}
	
		
	
	// ComboBox Anzahl Meldungen Selection
	@FXML 
	public void onMaxRowSelection(ActionEvent event) {
		if(max_rows.getSelectionModel().getSelectedItem() != null) {
			this.maxRowsValue = max_rows.getSelectionModel().getSelectedItem().toString();
		}else {
			this.maxRowsValue = "0";
		}
		loadData();
	}

	// Daten laden von SBB API
	private void loadData() {
		obsListTable.clear();
		data.loadData(maxRowsValue);
		for(RailTrafficDatasetFields tempData : data.actRecords) {
			obsListTable.add(tempData);
		}
		table.refresh();
	}
	
	
	private ArrayList<String> createList() {
		ArrayList<String> list = new ArrayList<String>();
		{
			for (int i = 0; i <= maxRowsCreate; i = i + 10) {
				if (i == 0) {
					list.add("1");
				} else if (i == maxRowsCreate) {
					list.add("unlimited");
				} else {
					list.add(Integer.toString(i));
				}
			}
		}
		return list;
	}
}

