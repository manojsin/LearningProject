package com.grow.repository.mongorepository;
import com.grow.dao.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MongoDbAddUserGroupRepository extends MongoRepository<MongoEntity,Long> {

    MongoEntity findByName(String name);
}
