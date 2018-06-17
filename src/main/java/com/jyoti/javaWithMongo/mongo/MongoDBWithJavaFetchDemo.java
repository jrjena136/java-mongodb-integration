package com.jyoti.javaWithMongo.mongo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class MongoDBWithJavaFetchDemo {
	public static void main(String[] args) {
		MongoClient client = null;
		try {
			client = new MongoClient("localhost", 27017);
			if (client != null) {
				System.out.println("******************Connection Successful************");
				MongoDatabase db = client.getDatabase("JyotiDB");
				System.out.println("Enter the Collection name :");
				@SuppressWarnings("resource")
				String collectionName = new Scanner(System.in).nextLine();
				boolean collectionExists = db.listCollectionNames().into(new ArrayList<String>()).contains(collectionName);
				if (collectionExists) {
					System.out.println("Collection is exist");
					MongoCollection<Document> collection = db.getCollection(collectionName);

					// find all data
					FindIterable<Document> dbObj = collection.find();
					MongoCursor<Document> itr = dbObj.iterator();
					while (itr.hasNext()) {
						Document doc = itr.next();
						System.out.println("Result : " + doc);
						int size = doc.size();
						System.out.println("size : " + size);
						Set<String> keySet = doc.keySet();
						System.out.println("*****************Employee Details*******************");
						for (String s : keySet) {
							System.out.println(s + " : " + doc.get(s));
						}
						System.out.println("********************************************");
					}
				}
				else {
					System.out.println("Collection does not exist. Creating new Collection");
					db.createCollection(collectionName);
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
