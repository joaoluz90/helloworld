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
    public String getHello() {
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

}
