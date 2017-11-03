package android.anative.com.demoadvance.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */
@Table(name = "comments_table")
public class CommentsTable extends Model {
    @Column(name = "postId")
    public long postId;

    @Column(name = "id")
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "email")
    public String email;

    @Column(name = "body")
    public String body;

    public CommentsTable() {

    }

    public CommentsTable(long postId, long id, String name, String email, String body) {
        super();
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
    public static List<CommentsTable> getPostComments(long postid){
        return new Select().from(CommentsTable.class).where("postId = ?",postid).orderBy("id").execute();
    }

}
