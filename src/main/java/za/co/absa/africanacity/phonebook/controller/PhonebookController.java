package za.co.absa.africanacity.phonebook.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.absa.africanacity.phonebook.domain.Entry;
import za.co.absa.africanacity.phonebook.service.PhonebookService;

@Slf4j
@Controller
public class PhonebookController {

    public static final String BY_NAME = "name";
    public static final String BY_SURNAME = "surname";
    public static final String ACTION_CANCEL = "Cancel";

    @Autowired
    private PhonebookService phonebookService;

    @GetMapping("/phonebook")
    public String phonebook(Model model){

        model.addAttribute("entries", phonebookService.getAllEntries());
        return "phonebook";
    }

    @GetMapping("/addnew")
    public String addNewEntry( Model model){

        model.addAttribute("entry", new Entry());

        return "addnew";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute(value="entry") Entry entry, Model model, @RequestParam(value="action", required = false) String action){

        if(ACTION_CANCEL.equals(action)) {
            model.addAttribute("entries", phonebookService.getAllEntries());

            return "redirect:phonebook";
        }

        if(valid(entry, model)) {

            entry.setName(entry.getName().toUpperCase());
            entry.setName(entry.getSurname().toUpperCase());

            log.info("Saving entry <" + entry + ">");
            phonebookService.addEntry(entry);
            model.addAttribute("entries", phonebookService.getAllEntries());

            return "redirect:phonebook";
        }

        return "addnew";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value="option", required=true) String option, @RequestParam(value="search", required=true) String search,
                         Model model){

        if(BY_NAME.equals(option))
            model.addAttribute("entries", phonebookService.findByName(search.toUpperCase()));
        else if(BY_SURNAME.equals(option))
            model.addAttribute("entries", phonebookService.findBySurname(search.toUpperCase()));
        else
            model.addAttribute("entries", phonebookService.findByNumber(search.toUpperCase()));

        return "phonebook";
    }

    @GetMapping(value="/entry/update")
    public String update(@ModelAttribute(value="entry") Entry entry,  Model model, @RequestParam(value="action", required = false) String action) {

        if(ACTION_CANCEL.equals(action)) {
            model.addAttribute("entries", phonebookService.getAllEntries());
            return "phonebook";
        }

        phonebookService.addEntry(entry);
        model.addAttribute("entries", phonebookService.getAllEntries());

        return "phonebook";
    }

    @GetMapping(value="/entry/edit")
    public String edit(@RequestParam(value="id") int id, Model model) {

        model.addAttribute("entry", phonebookService.findById(id).get());
        return "edit";
    }

    @GetMapping(value="/entry/delete")
    public String delete(@RequestParam(value="id") int id, Model model) {

        phonebookService.deleteById(id);

        model.addAttribute("entries", phonebookService.getAllEntries());
        model.addAttribute("deleted", true);

        return "phonebook";
    }


    private boolean valid(Entry entry, Model model) {
        if(StringUtils.isBlank(entry.getName()) || StringUtils.isBlank(entry.getSurname()) || StringUtils.isBlank(entry.getNumber())) {
            model.addAttribute("notAlldetailsSupplied", true);
            model.addAttribute("entry", entry);
            return false;
        }

        //TODO validate number
        return true;
    }


}
