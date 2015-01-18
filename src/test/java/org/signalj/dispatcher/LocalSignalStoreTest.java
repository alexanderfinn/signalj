package org.signalj.dispatcher;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;
import org.signalj.Signal;
import org.signalj.SignalHandler;
import org.signalj.test.TestHandler;
import org.signalj.test.TestSignal;

public class LocalSignalStoreTest {
  
  @Test
  public void testAssignRemoveHandler() {
    LocalSignalStore store = new LocalSignalStore();
    Set<SignalHandler> handlers = store.getHandlers(TestSignal.class);
    assertEquals(0, handlers.size());

    TestHandler handler = new TestHandler();
    store.assignHandler(TestSignal.class, handler);
    
    handlers = store.getHandlers(TestSignal.class);
    assertEquals(1, handlers.size());
    
    store.removeHandler(TestSignal.class, handler);
    handlers = store.getHandlers(TestSignal.class);
    assertEquals(0, handlers.size());
  }

}
