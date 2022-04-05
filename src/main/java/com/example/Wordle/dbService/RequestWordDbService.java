package com.example.Wordle.dbService;

public interface RequestWordDbService {
    String findWordForRequestId(String requestId);
    void saveRequestWord(String word, String requestId);
    boolean requestIdExists(String requestId);

}
