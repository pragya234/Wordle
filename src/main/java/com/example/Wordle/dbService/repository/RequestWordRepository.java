package com.example.Wordle.dbService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Wordle.dbService.entity.RequestWordEntity;

public interface RequestWordRepository extends JpaRepository<RequestWordEntity, String>{

    RequestWordEntity findByRequestId(String requestId);

}
