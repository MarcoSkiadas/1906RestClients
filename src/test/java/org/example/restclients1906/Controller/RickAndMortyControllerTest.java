package org.example.restclients1906.Controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RickAndMortyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }
    @AfterAll
    static void shutDown() throws IOException {
        mockWebServer.shutdown();
    }
    @DynamicPropertySource
    static void backendProps(DynamicPropertyRegistry registry) {
        registry.add("RICK_URL", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getAllChars_shouldReturnAllChars_whenCalled() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                      "info": {
                                          "count": 439,
                                          "pages": 22,
                                          "next": "https://rickandmortyapi.com/api/character/?page=2&status=alive",
                                          "prev": null
                                      },
                                      "results": [
                                          {
                                              "id": 1,
                                              "name": "Rick Sanchez",
                                              "status": "Alive",
                                              "species": "Human",
                                              "type": "",
                                              "gender": "Male",
                                              "origin": {
                                                  "name": "Earth (C-137)",
                                                  "url": "https://rickandmortyapi.com/api/location/1"
                                              },
                                              "location": {
                                                  "name": "Citadel of Ricks",
                                                  "url": "https://rickandmortyapi.com/api/location/3"
                                              },
                                              "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                              "episode": [
                                                  "https://rickandmortyapi.com/api/episode/1"
                                              ],
                                              "url": "https://rickandmortyapi.com/api/character/1",
                                              "created": "2017-11-04T18:48:46.250Z"
                                          }
                                          ]
                                          }
                        """));
        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/char"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
      [
        {
        "id": 1,
        "name": "Rick Sanchez",
        "species": "Human"
        }
      ]
"""));
    }
    @Test
    void getChar_shouldReturnRickSanchez_whenCalledWithId1() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                                       "id": 1,
                                                                       "name": "Rick Sanchez",
                                                                       "status": "Alive",
                                                                       "species": "Human",
                                                                       "type": "",
                                                                       "gender": "Male",
                                                                       "origin": {
                                                                           "name": "Earth (C-137)",
                                                                           "url": "https://rickandmortyapi.com/api/location/1"
                                                                       },
                                                                       "location": {
                                                                           "name": "Citadel of Ricks",
                                                                           "url": "https://rickandmortyapi.com/api/location/3"
                                                                       },
                                                                       "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                                       "episode": [
                                                                           "https://rickandmortyapi.com/api/episode/1"
                                                                       ],
                                                                       "url": "https://rickandmortyapi.com/api/character/1",
                                                                       "created": "2017-11-04T18:48:46.250Z"
                                                                   }
                        """));
        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/char/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
      
        {
        "id": 1,
        "name": "Rick Sanchez",
        "species": "Human"
        }
      
"""));
    }

    @Test
    void getSpeciesStatistic_shouldReturn1_whenCalledWithHuman() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("""
                        {
                                                                       "info": {
                                                                           "count": 245,
                                                                           "pages": 13,
                                                                           "next": "https://rickandmortyapi.com/api/character/?page=2&status=alive&species=Human",
                                                                           "prev": null
                                                                       },
                                                                       "results": [
                                                                           {
                                                                               "id": 1,
                                                                               "name": "Rick Sanchez",
                                                                               "status": "Alive",
                                                                               "species": "Human",
                                                                               "type": "",
                                                                               "gender": "Male",
                                                                               "origin": {
                                                                                   "name": "Earth (C-137)",
                                                                                   "url": "https://rickandmortyapi.com/api/location/1"
                                                                               },
                                                                               "location": {
                                                                                   "name": "Citadel of Ricks",
                                                                                   "url": "https://rickandmortyapi.com/api/location/3"
                                                                               },
                                                                               "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                                                               "episode": [
                                                                                   "https://rickandmortyapi.com/api/episode/1"
                                                                               ],
                                                                               "url": "https://rickandmortyapi.com/api/character/1",
                                                                               "created": "2017-11-04T18:48:46.250Z"
                                                                           }
                                                                           ]
                                                                           }
                       """));
        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/char/species-statistic?status=Human"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }


}