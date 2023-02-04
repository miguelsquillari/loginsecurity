package com.security.msq.students;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api/std")
public class StudentController {

// body, status, and headers of an HTTP response using ResponseEntity.
    @GetMapping("/gets")
    public ResponseEntity<String> getStudents(){
        return new ResponseEntity<String>("hola pupi", HttpStatus.OK);
    }

    @GetMapping("/calculateage")
    public ResponseEntity<String> calculateAge(@RequestParam("age") int age){
        if (age > 100){
            return new ResponseEntity<>("imposible tener esa Edad !", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("A VIVIR LA VIDA  !", HttpStatus.OK);
    }

    @GetMapping("/calculateAge2")
    public ResponseEntity<String> calculateAge2(){
        HttpHeaders headers =  new HttpHeaders();
        headers.set("calulcar-edad", "bien-calculado");
        return new ResponseEntity<>("Calulcados", headers, HttpStatus.OK);
    }

}
