import React, { useState } from "react";
import axios from "axios";

function App() {
  const [category, setCategory] = useState("");
  const [title, setTitle] = useState("");
  const [numQuestions, setNumQuestions] = useState(5);

  const [quizId, setQuizId] = useState("");
  const [questions, setQuestions] = useState([]);
  const [responses, setResponses] = useState({});
  const [score, setScore] = useState(null);

  // Create Quiz
  const createQuiz = async () => {
    const res = await axios.post("http://localhost:8080/api/quiz/create", {
      categoryName: category,
      numQuestions,
      title,
    });

    setQuizId(res.data.id);
    setQuestions([]);
    setScore(null);
    alert("Quiz Created Successfully!");
  };

  // Load Quiz
  const loadQuiz = async () => {
    const res = await axios.get(
      `http://localhost:8080/api/quiz/${quizId}`
    );
    setQuestions(res.data);
    setScore(null);
  };

  // Handle Answer
  const handleChange = (id, value) => {
    setResponses({ ...responses, [id]: value });
  };

  // Submit Quiz
  const submitQuiz = async () => {
    const payload = Object.keys(responses).map((id) => ({
      id: parseInt(id),
      response: responses[id],
    }));

    const res = await axios.post(
      `http://localhost:8080/api/quiz/submit/${quizId}`,
      payload
    );

    setScore(res.data);
  };

  return (
    <div className="container py-4">

      {/* Header */}
      <div className="text-center mb-4">
        <h1 className="fw-bold">🧠 Quiz Microservices App</h1>
        <p className="text-muted">Built with Spring Boot + React</p>
      </div>

      {/* Create Quiz Card */}
      <div className="card shadow mb-4">
        <div className="card-body">
          <h4 className="mb-3">Create Quiz</h4>

          <div className="row g-2">
            <div className="col-md-4">
              <input
                className="form-control"
                placeholder="Category"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
              />
            </div>

            <div className="col-md-4">
              <input
                className="form-control"
                placeholder="Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
            </div>

            <div className="col-md-2">
              <input
                type="number"
                className="form-control"
                value={numQuestions}
                onChange={(e) => setNumQuestions(e.target.value)}
              />
            </div>

            <div className="col-md-2">
              <button
                className="btn btn-primary w-100"
                onClick={createQuiz}
              >
                Create
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* Load Quiz */}
      <div className="card shadow mb-4">
        <div className="card-body">
          <h4 className="mb-3">Load Quiz</h4>

          <div className="input-group">
            <input
              className="form-control"
              placeholder="Enter Quiz ID"
              value={quizId}
              onChange={(e) => setQuizId(e.target.value)}
            />
            <button className="btn btn-success" onClick={loadQuiz}>
              Load
            </button>
          </div>
        </div>
      </div>

      {/* Questions */}
      {questions.length > 0 && (
        <div className="card shadow mb-4">
          <div className="card-body">
            <h4 className="mb-3">Questions</h4>

            {questions.map((q) => (
              <div key={q.id} className="mb-4 border-bottom pb-3">
                <h6>{q.question_title}</h6>

                {[q.option1, q.option2, q.option3, q.option4].map(
                  (opt, i) => (
                    <div className="form-check" key={i}>
                      <input
                        className="form-check-input"
                        type="radio"
                        name={q.id}
                        value={opt}
                        onChange={(e) =>
                          handleChange(q.id, e.target.value)
                        }
                      />
                      <label className="form-check-label">{opt}</label>
                    </div>
                  )
                )}
              </div>
            ))}

            <button
              className="btn btn-warning w-100"
              onClick={submitQuiz}
            >
              Submit Quiz
            </button>
          </div>
        </div>
      )}

      {/* Score */}
      {score !== null && (
        <div className="alert alert-success text-center">
          <h3>Your Score: {score}</h3>
        </div>
      )}
    </div>
  );
}

export default App;