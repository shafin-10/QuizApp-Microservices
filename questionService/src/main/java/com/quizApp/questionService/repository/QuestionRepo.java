package com.quizApp.questionService.repository;

import com.quizApp.questionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    //List<Question> findByCategory(String category);

    List<Question> findByCategoryIgnoreCase(String category);

    @Query(value = " SELECT q.id FROM question q WHERE LOWER(q.category) = LOWER(:category) ORDER BY RANDOM() LIMIT :numQ ",
            nativeQuery = true
    )
    List<Long> findRandomQuestionsByCategory(
            @Param("category") String category,
            @Param("numQ") Integer numQ
    );
}