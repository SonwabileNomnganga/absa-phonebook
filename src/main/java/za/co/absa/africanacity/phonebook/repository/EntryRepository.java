package za.co.absa.africanacity.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.absa.africanacity.phonebook.domain.Entry;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    Entry findByNameAndSurname(String name, String surname);

    List<Entry> findByName(String search);

    List<Entry> findBySurname(String search);

    List<Entry> findByNumber(String search);
}
