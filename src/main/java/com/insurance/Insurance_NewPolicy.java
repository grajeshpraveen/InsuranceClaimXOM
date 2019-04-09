package com.insurance;

import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;

public class Insurance_NewPolicy {
	boolean result;

	public boolean applyNewPolicy(String name, List<String> type,
			double paidMonths, double age) {

		Insurance_Claim newPolicy = new Insurance_Claim();
		newPolicy.setAge(age);
		newPolicy.setName(name);
		newPolicy.setPaidMonths(paidMonths);
		newPolicy.setType(type);

		try {
			MongoConnection connection=  new MongoConnection();
			DB db = connection.mongo().getDB("Insurance");
			DBCollection collection = db.getCollection("Insuer_data");
			BasicDBObject query = new BasicDBObject();
			query.put("name", newPolicy.getName());
			if (collection.findOne(query) == null) {
				Gson gson = new Gson();
				BasicDBObject obj = (BasicDBObject) JSON.parse(gson
						.toJson(newPolicy));
				collection.insert(obj);
				result = true;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String args[]) {
		// Insurance_NewPolicy insurance = new Insurance_NewPolicy();
		// insurance.applyNewPolicy("raj", Arrays.asList("Helath"), 30, 20);
		//
	}
}
