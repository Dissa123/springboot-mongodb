package com.example.Shop.SpringbootMongodb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Shop.SpringbootMongodb.exeption.TodoCollectionException;
import com.example.Shop.SpringbootMongodb.model.Todo;
import com.example.Shop.SpringbootMongodb.repositary.TodoRepositary;

@Service
public class TodoServiceimpl implements TodoService {
	
	@Autowired
	private TodoRepositary todoRepo;
	
	@Override
	public List<Todo> getAllTodos() {
		List<Todo> todos = todoRepo.findAll();
		if(todos.size() > 0) {
			return todos;
		}else {
			return new ArrayList<Todo>();
		}
	}
	

	@Override
	public Todo getSingleTodo(String id) throws TodoCollectionException {
		 Optional<Todo>todoOptional = todoRepo.findById(id);
		 if(!todoOptional.isPresent()) {
			 throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		 }else {
			 return todoOptional.get();
		 }
	}

	
	@Override
	public void createTodo(Todo todo) throws TodoCollectionException{
		Optional<Todo> todoOptional = todoRepo.findByTodo(todo.getItemName());
		if(todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.TodoAlredyExists());
			
		}else {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
		}
	}

	

	@Override
	public void updateTodo(String id, Todo todo) throws TodoCollectionException {
		Optional<Todo> todoWithId = todoRepo.findById(id);
		Optional<Todo> todoWithSameName = todoRepo.findByTodo(todo.getItemName());
		if(todoWithId.isPresent()) {
			

            if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id))
            {

                throw new TodoCollectionException(TodoCollectionException.TodoAlredyExists());
            }
			
			Todo todoToUpdate = todoWithId.get();
			
			todoToUpdate.setItemName(todo.getItemName());
			todoToUpdate.setDescription(todo.getDescription());
			todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
			todoToUpdate.setPrice(todo.getPrice());
			todoRepo.save(todoToUpdate);
			
     	}else{
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		}
	}


	@Override
	public void deleteTodoById(String id) throws TodoCollectionException {
		Optional<Todo> todoOptional = todoRepo.findById(id);
		
		 if(!todoOptional.isPresent())
	        {
	            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
	        }
	        else
	        {
	            todoRepo.deleteById(id);
	        }
			
		}
		
	


}
