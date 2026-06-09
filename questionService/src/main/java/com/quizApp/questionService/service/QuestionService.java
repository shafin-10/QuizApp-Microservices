package com.quizApp.questionService.service;

import com.quizApp.questionService.model.Question;
import com.quizApp.questionService.model.QuestionWrapper;
import com.quizApp.questionService.model.Response;
import com.quizApp.questionService.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public List<Question> getAllQuestions(){
        return questionRepo.findAll();
    }

    public Question saveQuestion(Question question){
        return questionRepo.save(question);
    }


    public List<Question> findByCategory(String category) {
        return questionRepo.findByCategoryIgnoreCase(category);
    }


    public List<Long> generateQuestionsForQuiz(String quesCategory, Integer noQ) {
        List<Long> questions = questionRepo.findRandomQuestionsByCategory(quesCategory, noQ);
        return questions;
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Long> questionIds) {
        List<Question> questionList = new ArrayList<>();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();

        for(Long i : questionIds){
            Question question = questionRepo.getById(i);
            questionList.add(question);
        }

        for(Question question : questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestion_title(question.getQuestion_title());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            questionWrapperList.add(questionWrapper);
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionWrapperList);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        Integer score = 0;

        for(Response response : responses){
            Question question = questionRepo.getById(response.getId());
            if(question.getRight_answer().equals(response.getResponse())){
                score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
