<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mathwhiz.model.GameScore" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PROTHAM - MATH WHIZ | Top 10 Leaderboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="container">
    <div class="header">
        <h1>PROTHAM – MATH WHIZ <span class="badge-normal">NORMAL</span></h1>
        <div class="subtitle">Top 10 High Scores &mdash; Database Leaderboard</div>
    </div>

    <table class="leaderboard-table">
        <thead>
            <tr>
                <th style="width: 60px;">Rank</th>
                <th>Player Name</th>
                <th style="width: 90px;">Score</th>
                <th style="width: 100px;">Difficulty</th>
                <th>Played At</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<GameScore> topScores = (List<GameScore>) request.getAttribute("topScores");
                if (topScores != null && !topScores.isEmpty()) {
                    int rank = 1;
                    for (GameScore score : topScores) {
                        String rankClass = rank == 1 ? "rank-1" : (rank == 2 ? "rank-2" : (rank == 3 ? "rank-3" : ""));
            %>
            <tr>
                <td><span class="rank-badge <%= rankClass %>"><%= rank %></span></td>
                <td><strong><%= score.getPlayerName() %></strong></td>
                <td><strong><%= score.getScore() %> / 10</strong></td>
                <td><span class="badge-normal"><%= score.getDifficulty() %></span></td>
                <td style="color: #57606a; font-size: 12.5px;"><%= score.getPlayedAt() %></td>
            </tr>
            <%
                        rank++;
                    }
                } else {
            %>
            <tr>
                <td colspan="5" style="text-align: center; color: #57606a; padding: 20px;">
                    No games played yet. Be the first to make the leaderboard!
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <div class="action-row">
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary btn-block">Play New Game</a>
    </div>

    <div class="footer">
        Database: <code>math_whiz_db</code> &bull; Query: <code>ORDER BY score DESC, played_at ASC LIMIT 10</code>
    </div>
</div>

</body>
</html>
