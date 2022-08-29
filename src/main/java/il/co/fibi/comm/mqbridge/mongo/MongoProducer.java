package il.co.fibi.comm.mqbridge.mongo;

import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
public class MongoProducer {
	@Inject
	@ConfigProperty(name = "mongo.server")
	String server;
	@Inject
	@ConfigProperty(name = "mongo.auth")
	String auth;
	@Inject
	@ConfigProperty(name = "mongo.user")
	String user;
	@Inject
	@ConfigProperty(name = "mongo.password")
	String password;
	@Inject
	@ConfigProperty(name = "mongo.db")
	String db;

	@Produces
	public MongoClient create() {
		ConnectionString connectionString = new ConnectionString(String.format("mongodb://%s:%s@%s/%s?authSource=%s", user, password, server, db, auth));
		return MongoClients.create(connectionString);
	}

	@Produces
	@Collection(CollectionType.PROTO)
	public MongoCollection<Document> getProtoCollection(MongoClient client) {
		return client.getDatabase(db).getCollection("proto");
		
	}
	
	@Produces
	@Collection(CollectionType.CBXML)
	public MongoCollection<Document> getCbxmlCollection(MongoClient client) {
		return client.getDatabase(db).getCollection("cbxml");
		
	}

	void close(@Disposes MongoClient client) {
		client.close();
	}
}