<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PROTHAM - MATH WHIZ | Game</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <div class="header">
        <h1>PROTHAM – MATH WHIZ <span class="badge-normal">NORMAL</span></h1>
        <div class="subtitle">Player: <strong><%= request.getAttribute("playerName") %></strong> &bull; Total Marks: 10</div>
    </div>

    <form action="${pageContext.request.contextPath}/game" method="post">
        <!-- Hidden parameters passed to server for dynamic evaluation -->
        <input type="hidden" name="playerName" value="<%= request.getAttribute("playerName") %>">
        <input type="hidden" name="q1Number" value="<%= request.getAttribute("q1Number") %>">
        <input type="hidden" name="q2A" value="<%= request.getAttribute("q2A") %>">
        <input type="hidden" name="q2B" value="<%= request.getAttribute("q2B") %>">
        <input type="hidden" name="q3Func" value="<%= request.getAttribute("q3Func") %>">
        <input type="hidden" name="q3Degree" value="<%= request.getAttribute("q3Degree") %>">
        <input type="hidden" name="q4Func" value="<%= request.getAttribute("q4Func") %>">
        <input type="hidden" name="q4Degree" value="<%= request.getAttribute("q4Degree") %>">

        <!-- QUESTION 1: Prime Number (2 Marks) -->
        <div class="question-card">
            <div class="question-header">
                <span class="question-title">Question 1: Prime Number</span>
                <span class="question-marks">2 Marks</span>
            </div>
            <div class="question-prompt">
                Determine whether <span class="math-expr"><%= request.getAttribute("q1Number") %></span> is a prime number.
            </div>
            <label for="q1Answer">Your Answer (1 = Prime, 0 = Not Prime):</label>
            <input type="number" id="q1Answer" name="q1Answer" min="0" max="1" placeholder="Enter 1 or 0" required autofocus>
            <div class="input-hint">Rule: Enter 1 if the number is prime; otherwise enter 0. Numbers &lt; 2 are not prime.</div>
        </div>

        <!-- QUESTION 2: Modulus (2 Marks) -->
        <div class="question-card">
            <div class="question-header">
                <span class="question-title">Question 2: Modulus Operation</span>
                <span class="question-marks">2 Marks</span>
            </div>
            <div class="question-prompt">
                Calculate the remainder: <span class="math-expr"><%= request.getAttribute("q2A") %> % <%= request.getAttribute("q2B") %></span>
            </div>
            <label for="q2Answer">Your Answer (Integer):</label>
            <input type="number" id="q2Answer" name="q2Answer" placeholder="Enter remainder" required>
            <div class="input-hint">Rule: Integer operands only. Divisor is non-zero.</div>
        </div>

        <!-- QUESTION 3: Trigonometry 1 (3 Marks) -->
        <div class="question-card">
            <div class="question-header">
                <span class="question-title">Question 3: Trigonometric Value</span>
                <span class="question-marks">3 Marks</span>
            </div>
            <div class="question-prompt">
                Calculate <span class="math-expr"><%= String.valueOf(request.getAttribute("q3Func")).toUpperCase() %>(<%= request.getAttribute("q3Degree") %>°)</span>
            </div>
            <label for="q3Answer">Your Answer (Decimal or Integer):</label>
            <input type="number" step="any" id="q3Answer" name="q3Answer" placeholder="e.g. 0.5, 1.0, 0.866" required>
            <div class="input-hint">Rule: Angles are in degrees. Evaluated dynamically via Java Math with tolerance.</div>
        </div>

        <!-- QUESTION 4: Trigonometry 2 (3 Marks) -->
        <div class="question-card">
            <div class="question-header">
                <span class="question-title">Question 4: Trigonometric Value</span>
                <span class="question-marks">3 Marks</span>
            </div>
            <div class="question-prompt">
                Calculate <span class="math-expr"><%= String.valueOf(request.getAttribute("q4Func")).toUpperCase() %>(<%= request.getAttribute("q4Degree") %>°)</span>
            </div>
            <label for="q4Answer">Your Answer (Decimal or Integer):</label>
            <input type="number" step="any" id="q4Answer" name="q4Answer" placeholder="e.g. 0.5, 1.0, 0.0" required>
            <div class="input-hint">Rule: Angles are in degrees. Evaluated dynamically via Java Math with tolerance.</div>
        </div>

        <button type="submit" class="btn btn-primary btn-block">Submit Answers</button>
    </form>

    <div class="footer">
        <a href="${pageContext.request.contextPath}/index.jsp">&larr; Cancel and Return to Home</a>
    </div>
</div>

</body>
</html>
