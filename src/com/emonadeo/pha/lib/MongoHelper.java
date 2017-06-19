package com.emonadeo.pha.lib;

import java.util.UUID;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoHelper {
	
	public static Document createStat(MongoCollection<Document> collection, UUID uniqueId, String key, Object value) {
		Document doc = getStat(collection, uniqueId);
		
		// Replace if it exists, Insert if it doesn't
		if(doc == null) {
			doc = new Document("_id", uniqueId.toString());
			doc.append(key, value);
			collection.insertOne(doc);
		} else {
			doc.append(key, value);
			collection.replaceOne(new Document("_id", uniqueId.toString()), doc);
		}
		
		return doc;
	}
	
	public static Document incrementStat(MongoCollection<Document> collection, UUID uniqueId, String key) {
		Document doc = getStat(collection, uniqueId);
		
		// Prevent NullPointerException
		if(doc == null) {
			doc = new Document("_id", uniqueId.toString());
			collection.insertOne(increment(doc, key));
		} else {
			collection.replaceOne(new Document("_id", uniqueId.toString()), increment(doc, key));
		}
		return doc;
	}
	
	public static Document getStat(MongoCollection<Document> collection, UUID uniqueId) {
		// Find the document by UUID
		System.out.println(collection.find(new Document("_id", uniqueId.toString())).first());
		return collection.find(new Document("_id", uniqueId.toString())).first();
	}
	
	// Methods
	private static Document increment(Document doc, String key) {
		// Create Key if it's NULL, increment it otherwise
		if(doc.containsKey(key)) {
			doc.put(key, doc.getInteger(key) + 1);
		} else {
			doc.append(key, 1);
		}
		return doc;
	}
}
