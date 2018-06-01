package mongo;



import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class MongodbData {
	private  MongoDatabase mongoDatabase;
	private  MongoCollection<Document> collection;
	private  FindIterable<Document> findIterable;
	private  Document info;
	private  int count;
	
	public MongodbData() {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			mongoDatabase = mongoclient.getDatabase("webofscience");
			System.out.println("Connect to database successfully");
			collection = mongoDatabase.getCollection("articles");
	}
	
	//¼ÇÂ¼¸öÊý
	public int count() { 
        return count;  
    }  
	
	public void Insert(String address, String author, String title, String periodical, 
			String year, String stage, String volume, String leaf, String dol, String 
			dol_catalog) {
		Document document =new Document("address", address).
				append("author", author).
				append("title", title).
				append("periodical", periodical).
				append("year", year).
				append("stage", stage).
				append("volume", volume).
				append("leaf", leaf).
				append("dol", dol).
				append("dol_catalog", dol_catalog);
		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		collection.insertMany(documents);
	}
	
	
	public String[][] get_query(String Col, String Text) {
		
		count = 0;
		BasicDBObject query = new BasicDBObject();
		query.put(Col, Text);
		findIterable = collection.find(query);
        MongoCursor<Document> cursor = findIterable.iterator();
		int i=0;
        String[][] str = new String[10000][10000];
		while(cursor.hasNext()) {
			info = cursor.next();
			int j=0;
			str[i][j] = (String) (info.get("address"));
			j++;
			str[i][j] = (String)(info.get("author"));
			j++;
			str[i][j] = (String)(info.get("title"));
			j++;
			str[i][j] = (String)(info.get("periodical"));
			j++;
			str[i][j] = (String)(info.get("year"));
			j++;
			str[i][j] = (String)(info.get("stage"));
			j++;
			str[i][j] = (String)(info.get("volume"));
			j++;
			str[i][j] = (String)(info.get("leaf"));
			j++;
			str[i][j] = (String)(info.get("dol"));
			j++;
			str[i][j] = (String)(info.get("dol_catalog"));
			i++;
			count++;
		}
		return str;
	}
}