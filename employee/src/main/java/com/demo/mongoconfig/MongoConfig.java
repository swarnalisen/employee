package com.demo.mongoconfig;

import java.util.Objects;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoConfig {

    @Value("${MONGO_URL:mongodb://localhost:27017}")
    private String mongoUrl;

    public MongoClient getMongoClient(){
        if(Objects.isNull(mongoUrl)){
            mongoUrl = "mongodb://localhost:27017";
        }
        System.out.println(mongoUrl);
        return MongoClients.create(mongoUrl);
    }

    public MongoTemplate getMongoTemplate(String dbName){
        if(Objects.isNull(mongoUrl)){
            mongoUrl = "mongodb://localhost:27017";
        }
        System.out.println(mongoUrl);
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client,dbName);
    }

}
