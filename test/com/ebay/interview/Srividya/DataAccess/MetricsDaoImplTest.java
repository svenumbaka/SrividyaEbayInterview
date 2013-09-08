package com.ebay.interview.Srividya.DataAccess;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class MetricsDaoImplTest {

	MetricsDaoImpl mdi;
	Calendar cal;
	Timestamp timestamp;
	
	@Before
	public void setUp() throws Exception {
		mdi = new MetricsDaoImpl();
		cal = Calendar.getInstance();
		timestamp = new Timestamp(cal.getTimeInMillis());
		
	}

	@Test
	public void testStoreMetrics() {
		MetricsDaoImpl mdi = new MetricsDaoImpl();
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		assertTrue(mdi.storeMetrics("21", "abc", timestamp));
	}

	@Test
	public void testGetMetricsById() {
		assertNotNull(mdi.getMetricsById("21"));
	}

}
