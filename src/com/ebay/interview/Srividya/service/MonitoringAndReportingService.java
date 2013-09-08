package com.ebay.interview.Srividya.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ebay.interview.Srividya.DataAccess.MetricsDao;
import com.ebay.interview.Srividya.DataAccess.MetricsDaoImpl;
import com.ebay.interview.Srividya.asynchronous.AsynchronousMetricsDataSave;

@Path("/interview")
public class MonitoringAndReportingService {

	@GET
	@Produces("application/json")
	@Path("/metric/{type}/{id}")
	public String getMetricsById(@PathParam("id") String id,
			@PathParam("type") String type) {
		MetricsDao dao = new MetricsDaoImpl();
		String metrics = dao.getMetricsById(id);
		return getJsonResponse(metrics);

	}

	@POST
	@Produces("application/json")
	@Path("/metric")
	public String storeMetrics(String jsonPayload) throws JSONException {
		JSONObject json = new JSONObject(jsonPayload);
		JSONObject cpu = json.getJSONObject("cpu");
		JSONObject mem = json.getJSONObject("mem");
		String id = generateId();
		JSONObject cpuData = new JSONObject();
		cpuData.put("cpu", cpu);
		Thread cpuSave = new Thread(new AsynchronousMetricsDataSave(id, cpuData));
		cpuSave.start();
		JSONObject cpuresponse = new JSONObject();
		cpuresponse.put("id", id);
		cpuresponse.put("Message", "Started");
		JSONObject memData = new JSONObject();
		memData.put("mem", mem);
		id = generateId();
		JSONObject memresponse = new JSONObject();
		memresponse.put("id", id);
		memresponse.put("Message", "Started");
		Thread memSave = new Thread(new AsynchronousMetricsDataSave(id, memData));
		memSave.start();
		JSONObject response = new JSONObject();
		JSONArray responseArray = new JSONArray();
		responseArray.put(memresponse);
		responseArray.put(cpuresponse);
		String str = response.put("Response", responseArray).toString();
		System.out.print(str);
		return str;
		
	}

	private String getJsonResponse(String metrics) {
		try {
			JSONObject json = new JSONObject(metrics);
			return json.toString();
		} catch (JSONException e) {
			return "Retrieval Failed due to json exception";
		}
	}
	
	private String generateId() {
		return java.util.UUID.randomUUID().toString();
	}

}