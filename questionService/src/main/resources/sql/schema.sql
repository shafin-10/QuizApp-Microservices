CREATE TABLE question (
    id SERIAL PRIMARY KEY,
    question_title VARCHAR(255),
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    right_answer VARCHAR(255),
    difficultylevel VARCHAR(50),
    category VARCHAR(50)
);
