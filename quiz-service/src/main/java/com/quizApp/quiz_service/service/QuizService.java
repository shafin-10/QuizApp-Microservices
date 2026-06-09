package com.quizApp.quiz_service.service;

import com.quizApp.quiz_service.feign.QuizInterface;
import com.quizApp.quiz_service.model.QuestionWrapper;
import com.quizApp.quiz_service.model.Quiz;
import com.quizApp.quiz_service.model.Response;
import com.quizApp.quiz_service.repo.QuizRepo;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<Quiz> createQuiz(String category, Integer numQ, String title) {
      List<Long> questions = quizInterface.generateQuestionsForQuiz(category, numQ).getBody();
      Quiz quiz = new Quiz();
      quiz.setTitle(title);
      quiz.setQuestionId(questions);
      quizRepo.save(quiz);
      return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer Id) {
        Quiz quiz = quizRepo.findById(Id).get();
        List<Long> questionTds = quiz.getQuestionId();
        List<QuestionWrapper> questions = quizInterface.getQuestions(questionTds).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    public ResponseEntity<Integer> calculateResults(Integer id, List<Response> response) {
        Integer score = quizInterface.getScore(response).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(score);
    }
}
