package com.exalt.resoursemanagementsystem.service;

import com.exalt.resoursemanagementsystem.dto.ServerDTO;
import com.exalt.resoursemanagementsystem.exception.NotSufficientMemoryException;
import com.exalt.resoursemanagementsystem.exception.ServerNotFoundException;
import com.exalt.resoursemanagementsystem.mapper.ServerMapper;
import com.exalt.resoursemanagementsystem.model.Server;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The ServerServiceImpl class represents the logic of operating with servers implementing ServerService method
 */
public class ServerServiceImpl implements ServerService{
    public static List<Server> activeServers = new ArrayList<>();
    private ReadWriteLock lock;
    private ServerMapper serverMapper;

    /**
     * This is constructor for ServerServiceImpl class, which creates new instances
     * and instantiates lock and serverMapper fields.
     */
    public ServerServiceImpl(){
        lock = new ReentrantReadWriteLock();
        serverMapper = Mappers.getMapper(ServerMapper.class);
    }

    /**
     * Gets all servers represented by ServerDTO.
     * @return List which contains all servers represented by ServerDTO
     */
    public List<ServerDTO> getServers(){
        return serverMapper.serversToDTO(activeServers);
    }

    /**
     * Gets server by id.
     * @return ServerDTO which represents Server model
     */
    public ServerDTO getServerById(int id) throws ServerNotFoundException {
        Optional<Server> optionalServer = activeServers.stream().
                filter(e -> e.getId() == id)
                .findFirst();

        Server server = optionalServer.orElseThrow(() -> new ServerNotFoundException());
        return serverMapper.serverToDTO(server);
    }

    /**
     * Allocates memory.
     * @param requestedMemory
     * @return ServerDTO of the Server which allocated memory
     */
    public ServerDTO allocate(double requestedMemory) throws NotSufficientMemoryException, InterruptedException {
        Server server;

        if(requestedMemory > Server.MAX_SIZE){
            throw new NotSufficientMemoryException();
        }

        server = activeServers.stream()
                .filter(s -> s.getFreeMemory() >= requestedMemory)
                .sorted((s1, s2) -> (int)(s1.getFreeMemory() - s2.getFreeMemory()))
                .findFirst().orElse(null);

        Lock writeLock = lock.writeLock();

        try {
            writeLock.lock();

            if (server == null) {
                server = createServer();
            }

            server.allocate(requestedMemory);
            return serverMapper.serverToDTO(server);
        }
        finally {
            writeLock.unlock();
        }
    }

    /**
     * Creates new Server.
     * @return Server
     */
    public Server createServer() throws InterruptedException {
        Server server = new Server();
        server.start();
        activeServers.add(server);

        return server;
    }


}
