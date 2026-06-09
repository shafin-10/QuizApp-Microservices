package com.quizApp.quiz_service.controller;


import com.quizApp.quiz_service.dto.QuizDto;
import com.quizApp.quiz_service.feign.QuizInterface;
import com.quizApp.quiz_service.model.QuestionWrapper;
import com.quizApp.quiz_service.model.Quiz;
import com.quizApp.quiz_service.model.Response;
import com.quizApp.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.categoryName(), quizDto.numQuestions(), quizDto.title());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizquistion(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> calculateResults(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.calculateResults(id, response);
    }

}

