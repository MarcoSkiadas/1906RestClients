package org.example.restclients1906.Service;

import org.example.restclients1906.Model.RickAndMortyCharacter;
import org.example.restclients1906.Model.RickAndMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.List;

@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<RickAndMortyCharacter> getAllChars() throws IOException {
        RickAndMortyResponse response = restClient.get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyResponse.class);
        if(response != null){
            return response.results();
        }
        else {
            throw new IOException("No Data Found");
        }

    }
}
