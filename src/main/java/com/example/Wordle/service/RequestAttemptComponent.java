package com.example.Wordle.service;

import com.example.Wordle.dbService.RequestAttemptDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestAttemptComponent {
    private final RequestAttemptDbService requestAttemptDbService;
    @Autowired
    public RequestAttemptComponent(RequestAttemptDbService requestAttemptDbService) {
        this.requestAttemptDbService = requestAttemptDbService;
    }

    public void updateAttemptCountForRequestId(String requestId, int attempt){
       requestAttemptDbService.saveRequestAttempt(attempt, requestId);
    }
    public int getAttemptCountForRequestId(String requestId){
        return requestAttemptDbService.getAttemptCountForRequestId(requestId);
    }
}
