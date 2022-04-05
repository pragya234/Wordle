package com.example.Wordle.models;

import lombok.Data;

@Data
public class PlayRequest {
    private String requestId;
    private String input;
}
