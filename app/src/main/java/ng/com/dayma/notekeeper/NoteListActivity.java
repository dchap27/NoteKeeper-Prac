package ng.com.dayma.notekeeper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private NoteRecyclerAdapter mNoteRecyclerAdapter;
    private SQLiteOpenHelper mDbOpenHelper;

//    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        mAdapterNotes.notifyDataSetChanged();
        loadNotes();
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void loadNotes() {
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        final String[] noteColumns = {
                NoteKeeperDatabaseContract.NoteInfoEntry.COLUMN_NOTE_TITLE,
                NoteKeeperDatabaseContract.NoteInfoEntry.COLUMN_COURSE_ID,
                NoteKeeperDatabaseContract.NoteInfoEntry._ID,
        };
        // order the notes using multiple columns
        String noteOrderBy = NoteKeeperDatabaseContract.NoteInfoEntry.COLUMN_COURSE_ID + "," + NoteKeeperDatabaseContract.NoteInfoEntry.COLUMN_NOTE_TITLE;
        Cursor noteCursor = db.query(NoteKeeperDatabaseContract.NoteInfoEntry.TABLE_NAME,
                noteColumns, null, null, null, null, noteOrderBy);
        mNoteRecyclerAdapter.changeCursor(noteCursor);
    }

    private void initializeDisplayContent() {
//        final ListView listnotes = (ListView) findViewById(R.id.list_notes);
//
//        List<NoteInfo> notes = DataManager.getInstance().getNotes();
//        mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
//
//        listnotes.setAdapter(mAdapterNotes);
//
//        listnotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // the first parameter represent Notelistactivity interface,
//                // while 2nd parameter is the activity i want to launch
//                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
////                NoteInfo note = (NoteInfo) listnotes.getItemAtPosition(position);
//                intent.putExtra(NoteActivity.NOTE_ID, position);
//                startActivity(intent);
//            }
//        });

        final RecyclerView recyclerNotes = (RecyclerView) findViewById(R.id.list_notes);
        final LinearLayoutManager notesLayoutManager = new LinearLayoutManager(this);
        recyclerNotes.setLayoutManager(notesLayoutManager);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this, null);
        recyclerNotes.setAdapter(mNoteRecyclerAdapter);
    }

}
