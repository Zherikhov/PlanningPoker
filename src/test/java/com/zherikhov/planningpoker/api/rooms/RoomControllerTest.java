package com.zherikhov.planningpoker.api.rooms;

import com.zherikhov.planningpoker.application.rooms.CreateRoomService;
import com.zherikhov.planningpoker.application.rooms.RoomQueryService;
import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateRoomService createRoomService;

    @MockBean
    private RoomQueryService roomQueryService;

    @Test
    void createRoom_ReturnsCreatedRoom() throws Exception {
        Room room = new Room(new RoomId("id1"), "Test room", "Desc", RoomStatus.OPEN);
        when(createRoomService.createRoom("Test room", "Desc")).thenReturn(room);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test room\",\"description\":\"Desc\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("id1"))
                .andExpect(jsonPath("$.name").value("Test room"))
                .andExpect(jsonPath("$.description").value("Desc"))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    @Test
    void createRoom_InvalidName_ReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getRooms_ReturnsListOfRooms() throws Exception {
        Room room = new Room(new RoomId("id1"), "Room1", "Desc1", RoomStatus.OPEN);
        when(roomQueryService.findAll()).thenReturn(List.of(room));

        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("id1"))
                .andExpect(jsonPath("$[0].name").value("Room1"))
                .andExpect(jsonPath("$[0].description").value("Desc1"))
                .andExpect(jsonPath("$[0].status").value("OPEN"));
    }
}
