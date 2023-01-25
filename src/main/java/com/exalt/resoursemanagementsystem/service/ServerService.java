package com.exalt.resoursemanagementsystem.service;

import com.exalt.resoursemanagementsystem.dto.ServerDTO;
import com.exalt.resoursemanagementsystem.exception.NotSufficientMemoryException;
import com.exalt.resoursemanagementsystem.exception.ServerNotFoundException;
import com.exalt.resoursemanagementsystem.model.Server;

import java.util.List;

/**
 * The ServerService interface represents the abstraction of the logic of operating with servers
 */
public interface ServerService {
    /**
     * Gets all servers represented by ServerDTO.
     * @return List which contains all servers represented by ServerDTO
     */
    List<ServerDTO> getServers();
    /**
     * Gets server by id.
     * @return ServerDTO which represents Server model
     */
    ServerDTO getServerById(int id) throws ServerNotFoundException;
    /**
     * Allocates memory.
     * @param requestedMemory
     * @return ServerDTO of the Server which allocated memory
     */
    ServerDTO allocate(double requestedMemory) throws NotSufficientMemoryException, InterruptedException;
    /**
     * Creates new Server.
     * @return Server
     */
    Server createServer() throws InterruptedException;
}
