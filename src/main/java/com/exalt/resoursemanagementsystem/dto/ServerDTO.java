package com.exalt.resoursemanagementsystem.dto;

import com.exalt.resoursemanagementsystem.model.enums.ServerStatus;

/**
 * The ServerDTO class represents data transfer object for Server model
 */
public class ServerDTO {
    private int id;
    private double freeMemory;
    private double occupancy;
    private ServerStatus status;

    /**
     * Gets id
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets freeMemory
     * @return  freeMemory
     */
    public double getFreeMemory() {
        return this.freeMemory;
    }

    /**
     * Gets occupancy.
     * @return occupancy
     */
    public double getOccupancy() {
        return this.occupancy;
    }

    /**
     * Gets status.
     * @return status
     */
    public ServerStatus getStatus() {
        return this.status;
    }

    /**
     * Sets id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets occupancy.
     * @param occupancy
     */
    public void setOccupancy(double occupancy) {
        this.occupancy = occupancy;
    }

    /**
     * Sets freeMemory.
     * @param freeMemory
     */
    public void setFreeMemory(double freeMemory) {
        this.freeMemory = freeMemory;
    }

    /**
     * Sets status.
     * @param status
     */
    public void setStatus(ServerStatus status) {
        this.status = status;
    }

    /**
     * Overrides equals method
     * @param o
     */
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof ServerDTO)) {
            return false;
        }

        ServerDTO c = (ServerDTO) o;

        return Integer.compare(id, c.id) == 0
                && Double.compare(occupancy, c.occupancy) == 0
                && Double.compare(freeMemory, c.freeMemory) == 0;
    }
}
