package pt.iade.helloworld.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path="/api/java/tester/")
public class JavaTesterController {
    private Logger logger = LoggerFactory.getLogger(JavaTesterController.class);
    @GetMapping(path = "", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getMessage() {
        logger.info("Deploying personalized information");
        boolean isFan = true;
        String name = "João Luz";
        int number = 20190798;
        float height = 1.83f;
        String club = "Juventos";
        if (isFan) {
            return "Done by " + name + " with number " + number + ".\n" + "I am " + height + " tall and I am fan of football.\nMy favourite club is " + club + ".";
        }
        else if (!isFan) {
            return "Done by " + name + " with number " + number + "\n" + "I am " + height + " tall  and not a fan of football.";
        }
        else {
            return ("Hello World!");
        }
    }

    @GetMapping(path = "{name}",
    produces= MediaType.APPLICATION_JSON_VALUE)
        public String getAuthor(@PathVariable("name") String name) {
            logger.info("Saying Hello to " + name);
            return "Hello " + name;
    }

    @GetMapping(path = "access/{student}/{covid}",              //url: Ex: http://localhost:8080/api/java/tester/access/true/false 
    produces= MediaType.APPLICATION_JSON_VALUE)                 // url output --> true   
    public boolean getGreeting(@PathVariable("student") boolean isStudent,
    @PathVariable("covid") boolean hasCovid) {
        if (isStudent && (!hasCovid)) {
            return true;
        }
        else {
            return false;
        }
        }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}",       //url example: http://localhost:8080/api/java/tester/required/true/35/presential    
    produces= MediaType.APPLICATION_JSON_VALUE)                               // url output --> true
    public boolean getRequired(@PathVariable("student") boolean isStudent,
            @PathVariable("temperature") double hasCovid,
            @PathVariable("classType") String type) {
                if (isStudent && type.equals("presential") &&  (hasCovid > 34.5 && hasCovid < 37.5)) { 
                    return true;                    
                }
                else {
                    return false;
                }
    }

    @GetMapping(path = "/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}",  //url example:http://localhost:8080/api/java/tester/evacuation/true/4/true/2
    produces= MediaType.APPLICATION_JSON_VALUE)                                               // url output --> true
    public boolean getEvacuation(@PathVariable("fire") boolean isBurning, @PathVariable("numberOfCovids") int numberOfCovids,
    @PathVariable("powerShutdown") boolean isPower, @PathVariable("comeBackTime") int time) {
        if (isBurning) {
            return true;
        }
        else if(numberOfCovids > 5) {
            return true;
        }
        else if(isPower || time > 15) {
            return true;
        }
        else {
            return false;
        }
    }
            
}


