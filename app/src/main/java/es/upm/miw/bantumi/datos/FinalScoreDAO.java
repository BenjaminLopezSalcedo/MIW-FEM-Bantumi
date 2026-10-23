package es.upm.miw.bantumi.datos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FinalScoreDAO {

    @Query("SELECT * FROM " + FinalScore.TABLA)
    List<FinalScore> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(FinalScore finalScore);

}
