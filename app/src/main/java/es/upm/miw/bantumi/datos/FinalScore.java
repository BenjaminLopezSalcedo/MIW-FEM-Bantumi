package es.upm.miw.bantumi.datos;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(tableName = FinalScore.TABLA)
public class FinalScore {
    static public final String TABLA = "finalScore";

    @PrimaryKey
    @NonNull
    protected String id = UUID.randomUUID().toString();

    protected String playerName;
    protected LocalDateTime timeStamp;
    protected int seedsPlayer1;
    protected int seedsPlayer2;

    public FinalScore(String playerName, LocalDateTime timeStamp, int seedsPlayer1, int seedsPlayer2) {
        this.playerName = playerName;
        this.timeStamp = timeStamp;
        this.seedsPlayer1 = seedsPlayer1;
        this.seedsPlayer2 = seedsPlayer2;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getSeedsPlayer1() {
        return seedsPlayer1;
    }

    public void setSeedsPlayer1(int seedsPlayer1) {
        this.seedsPlayer1 = seedsPlayer1;
    }

    public int getSeedsPlayer2() {
        return seedsPlayer2;
    }

    public void setSeedsPlayer2(int seedsPlayer2) {
        this.seedsPlayer2 = seedsPlayer2;
    }

    @Override
    public String toString() {
        return "FinalScore{" +
                "playerName='" + playerName + '\'' +
                ", timeStamp=" + timeStamp +
                ", seedsPlayer1=" + seedsPlayer1 +
                ", seedsPlayer2=" + seedsPlayer2 +
                ", id='" + id + '\'' +
                '}';
    }
}
