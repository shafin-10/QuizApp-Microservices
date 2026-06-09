package com.quizApp.quiz_service.feign;

import com.quizApp.quiz_service.model.QuestionWrapper;
import com.quizApp.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE", path = "/api")
public interface QuizInterface {
    // generate questions
    @GetMapping("questions/generate")
    ResponseEntity<List<Long>> generateQuestionsForQuiz(
            @RequestParam("quesCategory") String quesCategory,
            @RequestParam("NoQ") Integer NoQ
    );

    //get questions by id from list of ids
    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Long> questionIds);

    //get score
    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
