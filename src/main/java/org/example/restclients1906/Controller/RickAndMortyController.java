package org.example.restclients1906.Controller;

import lombok.RequiredArgsConstructor;
import org.example.restclients1906.Model.RickAndMortyCharacter;
import org.example.restclients1906.Service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/char")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final CharacterService service;

    @GetMapping
    public List<RickAndMortyCharacter> getAllChars(@RequestParam(required = false, defaultValue = "")String status) throws IOException {
        if (status.equals("")) {
            return service.getAllChars();
        }
        else {
            return service.checkIfAliveChars(status);
        }

    }
    @GetMapping("/{id}")
    public RickAndMortyCharacter getChar(@PathVariable int id) throws IOException {
        return service.getCharById(id);
    }
    @GetMapping("/species-statistic")
    public int getSpeciesStatistic(@RequestParam(required = false, defaultValue = "")String species) throws IOException {
        return service.getSpeciesStatistic(species);
    }

}
