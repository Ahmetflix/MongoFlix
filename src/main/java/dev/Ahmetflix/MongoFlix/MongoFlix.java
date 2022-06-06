package dev.Ahmetflix.MongoFlix;

import java.util.HashMap;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoFlix {

	private MongoClient mongoClient;
	private MongoDatabase database;
	private final HashMap<String, MongoFlixCollection> openedCollections = new HashMap<>();
	
	public MongoFlix(String connectionUri, String database) {
		mongoClient = MongoClients.create(connectionUri);
		this.database = mongoClient.getDatabase(database);
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
	
	public MongoDatabase getDatabase() {
		return database;
	}
	
	public MongoCollection<Document> getCollection(String collectionName) {
		return database.getCollection(collectionName);
	}
	
	public MongoFlixCollection getMongoFlixCollection(String collectionName) {
		if (this.openedCollections.containsKey(collectionName)) return this.openedCollections.get(collectionName);
		else {
			MongoFlixCollection collection = new MongoFlixCollection(this, collectionName);
			this.openedCollections.put(collectionName, collection);
			return this.openedCollections.get(collectionName);
		}
	}
	
	/** public boolean insertOne(Object obj) {
		Class<?> clazz = obj.getClass();
		if (haveFlixClassAnnotation(clazz)) {
			String collectionName = clazz.getAnnotation(FlixClass.class).collection();
			MongoFlixCollection collection = this.getMongoFlixCollection(collectionName);
			collection.insertOne(DocumentUtils.objectToDocument(obj));
			return true;
		} else return false;
	}
	
	public boolean insertMany(List<Object> objs) {
		boolean flag = true;
		for (Object obj : objs) {
			if (!this.insertOne(obj)) flag = false;
		}
		return flag;
	}
	
	
	
	private boolean haveFlixClassAnnotation(Class<?> clazz) {
		boolean flag = false;
		for (Annotation ann : clazz.getAnnotations()) {
			if (ann.getClass().equals(FlixClass.class)) {
				flag = true;
			}
		}
		return flag;
	} **/
	
}
