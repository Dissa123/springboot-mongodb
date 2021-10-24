package com.example.Shop.SpringbootMongodb.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")
public class Todo {
	
	@Id
	private String id;
	
	@NotNull(message="You should Enter Item Name ")
	private String itemName;
	
	@NotNull(message="You should Enter Description ")
	private String description;
	
	
	private Date createdAt;
	
	private Date updatedAt;
	
	@NotNull(message="You should Enter Price ")
	private String price;

	
	

}
