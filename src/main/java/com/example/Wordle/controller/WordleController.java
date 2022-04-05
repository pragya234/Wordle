package com.example.Wordle.controller;

import com.example.Wordle.exception.WordleException;
import com.example.Wordle.models.PlayRequest;
import com.example.Wordle.models.PlayResponse;
import com.example.Wordle.service.WordleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/wordle")
public class WordleController {

private final WordleService wordleService;
    @Autowired
    public WordleController(WordleService wordleService) {
        this.wordleService = wordleService;
    }

    @RequestMapping(method = POST, value = "/play")
    public @ResponseBody ResponseEntity<PlayResponse> play(@RequestBody PlayRequest playRequest) throws WordleException {
     PlayResponse playResponse = wordleService.play(playRequest);
     return ResponseEntity.ok(playResponse);
   }

    @RequestMapping(method = POST, value = "/start")
    public String start(){
        String requestId = wordleService.start();
        return requestId;
    }

}
