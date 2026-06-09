package com.quizApp.quiz_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionWrapper {

    private Long id;

    private String question_title;

    private String option1;

    private String option2;

    private String option3;

    private String option4;
}
