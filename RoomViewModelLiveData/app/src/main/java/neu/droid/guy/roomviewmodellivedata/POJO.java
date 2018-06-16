package neu.droid.guy.roomviewmodellivedata;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/** Adding annotation @Entity makes ROOM generate a table call POJO */
@Entity(tableName = "sampleTable")
public class POJO {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mMovieName;
    private Boolean mIsMovieFav;
    private int mMovieRating;
    private String mImageUrl;

    /**
     * Default constructor
     * Room should have 1 constructor in POJO.
     * If we have more than 1 constructor, we can add the @Ignore annotation
     */
    @Ignore
    public POJO(String mMovieName, Boolean mIsMovieFav, int mMovieRating, String mImageUrl) {
        this.mMovieName = mMovieName;
        this.mIsMovieFav = mIsMovieFav;
        this.mMovieRating = mMovieRating;
        this.mImageUrl = mImageUrl;
    }


    public POJO(int id, String mMovieName, Boolean mIsMovieFav, int mMovieRating, String mImageUrl) {
        this.id = id;
        this.mMovieName = mMovieName;
        this.mIsMovieFav = mIsMovieFav;
        this.mMovieRating = mMovieRating;
        this.mImageUrl = mImageUrl;
    }


    public String getMovieName() {
        return mMovieName;
    }

    public void setMovieName(String mMovieName) {
        this.mMovieName = mMovieName;
    }

    public Boolean getIsMovieFav() {
        return mIsMovieFav;
    }

    public void setIsMovieFav(Boolean mIsMovieFav) {
        this.mIsMovieFav = mIsMovieFav;
    }

    public int getMovieRating() {
        return mMovieRating;
    }

    public void setMovieRating(int mMovieRating) {
        this.mMovieRating = mMovieRating;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
