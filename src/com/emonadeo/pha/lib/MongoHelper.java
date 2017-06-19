package com.emonadeo.pha.lib;

import java.util.UUID;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MongoHelper {
	
	public static Document createStat(MongoCollection<Document> collection, UUID uniqueId, String key, Object value) {
		Document doc = getStat(collection, uniqueId);
		
		// Prevent NullPointerException
		if(doc == null)
			doc = new Document("_id", uniqueId.toString());
		
		// Append & Insert
		doc.append(key, value);
		collection.replaceOne(new Document("_id", uniqueId.toString()), doc);
		
		return doc;
	}
	
	public static Document incrementStat(MongoCollection<Document> collection, UUID uniqueId, String key) {
		Document doc = getStat(collection, uniqueId);
		
		// Prevent NullPointerException
		if(doc == null)
			doc = new Document("_id", uniqueId.toString());
		
		// Create Key if it's NULL, increment it otherwise
		if(doc.containsKey(key)) {
			doc.put(key, doc.getInteger(key) + 1);
		} else {
			doc.append(key, 1);
		}
		
		collection.replaceOne(new Document("_id", uniqueId.toString()), doc);
		return doc;
	}
	
	public static Document getStat(MongoCollection<Document> collection, UUID uniqueId) {
		// Find the document by UUID
		System.out.println(collection.find(new Document("_id", uniqueId.toString())).first());
		return collection.find(new Document("_id", uniqueId.toString())).first();
	}
}
