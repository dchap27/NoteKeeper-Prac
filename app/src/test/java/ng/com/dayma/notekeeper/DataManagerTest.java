package ng.com.dayma.notekeeper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ahmad on 7/4/2019.
 */
public class DataManagerTest {
    @Test
    public void createNewNote() throws Exception {
        DataManager dm = DataManager.getInstance();
        final CourseInfo course = dm.getCourse("android_sync");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body text of my test note";

        int noteIndex = dm.createNewNote();
        NoteInfo newNote = dm.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        NoteInfo compareNote = dm.getNotes().get(noteIndex);
        assertEquals(compareNote.getCourse(), course);
        assertEquals(compareNote.getTitle(), noteTitle);
        assertEquals(compareNote.getText(), noteText);

    }

}