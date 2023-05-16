package com.byted.camp.todolist.beans;

import android.graphics.Color;

public enum Priority {
    High(2, Color.parseColor("#FBEAEA")),
    Medium(1, Color.parseColor("#F9F8E9")),
    Low(0, Color.rgb(228,249,245));

    public final int value;
    public final int color;

    Priority(int intValue, int color) {
        this.value = intValue;
        this.color = color;
    }

    public static Priority getPriority(int v) {
        for (Priority priority : Priority.values()) {
            if (priority.value == v) {
                return priority;
            }
        }

        return Priority.Low ;
    }
}
