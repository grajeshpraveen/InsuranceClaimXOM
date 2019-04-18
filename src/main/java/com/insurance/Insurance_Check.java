package com.insurance;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Insurance_Check {

	int claimAmount;
	String priority;
	String Status;

	public boolean newPolicy(String policyId, String name, int age,
			String type, String nominee, int noOfMonths, String status,
			String priority) {

		boolean result = false;
		try {
			MongoConnection connection = new MongoConnection();
			DB db = connection.mongo().getDB("Insurance");
			DBCollection collection = db.getCollection("Insuer_data");
			BasicDBObject query = new BasicDBObject();
			query.put("policyId", policyId);
			if (collection.findOne(query) == null) {
				BasicDBObject document = new BasicDBObject();
				document.put("policyId", policyId);
				document.put("name", name);
				document.put("age", age);
				document.put("type", type);
				document.put("nominee", nominee);
				document.put("noOfMonths", noOfMonths);
				document.put("status", status);
				document.put("priority", checkPolicy(type, noOfMonths, age));
				collection.insert(document);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public int claimPolicy(String policyId) {
		try {

			MongoConnection connection = new MongoConnection();
			DB db = connection.mongo().getDB("Insurance");
			DBCollection collection = db.getCollection("Insuer_data");

			DBObject results = collection.findOne(new BasicDBObject("policyId",
					policyId));
			if (results != null) {
				claimAmount = amountCalculation(
						(String) results.get("priority"),
						(String) results.get("type"));
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("amount", claimAmount);
				newDocument.append("status", "CLOSED");
				newDocument.append("policyId", results.get("policyId"));
				newDocument.append("age", results.get("age"));

				newDocument.append("name", results.get("name"));
				newDocument.append("nominee", results.get("nominee"));
				newDocument.append("noOfMonths", results.get("noOfMonths"));
				newDocument.append("priority", results.get("priority"));
				newDocument.append("type", results.get("type"));

				BasicDBObject query = new BasicDBObject();
				query.put("policyId", policyId);

				collection.update(query, newDocument);

			}

		} catch (Exception e) {

		}
		return claimAmount;
	}

	public String renewalPolicy(String policyId) {
		try {

			MongoConnection connection = new MongoConnection();
			DB db = connection.mongo().getDB("Insurance");
			DBCollection collection = db.getCollection("Insuer_data");

			DBObject results = collection.findOne(new BasicDBObject("policyId",
					policyId));
			if (results != null) {
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("amount", results.get("amount"));
				newDocument.append("status", "ACTIVE");
				newDocument.append("policyId", results.get("policyId"));
				newDocument.append("age", results.get("age"));

				newDocument.append("name", results.get("name"));
				newDocument.append("nominee", results.get("nominee"));
				newDocument.append("noOfMonths", results.get("noOfMonths"));
				newDocument.append("priority", results.get("priority"));
				newDocument.append("type", results.get("type"));

				BasicDBObject query = new BasicDBObject();
				query.put("policyId", policyId);

				collection.update(query, newDocument);

			}

		} catch (Exception e) {

		}
		return "PolicyActivated";
	}

	public int amountCalculation(String priority, String type) {
		int amount = 0;
		if (!priority.isEmpty() && type.equalsIgnoreCase("vehicle")) {
			amount = 10000;
		} else if (!priority.isEmpty() && type.equalsIgnoreCase("HEALTH")) {
			amount = 100000;
		} else if (!priority.isEmpty() && type.equalsIgnoreCase("LIFE")) {
			amount = 100000;
		} else if (!priority.isEmpty() && type.equalsIgnoreCase("THEFT")) {
			amount = 50000;
		} else if (!priority.isEmpty() && type.equalsIgnoreCase("HOME")) {
			amount = 200000;
		} else {
			amount = 0;
		}

		return amount;

	}

	public String checkPolicy(String typeOfPolicy, int paidMonths, int age) {

		if (typeOfPolicy.toUpperCase().equals("VEHICLE")) {
			if (paidMonths >= 54) {
				priority = "HIGH";
			} else if (paidMonths >= 42) {
				priority = "MEDIUM";
			} else {
				priority = "LOW";
			}
		} else if (typeOfPolicy.toUpperCase().equals("HEALTH")) {
			if (paidMonths >= 54) {
				priority = "HIGH";
			} else if (paidMonths >= 42) {
				priority = "MEDIUM";
			} else {
				priority = "LOW";
			}
		} else if (typeOfPolicy.toUpperCase().equals("LIFE")) {
			if (age != 0) {
				lifeInsurance(paidMonths, age);
			}
		} else if (typeOfPolicy.toUpperCase().equals("THEFT")) {
			if (paidMonths >= 30) {
				priority = "HIGH";
			} else if (paidMonths >= 18) {
				priority = "MEDIUM";
			} else {
				priority = "LOW";
			}
		} else if (typeOfPolicy.toUpperCase().equals("HOME")) {
			if (paidMonths >= 96) {
				priority = "HIGH";
			} else if (paidMonths >= 84) {
				priority = "MEDIUM";
			} else {
				priority = "LOW";
			}
		}

		return priority;

	}

	private void lifeInsurance(int paidMonths, int age) {
		if (paidMonths >= 60 && (age >= 40 || age <= 60)) {
			priority = "HIGH";
		} else if (paidMonths >= 36 && (age >= 45 || age <= 50)) {
			priority = "MEDIUM";
		} else {
			priority = "LOW";
		}

	}
}
