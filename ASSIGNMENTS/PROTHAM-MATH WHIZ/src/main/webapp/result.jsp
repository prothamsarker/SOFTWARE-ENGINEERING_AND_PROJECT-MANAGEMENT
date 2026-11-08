<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PROTHAM - MATH WHIZ | Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <div class="header">
        <h1>PROTHAM – MATH WHIZ <span class="badge-normal">NORMAL</span></h1>
        <div class="subtitle">Game Performance Summary</div>
    </div>

    <%
        int totalScore = (Integer) request.getAttribute("totalScore");
        int maxScore = (Integer) request.getAttribute("maxScore");
        String playerName = (String) request.getAttribute("playerName");
        boolean passed = totalScore >= 6;
    %>

    <div class="score-summary">
        <div class="score-marks">Final Score for <strong><%= playerName %></strong></div>
        <div class="score-number"><%= totalScore %> / <%= maxScore %></div>
        <div>
            <% if (totalScore == 10) { %>
                <span class="status-badge status-pass">Perfect Score! 10 / 10 Marks</span>
            <% } else if (passed) { %>
                <span class="status-badge status-pass">Passed (<%= totalScore %> Marks)</span>
            <% } else { %>
                <span class="status-badge status-fail">Needs Practice (<%= totalScore %> Marks)</span>
            <% } %>
        </div>
        <div style="font-size: 12px; color: #57606a; margin-top: 8px;">
            Recorded to MySQL Database <code>math_whiz_db.game_scores</code> at <%= request.getAttribute("playedAt") %>
        </div>
    </div>

    <h3 style="font-size: 15px; font-weight: 600; margin-bottom: 10px;">Question Breakdown</h3>
    <table class="breakdown-table">
        <thead>
            <tr>
                <th>Question</th>
                <th>Your Answer</th>
                <th>Calculated Answer</th>
                <th>Result</th>
                <th>Marks</th>
            </tr>
        </thead>
        <tbody>
            <!-- Q1 -->
            <tr>
                <td><strong>Q1: Prime Number</strong><br><span style="color:#57606a;">Is <%= request.getAttribute("q1Number") %> prime?</span></td>
                <td><%= request.getAttribute("userQ1") %></td>
                <td><%= request.getAttribute("expectedQ1") %> (<%= (Integer) request.getAttribute("expectedQ1") == 1 ? "Prime" : "Not Prime" %>)</td>
                <td>
                    <% if ((Boolean) request.getAttribute("q1Correct")) { %>
                        <span class="tag-correct">&#10003; Correct</span>
                    <% } else { %>
                        <span class="tag-incorrect">&#10007; Incorrect</span>
                    <% } %>
                </td>
                <td><strong><%= request.getAttribute("q1Score") %> / 2</strong></td>
            </tr>

            <!-- Q2 -->
            <tr>
                <td><strong>Q2: Modulus</strong><br><span style="color:#57606a;"><%= request.getAttribute("q2A") %> % <%= request.getAttribute("q2B") %></span></td>
                <td><%= request.getAttribute("userQ2") %></td>
                <td><%= request.getAttribute("expectedQ2") %></td>
                <td>
                    <% if ((Boolean) request.getAttribute("q2Correct")) { %>
                        <span class="tag-correct">&#10003; Correct</span>
                    <% } else { %>
                        <span class="tag-incorrect">&#10007; Incorrect</span>
                    <% } %>
                </td>
                <td><strong><%= request.getAttribute("q2Score") %> / 2</strong></td>
            </tr>

            <!-- Q3 -->
            <tr>
                <td><strong>Q3: Trigonometry</strong><br><span style="color:#57606a;"><%= request.getAttribute("q3Func") %>(<%= request.getAttribute("q3Degree") %>°)</span></td>
                <td><%= request.getAttribute("userQ3") %></td>
                <td><%= request.getAttribute("expectedQ3") %></td>
                <td>
                    <% if ((Boolean) request.getAttribute("q3Correct")) { %>
                        <span class="tag-correct">&#10003; Correct</span>
                    <% } else { %>
                        <span class="tag-incorrect">&#10007; Incorrect</span>
                    <% } %>
                </td>
                <td><strong><%= request.getAttribute("q3Score") %> / 3</strong></td>
            </tr>

            <!-- Q4 -->
            <tr>
                <td><strong>Q4: Trigonometry</strong><br><span style="color:#57606a;"><%= request.getAttribute("q4Func") %>(<%= request.getAttribute("q4Degree") %>°)</span></td>
                <td><%= request.getAttribute("userQ4") %></td>
                <td><%= request.getAttribute("expectedQ4") %></td>
                <td>
                    <% if ((Boolean) request.getAttribute("q4Correct")) { %>
                        <span class="tag-correct">&#10003; Correct</span>
                    <% } else { %>
                        <span class="tag-incorrect">&#10007; Incorrect</span>
                    <% } %>
                </td>
                <td><strong><%= request.getAttribute("q4Score") %> / 3</strong></td>
            </tr>
        </tbody>
    </table>

    <div class="action-row">
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" style="flex: 1;">Play Again</a>
        <a href="${pageContext.request.contextPath}/leaderboard" class="btn btn-secondary" style="flex: 1;">View Leaderboard</a>
    </div>

    <div class="footer">
        Software Engineering and Project Management &bull; Lab Practice: Java Servlet &amp; JDBC
    </div>
</div>

</body>
</html>
