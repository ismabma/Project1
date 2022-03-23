package dates;

public class StockInsuficientException extends Exception{
    public StockInsuficientException(){
        super();
    }

    public StockInsuficientException(String errorMessage) {
        super(errorMessage);
    }
}
