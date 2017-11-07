package android.anative.com.demoadvance.volley.Models;

import android.anative.com.demoadvance.database.TodoTable;

/**
 * Created by Neeraj VijayVargiya on 3/11/17.
 */

public class TodoModel extends BaseModel{
    /**
     * userId : 1
     * id : 1
     * title : delectus aut autem
     * completed : false
     */

    private int userId;
    private int id;
    private String title;
    private boolean completed;

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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public void saveToDb() {
        TodoTable todoTable = new TodoTable();
        todoTable.userId = userId;
        todoTable.id = id;
        todoTable.title = title;
        todoTable.completed = completed;
        todoTable.save();
    }
}

