package za.co.absa.africanacity.phonebook.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import za.co.absa.africanacity.phonebook.domain.Entry;
import za.co.absa.africanacity.phonebook.repository.EntryRepository;
import za.co.absa.africanacity.phonebook.service.PhonebookService;

import javax.print.attribute.standard.MediaSize;

import static org.junit.Assert.*;

public class PhonebookServiceImplTest {

    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String NUMBER = "NUMBER";
    private EntryRepository repository;

    private Entry entry;

    private PhonebookService phonebookService;

    @Before
    public void setUp() throws Exception {

        phonebookService = new PhonebookServiceImpl();
        repository = Mockito.mock(EntryRepository.class);
        entry = Mockito.mock(Entry.class);
        ReflectionTestUtils.setField(phonebookService, "repository", repository);
    }

    @Test
    public void testIfRepositoryIsCalledWhenAddEntryIsCalled() {

        Mockito.when(repository.findByNameAndSurname(NAME, SURNAME)).thenReturn(entry);
        Mockito.when(entry.getName()).thenReturn(NAME);
        Mockito.when(entry.getSurname()).thenReturn(SURNAME);

        phonebookService.addEntry(entry);

       Mockito.verify(repository, Mockito.atLeast(1)).saveAndFlush(entry);

    }

    @Test
    public void testIfRepositoryIsCalledWhenGetAllEntries() {

        phonebookService.getAllEntries();

        Mockito.verify(repository, Mockito.atLeastOnce()).findAll();
    }

    @Test
    public void testIfRepositoryIsCalledWhenFindByName() {

        phonebookService.findByName(NAME);

        Mockito.verify(repository, Mockito.atLeastOnce()).findByName(NAME);
    }

    @Test
    public void testIfRepositoryIsCalledWhenFindBySurname() {

        phonebookService.findBySurname(SURNAME);

        Mockito.verify(repository, Mockito.atLeastOnce()).findBySurname(SURNAME);
    }

    @Test
    public void testIfRepositoryIsCalledWhenFindByNumber() {

        phonebookService.findByNumber(NUMBER);

        Mockito.verify(repository, Mockito.atLeastOnce()).findByNumber(NUMBER);
    }

    @Test
    public void testIfRepositoryIsCalledWhenFindById() {

        phonebookService.findById(1);

        Mockito.verify(repository, Mockito.atLeastOnce()).findById(new Long(1));
    }

    @Test
    public void testIfRepositoryIsCalledWhenDeleteById() {

        phonebookService.deleteById(1);

        Mockito.verify(repository, Mockito.atLeastOnce()).deleteById(new Long(1));
    }

    @After
    public void tearDown() throws Exception {
    }
}