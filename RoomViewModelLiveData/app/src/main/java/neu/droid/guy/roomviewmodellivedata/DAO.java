package neu.droid.guy.roomviewmodellivedata;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM sampleTable")
    List<POJO> getAllData();

    @Insert
    void insertObject(POJO newObjectToAdd);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateObject(POJO objectToUpdate);

    @Delete
    void deleteSelectedPojo(POJO objectToDelete);

    @Query("SELECT * FROM sampleTable where id = :id")
    void getSpecificData(String id);
}
