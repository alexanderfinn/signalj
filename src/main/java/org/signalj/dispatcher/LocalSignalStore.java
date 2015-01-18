package org.signalj.dispatcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.signalj.Signal;
import org.signalj.SignalHandler;

/**
 * Basic SignalStore implementation using SignalStore
 * 
 * @author Alexander Finn
 *
 */
public class LocalSignalStore implements SignalStore {

  private final Map<Class<Signal>, Set<SignalHandler>> signalsMap = 
      new HashMap<Class<Signal>, Set<SignalHandler>>();

  public <T extends Signal> void assignHandler(Class<T> signalClass,
      SignalHandler<T> handler) {
    if (!signalsMap.containsKey(signalClass)) {
      signalsMap.put((Class<Signal>) signalClass, new HashSet<SignalHandler>());
    }
    signalsMap.get(signalClass).add(handler);
  }

  public <T extends Signal> void removeHandler(Class<T> signalClass,
      SignalHandler<T> handler) {
    if (signalsMap.containsKey(signalClass)) {
      signalsMap.get(signalClass).remove(handler);
    }
  }
  
  public <T extends Signal> Set<SignalHandler> getHandlers(Class<T> signalClass) {
    if (signalsMap.containsKey(signalClass)) {
      return signalsMap.get(signalClass);
    }
    return new HashSet<SignalHandler>(0);
  }

}
