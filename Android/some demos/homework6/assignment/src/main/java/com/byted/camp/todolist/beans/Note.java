package com.byted.camp.todolist.beans;

import java.util.Date;

/**
 * @author zhongshan
 * @date 2021-04-19
 */
public class Note {

    public final long id;
    private Date date;
    private State state;
    private String content;

    public Note(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDeadline(String deadline) {
    }
}
