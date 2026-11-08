-- ==============================================================
-- PROTHAM - MATH WHIZ: Database Setup Script
-- Course: Software Engineering and Project Management
-- Database: math_whiz_db
-- Table: game_scores
-- ==============================================================

-- 1. Create Database if not exists
CREATE DATABASE IF NOT EXISTS math_whiz_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE math_whiz_db;

-- 2. Create Table: game_scores
DROP TABLE IF EXISTS game_scores;

CREATE TABLE game_scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    difficulty VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    played_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 3. Sample Data for Leaderboard Demonstration
INSERT INTO game_scores (player_name, score, difficulty, played_at) VALUES
('Protham Sarker', 10, 'NORMAL', '2026-11-14 10:15:00'),
('Alice Walker', 10, 'NORMAL', '2026-11-14 10:30:00'),
('Bob Smith', 9, 'NORMAL', '2026-11-14 11:00:00'),
('Charlie Brown', 8, 'NORMAL', '2026-11-14 11:15:00'),
('David Miller', 8, 'NORMAL', '2026-11-14 11:45:00'),
('Emma Watson', 7, 'NORMAL', '2026-11-14 12:00:00'),
('Frank Castle', 7, 'NORMAL', '2026-11-14 12:20:00'),
('Grace Hopper', 6, 'NORMAL', '2026-11-14 12:35:00'),
('Harry Potter', 5, 'NORMAL', '2026-11-14 12:50:00'),
('Ian Malcolm', 5, 'NORMAL', '2026-11-14 13:00:00');

-- 4. Verification Query: Top 10 Leaderboard
SELECT *
FROM game_scores
ORDER BY score DESC, played_at ASC
LIMIT 10;
