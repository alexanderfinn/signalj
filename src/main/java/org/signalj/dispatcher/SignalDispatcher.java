package org.signalj.dispatcher;

import java.util.Collection;

import org.signalj.Configuration;
import org.signalj.Signal;
import org.signalj.SignalHandler;
import org.signalj.executor.SignalExecutor;

/**
 * Connects signals to handlers and executes
 * when the signal is sent
 * 
 * @author Alexander Finn
 *
 */
public class SignalDispatcher {
  
  private final Configuration configuration;

  public SignalDispatcher(Configuration configuration) {
    this.configuration = configuration;
  }
  
  private SignalStore getSignalStore() {
    return this.configuration.getSignalStore();
  }
  
  private SignalExecutor getSignalExecutor() {
    return this.configuration.getSignalExecutor();
  }
  
  /* connects handler to the signal */
  public <T extends Signal> void connect(Class<T> signalClass, SignalHandler<T> handler) {
    getSignalStore().assignHandler(signalClass, handler);
  }
  
  /* disconnects handler from the signal */
  public <T extends Signal> void disconnect(Class<T> signalClass, SignalHandler<T> handler) {
    getSignalStore().removeHandler(signalClass, handler);
  }
  
  /* send signal instance */
  public <T extends Signal> void send(T signal) {
    getSignalExecutor().execute(signal, getSignalStore().getHandlers(signal.getClass()));
  }
  
}
