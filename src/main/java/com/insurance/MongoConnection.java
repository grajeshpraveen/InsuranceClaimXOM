package com.insurance;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoConnection {

	public MongoClient mongo() throws Exception {

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream("config.properties");
		Properties prop = new Properties();
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException(
					"property file not found in the classpath");
		}

		ServerAddress serverAddress = new ServerAddress(
				prop.getProperty("hostname"), 27017);
		MongoCredential credential = MongoCredential.createCredential(
				prop.getProperty("username"), prop.getProperty("database"),
				prop.getProperty("password").toCharArray());

		@SuppressWarnings("deprecation")
		MongoClient client = new MongoClient(serverAddress,
				Arrays.asList(credential));
		return client;
	}
}