package br.com.brittosw.generativeai.repository;

import br.com.brittosw.generativeai.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.UUID;

@EnableMongoRepositories
public interface MessageRepository extends MongoRepository<Message, UUID> {
}
