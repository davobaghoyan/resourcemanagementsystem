package com.exalt.resoursemanagementsystem.rest;

import com.exalt.resoursemanagementsystem.dto.ServerDTO;
import com.exalt.resoursemanagementsystem.exception.ErrorResponse;
import com.exalt.resoursemanagementsystem.exception.NotSufficientMemoryException;
import com.exalt.resoursemanagementsystem.exception.ServerNotFoundException;
import com.exalt.resoursemanagementsystem.service.ServerService;
import com.exalt.resoursemanagementsystem.service.ServerServiceImpl;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The ServerController is responsible for handling Server requests
 */
@Path("/server")
public class ServerController {
    private ServerService serverService;

    private static final Logger logger = Logger.getLogger(ServerController.class);

    /**
     * This is constructor for ServerController class, which creates new instances
     * and instantiates serverService field.
     */
    public ServerController(){
        serverService = new ServerServiceImpl();
    }

    /**
     * Gets server by id.
     * @param id
     * @return Response which contains ServerDTO in case of it exist
     */
    @GET
    @Path("/get/{id}")
    public Response getServer(@PathParam("id") int id) {
        ServerDTO serverDTO;

        try{
            serverDTO = serverService.getServerById(id);
        }
        catch (ServerNotFoundException e){
            logger.error("Server not found" + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage(), 404))
                    .build();
        }

        return Response.ok(serverDTO).build();
    }

    /**
     * Gets all servers represented by ServerDTO.
     * @return Response which contains all servers represented by ServerDTO
     */
    @GET
    @Path("/get")
    public Response getServers(){
        List<ServerDTO> servers = serverService.getServers();
        return Response.ok(servers).build();
    }

    /**
     * Allocates memory.
     * @param requestedMemory
     * @return Response which contains the ServerDTO of Server, which allocated memory
     */
    @PATCH
    @Path("/allocate/{requestedMemory}")
    public Response allocateMemory(@PathParam("requestedMemory") double requestedMemory) {
        ServerDTO serverDTO;

        try{
            serverDTO = serverService.allocate(requestedMemory);
        } catch (InterruptedException e) {
            logger.error("Unable to start server" + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage(), 500))
                    .build();
        } catch (NotSufficientMemoryException e) {
            logger.error("Not available memory" + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage(), 400))
                    .build();        }

        return Response.ok(serverDTO).build();
    }
}
