package com.mathwhiz.model;

import java.sql.Timestamp;

/**
 * Model representing a player's game score record.
 */
public class GameScore {

    private int id;
    private String playerName;
    private int score;
    private String difficulty;
    private Timestamp playedAt;

    public GameScore() {
    }

    public GameScore(String playerName, int score, String difficulty, Timestamp playedAt) {
        this.playerName = playerName;
        this.score = score;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }

    public GameScore(int id, String playerName, int score, String difficulty, Timestamp playedAt) {
        this.id = id;
        this.playerName = playerName;
        this.score = score;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Timestamp getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Timestamp playedAt) {
        this.playedAt = playedAt;
    }

    @Override
    public String toString() {
        return "GameScore{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", score=" + score +
                ", difficulty='" + difficulty + '\'' +
                ", playedAt=" + playedAt +
                '}';
    }
}
