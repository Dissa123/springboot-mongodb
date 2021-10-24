package com.example.Shop.SpringbootMongodb.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.example.Shop.SpringbootMongodb.exeption.TodoCollectionException;
import com.example.Shop.SpringbootMongodb.model.Todo;

public interface TodoService {
	public void createTodo(Todo todo) throws ConstraintViolationException, TodoCollectionException;
	
	public List<Todo> getAllTodos();
	
	public Todo getSingleTodo(String id) throws TodoCollectionException;
	
	public void updateTodo(String id, Todo todo) throws TodoCollectionException;
	
	public void deleteTodoById(String id) throws TodoCollectionException;
	
}
