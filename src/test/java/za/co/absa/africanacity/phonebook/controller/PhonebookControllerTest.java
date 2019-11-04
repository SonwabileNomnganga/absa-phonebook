package za.co.absa.africanacity.phonebook.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import za.co.absa.africanacity.phonebook.domain.Entry;
import za.co.absa.africanacity.phonebook.service.PhonebookService;

import static org.mockito.Mockito.*;

public class PhonebookControllerTest {

    public static final String ADDNEW = "addnew";

    public static final String ACTION_CANCEL = "Cancel";
    public static final String PHONEBOOK = "phonebook";
    public static final String REDIRECT_PHONEBOOK = "redirect:phonebook";
    public static final String BY_NAME = "name";
    public static final String BY_SURNAME = "surname";
    public static final String BY_NUMBER = "number";
    public static final String SEARCH = "SEARCH";
    public static final String EDIT = "edit";

    private PhonebookService phonebookService;

    private Model model;

    private Entry entry;

    private PhonebookController phonebookController;

    @Before
    public void setup() {

        phonebookController = new PhonebookController();
        phonebookService = Mockito.mock(PhonebookService.class);
        model = Mockito.mock(Model.class);
        entry = Mockito.mock(Entry.class);

        ReflectionTestUtils.setField(phonebookController, "phonebookService", phonebookService);

    }

    @Test
    public void testThatThePhonebookServiceIsCalledOnStartup(){

        phonebookController.phonebook(model);
        verify(phonebookService, times(1)).getAllEntries();
    }

    @Test
    public void testTheAddNewStringResourceIsReturnedWhenAddNewIsCalled() {

       String resource =  phonebookController.addNewEntry(model);

       Assert.assertEquals(ADDNEW, resource);

    }

    @Test
    public void testThatTheAddEntryMethodIsNeverCalledWhenActionIsCancelWhenAddingNewEntry() {

        String resource = phonebookController.add(entry, model, ACTION_CANCEL);

        verify(phonebookService, times(1)).getAllEntries();
        verify(phonebookService, times(0)).addEntry(entry);

        Assert.assertEquals(REDIRECT_PHONEBOOK, resource);
    }

    @Ignore
    public void testThatTheAddEntryMethodCalledWhenAddingNewEntry(){

        String resource = phonebookController.add(entry, model, ACTION_CANCEL);

        verify(phonebookService, times(1)).getAllEntries();
        verify(phonebookService, times(1)).addEntry(entry);

        Assert.assertEquals(REDIRECT_PHONEBOOK, resource);
    }

    @Test
    public void testFindBuyNameIsCalledAndOthersSearchesAreNot() {

        String resource = phonebookController.search(BY_NAME, SEARCH, model);
        verify(phonebookService, times(1)).findByName(SEARCH);
        verify(phonebookService, times(0)).findByNumber(SEARCH);
        verify(phonebookService, times(0)).findBySurname(SEARCH);

        Assert.assertEquals(PHONEBOOK, resource);
    }

    @Test
    public void testFindBuySurnameIsCalledAndOthersSearchesAreNot() {

        String resource = phonebookController.search(BY_SURNAME, SEARCH, model);
        verify(phonebookService, times(0)).findByName(SEARCH);
        verify(phonebookService, times(0)).findByNumber(SEARCH);
        verify(phonebookService, times(1)).findBySurname(SEARCH);

        Assert.assertEquals(PHONEBOOK, resource);
    }

    @Test
    public void testFindBuyNumberIsCalledAndOthersSearchesAreNot() {

        String resource = phonebookController.search(BY_NUMBER, SEARCH, model);
        verify(phonebookService, times(0)).findByName(SEARCH);
        verify(phonebookService, times(1)).findByNumber(SEARCH);
        verify(phonebookService, times(0)).findBySurname(SEARCH);
        Assert.assertEquals(PHONEBOOK, resource);
    }

}