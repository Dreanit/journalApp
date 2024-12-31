package com.dreanit.journalApp.service;

import com.dreanit.journalApp.entity.JournalEntry;
import com.dreanit.journalApp.entity.User;
import com.dreanit.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository _journalEntryRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());

            JournalEntry saved = _journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    public void saveEntry(JournalEntry journalEntry) {

        _journalEntryRepo.save(journalEntry);

    }

    public List<JournalEntry> getEntries() {
        return _journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return _journalEntryRepo.findById(id);
    }

    public void deleteEntryById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        _journalEntryRepo.deleteById(id);
    }


}
