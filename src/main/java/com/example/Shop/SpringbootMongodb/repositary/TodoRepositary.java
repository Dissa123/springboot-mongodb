package com.example.Shop.SpringbootMongodb.repositary;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Shop.SpringbootMongodb.model.Todo;

@Repository
public interface TodoRepositary extends MongoRepository<Todo, String> {
	
	@Query("{'itemName': ?0}")
	Optional<Todo> findByTodo(String itemName);
}
