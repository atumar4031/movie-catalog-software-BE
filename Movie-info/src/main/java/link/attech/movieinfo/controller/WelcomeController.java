package link.attech.movieinfo.controller;

import link.attech.movieinfo.entity.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/welcome")
@CrossOrigin("*")
public class WelcomeController {
        @GetMapping("/sms")
        public String greeting(){
            return "Category welcome greeting";
        }

        @PostMapping("/sms")
        public String greeting(@RequestBody UserRequest userRequest){
            log.info("user : {}", userRequest);
            return "request seccess";
        }

}
