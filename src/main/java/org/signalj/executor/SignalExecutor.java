package org.signalj.executor;

import java.util.Set;

import org.signalj.Signal;
import org.signalj.SignalHandler;

/**
 * Executor interface that is used to execute signals
 * 
 * @author Alexander Finn
 *
 */
public interface SignalExecutor {
  
  <T extends Signal> void execute(T signal, Set<SignalHandler> handlers);

}
