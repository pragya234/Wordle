package com.example.Wordle.dbService;

public interface RequestAttemptDbService {
   void saveRequestAttempt(int attempt, String requestId);
   int getAttemptCountForRequestId(String requestId);
}
