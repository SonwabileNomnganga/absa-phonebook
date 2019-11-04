package za.co.absa.africanacity.phonebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.absa.africanacity.phonebook.domain.Entry;
import za.co.absa.africanacity.phonebook.domain.Phonebook;
import za.co.absa.africanacity.phonebook.repository.EntryRepository;
import za.co.absa.africanacity.phonebook.repository.PhonebookRepository;
import za.co.absa.africanacity.phonebook.service.PhonebookService;

import java.util.List;
import java.util.Optional;

@Service
public class PhonebookServiceImpl implements PhonebookService {

    @Autowired
    private EntryRepository repository;

    @Autowired
    private PhonebookRepository phonebookRepository;

    @Override
    public void addEntry(Entry entry) {

        Entry existingEntry = repository.findByNameAndSurname(entry.getName().toUpperCase(), entry.getSurname().toUpperCase());

        if(existingEntry != null)
            entry.setId(existingEntry.getId());

        repository.saveAndFlush(entry);
    }

    @Override
    public List<Entry> getAllEntries() {

        return repository.findAll();
    }

    @Override
    public List<Entry> findByName(String search) {
        return repository.findByName(search);
    }

    @Override
    public List<Entry> findBySurname(String search) {
        return repository.findBySurname(search);
    }

    @Override
    public List<Entry> findByNumber(String search) {
        return repository.findByNumber(search);
    }

    @Override
    public Optional<Entry> findById(int id) {
        return repository.findById(new Long(id));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(new Long(id));
    }

    @Override
    public List<Phonebook> getAllPhonebooks() {
        return phonebookRepository.findAll();
    }
}
