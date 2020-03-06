package com.grow.dao;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserDetails")
@Data
public class MongoEntity {
    @Id
    private int id;
    private String name;
     private String lastName;

}
