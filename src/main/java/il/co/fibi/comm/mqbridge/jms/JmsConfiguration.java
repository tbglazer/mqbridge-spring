package il.co.fibi.comm.mqbridge.jms;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.Queue;
import jakarta.jms.QueueConnectionFactory;

@ApplicationScoped
public class JmsConfiguration {

	private Queues cicsL3270;
	private Queues cics2L3270;
	private Queues cicsDpl;
	private Queues cics2Dpl;
	private Queues cicsCont;
	private Queues cics2Cont;

	public class Queues {
		public Queues(QueueConnectionFactory factory, Queue sender, Queue receiver) {
			this.factory = factory;
			this.sender = sender;
			this.receiver = receiver;
		}
		public QueueConnectionFactory factory;
		public Queue sender;
		public Queue receiver;
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
			Context ctx = new InitialContext();
			Config config = ConfigProvider.getConfig();
			QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup(config.getValue("jms.cics.3270.factory.jndiName", String.class));
			Queue receiver = (Queue) ctx.lookup(config.getValue("jms.cics.3270.receiver.jndiName", String.class));
			Queue sender = (Queue) ctx.lookup(config.getValue("jms.cics.3270.sender.jndiName", String.class));
			cicsL3270 = new Queues(factory, sender, receiver);
			factory = (QueueConnectionFactory) ctx.lookup(config.getValue("jms.cics2.3270.factory.jndiName", String.class));
			receiver = (Queue) ctx.lookup(config.getValue("jms.cics2.3270.receiver.jndiName", String.class));
			sender = (Queue) ctx.lookup(config.getValue("jms.cics2.3270.sender.jndiName", String.class));
			cics2L3270 = new Queues(factory, sender, receiver);
			factory = (QueueConnectionFactory) ctx.lookup(config.getValue("jms.cics.dpl.factory.jndiName", String.class));
			receiver = (Queue) ctx.lookup(config.getValue("jms.cics.dpl.receiver.jndiName", String.class));
			sender = (Queue) ctx.lookup(config.getValue("jms.cics.dpl.sender.jndiName", String.class));
			cicsDpl = new Queues(factory, sender, receiver);
			factory = (QueueConnectionFactory) ctx.lookup(config.getValue("jms.cics2.dpl.factory.jndiName", String.class));
			receiver = (Queue) ctx.lookup(config.getValue("jms.cics2.dpl.receiver.jndiName", String.class));
			sender = (Queue) ctx.lookup(config.getValue("jms.cics2.dpl.sender.jndiName", String.class));
			cics2Dpl = new Queues(factory, sender, receiver);
			factory = (QueueConnectionFactory) ctx.lookup(config.getValue("jms.cics.cont.factory.jndiName", String.class));
			receiver = (Queue) ctx.lookup(config.getValue("jms.cics.cont.receiver.jndiName", String.class));
			sender = (Queue) ctx.lookup(config.getValue("jms.cics.cont.sender.jndiName", String.class));
			cicsCont = new Queues(factory, sender, receiver);
			factory = (QueueConnectionFactory) ctx.lookup(config.getValue("jms.cics2.cont.factory.jndiName", String.class));
			receiver = (Queue) ctx.lookup(config.getValue("jms.cics2.cont.receiver.jndiName", String.class));
			sender = (Queue) ctx.lookup(config.getValue("jms.cics2.cont.sender.jndiName", String.class));
			cics2Cont = new Queues(factory, sender, receiver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
