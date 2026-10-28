package es.upm.miw.bantumi.datos;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {FinalScore.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class FinalScoreRepository extends RoomDatabase {
    public static final String DATA_BASE = FinalScore.TABLA + ".db";
    public abstract FinalScoreDAO finalScoreDAO();
}
