package com.mathwhiz.controller;

import com.mathwhiz.dao.ScoreDAO;
import com.mathwhiz.model.GameScore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Controller Servlet for displaying the Top 10 Leaderboard.
 * Implements TASK 08 requirements.
 */
@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {

    private ScoreDAO scoreDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.scoreDAO = new ScoreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<GameScore> topScores = scoreDAO.getTopScores(10);
        request.setAttribute("topScores", topScores);
        request.getRequestDispatcher("/leaderboard.jsp").forward(request, response);
    }
}
