package android.anative.com.demoadvance.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */
@Table(name = "photos_table")
public class PhotosTable extends Model {
    @Column(name = "albumId")
    public long albumId;

    @Column(name = "id")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "url")
    public String url;

    @Column(name = "thumbnailUrl")
    public String thumbnailUrl;

    public PhotosTable() {

    }

    public PhotosTable(long albumId, long id, String title, String url, String thumbnailUrl) {
        super();
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static List<PhotosTable> getAlbumPhotos(long albumId) {
        return new Select().from(PhotosTable.class).where("albumId = ?",albumId).execute();
    }
}
