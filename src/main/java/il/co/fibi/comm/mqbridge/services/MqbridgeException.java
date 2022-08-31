package il.co.fibi.comm.mqbridge.services;

public class MqbridgeException extends Exception {
	
	private static final long serialVersionUID = -8751270948097648506L;

	public MqbridgeException() {
		super();
	}

	public MqbridgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MqbridgeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MqbridgeException(String message) {
		super(message);
	}

	public MqbridgeException(Throwable cause) {
		super(cause);
	}
}
