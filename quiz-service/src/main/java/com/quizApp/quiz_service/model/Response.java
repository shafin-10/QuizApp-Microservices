package com.quizApp.quiz_service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Response {
    private Long id;
    private String response;
}

