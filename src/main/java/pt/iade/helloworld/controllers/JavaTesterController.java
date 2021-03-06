package pt.iade.helloworld.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.helloworld.models.CurricularUnit;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/java/tester")
public class JavaTesterController {

    private ArrayList<CurricularUnit> units = new ArrayList<CurricularUnit>();
    
    @PostMapping(path = "/units", produces= MediaType.APPLICATION_JSON_VALUE)    // http://localhost:8080/api/java/tester/units
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit) {
        logger.info("Added unit " + unit.getName());
        units.add(unit);
        return unit;
    }

    @GetMapping(path = "/units",
    produces= MediaType.APPLICATION_JSON_VALUE)                                  // http://localhost:8080/api/java/tester/units
    public ArrayList<CurricularUnit> getUnits() {
        logger.info("Get " + units.size() + " Units");
        return units;
    } 

    @GetMapping(path = "/average",                                //http://localhost:8080/api/java/tester/average
    produces= MediaType.APPLICATION_JSON_VALUE)
    public double calcAverage() {
        double credits = 0;
        double total = 0;
        for(int i=0; i < units.size(); i++) {
            total += units.get(i).getGrade() * units.get(i).getEcts();
            credits += units.get(i).getEcts();
        }
        return total/credits; 
    }

    @GetMapping(path = "/max",                                //http://localhost:8080/api/java/tester/max
    produces= MediaType.APPLICATION_JSON_VALUE)
    public double calcMax() {
        double max = 0;
        for(int i=0; i < units.size(); i++)
            if(units.get(i).getGrade() > max){
                max = units.get(i).getGrade();
            }
        return max;
    }

    @GetMapping(path = "/grade",                                //http://localhost:8080/api/java/tester/grade
    produces= MediaType.APPLICATION_JSON_VALUE)
    public double calcGrade(){
        String name = units.get(units.size()-1).getName();      //calculates the grade for the last UC name given 
        double finalGrade = 0;
        for(int i=0; i < units.size(); i++)
            if(units.get(i).getName().equals(name)){
                finalGrade = units.get(i).getGrade();
            }
        return finalGrade;
    }

    @GetMapping(path = "/semester",                                //http://localhost:8080/api/java/tester/semester
    produces= MediaType.APPLICATION_JSON_VALUE)
    public String calcSemester(){
        int givenSemester = 3;                          //calculates the number of UCs given for exmaple the 3rd semester
        String finalUcs = "";
        for(int i=0; i < units.size(); i++)
            if(units.get(i).getSemester() == givenSemester){
                finalUcs += units.get(i).getName() + " ";
            }
        return finalUcs;
    }

    @GetMapping(path = "/limit",                                //http://localhost:8080/api/java/tester/limit
    produces= MediaType.APPLICATION_JSON_VALUE)
    public int calcLimit(){
        double min = 9.5;                                       //calculates the number of UCs that have grades between 9.5 and 13
        double max = 13;
        int numberOutput = 0;
        for(int i=0; i < units.size(); i++)
            if(units.get(i).getGrade() > min && units.get(i).getGrade() < max){
                numberOutput += 1;
            }
        return numberOutput;
    }

    private Logger logger = LoggerFactory.getLogger(JavaTesterController.class);

    @GetMapping(path = "", produces = APPLICATION_JSON_VALUE)
    public String getMessage() {                                // url:http://localhost:8080/api/java/tester/
        logger.info("Deploying personalized information");      // output --> variable text
        boolean isFan = true;
        String name = "João Luz";
        int number = 20190798;
        float height = 1.83f;
        String club = "Juventos";
        if (isFan) {
            return "Done by " + name + " with number " + number + ".\n" + "I am " + height
                    + " tall and I am fan of football.\nMy favourite club is " + club + ".";
        } else if (!isFan) {
            return "Done by " + name + " with number " + number + "\n" + "I am " + height
                    + " tall  and not a fan of football.";
        } else {
            return ("Hello World!");
        }
    }

    @GetMapping(path = "{name}", produces = APPLICATION_JSON_VALUE)
    public String getAuthor(@PathVariable("name") String name) {
        logger.info("Saying Hello to " + name);
        return "Hello " + name;
    }

    @GetMapping(path = "access/{student}/{covid}",          // url: Ex: http://localhost:8080/api/java/tester/access/true/false
            produces = APPLICATION_JSON_VALUE)              // url output --> true
    public boolean getGreeting(@PathVariable("student") boolean isStudent, @PathVariable("covid") boolean hasCovid) {
        if (isStudent && (!hasCovid)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}",       // url example:tp://localhost:8080/api/java/tester/required/true/35/presential                                                                        
            produces = APPLICATION_JSON_VALUE)                               // url output --> true
    public boolean getRequired(@PathVariable("student") boolean isStudent, @PathVariable("temperature") double hasCovid,
            @PathVariable("classType") String type) {
        if (isStudent && type.equals("presential") && (hasCovid > 34.5 && hasCovid < 37.5)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping(path = "/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}",       // url example:http://localhost:8080/api/java/tester/evacuation/true/4/true/2                                                                                        
            produces = APPLICATION_JSON_VALUE)                                                     // url output --> true
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
    
    private static double grades[] = {10.5, 12, 14.5};      //url:http://localhost:8080/api/java/tester/arrays
    private static String ucs[] = {"FP","POO","BD"};

    @GetMapping(path = "/arrays",                                                                     
            produces = APPLICATION_JSON_VALUE)
    public double average() {
        double total = 0;
        for(int i=0; i<grades.length; i++) {
            total += grades[i];
        }
        double averageFinal = total / grades.length;
        return averageFinal;
    }

    @GetMapping(path = "/conditionals",                 //url:http://localhost:8080/api/java/tester/conditionals                                           
            produces = APPLICATION_JSON_VALUE)          
    public double max() {
        double max = grades [0];
        for(int i=0; i<grades.length; i++) {
            if (grades[i] > max) {
                max = grades[i];
            }
        }
        return max;
    }

    @GetMapping(path = "/loops",
        produces = APPLICATION_JSON_VALUE) 
    public double guessGrade() {                 //url:http://localhost:8080/api/java/tester/loops
        String name;                             //example output --> 14.5 with string variable "BD"
        name = "BD";
        int position = 0;
        for(int i=0; i<ucs.length; i++) {
            if (ucs[i].equals(name)) {
                position = i;
                break;
            }
        }
        double gradeFinal = grades[position];
        return gradeFinal;
    }
    
    @GetMapping(path = "/loops1",              //url:http://localhost:8080/api/java/tester/loops1
        produces = APPLICATION_JSON_VALUE)     //example output --> 2
    public int limits() {
        double min = 11;
        double max = 15;
        int times = 0;
        for(int i=0; i<grades.length; i++) {
            if (grades[i] > min && grades[i] < max){
                times++;
            }
        }
    return times;
    }

    @GetMapping(path = "/loops2",                    //url:http://localhost:8080/api/java/tester/loops2
        produces = APPLICATION_JSON_VALUE)
    public String text() {
        String text = "";
        for(int i = 0; i < ucs.length; i++) {
            text += ucs[i] + ":" + grades[i] + "\n";
        }
        return text;
    }

}


