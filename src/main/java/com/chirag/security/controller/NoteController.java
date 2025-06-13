package com.chirag.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chirag.security.model.Note;
import com.chirag.security.services.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(
            @RequestBody String content,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.createNoteForUser(username, content);
    }

    @GetMapping
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.getNoteForUser(username);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable(name = "noteId") Long noteId) {
        noteService.deleteNoteForUser(userDetails.getUsername(), noteId);
    }

    @PutMapping("/{noteId}")
    public Note updateNote(
            @RequestBody String content,
            @PathVariable(name = "noteId") Long noteId,
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Note note = noteService.updateNoteForUser(username, content, noteId);
        return note;
    }
}
