package android.anative.com.demoadvance.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */
@Table(name = "todo_table")
public class TodoTable extends Model {
    @Column(name = "userId")
    public long userId;

    @Column(name = "id")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "completed")
    public boolean completed;

    public TodoTable(){

    }
    public TodoTable(long userId,long id,String title,boolean completed){
        super();
        this.userId=userId;
        this.id=id;
        this.title=title;
        this.completed=completed;
    }
    public static List<TodoTable> getUsersTodo(long userId){
       return new Select().from(TodoTable.class).where("userId = ?",userId).execute();
    }
}
