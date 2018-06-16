package neu.droid.guy.roomviewmodellivedata;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {POJO.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Give a name to Database
     */
    private static final String DATABASE_NAME = "sampledb";
    private static final Object LOCK = new Object();
    private static AppDatabase dbInstance;

    public static AppDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LOCK) {
                dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return dbInstance;
    }

    public abstract DAO taskDAO();
}
