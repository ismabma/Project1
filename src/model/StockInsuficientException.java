package model;

import java.io.Serializable;

public class StockInsuficientException extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;
	
    public StockInsuficientException(){
        super();
    }

    public StockInsuficientException(String errorMessage) {
        super(errorMessage);
    }
}

