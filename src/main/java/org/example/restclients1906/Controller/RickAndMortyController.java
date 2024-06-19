package org.example.restclients1906.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/char")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final CharacterService service;

    @GetMapping
    public List<RickAndMortyChar> getAllChars(){
        return service.getAllChars;
    }
}
