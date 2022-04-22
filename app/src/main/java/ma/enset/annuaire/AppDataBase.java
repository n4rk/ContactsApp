package ma.enset.annuaire;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase database;

    private static String DATABASE_NAME="db";

    public synchronized static AppDataBase getInstance(Context context) {
        if(database==null) {
            database= Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }

    public abstract ContactDAO contactDAO();
}
