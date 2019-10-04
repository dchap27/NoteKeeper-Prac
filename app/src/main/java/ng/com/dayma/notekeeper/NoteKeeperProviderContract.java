package ng.com.dayma.notekeeper;

import android.net.Uri;
import android.provider.BaseColumns;

public final class NoteKeeperProviderContract {
    private NoteKeeperProviderContract(){}
    public static final String AUTHORITY = "ng.com.dayma.notekeeper.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    // create column interface common to both tables
    protected interface CoursesIdColumns {
        public static final String COLUMN_COURSE_ID = "course_id";
    }

    // create interfaces for table columns
    protected interface CoursesColumns{
        public static final String COLUMN_COURSE_TITLE = "course_title";
    }

    protected interface NotesColumns {
        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_TEXT = "note_text";
    }

    // create the courses table:  the BaseColumns contains the _ID column interface
    public static final class Courses implements BaseColumns, CoursesColumns, CoursesIdColumns {
        // create path for the table
        public static final String PATH = "courses";
        // content://ng.com.dayma.notekeeper.provider/courses
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
    }

    // create the notes table
    public static final class Notes implements BaseColumns, NotesColumns, CoursesIdColumns, CoursesColumns {
        // the CoursesColumns is available for the EXPANDED_URI
        public static final String PATH = "notes";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
        public static final String PATH_EXPANDED = "notes_expanded";
        public static final Uri CONTENT_EXPANDED_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH_EXPANDED);

    }
}
