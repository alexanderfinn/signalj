package org.signalj.executor;

import java.util.Set;

import org.signalj.Signal;
import org.signalj.SignalHandler;

/**
 * Default executor implementation
 * 
 * @author Alexander Finn
 *
 */
public class BasicExecutor implements SignalExecutor {

  public <T extends Signal> void execute(T signal, Set<SignalHandler> handlers) {
    for(SignalHandler<T> handler: handlers) {
      handler.execute(signal);
    }
  }

}
