package br.com.brittosw.generativeai.repository;

import br.com.brittosw.generativeai.model.Prompt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableMongoRepositories
public interface PromptRepository extends MongoRepository<Prompt, UUID> {

    Optional<Prompt> findByContent(String content);
}
