/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.NoteDB;
import java.util.List;
import model.Note;

/**
 *
 * @author 798419
 */
public class NoteService {
    
    private NoteDB noteDB;
    
    public NoteService() {
        noteDB = new NoteDB();
    }
    
    public Note get(int noteid) {
        return noteDB.get(noteid);
    }
    
    public List<Note> getAll() {
        return noteDB.getAll();
    }
    
    public int update(int noteid, String title, String contents, java.util.Date datecreated) {
        Note note = new Note();
        note.setNoteid(noteid);
        note.setTitle(title);
        note.setContents(contents);
        note.setDatecreated(datecreated);//!!!!!!!!
        int update = noteDB.update(note);
        return update;
    }
    
    public int delete(int noteid) {
        return noteDB.delete(noteDB.get(noteid));
    }
    
    public int insert(String contents, String title){
        Note note = new Note();
        note.setContents(contents);
        note.setTitle(title);
//        note.setNoteid(0);//????????
        note.setDatecreated(new java.util.Date());// This should be Okay.
        int update = noteDB.update(note);
        return update;
    }

    public void get(String selectedNote) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
