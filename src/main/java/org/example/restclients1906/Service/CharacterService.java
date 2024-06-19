package org.example.restclients1906.Service;

import org.example.restclients1906.Model.RickAndMortyCharacter;
import org.example.restclients1906.Model.RickAndMortyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.List;

@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService(@Value("${RICK_URL}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<RickAndMortyCharacter> getAllChars() throws IOException {
        RickAndMortyResponse response = restClient.get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyResponse.class);
        if (response != null) {
            return response.results();
        } else {
            throw new IOException("No Data Found");
        }

    }

    public List<RickAndMortyCharacter> checkIfAliveChars(String status) throws IOException {
        String uri = "/character?status=" + status;
        RickAndMortyResponse response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(RickAndMortyResponse.class);
        if (response != null) {
            return response.results();
        } else {
            throw new IOException("No Data Found");
        }
    }

    public RickAndMortyCharacter getCharById(int id) throws IOException {
        String uri = "/character/" + id;
        RickAndMortyCharacter response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(RickAndMortyCharacter.class);
        if (response != null) {
            return response;
        } else {
            throw new IOException("No Data Found");
        }
    }

    public int getSpeciesStatistic(String species) throws IOException {
        String uri = "/character/?status=alive&species=" + species;
        RickAndMortyResponse response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(RickAndMortyResponse.class);
        if (response != null) {
            return response.results().size();
        } else {
            throw new IOException("No Data Found");
        }

    }
}
