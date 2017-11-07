package android.anative.com.demoadvance.volley.Models;

import android.anative.com.demoadvance.database.PhotosTable;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class PhotosModel extends BaseModel {
    /**
     * albumId : 1
     * id : 1
     * title : accusamus beatae ad facilis cum similique qui sunt
     * url : http://placehold.it/600/92c952
     * thumbnailUrl : http://placehold.it/150/92c952
     */

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public void saveToDb() {
        PhotosTable photosModel = new PhotosTable();
        photosModel.albumId = albumId;
        photosModel.id = id;
        photosModel.title = title;
        photosModel.url = url;
        photosModel.thumbnailUrl = thumbnailUrl;
        photosModel.save();
    }
}
