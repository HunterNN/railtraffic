package ch.railTraffic;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class RailTrafficDataset {

	@SerializedName("datasetid")
	private String datasetid;
	@SerializedName("recordid")
	private String recordid;
	@SerializedName("record_timestamp")
	private Date record_timestamp;
	@SerializedName("fields")
	private RailTrafficDatasetFields dataset_fields;
	
	public void perpareDatasets() {
		dataset_fields.setRecord_timestamp(record_timestamp);
		dataset_fields.setRecord_id(recordid);
		dataset_fields.prepareProperty();
	}
	
	public RailTrafficDatasetFields getDatasetFields () {
		return this.dataset_fields;
	}
}
