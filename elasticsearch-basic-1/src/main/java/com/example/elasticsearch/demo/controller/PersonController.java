package com.example.elasticsearch.demo.controller;

import com.example.elasticsearch.demo.entity.Person;
import com.example.elasticsearch.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/{search}")
    public ResponseEntity<List<Person>> getPersonList(@PathVariable String search) {
        List<Person> personList = personRepository.getByCustomQuery(search);
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/{name}/{surname}")
    public ResponseEntity<List<Person>> getPersonListLike(@PathVariable String name, @PathVariable String surname) {
        List<Person> personList = personRepository.findByNameLikeOrSurnameLike(name, surname);
        return ResponseEntity.ok(personList);
    }

    @PostMapping
    public void savePerson(@RequestBody final Person person) {
        personRepository.save(person);
    }
}
