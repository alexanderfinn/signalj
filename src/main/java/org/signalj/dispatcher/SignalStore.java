package org.signalj.dispatcher;

import java.util.Set;

import org.signalj.Signal;
import org.signalj.SignalHandler;

/**
 * Stores handlers to signals assignment
 * Implementations can use local or remote storages
 * 
 * @author Alexander Finn
 *
 */
public interface SignalStore {
  
  <T extends Signal> void assignHandler(Class<T> signalClass, SignalHandler<T> handler);
  
  <T extends Signal> void removeHandler(Class<T> signalClass, SignalHandler<T> handler);
  
  <T extends Signal> Set<SignalHandler> getHandlers(Class<T> signalClass);

}
