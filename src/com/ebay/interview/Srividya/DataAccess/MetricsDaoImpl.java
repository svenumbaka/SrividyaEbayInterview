package com.ebay.interview.Srividya.DataAccess;

import java.sql.*;
import java.util.Calendar;
import com.mysql.jdbc.Driver;

public class MetricsDaoImpl implements MetricsDao {

	String temp = "{\"cpu\":{\"core1\":\"80%\",\"core2\":\"60%\"}}";
	Connection con;
	Statement stmt;
	ResultSet rs;

	public MetricsDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebay", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MetricsDaoImpl mdi = new MetricsDaoImpl();
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		// mdi.storeMetrics("21", "abc", timestamp);
		// mdi.getMetricsById("21");
		System.out.print(mdi.getMetricsById("21"));
	}

	public boolean storeMetrics(String id, String metrics, Timestamp timestamp) {

		try {
			stmt = con.createStatement();
			String str = "insert into interview values('" + id + "', '"
					+ metrics + "', '" + timestamp.toString() + "')";
			System.out.println(str);
			stmt.executeUpdate("insert into interview values('" + id + "', '"
					+ metrics + "', '" + timestamp.toString() + "')");
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}

	}

	public String getMetricsById(String id) {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from interview where db_id = "
					+ id);
			while (rs.next())
				return rs.getString("db_metrics");
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return "failed";
	}

	public boolean deleteMetricsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}