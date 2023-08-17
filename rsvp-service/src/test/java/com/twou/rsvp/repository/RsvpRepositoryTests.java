package com.twou.rsvp.repository;

import com.twou.rsvp.model.Rsvp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RsvpRepositoryTests {

    @Autowired
    RsvpRepository repo;

    @BeforeEach
    public void setUp() throws Exception {
        repo.deleteAll();
    }

    @Test
    public void addGetDeleteRsvp() {
        Rsvp newRsvp = repo.save(new Rsvp(0, "John Doe", 2));
        assertEquals(true, repo.existsById(newRsvp.getId()));
        repo.deleteById(newRsvp.getId());
        assertEquals(false, repo.existsById(newRsvp.getId()));
    }

    @Test
    public void getAllRsvps() {
        repo.save(new Rsvp(0, "Sally Smith", 4));
        repo.save(new Rsvp(0, "George Smith", 3));

        List<Rsvp> rsvps = repo.findAll();
        assertEquals(2, rsvps.size());
    }

    @Test
    public void updateRsvp() {
        final String newName = "NEW NAME";

        Rsvp newRsvp = repo.save(new Rsvp(0, "Joe Jones", 5));
        newRsvp.setName(newName);
        Rsvp updatedRsvp = repo.save(newRsvp);
        assertEquals(updatedRsvp.getGuestName(), newName);
    }

}