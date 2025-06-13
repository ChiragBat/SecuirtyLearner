package com.chirag.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chirag.security.model.Note;
import com.chirag.security.repository.NoteRepository;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        return savedNote;
    }

    public Note updateNoteForUser(String username, String content, Long noteId) {
        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(
                        () -> new RuntimeException("Note not found"));
        note.setContent(content);
        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    public void deleteNoteForUser(String username, Long noteId) {
        noteRepository.deleteById(noteId);
    }

    public List<Note> getNoteForUser(String username) {
        List<Note> personalNotes = noteRepository.findByOwnerUsername(username);
        return personalNotes;
    }
}
