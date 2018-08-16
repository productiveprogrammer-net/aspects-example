package net.productiveprogrammer.controller;

import net.productiveprogrammer.model.HelloMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public HelloMessage sayHello(@PathVariable final String name, @RequestParam("age") final int age){
        return new HelloMessage(name, age);
    }

}
