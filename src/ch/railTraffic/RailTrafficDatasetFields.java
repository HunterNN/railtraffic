package ch.railTraffic;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class RailTrafficDatasetFields {

	@SerializedName("validitybegin")
	private Date validitybegin;
	@SerializedName("description")
	private String description;
	@SerializedName("description_html")
	private String description_html;
	@SerializedName("title")
	private String title;
	@SerializedName("validityend")
	private Date validityend;
	@SerializedName("link")
	private String link;
	@SerializedName("published")
	private Date published;
	
	// Eigenschaften
	private SimpleStringProperty property_description;
	private SimpleObjectProperty<Date> property_date;
	private SimpleStringProperty property_new;
	
	// Attribute
	private Date record_timestamp;
	private boolean new_entry = false;
	private String record_id;
	
	// Properties vorbereiten
	public void prepareProperty() {
		property_description = new SimpleStringProperty(title);
		property_date = new SimpleObjectProperty<>(record_timestamp);
		if(this.new_entry) {
			property_new = new SimpleStringProperty("New");
		}else {
			property_new = new SimpleStringProperty("");
		}
	} 
	
	// Getter 
	public String getProperty_description() {
		return property_description.get();
	}
	public String getRecord_id() {
		return this.record_id;
	}

	public String getDescription_html() {
		return this.description_html;
	}

	public String getTitle() {
		return this.title;
	}

	public Date getProperty_date() {
		return property_date.get();
	}
	
	public String getProperty_new() {
		return this.property_new.get();
	}

	public boolean getNew_entry() {
		return this.new_entry;
	}

	
	// Setter 
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public void setRecord_timestamp(Date timestamp) {
		this.record_timestamp = timestamp;
	}
	
	public void setNew_entry(boolean new_entry) {
		this.new_entry = new_entry;
		this.prepareProperty();
	}

}
