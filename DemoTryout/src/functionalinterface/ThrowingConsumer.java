package functionalinterface;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
    
    static <T, E extends Exception> Consumer<T> consumerWrapper(Consumer<T> consumer, Class<E> clazz) {
   	 
	    return i -> {
	        try {
	            consumer.accept(i);
	        } catch (Exception ex) {
	            try {
	                E exCast = clazz.cast(ex);
	                System.err.println(
	                  "Exception occured : " + exCast.getMessage());
	            } catch (ClassCastException ccEx) {
	                throw ex;
	            }
	        }
	    };
	}
}
