package com.itivitykids.reservation.amq.sender;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.naming.NamingException;

import com.itivitykids.reservation.messaging.ITKTask;

import static com.itivitykids.reservation.amq.sender.SyncTaskFactory.getSyncTask;

/**
 * Send a message to the ActiveMQ queue. It should send the message to the
 * ActiveMQ that the worker is listening. It has a ITKReservationMessaging
 * dependency, if the dependency is not solved ensure that you have the
 * messaging project opened and add the dependency.
 *
 * Don't forget change the IP of your ActiveMQ machine in the MSender.java file.
 *
 * @author Cesar Flores
 */
public class RunSync {

    /**
     * Runs sync for the given vendor id.
     * @param syncMessageText sync message to send.
     */
    public static void run(String syncMessageText) {
        try {
            MSender sender = new MSender();
            Session session = sender.getSession();
            // Replate with the vendor type for the message.
            ITKTask syncTask = getSyncTask(syncMessageText);
            Message msg = session.createObjectMessage(syncTask);
            sender.send(msg);
            sender.close();
        } catch (JMSException ex) {
            Logger.getLogger(RunSync.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RunSync.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
