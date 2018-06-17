package com.jyoti.javaWithMongo.mongo;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class MongoWithJavaDeleteDemo {

	public static void main(String[] args) {
		MongoClient client = null;
		try {

			client = new MongoClient("localhost", 27017);
			if (client != null) {
				System.out.println("******************Connection Successful************");
				MongoDatabase db = client.getDatabase("JyotiDB");
				boolean collectionExists = db.listCollectionNames().into(new ArrayList<String>()).contains("Employee");
				if(collectionExists) {
					MongoCollection<Document> collection = db.getCollection("Employee");
					
					//delete records
					Bson filter = Filters.eq("name", "Naresh");
					DeleteResult deleteResult = collection.deleteOne(filter);
					System.out.println(deleteResult.getDeletedCount() + " Record(s) deleted successfully");
					
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
				System.out.println("Connection closed successfully");
			}
		}
	}

}
