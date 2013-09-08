package com.ebay.interview.Srividya.asynchronous;

import java.sql.Timestamp;
import java.util.Calendar;

import org.json.JSONObject;

import com.ebay.interview.Srividya.DataAccess.MetricsDao;
import com.ebay.interview.Srividya.DataAccess.MetricsDaoImpl;

public class AsynchronousMetricsDataSave implements Runnable {

	JSONObject dataToSave;
	String id;

	public AsynchronousMetricsDataSave(String id, JSONObject dataToSave) {
		super();
		this.dataToSave = dataToSave;
		this.id = id;
	}

	public void run() {
		MetricsDao dao = new MetricsDaoImpl();
		Calendar calendar = Calendar.getInstance();
		dao.storeMetrics(id, dataToSave.toString(),
				new Timestamp(calendar.getTimeInMillis()));
	}

}
