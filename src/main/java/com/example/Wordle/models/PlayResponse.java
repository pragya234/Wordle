package com.example.Wordle.models;

import lombok.Data;

@Data
public class PlayResponse {
    private String requestId;
    private int[] output;
}
