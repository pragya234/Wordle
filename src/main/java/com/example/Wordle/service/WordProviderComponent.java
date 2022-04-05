package com.example.Wordle.service;

import com.example.Wordle.dbService.RequestWordDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.example.Wordle.service.Utility.randomWord;

@Component
public class WordProviderComponent {
    private final static String WORD_GENERATION_POLICY= "FOR_NEW_REQUEST_ID";
    private final RequestWordDbService requestWordDbService;
    @Autowired
    public WordProviderComponent(RequestWordDbService requestWordDbService) {
        this.requestWordDbService = requestWordDbService;
    }

    public String provideWord(String requestId){
        return generateWordForNewRequestId(requestId);
    }

    private String generateWordForNewRequestId(String requestId){
        String existingWord = requestWordDbService.findWordForRequestId(requestId);
     if(existingWord!=null){
         return existingWord;
     }
     String randomWord = randomWord(5);
     requestWordDbService.saveRequestWord(randomWord, requestId);
     return randomWord;
    }


}
