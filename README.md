SrividyaEbayInterview
=====================

This is a project to be submitted to the Cloud Engineering team of ebay for a code labs interview. 

This module is a restful interface which has 2 methods:

1) POST
	POST uploads the metric data into the database asynchronously by spawning of a new thread and returns the id of the tuple being inserted into the database. 
	URL:  http://localhost:8080/SrividyaEbayInterview/interview/metric
	Payload:
		{
			"cpu": {
				"core": "80%",
				"core2": "60%"
			},
			"mem": {
				"full": "40%",
				"empty": "50%"
			}
		}
		
2) GET
	GET retrieves the metrics posted previously based on the id of metric data inserted into the database before.
	URL:  http://localhost:8080/SrividyaEbayInterview/interview/metric/cpu/<id returned from post>
	
Open source technologies used:
	1) Maven for dependency management
	2) JAX-RS and jersey for restful web services
	3) JUnit for unit testing
	4) JDBC to connect to the mysql backend database for metrics
	
Database SQL script:
Only one script to create a database table has been used in the project. Database used is mysql
	
	CREATE SCHEMA `ebay` ;
	create table interview(db_id varchar(20), db_metrics varchar(200), db_timestamp timestamp) ;

Instructions:
1) Please create the schema and the table from the above 2 sql statements in a mysql instance.
2) Please deploy the war of the project in a tom cat working directory and start the server.
3) Please make a post call as shown above and this would return an identifier, please save this identifier
4) Make a get call using the identifier from step 3
