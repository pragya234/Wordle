package com.example.Wordle.dbService.entity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "request_word")
public class RequestWordEntity {
@Column(name="request_id")
@Id
private String requestId;
private String word;

}
