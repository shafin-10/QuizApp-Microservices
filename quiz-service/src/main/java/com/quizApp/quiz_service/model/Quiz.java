package com.quizApp.quiz_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@Entity
@Table(name = "quiz")
@Getter@Setter
public class Quiz {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @ElementCollection
    private List<Long> questionId;
}