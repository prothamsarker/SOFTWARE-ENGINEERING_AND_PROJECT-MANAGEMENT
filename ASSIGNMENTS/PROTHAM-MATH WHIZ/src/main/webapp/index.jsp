<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PROTHAM - MATH WHIZ | Welcome</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <div class="header">
        <h1>PROTHAM – MATH WHIZ <span class="badge-normal">NORMAL</span></h1>
        <div class="subtitle">A Web-Based Mathematical Game using Java Servlets &amp; JDBC</div>
    </div>

    <div class="info-box">
        <h3>Game Rules &amp; Mark Distribution</h3>
        <ul>
            <li><strong>Q1 &mdash; Prime Number (2 Marks):</strong> Enter <code>1</code> if prime, <code>0</code> if not prime. Numbers &lt; 2 are not prime.</li>
            <li><strong>Q2 &mdash; Modulus (2 Marks):</strong> Calculate integer remainder (<code>a % b</code>). Divisor is never zero.</li>
            <li><strong>Q3 &mdash; Trigonometry (3 Marks):</strong> Calculate trigonometric value (<code>sin</code> / <code>cos</code> / <code>tan</code>) from angles in degrees.</li>
            <li><strong>Q4 &mdash; Trigonometry (3 Marks):</strong> Calculate trigonometric value (<code>sin</code> / <code>cos</code> / <code>tan</code>) from angles in degrees.</li>
        </ul>
        <p style="margin-top: 8px;"><strong>Total Marks:</strong> 10 Marks &bull; <strong>Difficulty:</strong> NORMAL</p>
    </div>

    <form action="${pageContext.request.contextPath}/game" method="get">
        <div class="form-group">
            <label for="playerName">Enter Your Name:</label>
            <input type="text" id="playerName" name="playerName" placeholder="e.g. Protham Sarker" required autofocus>
            <div class="input-hint">Your name and final score will be recorded on the database leaderboard.</div>
        </div>

        <button type="submit" class="btn btn-primary btn-block">Start Game</button>
    </form>

    <div class="action-row">
        <a href="${pageContext.request.contextPath}/leaderboard" class="btn btn-secondary btn-block">View Top 10 Leaderboard</a>
    </div>

    <div class="footer">
        Software Engineering and Project Management &bull; Lab Practice: Java Servlet &amp; JDBC
    </div>
</div>

</body>
</html>
