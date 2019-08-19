package ch.railTraffic;

import com.google.gson.annotations.SerializedName;

public class SBBDatenRecords {

	// Public Test
	@SerializedName("nhits")
	private int nhits;
	
	@SerializedName("records")
	private RailTrafficDataset[] records;
	
	@SerializedName("parameters")
	private SBBDatenRecordsParameter parameter;

	// Getter
	public int getNhits() {
		// TODO Auto-generated method stub
		return this.nhits;
	}
	
	public RailTrafficDataset[] getRecords() {
		return this.records;
	}

}
