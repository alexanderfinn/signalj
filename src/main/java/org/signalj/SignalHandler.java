package org.signalj;

/**
 * Represents signal handler
 * 
 * @author Alexander Finn
 *
 * @param <T> Singal class that will be handled by this handler
 */
public interface SignalHandler<T extends Signal> {
  
  /* contains the actual business logic */
  void execute(T signal);
  
}
