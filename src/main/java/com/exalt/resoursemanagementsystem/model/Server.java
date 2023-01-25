package com.exalt.resoursemanagementsystem.model;

import com.exalt.resoursemanagementsystem.exception.NotSufficientMemoryException;
import com.exalt.resoursemanagementsystem.model.enums.ServerStatus;
import org.apache.log4j.Logger;

/**
 * The Server class represents Server model
 */
public class Server {
    public static final double MAX_SIZE = 100;
    public static int COUNT = 0;
    private static final Logger logger = Logger.getLogger(Server.class);
    private int id;
    private double freeMemory;
    private double occupancy;
    private ServerStatus status;

    /**
     * This is constructor for Server class, which creates new instances.
     */
    public Server(){
        this.id = ++COUNT;
        this.freeMemory = MAX_SIZE;
        this.occupancy = 0;
        this.status = ServerStatus.NEW;
    }

    /**
     * Gets id
     * @return  id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Gets freeMemory
     * @return  freeMemory
     */
    public double getFreeMemory() {
        return freeMemory;
    }

    /**
     * Gets occupancy.
     * @return  occupancy
     */
    public double getOccupancy(){
       return this.occupancy;
    }

    /**
     * Gets status.
     * @return  status
     */
    public ServerStatus getStatus(){
        return this.status;
    }

    /**
     * allocate method allocates memory if it is available, throws NotSufficientMemoryException otherwise.
     * @param requestedMemory
     */
    public void allocate(double requestedMemory) throws NotSufficientMemoryException {
        if(requestedMemory > freeMemory){
            throw new NotSufficientMemoryException();
        }

        this.occupancy += requestedMemory;
        this.freeMemory -= requestedMemory;
    }

    /**
     * start method starts server in a synchronized way
     */
    public synchronized void  start() throws InterruptedException {
            Thread.sleep(60000);
            status = ServerStatus.ACTIVE;
    }
}
