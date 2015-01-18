# signalj - Java Signals micro framework
signalj is a micro framework implementing signals pattern in Java, inspired by Django signals.

## Concepts
This micro framework provides two main concepts: Signal and SignalHandler. Any class that implements Signal interface can be used 
to inform handlers about the event that needs to be handled. Signal class is passed to the handler and can contain any data needed 
to handle the signal.

SignalHandler exposes just one method execute(Signal). Once handler is assigned as a handler for any given Signal class, this method
will be called once the signal is sent. 

## Configuration
org.signalj.Configuration class is used to store signalj configuration. It has 2 parameters: SignalExecutor and SognalStore 
that allow to substitute the default implementations. Both parameters could be passed in the Configuration constructor:

	Configuration configuration = new Configuration(new LocalSignalStore(), new BasicExecutor());
	
SignalStore interface represents the storage for the signals. The default implementation LocalSignalStore is using HashMap 
to store signals to handlers mapping. If you need any kind of centralized or remote signal storage - you can provide your own implementation.

SignalExecutor interface represents the executor pattern. BasicExecutor implementation provides the most simplistic implementation of synchronious 
signal handling. Multi-threaded imlementation could be provided to handle signals asynchroniously.     

## Usage
You have to implement your own Signal classes, each one implementing the org.signalj.Signal interface:

	public class MySignal implements Signal {
	
		private final String message;
	
		public MySignal(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
	
	} 
	
SignalDispatcher is used to assign handlers to signals:

	public class MyApp {

		public static void main(String[] args) {
			SignalDispatcher dispatcher = new SignalDispatcher(new Configuration));
			dispatcher.connect(MySignal.class, new SignalHandler<MySignal> {
				public execute(MySignal signal) {
					System.out.println(signal.getMessage());
				}
			});
			MySender sender = new MySender(dispatcher);
			sender.performSomeTask();
		} 
	}

Sending the signals is also handled by the SignalDispatcher 

	public class MySender {
		
		private final SignalDispatcher dispatcher;

		public MySender(SignalDispatcher dispatcher) {
			// it is always better to use your dependency injection framework to inject dispatcher instance
			// wherever it is needed.
			this.dispatcher = dispatcher;
		}
		
		public void performSomeTask() {
			// peroform some task here
			dispatcher.send(new MySignal("Task performed successfully!"));
		}
	} 