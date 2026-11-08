package com.mathwhiz.controller;

import com.mathwhiz.dao.ScoreDAO;
import com.mathwhiz.model.GameScore;
import com.mathwhiz.service.MathService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

/**
 * Main Controller Servlet for PROTHAM - MATH WHIZ.
 * Handles game initialization, question generation, server-side answer evaluation,
 * database persistence via JDBC, and dispatching to JSP views.
 */
@WebServlet(urlPatterns = {"/game", "/play"})
public class GameServlet extends HttpServlet {

    private ScoreDAO scoreDAO;
    private Random random;

    @Override
    public void init() throws ServletException {
        super.init();
        this.scoreDAO = new ScoreDAO();
        this.random = new Random();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String playerName = request.getParameter("playerName");
        if (playerName == null || playerName.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        playerName = playerName.trim();

        // 1. Generate Q1: Prime Number (integer between 2 and 50)
        int[] primeCandidates = {2, 3, 4, 7, 8, 9, 11, 13, 14, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 37, 41, 45, 47};
        int q1Number = primeCandidates[random.nextInt(primeCandidates.length)];

        // 2. Generate Q2: Modulus (dividend 10..50, divisor 2..9, non-zero divisor guaranteed)
        int q2A = 10 + random.nextInt(41); // 10..50
        int q2B = 2 + random.nextInt(8);   // 2..9

        // 3. Generate Q3: Trigonometry (Sin or Cos, standard angles)
        String[] q3Funcs = {"sin", "cos"};
        String q3Func = q3Funcs[random.nextInt(q3Funcs.length)];
        double[] trigAngles1 = {0.0, 30.0, 45.0, 60.0, 90.0};
        double q3Degree = trigAngles1[random.nextInt(trigAngles1.length)];

        // 4. Generate Q4: Trigonometry (Cos or Tan, avoiding undefined 90° for tan)
        String[] q4Funcs = {"cos", "tan"};
        String q4Func = q4Funcs[random.nextInt(q4Funcs.length)];
        double[] tanSafeAngles = {0.0, 30.0, 45.0, 60.0};
        double q4Degree = "tan".equalsIgnoreCase(q4Func)
                ? tanSafeAngles[random.nextInt(tanSafeAngles.length)]
                : trigAngles1[random.nextInt(trigAngles1.length)];

        // Set attributes for game.jsp
        request.setAttribute("playerName", playerName);
        request.setAttribute("difficulty", "NORMAL");

        request.setAttribute("q1Number", q1Number);
        request.setAttribute("q2A", q2A);
        request.setAttribute("q2B", q2B);
        request.setAttribute("q3Func", q3Func);
        request.setAttribute("q3Degree", q3Degree);
        request.setAttribute("q4Func", q4Func);
        request.setAttribute("q4Degree", q4Degree);

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String playerName = request.getParameter("playerName");
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Anonymous";
        } else {
            playerName = playerName.trim();
        }

        String difficulty = "NORMAL";

        // Retrieve Question Parameters
        int q1Number = Integer.parseInt(request.getParameter("q1Number"));
        int q2A = Integer.parseInt(request.getParameter("q2A"));
        int q2B = Integer.parseInt(request.getParameter("q2B"));
        String q3Func = request.getParameter("q3Func");
        double q3Degree = Double.parseDouble(request.getParameter("q3Degree"));
        String q4Func = request.getParameter("q4Func");
        double q4Degree = Double.parseDouble(request.getParameter("q4Degree"));

        // Retrieve User Submitted Answers
        String q1Input = request.getParameter("q1Answer");
        String q2Input = request.getParameter("q2Answer");
        String q3Input = request.getParameter("q3Answer");
        String q4Input = request.getParameter("q4Answer");

        int userQ1 = -1;
        int userQ2 = -99999;
        double userQ3 = Double.NaN;
        double userQ4 = Double.NaN;

        try {
            if (q1Input != null && !q1Input.trim().isEmpty()) {
                userQ1 = Integer.parseInt(q1Input.trim());
            }
        } catch (NumberFormatException ignored) {}

        try {
            if (q2Input != null && !q2Input.trim().isEmpty()) {
                userQ2 = Integer.parseInt(q2Input.trim());
            }
        } catch (NumberFormatException ignored) {}

        try {
            if (q3Input != null && !q3Input.trim().isEmpty()) {
                userQ3 = Double.parseDouble(q3Input.trim());
            }
        } catch (NumberFormatException ignored) {}

        try {
            if (q4Input != null && !q4Input.trim().isEmpty()) {
                userQ4 = Double.parseDouble(q4Input.trim());
            }
        } catch (NumberFormatException ignored) {}

        // ==============================================================
        // DYNAMIC EVALUATION USING MathService METHODS (TASK 03, 04, 05, 06)
        // Answers are calculated dynamically, NOT hardcoded!
        // ==============================================================

        // Q1: Prime Number (2 Marks)
        int expectedQ1 = MathService.isPrime(q1Number);
        boolean q1Correct = (userQ1 == expectedQ1);
        int q1Score = q1Correct ? 2 : 0;

        // Q2: Modulus (2 Marks)
        int expectedQ2 = MathService.calculateMod(q2A, q2B);
        boolean q2Correct = (userQ2 == expectedQ2);
        int q2Score = q2Correct ? 2 : 0;

        // Q3: Trigonometry 1 (3 Marks)
        double expectedQ3 = calculateTrig(q3Func, q3Degree);
        boolean q3Correct = !Double.isNaN(userQ3) && MathService.isTrigEqual(expectedQ3, userQ3, MathService.TOLERANCE);
        int q3Score = q3Correct ? 3 : 0;

        // Q4: Trigonometry 2 (3 Marks)
        double expectedQ4 = calculateTrig(q4Func, q4Degree);
        boolean q4Correct = !Double.isNaN(userQ4) && MathService.isTrigEqual(expectedQ4, userQ4, MathService.TOLERANCE);
        int q4Score = q4Correct ? 3 : 0;

        // Calculate Total Score (Max: 10 Marks)
        int totalScore = 0;
        if (q1Correct) totalScore += 2;
        if (q2Correct) totalScore += 2;
        if (q3Correct) totalScore += 3;
        if (q4Correct) totalScore += 3;

        // Save Player Score to Database (TASK 07)
        Timestamp now = new Timestamp(System.currentTimeMillis());
        GameScore gameScore = new GameScore(playerName, totalScore, difficulty, now);
        boolean saved = scoreDAO.saveScore(gameScore);

        // Pass Evaluation Results to result.jsp
        request.setAttribute("playerName", playerName);
        request.setAttribute("difficulty", difficulty);
        request.setAttribute("totalScore", totalScore);
        request.setAttribute("maxScore", 10);
        request.setAttribute("savedToDb", saved);
        request.setAttribute("playedAt", now);

        // Q1 details
        request.setAttribute("q1Number", q1Number);
        request.setAttribute("userQ1", q1Input);
        request.setAttribute("expectedQ1", expectedQ1);
        request.setAttribute("q1Correct", q1Correct);
        request.setAttribute("q1Score", q1Score);

        // Q2 details
        request.setAttribute("q2A", q2A);
        request.setAttribute("q2B", q2B);
        request.setAttribute("userQ2", q2Input);
        request.setAttribute("expectedQ2", expectedQ2);
        request.setAttribute("q2Correct", q2Correct);
        request.setAttribute("q2Score", q2Score);

        // Q3 details
        request.setAttribute("q3Func", q3Func.toUpperCase());
        request.setAttribute("q3Degree", q3Degree);
        request.setAttribute("userQ3", q3Input);
        request.setAttribute("expectedQ3", expectedQ3);
        request.setAttribute("q3Correct", q3Correct);
        request.setAttribute("q3Score", q3Score);

        // Q4 details
        request.setAttribute("q4Func", q4Func.toUpperCase());
        request.setAttribute("q4Degree", q4Degree);
        request.setAttribute("userQ4", q4Input);
        request.setAttribute("expectedQ4", expectedQ4);
        request.setAttribute("q4Correct", q4Correct);
        request.setAttribute("q4Score", q4Score);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private double calculateTrig(String func, double degree) {
        if ("sin".equalsIgnoreCase(func)) {
            return MathService.calculateSin(degree);
        } else if ("cos".equalsIgnoreCase(func)) {
            return MathService.calculateCos(degree);
        } else if ("tan".equalsIgnoreCase(func)) {
            return MathService.calculateTan(degree);
        }
        return 0.0;
    }
}
