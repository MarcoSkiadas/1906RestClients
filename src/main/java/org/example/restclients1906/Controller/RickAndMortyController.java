package org.example.restclients1906.Controller;

import lombok.RequiredArgsConstructor;
import org.example.restclients1906.Model.RickAndMortyCharacter;
import org.example.restclients1906.Service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/char")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final CharacterService service;

    @GetMapping
    public List<RickAndMortyCharacter> getAllChars() throws IOException {
        return service.getAllChars();
    }
}
