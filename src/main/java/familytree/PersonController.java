package familytree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/persons
 *
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<Person> csvToDirectNestedJson() {
        CsvToDirectNestedJson json = new CsvToDirectNestedJson();
        Collection<Person> roots = new ArrayList<>();

        try {
            roots = json.execute();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

    // Insert a new item.
    @RequestMapping(method = RequestMethod.POST, value = "/item", headers = {
            "Content-Type=application/json, application/xml", "Accept=application/json, application/xml" })
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Person addItem(@RequestBody Person item) {
        service.insert(item);
        return item;
    }
}