package com.edigest.journalapp.repository;

import com.edigest.journalapp.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
