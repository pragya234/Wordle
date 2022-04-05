package com.example.Wordle.dbService.impl;

import com.example.Wordle.dbService.RequestAttemptDbService;
import com.example.Wordle.dbService.entity.RequestAttemptEntity;
import com.example.Wordle.dbService.repository.RequestAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestAttemptDbServiceImpl implements RequestAttemptDbService {
    private final RequestAttemptRepository requestAttemptRepository;
    @Autowired
    public RequestAttemptDbServiceImpl(RequestAttemptRepository requestAttemptRepository) {
        this.requestAttemptRepository = requestAttemptRepository;
    }

    @Override
    public void saveRequestAttempt(int attempt, String requestId) {
        RequestAttemptEntity requestAttemptEntity = new RequestAttemptEntity();
        requestAttemptEntity.setRequestId(requestId);
        requestAttemptEntity.setAttempt(attempt);
        requestAttemptRepository.save(requestAttemptEntity);
    }

    @Override
    public int getAttemptCountForRequestId(String requestId) {
        RequestAttemptEntity requestAttemptEntity = requestAttemptRepository.findByRequestId(requestId);
        if(requestAttemptEntity!=null){
            return requestAttemptEntity.getAttempt();
        }
        return 0;
    }

}
