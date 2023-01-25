package resoursemanagementsystem.service;

import com.exalt.resoursemanagementsystem.dto.ServerDTO;
import com.exalt.resoursemanagementsystem.exception.NotSufficientMemoryException;
import com.exalt.resoursemanagementsystem.exception.ServerNotFoundException;
import com.exalt.resoursemanagementsystem.model.Server;
import com.exalt.resoursemanagementsystem.model.enums.ServerStatus;
import com.exalt.resoursemanagementsystem.service.ServerServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServerServiceImplTest {

    private ServerServiceImpl serverService;

    @BeforeAll
    void setUp() {
        serverService = new ServerServiceImpl();
    }

    @Test
    public void getServers_Return_EmptyList() {
        List<ServerDTO> servers = serverService.getServers();

        assertEquals(servers.size(), 0);
    }

    @Test
    public void getServers_Return_ServerDTO() {
        Server server = new Server();
        ServerDTO serverDTO = new ServerDTO();
        ServerServiceImpl.activeServers.add(server);
        serverDTO.setId(server.getId());
        serverDTO.setOccupancy(server.getOccupancy());
        serverDTO.setStatus(server.getStatus());
        serverDTO.setFreeMemory(server.getFreeMemory());
        List<ServerDTO> expected = new ArrayList<>();
        expected.add(serverDTO);

        List<ServerDTO> result = serverService.getServers();

        assertEquals(expected, result);
    }

    @Test
    public void getServerById_Throws_ServerNotFoundException() {
        assertThrows(ServerNotFoundException.class,
                () -> serverService.getServerById(6));
    }

    @Test
    public void getServerById_Returns_Dto() {
        Server server = new Server();
        ServerDTO expected = new ServerDTO();
        ServerServiceImpl.activeServers.add(server);
        expected.setId(server.getId());
        expected.setOccupancy(server.getOccupancy());
        expected.setStatus(server.getStatus());
        expected.setFreeMemory(server.getFreeMemory());

        assertDoesNotThrow(() ->
                assertEquals(expected, serverService.getServerById(1)));
    }

    @Test
    public void allocate_Throws_NotSufficientMemoryException() {
        assertThrows(NotSufficientMemoryException.class,
                () -> serverService.allocate(101));
    }

    @Test
    public void allocate_Returns_ServerDto() {
        double requestedMemory = 40;
        Server server = new Server();
        ServerDTO expected = new ServerDTO();
        ServerServiceImpl.activeServers.add(server);
        expected.setId(server.getId());
        expected.setOccupancy(requestedMemory);
        expected.setStatus(server.getStatus());
        expected.setFreeMemory(100 - requestedMemory);

        assertDoesNotThrow(() ->
                assertEquals(expected, serverService.allocate(requestedMemory)));
    }

    @Test
    public void allocate_Creates_NewServer() throws NotSufficientMemoryException {
        double requestedMemory = 40;
        Server server = new Server();
        server.allocate(80);
        ServerDTO expected = new ServerDTO();
        ServerServiceImpl.activeServers.add(server);
        expected.setId(2);
        expected.setOccupancy(requestedMemory);
        expected.setStatus(ServerStatus.ACTIVE);
        expected.setFreeMemory(100 - requestedMemory);

        assertDoesNotThrow(() ->
                assertEquals(expected, serverService.allocate(requestedMemory)));
    }
}
