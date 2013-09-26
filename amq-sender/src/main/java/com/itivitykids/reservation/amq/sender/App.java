package com.itivitykids.reservation.amq.sender;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.naming.NamingException;

import com.itivitykids.reservation.messaging.ITKTask;
import com.itivitykids.reservation.messaging.tasks.VendorSyncTask;

/**
 * Send a message to the ActiveMQ queue.
 * It should send the message to the ActiveMQ that the worker is listening.
 * It has a ITKReservationMessaging dependency, if the dependency is not solved
 * ensure that you have the messaging project opened and add the dependency.
 * 
 * Just change the message that you need to send compile all project first time
 * and run this file, that should be enough.
 * 
 * Don't forget change the IP of your ActiveMQ machine in the MSender.java file.
 *
 * @author Cesar Flores
 */
public class App {

    /**
     * Main method just to run the program.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            MSender sender = new MSender();
            Session session = sender.getSession();
            // Replate with the messge task that you want to send.
            ITKTask syncTask = createJRSyncTask();
            Message msg = session.createObjectMessage(syncTask);
            sender.send(msg);
            sender.close();
        } catch (JMSException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a MindBody sync message.
     * @return MindBody sync message task.
     */
    private static ITKTask createMBSyncTask() {
        return new VendorSyncTask(1);
    }

    /**
     * Creates a Jack Rabbit sync message.
     * @return Jack Rabbit sync message task.
     */
    private static ITKTask createJRSyncTask() {
        return new VendorSyncTask(2);
    }

    /**
     * Creates a GeenBook sync message.
     * @return GeenBook sync message task.
     */
    private static ITKTask createGBSyncTask() {
        return new VendorSyncTask(6);
    }
}
