package ch.railTraffic;

import com.google.gson.annotations.SerializedName;

public class SBBDatenRecordsParameter {

	@SerializedName("timezone")
	public String timezone;
	@SerializedName("rows")
	public int rows;

	// Getter
	public String getTimezone() {
		return timezone;
	}

	public int getRows() {
		return rows;
	}

}
