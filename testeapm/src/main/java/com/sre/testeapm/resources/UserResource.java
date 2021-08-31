package com.sre.testeapm.resources;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sre.testeapm.domain.User;
import com.sre.testeapm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<User>> findALL() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
