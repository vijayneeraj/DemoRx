package android.anative.com.demoadvance.volley.Models;

import android.anative.com.demoadvance.database.PostTable;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class PostModel extends BaseModel
{
    /**
     * userId : 1
     * id : 1
     * title : sunt aut facere repellat provident occaecati excepturi optio reprehenderit
     * body : quia et suscipit
     suscipit recusandae consequuntur expedita et cum
     reprehenderit molestiae ut ut quas totam
     nostrum rerum est autem sunt rem eveniet architecto
     */

    private int userId;
    private int id;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void saveToDb() {
        PostTable postTable=new PostTable();
        postTable.userId=userId;
        postTable.id=id;
        postTable.title=title;
        postTable.body=body;
        postTable.save();
    }
}
