package com.mathwhiz.dao;

import com.mathwhiz.model.GameScore;
import com.mathwhiz.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for handling game_scores database operations.
 * Implements TASK 07 (INSERT with PreparedStatement) and TASK 08 (Top 10 leaderboard retrieval).
 */
public class ScoreDAO {

    /**
     * TASK 07: Save player score to the database using PreparedStatement.
     *
     * @param score the GameScore object containing player details
     * @return true if inserted successfully, false otherwise
     */
    public boolean saveScore(GameScore score) {
        String insertSQL = "INSERT INTO game_scores (player_name, score, difficulty, played_at) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, score.getPlayerName());
            pstmt.setInt(2, score.getScore());
            pstmt.setString(3, score.getDifficulty());

            Timestamp playedAt = score.getPlayedAt() != null
                    ? score.getPlayedAt()
                    : new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(4, playedAt);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Database error saving score: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * TASK 08: Retrieve top scores for the leaderboard.
     * Orders primarily by score DESC and secondarily by played_at ASC (earliest first for ties).
     *
     * @param limit maximum number of records to return (10 as per requirements)
     * @return list of top GameScore records
     */
    public List<GameScore> getTopScores(int limit) {
        List<GameScore> leaderboard = new ArrayList<>();
        String querySQL = "SELECT id, player_name, score, difficulty, played_at " +
                          "FROM game_scores " +
                          "ORDER BY score DESC, played_at ASC " +
                          "LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {

            pstmt.setInt(1, limit);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    GameScore score = new GameScore(
                            rs.getInt("id"),
                            rs.getString("player_name"),
                            rs.getInt("score"),
                            rs.getString("difficulty"),
                            rs.getTimestamp("played_at")
                    );
                    leaderboard.add(score);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error retrieving leaderboard: " + e.getMessage());
            e.printStackTrace();
        }

        return leaderboard;
    }
}
