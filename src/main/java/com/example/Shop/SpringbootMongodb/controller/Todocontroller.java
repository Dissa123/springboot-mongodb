package com.example.Shop.SpringbootMongodb.controller;

import java.util.Date;
import java.util.List;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shop.SpringbootMongodb.exeption.TodoCollectionException;
import com.example.Shop.SpringbootMongodb.model.Todo;
import com.example.Shop.SpringbootMongodb.repositary.TodoRepositary;
import com.example.Shop.SpringbootMongodb.service.TodoService;

@RestController
public class Todocontroller {

	@Autowired
	private TodoRepositary todoRepo;
	

	@Autowired
	private TodoService todoService;

	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos() {
		List<Todo> todos = todoService.getAllTodos();
		return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK:HttpStatus.NOT_FOUND);
	}

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
		try {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id ,@RequestBody Todo todo) {
		try {
			todoService.updateTodo(id, todo);
			return new ResponseEntity<>("Update Todo with id" +id,HttpStatus.OK);
			
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (TodoCollectionException e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		try {
			todoService.deleteTodoById(id);
			return new ResponseEntity<>("Successfully deleted with id"+id, HttpStatus.OK);
		}catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

}
