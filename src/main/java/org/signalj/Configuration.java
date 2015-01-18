package org.signalj;

import org.signalj.dispatcher.LocalSignalStore;
import org.signalj.dispatcher.SignalStore;
import org.signalj.executor.BasicExecutor;
import org.signalj.executor.SignalExecutor;

/**
 * signalj configuration class
 * 
 * @author Alexander Finn
 *
 */
public class Configuration {
  
  private final SignalExecutor signalExecutor;
  private final SignalStore signalStore;
  
  public Configuration(SignalStore signalStore, SignalExecutor signalExecutor) {
    this.signalStore = signalStore;
    this.signalExecutor = signalExecutor;
  }
  
  public Configuration() {
    this(new LocalSignalStore(), new BasicExecutor());
  }

  public SignalExecutor getSignalExecutor() {
    return signalExecutor;
  }

  public SignalStore getSignalStore() {
    return signalStore;
  }
  
}
