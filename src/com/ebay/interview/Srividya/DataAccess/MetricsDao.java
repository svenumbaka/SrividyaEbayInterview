package com.ebay.interview.Srividya.DataAccess;

import java.sql.Timestamp;

public interface MetricsDao {

	public String getMetricsById(String id);
	
	public boolean deleteMetricsById(String id);

	public boolean storeMetrics(String id, String string, Timestamp timestamp);
	
}
