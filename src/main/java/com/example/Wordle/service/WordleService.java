package com.example.Wordle.service;

import com.example.Wordle.dbService.RequestWordDbService;
import com.example.Wordle.exception.WordleException;
import com.example.Wordle.models.PlayRequest;
import com.example.Wordle.models.PlayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordleService {
private final RequestAttemptComponent requestAttemptComponent;
private final WordProviderComponent wordProviderComponent;
private final RequestWordDbService requestWordDbService;
   @Autowired
   public WordleService(RequestAttemptComponent requestAttemptComponent, WordProviderComponent wordProviderComponent, RequestWordDbService requestWordDbService) {
      this.requestAttemptComponent = requestAttemptComponent;
      this.wordProviderComponent = wordProviderComponent;
      this.requestWordDbService = requestWordDbService;
   }

   public PlayResponse play(PlayRequest playRequest) throws WordleException {
      if(!(requestIdExists(playRequest.getRequestId()))){
         throw new WordleException("requestId does not exists, please start the game first", 100);
      }
     int attempt = requestAttemptComponent.getAttemptCountForRequestId(playRequest.getRequestId());
     if(attempt==6){
        throw new WordleException("max 6 attempts allowed", 101);
     }
      String actualWord = wordProviderComponent.provideWord(playRequest.getRequestId());
      int[] output = validateTheWord(playRequest.getInput(), actualWord);
      PlayResponse playResponse = new PlayResponse();
      playResponse.setRequestId(playRequest.getRequestId());
      playResponse.setOutput(output);
      requestAttemptComponent.updateAttemptCountForRequestId(playRequest.getRequestId(), ++attempt);
      return playResponse;
   }

   public String start(){
      String requestId = Utility.randomWord(10);
      wordProviderComponent.provideWord(requestId);
      return requestId;
   }

   private boolean requestIdExists(String requestId){
      return requestWordDbService.requestIdExists(requestId);
   }

   private int[] validateTheWord(String input, String actualWord){
      Map<Character, Integer> characterCount = new HashMap<>();
      for(int i=0; i<actualWord.length(); i++){
         char ch = actualWord.charAt(i);
         if(characterCount.containsKey(ch)){
            int count = characterCount.get(ch);
            count++;
            characterCount.put(ch, count);
         }else{
            characterCount.put(ch, 1);
         }
      }
      int[] output = new int[5];
      for(int i=0; i<input.length(); i++){
         if(characterCount.containsKey(input.charAt(i))){
            if(actualWord.charAt(i)==input.charAt(i)){
               output[i] = 0;
            }else{
               output[i] = 1;
            }
            int count = characterCount.get(input.charAt(i));
            if(count == 1){
               characterCount.remove(input.charAt(i));
            }else{
               count--;
               characterCount.put(input.charAt(i), count);
            }
         }else{
            output[i]=2;
         }
      }
      return output;
   }
}
