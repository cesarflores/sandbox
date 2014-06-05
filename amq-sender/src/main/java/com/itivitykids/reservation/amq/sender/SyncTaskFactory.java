package com.itivitykids.reservation.amq.sender;

import com.itivitykids.reservation.messaging.ITKTask;
import com.itivitykids.reservation.messaging.tasks.VendorSyncTask;

/**
 * Factory of sync tasks.
 *
 * @author Cesar Flores
 */
public class SyncTaskFactory {

    /**
     * Creates a sync task for the given vendor type.
     *
     * @param syncMessageText for which the sync task will be created.
     * @return sync task message.
     */
    public static ITKTask getSyncTask(String syncMessageText) {
        return new VendorSyncTask(syncMessageText);
    }
}
