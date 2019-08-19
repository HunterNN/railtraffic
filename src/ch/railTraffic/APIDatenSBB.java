package ch.railTraffic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;

import javafx.util.Duration;

public class APIDatenSBB {
					
	private static final String API_URL_parameter = "https://data.sbb.ch/api/records/1.0/search/?dataset=rail-traffic-information&refine.validitybegin=2019&refine.validityend=2019&pretty_print=true&rows=";
	
	private SBBDatenRecords importRecords;
	protected ArrayList<RailTrafficDatasetFields> actRecords = new ArrayList<>();
	private ArrayList<String> preRecords = new ArrayList<>();
	
	// Konstruktor for creating API class
	APIDatenSBB(String maxRowsValue){
		this.loadData(maxRowsValue);
	}
	
	// LoadData Methode
	public void loadData(String maxRowsValue) {
		String API_URL_prepare;
		URL url;
		try {
			// Block f�r den Selektor "unlimited"
			if(maxRowsValue.equals("unlimmited")) {
				if(importRecords.getNhits() > 0) {
					API_URL_prepare = API_URL_parameter + importRecords.getNhits();
				}else {
					API_URL_prepare = API_URL_parameter + 999;
				}
			}else {
				API_URL_prepare = API_URL_parameter + maxRowsValue;
			}
			
			// URL Stream starten
			url = new URL(API_URL_prepare);
			InputStream input = url.openStream();
			Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8.name());
			importRecords = new Gson().fromJson(reader, SBBDatenRecords.class);	
			
			// If Block wenn noch keine Daten geladen wurden
			if (actRecords.isEmpty()) {
				for (RailTrafficDataset temp_datasets : importRecords.getRecords()) {
					temp_datasets.perpareDatasets();
					actRecords.add(temp_datasets.getDatasetFields());
				}
			} else {
				// Else Block wenn bereits Daten geladen wurden
				preRecords.clear();
				for (RailTrafficDatasetFields temp_fields : actRecords) {
					temp_fields.setNew_entry(false);
					preRecords.add(temp_fields.getRecord_id());
				}
				actRecords.clear();
				for (RailTrafficDataset temp_datasets : importRecords.getRecords()) {
					temp_datasets.perpareDatasets();
					actRecords.add(temp_datasets.getDatasetFields());
				}
			}
		
		}catch(MalformedURLException e) {
		System.out.println(e.getMessage());
		}catch(IOException e1) {
		System.out.println(e1.getMessage());
		}
	}
	
	
	
}

