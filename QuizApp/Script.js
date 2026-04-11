// SIGNUP FUNCTION 
function signup()
 {
  const username = document.getElementById("signup-username").value.trim();
  const password = document.getElementById("signup-password").value.trim();

  if (!username || !password) {
    alert("Please fill all fields.");
    return;
  }

  const users = JSON.parse(localStorage.getItem("users") || "{}");

  if (users[username]) {
    alert("Username already exists. Choose another.");
  } else {
    users[username] = password;
    localStorage.setItem("users", JSON.stringify(users));
    alert("Signup successful! Please login.");
    window.location.href = "index.html";
  }
}

// LOGIN FUNCTION 
function login() 
{
  const username = document.getElementById("login-username").value.trim();
  const password = document.getElementById("login-password").value.trim();

  const users = JSON.parse(localStorage.getItem("users") || "{}");

  if (users[username] && users[username] === password) {
    localStorage.setItem("loggedInUser", username);
    alert("Login successful!");
  if (username.startsWith("lect@")) {
  window.location.href = "admin.html";
  } else {
    window.location.href = "quiz.html";
  }
 }else {
    alert("Invalid username or password.");
  }
}


//  QUIZ FUNCTION

const allQuizzes = JSON.parse(localStorage.getItem("quizzes") || "{}");

const quizSelector = document.getElementById("quiz-selector");
const quizContainer = document.getElementById("quiz-container");

let quizData = [];
let currentQuiz = 0;
let score = 0;
let selectedOption = null;

//populate the drop down for quiz
Object.keys(allQuizzes).forEach(title =>{
const option = document.createElement("option");
option.value = title;
option.textContent = title;
quizSelector.appendChild(option);
console.log("selected quiz data:", quizData);
});

//handle the quiz selection
quizSelector.addEventListener("change", ()=>{
const selectedQuiz = quizSelector.value;
if (selectedQuiz && allQuizzes[selectedQuiz]){
  quizData = allQuizzes[selectedQuiz].quiz;
  currentQuiz = 0;
  score =0;
  selectedOption = null;
  //quizSelector.classList.add("hide");
  quizContainer.classList.remove("hide");
  document.getElementById("quiz-select-wrapper").classList.add("hide");
  document.getElementById("result").classList.add("hide");
 //  document,gerElementById("quiz-title").textContent = selectedQuiz;
  loadQuiz();
} else {
  quizContainer.classList.add("hide");
}
});

const questionEl = document.getElementById("question");
const optionsEl = document.getElementById("options");
const nextBtn = document.getElementById("next-btn");
const exitBtn = document.getElementById("exit-btn");
const exitBtnResult = document.getElementById("exit-btn-result");
const resultEl = document.getElementById("result");
const scoreEl = document.getElementById("score");
const totalEl = document.getElementById("total");
const restartBtn = document.getElementById("restart-btn");
const logOutBtn = document.getElementById("log-out-btn");
const logOutBtnResult = document.getElementById("log-out-btn-result");


function loadQuiz() {
  selectedOption = null;
  nextBtn.disabled = true;
  optionsEl.innerHTML = "";
  
  const currentQuizData = quizData[currentQuiz];
  questionEl.textContent = currentQuizData.question;

  currentQuizData.options.forEach((option, index) => {
    const li = document.createElement("li");
    li.textContent = option;
    li.addEventListener("click", () => selectOption(li, index));
    optionsEl.appendChild(li);
  });
}

function selectOption(optionEl, index) {
  
  const options = optionsEl.querySelectorAll("li");
  options.forEach((option) => option.classList.remove("selected"));

  
  optionEl.classList.add("selected");
  selectedOption = index;
  nextBtn.disabled = false;
}

nextBtn.addEventListener("click", () => {
  if (selectedOption === null) return;
  
  quizData[currentQuiz].selected = selectedOption;
  if (selectedOption === quizData[currentQuiz].answer) {
    score++;
  }
  
  currentQuiz++;
  if (currentQuiz < quizData.length) {
    loadQuiz();
  } else {
    showResult();
  }
});

exitBtn.addEventListener("click", () => {
const confirmExit = confirm("Are you sure, you wanna exit?");
if (confirmExit){
  currentQuiz = 0;
  score = 0;
  document.getElementById("quiz-container").classList.add("hide");
  document.getElementById("quiz-select-wrapper").classList.remove("hide");
  quizSelector.value = "";
}
});

// function showResult() {
//   document.getElementById("quiz-container").classList.add("hide");
//   resultEl.classList.remove("hide");
//   scoreEl.textContent = score;
//   totalEl.textContent = quizData.length;
// }
function showResult() {
  document.getElementById("quiz-container").classList.add("hide");
  resultEl.classList.remove("hide");
  scoreEl.textContent = score;
  totalEl.textContent = quizData.length;

  // Remove any old reviews before adding new
  const oldReviews = resultEl.querySelectorAll(".review-item");
  oldReviews.forEach(item => item.remove());

  // Create detailed review
  quizData.forEach((q, index) => {
    const reviewDiv = document.createElement("div");
    reviewDiv.classList.add("review-item");
    const isCorrect = q.selected === q.answer;
    reviewDiv.style.background = isCorrect ? "#c8e6c9" : "#ffcdd2";
    reviewDiv.style.padding = "10px";
    reviewDiv.style.margin = "8px 0";
    reviewDiv.style.borderRadius = "8px";

    reviewDiv.innerHTML = `
      <b>Q${index + 1}:</b> ${q.question}<br>
      <br><b>
          Your answer: ${q.options[q.selected] || "No answer"}<br>
          Correct answer: ${q.options[q.answer]}
    `;
    resultEl.appendChild(reviewDiv);
  });

  // --- Save student progress ---
  const username = localStorage.getItem("loggedInUser") || "Guest";
  const selectedQuiz = quizSelector.value;
  const progress = JSON.parse(localStorage.getItem("progress") || "[]");

  progress.push({
    username: username,
    quizTitle: selectedQuiz,
    score: score,
    total: quizData.length,
    date: new Date().toLocaleString()
  });

  localStorage.setItem("progress", JSON.stringify(progress));
}


restartBtn.addEventListener("click", () => {
  currentQuiz = 0;
  score = 0;
  resultEl.classList.add("hide");
  document.getElementById("quiz-container").classList.remove("hide");
  loadQuiz();
});



exitBtnResult.addEventListener("click", () => {
  const confirmExit = confirm("Are you sure you are exiting?");
  if (confirmExit) {
    currentQuiz = 0;
    score = 0;
    resultEl.classList.add("hide");
    document.getElementById("quiz-container").classList.add("hide");
    document.getElementById("quiz.select-wrapper").classList.remove("hide");
    quizSelector.value ="";
  }
});

logOutBtn.addEventListener("click", () =>{
  const confirmExit = confirm("Are you sure you loging out?");
  if (confirmExit){
    window.location.href = "index.html";
  }
});

logOutBtnResult.addEventListener("click", () =>{
  const confirmExit = confirm("Are you sure you loging out?");
  if (confirmExit){
    window.location.href ="index.html";
  }
});

