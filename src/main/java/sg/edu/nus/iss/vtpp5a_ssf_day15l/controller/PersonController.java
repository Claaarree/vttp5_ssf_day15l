package sg.edu.nus.iss.vtpp5a_ssf_day15l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vtpp5a_ssf_day15l.model.Person;
import sg.edu.nus.iss.vtpp5a_ssf_day15l.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personSvc;
    
    @GetMapping("")
    public String personFindAll(Model model) {
        List<Person> persons = personSvc.findAll("persons");
        model.addAttribute("persons", persons);
        return "persons";
    }

    @GetMapping("/create")
    public String getPersonForm(Model model) {
        Person p = new Person();
        model.addAttribute("person", p);
        return "addPersonForm";
    }

    @PostMapping("/create")
    public String handleNewPersonForm(@Valid Person person, BindingResult result,
    Model model) {
        if (result.hasErrors()){
            return "addPersonForm";
        }
        
        Person p = new Person(person.getId(), person.getFullName(), person.getEmail());
        // System.out.println(p);
        personSvc.addPerson("persons", p);
        
        return "redirect:/persons";
    }
}
