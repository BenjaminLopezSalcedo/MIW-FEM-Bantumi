package es.upm.miw.bantumi.datos;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class Converters {

    @TypeConverter
    public Long fromLocalDateTime (LocalDateTime localDateTime){
        return localDateTime == null ? null :
                localDateTime
                        .atZone(ZoneId.systemDefault())
                        .toEpochSecond();
    }

    @TypeConverter
    public LocalDateTime toLocalDateTime (Long date){
        return date == null ? null :
                LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(date),
                        ZoneId.systemDefault()
                );
    }

}
