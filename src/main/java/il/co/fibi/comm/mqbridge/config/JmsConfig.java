package il.co.fibi.comm.mqbridge.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class JmsConfig {

	@Autowired
	private Environment env;

	private Queues cicsL3270;
	private Queues cics2L3270;
	private Queues cicsDpl;
	private Queues cics2Dpl;
	private Queues cicsCont;
	private Queues cics2Cont;

	public class Queues {
		public String sender;
		public String receiver;

		public Queues(String sender, String receiver) {
			this.sender = sender;
			this.receiver = receiver;
		}
	}

	public Queues getQueue(String name, String cicsid) {
		switch (cicsid) {
		case " ":
		case "":
			switch (name) {
			case "3270":
				return cicsL3270;
			case "DPL":
				return cicsDpl;
			case "CONT":
				return cicsCont;
			default:
				return null;
			}
		case "2":
			switch (name) {
			case "3270":
				return cics2L3270;
			case "DPL":
				return cics2Dpl;
			case "CONT":
				return cics2Cont;
			default:
				return null;
			}
		default:
			return null;
		}
	}

	@PostConstruct
	void initialize() {
		try {
			String receiver = env.getProperty("jms.cics.3270.receiver", String.class);
			String sender = env.getProperty("jms.cics.3270.sender", String.class);
			cicsL3270 = new Queues(sender, receiver);
			receiver = env.getProperty("jms.cics2.3270.receiver", String.class);
			sender = env.getProperty("jms.cics2.3270.sender", String.class);
			cics2L3270 = new Queues(sender, receiver);
			receiver = env.getProperty("jms.cics.dpl.receiver", String.class);
			sender = env.getProperty("jms.cics.dpl.sender", String.class);
			cicsDpl = new Queues(sender, receiver);
			receiver = env.getProperty("jms.cics2.dpl.receiver", String.class);
			sender = env.getProperty("jms.cics2.dpl.sender", String.class);
			cics2Dpl = new Queues(sender, receiver);
			receiver = env.getProperty("jms.cics.cont.receiver", String.class);
			sender = env.getProperty("jms.cics.cont.sender", String.class);
			cicsCont = new Queues(sender, receiver);
			receiver = env.getProperty("jms.cics2.cont.receiver", String.class);
			sender = env.getProperty("jms.cics2.cont.sender", String.class);
			cics2Cont = new Queues(sender, receiver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
