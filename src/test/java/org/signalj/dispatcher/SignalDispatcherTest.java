package org.signalj.dispatcher;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.signalj.Configuration;
import org.signalj.Signal;
import org.signalj.SignalHandler;
import org.signalj.executor.SignalExecutor;
import org.signalj.test.TestHandler;
import org.signalj.test.TestSignal;

public class SignalDispatcherTest {

  private SignalDispatcher dispatcher;
  private SignalStore store;
  private SignalExecutor executor;

  @Before
  public void setUp() {
    this.store = mock(SignalStore.class);
    this.executor = mock(SignalExecutor.class);
    this.dispatcher = new SignalDispatcher(new Configuration(store, executor));
    
  }
  
  @Test
  public void testConnect() {
    TestHandler handler = new TestHandler();
    dispatcher.connect(TestSignal.class, handler);
    verify(store).assignHandler(TestSignal.class, handler);
  }
  
  @Test
  public void testDisconnect() {
    TestHandler handler = new TestHandler();
    dispatcher.disconnect(TestSignal.class, handler);
    verify(store).removeHandler(TestSignal.class, handler);
  }
  
  @Test
  public void testSend() {
    SignalDispatcher realDispatcher = new SignalDispatcher(new Configuration());
    SignalHandler<TestSignal> handler = mock(SignalHandler.class);
    realDispatcher.connect(TestSignal.class, handler);
    TestSignal signal = new TestSignal();
    realDispatcher.send(signal);
    verify(handler).execute(signal);
  }

}
