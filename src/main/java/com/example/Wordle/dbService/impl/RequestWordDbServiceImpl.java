package com.example.Wordle.dbService.impl;

import com.example.Wordle.dbService.RequestWordDbService;
import com.example.Wordle.dbService.entity.RequestWordEntity;
import com.example.Wordle.dbService.repository.RequestWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestWordDbServiceImpl implements RequestWordDbService {
    private final RequestWordRepository requestWordRepository;
    @Autowired
    public RequestWordDbServiceImpl(RequestWordRepository requestWordRepository) {
        this.requestWordRepository = requestWordRepository;
    }

    @Override
    public String findWordForRequestId(String requestId) {
        RequestWordEntity requestWordEntity = requestWordRepository.findByRequestId(requestId);
        if(requestWordEntity != null){
            return requestWordEntity.getWord();
        }
        return null;
    }

    @Override
    public void saveRequestWord(String word, String requestId) {
        if(findWordForRequestId(requestId)!= null){
            return;
        }
        RequestWordEntity requestWordEntity = new RequestWordEntity();
        requestWordEntity.setRequestId(requestId);
        requestWordEntity.setWord(word);
        requestWordRepository.save(requestWordEntity);
    }

    @Override
    public boolean requestIdExists(String requestId) {
        RequestWordEntity requestWordEntity = requestWordRepository.findByRequestId(requestId);
        if(requestWordEntity != null){
            return true;
        }
        return false;
    }
}
