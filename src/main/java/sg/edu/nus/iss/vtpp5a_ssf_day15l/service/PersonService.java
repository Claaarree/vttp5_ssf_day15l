package sg.edu.nus.iss.vtpp5a_ssf_day15l.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vtpp5a_ssf_day15l.model.Person;
import sg.edu.nus.iss.vtpp5a_ssf_day15l.repo.ListRepo;

@Service
public class PersonService {
    
    @Autowired
    ListRepo personRepo;


    public void addPerson(String redisKey, Person person) {
        personRepo.rightPush(redisKey, person.toString());
    }

    public List<Person> findAll(String key) {
        List<Object> listData = personRepo.getList(key);
        List<Person> persons = new ArrayList<>();


        for(Object data : listData){
            String[] rawData = data.toString().split(",");
            // System.out.println(data);
            Person p = new Person(Integer.parseInt(rawData[0]), rawData[1], rawData[2]);
            persons.add(p);
        }

        return persons;
    }

    public Boolean delete(String redisKey, Person person) {
        return personRepo.deleteItem(redisKey, person.toString());
    }
}