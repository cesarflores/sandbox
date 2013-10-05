package com.itivitykids.reservation.amq.sender;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Sends the message to the broker.
 *
 * @author Giovanny Delgadillo
 */
public class MSender {

    private static final Logger LOGGER = Logger.getLogger(MSender.class.getName());
    
    /*
     * JMS destination resource queue name.
     */
    public static final String QUEUE_NAME = "jms/ITKReservationQueue";
    
    /*
     * JMS resource factory implementation name.
     */
    // FIXME: Change it with the ip of where your ActiveMQ is running.
    public static final String CONNECTION_FACTORY_NAME = "tcp://10.31.129.162:61616";
    
    /*
     * JMS connection factory reference.
     */
    private ConnectionFactory factory;
    
    /*
     * JMS connection reference.
     */
    private Connection connection;
    
    /*
     * JMS session seference.
     */
    private Session session;
    
    /*
     * JMS message producer and sender reference.
     */
    private MessageProducer producer;
    
    /*
     * JMS destination reference.
     */
    private Destination destination;

    /**
     * Creating a new Sender instance.
     * 
     * @throws JMSException in case of exceptions at creating time.
     * @throws NamingException in case to exception at lookup time.
     */
    public MSender() throws JMSException, NamingException {
        factory = new ActiveMQConnectionFactory(CONNECTION_FACTORY_NAME);
        /*InitialContext context = new InitialContext();
        factory = (ConnectionFactory) context.lookup(CONNECTION_FACTORY_NAME);*/
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(QUEUE_NAME);
        producer = session.createProducer(destination);
    }

    /**
     * Sends the message to the Broker.
     * 
     * @param message to me send to queue
     * @throws JMSException in case of error in the send process.
     */
    public void send(Message message) throws JMSException {
        LOGGER.log(Level.INFO, "Sending message");
        producer.send(message);
    }

    /**
     * Closes all the connections with the Broker.
     * 
     * @throws JMSException in case or error at close time.
     */
    public void close() throws JMSException {
        LOGGER.log(Level.INFO, "Closing Connections");
        if (connection != null) {
            connection.close();
        }
        if (session != null) {
            session.close();
        }
    }

    /**
     * Gest a current session with the Broker.
     * 
     * @return session with the Broker.
     */
    public Session getSession() {
        return this.session;
    }
}
