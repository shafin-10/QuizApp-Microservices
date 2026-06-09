package com.quizApp.questionService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question_title;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String right_answer;

    private String difficultylevel;

    private String category;

}
