package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoDbHelper;
import com.byted.camp.todolist.ui.NoteListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.provider.BaseColumns._ID;
import static com.byted.camp.todolist.db.TodoContract.TodoEntry.COLUMN_CONTENT;
import static com.byted.camp.todolist.db.TodoContract.TodoEntry.COLUMN_DATE;
import static com.byted.camp.todolist.db.TodoContract.TodoEntry.COLUMN_DEADLINE;
import static com.byted.camp.todolist.db.TodoContract.TodoEntry.COLUMN_STATE;
import static com.byted.camp.todolist.db.TodoContract.TodoEntry.TABLE_NAME;

/**
 * @author zhongshan
 * 2020-04-19.
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 1002;
    private TodoDbHelper dbHelper;
    private RecyclerView recyclerView;
    private NoteListAdapter notesAdapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NoteActivity.class), REQUEST_CODE_ADD);
            }
        });
        dbHelper = new TodoDbHelper(this);
        db = dbHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.list_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesAdapter = new NoteListAdapter(new NoteOperator() {
            @Override
            public void deleteNote(Note note) {
                MainActivity.this.deleteNote(note);
            }

            @Override
            public void updateNote(Note note) {
                MainActivity.this.updateNode(note);
            }
        });
        recyclerView.setAdapter(notesAdapter);

        notesAdapter.refresh(loadNotesFromDatabase());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK) {
            notesAdapter.refresh(loadNotesFromDatabase());
        }
    }

    private List<Note> loadNotesFromDatabase() {
        // TODO 从数据库中查询数据，并转换成 JavaBeans
        if (db == null) {
            return Collections.emptyList();
        }
        List<Note> result = new LinkedList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, null,
                    null, null,
                    null, null,
                    null);

            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndex(_ID));
                long date = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
                int state = cursor.getInt(cursor.getColumnIndex(COLUMN_STATE));
                String content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT));
                String deadline = cursor.getString(cursor.getColumnIndex(COLUMN_DEADLINE));
                Note note = new Note(id);
                note.setContent(content);
                note.setDate(new Date(date));
                note.setState(State.from(state));
                note.setDeadline(deadline);
                result.add(note);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    private void deleteNote(Note note) {
        // TODO 删除数据
        String selection = _ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(note.id)};
        int count = db.delete(TABLE_NAME, selection, selectionArgs);
        if (count > 0) {
            notesAdapter.refresh(loadNotesFromDatabase());
        } else {
            Toast.makeText(MainActivity
                    .this, "删除失败,请稍后再试", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateNode(Note note) {
        // 更新数据
        String selection = _ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(note.id)};
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATE, note.getState().intValue);
        int count = db.update(TABLE_NAME, values, selection, selectionArgs);
        if (count > 0) {
            notesAdapter.refresh(loadNotesFromDatabase());
        } else {
            Toast.makeText(MainActivity
                    .this, "更新,请稍后再试", Toast.LENGTH_SHORT).show();
        }
    }

}
