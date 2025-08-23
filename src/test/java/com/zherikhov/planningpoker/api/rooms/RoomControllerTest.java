package com.zherikhov.planningpoker.api.rooms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zherikhov.planningpoker.domain.votes.DeckType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createAndListRoom() throws Exception {
        CreateRoomRequest request = new CreateRoomRequest("Sprint 26", "Planning", DeckType.FIBONACCI, true);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.code").isNotEmpty());

        mockMvc.perform(get("/api/rooms").param("mine", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Sprint 26"));
    }
}

