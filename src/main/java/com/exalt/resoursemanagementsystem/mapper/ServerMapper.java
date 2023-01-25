package com.exalt.resoursemanagementsystem.mapper;

import com.exalt.resoursemanagementsystem.dto.ServerDTO;
import com.exalt.resoursemanagementsystem.model.Server;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The ServerMapper interface contains methods for mapping
 * Server models to ServerDTO and vice versa.
 */
@Mapper
public interface ServerMapper {
    /**
     * maps Server model to ServerDTO
     * @param server
     * @return  ServerDTO
     */
    ServerDTO serverToDTO(Server server);

    /**
     * maps ServerDTO model to Server
     * @param serverDTO
     * @return  Server
     */
    Server DTOtoServer(ServerDTO serverDTO);

    /**
     * maps list of servers to the list of ServerDTO
     * @param servers
     * @return  list of ServerDTO
     */
    List<ServerDTO> serversToDTO(List<Server> servers);
}
