package com.example.Shop.SpringbootMongodb.exeption;

public class TodoCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TodoCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Todo with"+id+"not found!";
	}
	
	public static String TodoAlredyExists() {
		return "Todo with given new alredy exists";
	}

}
