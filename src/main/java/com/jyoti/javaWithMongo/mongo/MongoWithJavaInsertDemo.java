package com.jyoti.javaWithMongo.mongo;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoWithJavaInsertDemo {

	public static void main(String[] args) {
		MongoClient client = null;
		Scanner scanner = null;
		try {
			client = new MongoClient("localhost", 27017);
			scanner = new Scanner(System.in);
			System.out.println("Enter how many records you want to insert??");
			int size = Integer.parseInt(scanner.next());
			if (client != null) {
				System.out.println("******************Connection Successful************");
				MongoDatabase db = client.getDatabase("JyotiDB");
				boolean collectionExists = db.listCollectionNames().into(new ArrayList<String>()).contains("Employee");
				if(collectionExists) {
					System.out.println("Collection exist");
					MongoCollection<Document> collection = db.getCollection("Employee");
					// insert records
					for(int i=1; i<=size; i++) {
						Document document = new Document();
						System.out.println("Enter " + i + " Employee Details");
						System.out.println("-------------------------------------------------------------");
						System.out.println("Enter Employee Name:");
						String name = scanner.nextLine();
						System.out.println("Enter Employee age:");
						String age = scanner.nextLine();
						System.out.println("Enter Employee address:");
						String address = scanner.nextLine();
						System.out.println("Enter Employee contact:");
						String contact = scanner.nextLine();
						System.out.println("Enter Employee Salary:");
						String sal = scanner.nextLine();
						System.out.println("Enter Employee Marital Stauts:");
						String marital_status = scanner.next();
						
						document.put("name", name);
						document.put("age", Integer.parseInt(age));
						document.put("address", address);
						document.put("contact", contact);
						document.put("salary", Double.parseDouble(sal));
						document.put("Marital Status", marital_status);
						collection.insertOne(document);
						System.out.println("Employee details inserted successfully");
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
				System.out.println("Connection closed successfully");
			}
			if(scanner!=null) {
				
			}
		}
	}


}
