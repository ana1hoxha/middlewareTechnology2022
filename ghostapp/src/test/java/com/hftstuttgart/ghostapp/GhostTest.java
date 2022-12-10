package com.hftstuttgart.ghostapp;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GhostTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(GhostController.class).build();
    }

    @Test
    public void testLittleSpook() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/spooky/littlespook")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("...Boo!!");
    }

    @Test
    public void createGhost() throws Exception {
        String requestObject = objectMapper.writeValueAsString(new Ghost("Bob", GhostType.JIN, ThreadLevel.HIGH));
        MockHttpServletResponse response =
                mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(requestObject);
    }

    @Test
    public void createGhostandRetrieve() throws Exception{
        String requestObject = objectMapper.writeValueAsString(new Ghost("Bob",GhostType.JIN, ThreadLevel.HIGH));
        mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        MockHttpServletResponse response= mockMvc.perform(MockMvcRequestBuilders.get("/spooky/ghost?name=Bob")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(requestObject);
    }

    @Test
    public void createAndDeleteGhost() throws Exception{
        String requestObject = objectMapper.writeValueAsString(new Ghost("Bob",GhostType.JIN, ThreadLevel.HIGH));

        String requestObject2 = objectMapper.writeValueAsString(new Ghost("Bob", GhostType.JIN, ThreadLevel.HIGH));
        mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.put("/spooky/ghost").content(requestObject2).contentType(MediaType.APPLICATION_JSON));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/spooky/ghost?name=Bob")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(requestObject);


    }

    @Test
    public void createAndChangeGhost() throws Exception {
        String requestObject = objectMapper.writeValueAsString(new Ghost("Bob", GhostType.JIN, ThreadLevel.HIGH));
        String requestObject2 = objectMapper.writeValueAsString(new Ghost("Bob", GhostType.JIN, ThreadLevel.HIGH));
        mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/spooky/ghost").content(requestObject2).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(requestObject2);
    }





}