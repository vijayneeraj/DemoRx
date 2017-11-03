package android.anative.com.demoadvance.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */
@Table(name = "post_table")
public class PostTable extends Model {
    @Column(name = "userId")
    public long userId;

    @Column(name = "_id")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "body")
    public String body;

    public PostTable(){

    }
    public PostTable(long userId,long id,String title,String body){
        super();
        this.userId=userId;
        this.id=id;
        this.title=title;
        this.body=body;
    }
    public static List<PostTable> getUsersPost(long userId){
        return new Select().from(PostTable.class).where("userId = ?",userId).execute();
    }
}
