package com.quizApp.questionService.controller;

import com.quizApp.questionService.model.Question;
import com.quizApp.questionService.model.QuestionWrapper;
import com.quizApp.questionService.model.Response;
import com.quizApp.questionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());
    }

    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question){
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory
            (@PathVariable(name = "category") String category){
        List<Question> questions = questionService.findByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }


    // generate questions
    @GetMapping("/generate")
    public ResponseEntity<List<Long>> generateQuestionsForQuiz(@RequestParam String quesCategory, @RequestParam Integer NoQ){
        List<Long> questions = questionService.generateQuestionsForQuiz(quesCategory, NoQ);
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    //get questions by id from list of ids
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Long> questionIds){
        return questionService.getQuestions(questionIds);
    }

    //get score
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }
}