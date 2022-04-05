package com.example.Wordle.dbService.repository;

import com.example.Wordle.dbService.entity.RequestAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestAttemptRepository extends JpaRepository<RequestAttemptEntity, String> {
    RequestAttemptEntity findByRequestId(String requestId);
}
