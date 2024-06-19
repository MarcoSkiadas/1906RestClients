package org.example.restclients1906.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<RickAndMortyCharacter> getAllChars(){

    }
}
