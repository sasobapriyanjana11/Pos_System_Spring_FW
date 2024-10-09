package lk.ijse.gdse68.POS_System_REST_API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthTest")
public class HealthTestController {
    @GetMapping
    public String healthTesting(){
        return "pos system api run successfully !";
    }
}
