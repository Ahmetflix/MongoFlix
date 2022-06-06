package dev.Ahmetflix.MongoFlix;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoFlixCollection {

	private MongoCollection<Document> collection;
	
	public MongoFlixCollection(MongoFlix api, String collectionName) {
		this.collection = api.getCollection(collectionName);
	}
	
	public void insertOne(Document document) {
		this.collection.insertOne(document);
	}
	
	public void insertMany(List<Document> documents) {
		this.collection.insertMany(documents);
	}
	
	public long count() {
		return this.collection.countDocuments();
	}
	
	public Document first() {
		return this.collection.find().first();
	}
	
	public List<Document> findAll() {
		ArrayList<Document> documents = new ArrayList<>();
		MongoCursor<Document> cursor = this.collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        documents.add(cursor.next());
		    }
		} finally {
		    cursor.close();
		}
		return documents;
	}
	
	/**
	 * 
	 * @param filter You can use Filters.eq etc.
	 * @return
	 */
	public List<Document> find(Bson filter) {
		ArrayList<Document> documents = new ArrayList<>();
		MongoCursor<Document> cursor = this.collection.find(filter).iterator();
		try {
		    while (cursor.hasNext()) {
		        documents.add(cursor.next());
		    }
		} finally {
		    cursor.close();
		}
		return documents;
	}
	
	public void updateOne(Bson filter, Bson update) {
		this.collection.updateOne(filter, update);
	}
	
	public void updateMany(Bson filter, Bson update) {
		this.collection.updateMany(filter, update);
	}
	
	public void updateMany(Bson filter, List<? extends Bson> update) {
		this.collection.updateMany(filter, update);
	}
	
	public void deleteOne(Bson filter) {
		this.collection.deleteOne(filter);
	}
	
	public void deleteMany(Bson filter) {
		this.collection.deleteMany(filter);
	}
	
	public void createIndex(Bson keys) {
		this.collection.createIndex(keys);
	}
	
	public void drop() {
		this.collection.drop();
	}
	
}
