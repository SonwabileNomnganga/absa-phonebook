package za.co.absa.africanacity.phonebook.service;

import za.co.absa.africanacity.phonebook.domain.Entry;

import java.util.List;
import java.util.Optional;

public interface PhonebookService {

    void addEntry(Entry entry);

    List<Entry> getAllEntries();

    List<Entry> findByName(String search);

    List<Entry> findBySurname(String search);

    List<Entry> findByNumber(String search);

    Optional<Entry> findById(int id);

    void deleteById(int id);
}
