package sg.edu.nus.iss.CalculatorAssessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.CalculatorAssessment.Services.CalculatorService;

@RestController
@RequestMapping(path="/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
public class CalculatorController {

    @Autowired
    CalculatorService cService;
    

    @PostMapping
    public ResponseEntity<String> result(@RequestBody String body,
                                        @RequestHeader ("User-Agent")  String header){

        System.out.println(">>>>>>>>>>>" + body); 
        System.out.println(">>>>>>>>>>>" + header.toString());
        System.out.println(">>>>>>>>>>>" + header.toString());

        JsonObject reqBody = cService.createJsonObj(body);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + reqBody.toString());

        Integer answer = cService.performMath(reqBody);

        JsonObject result = Json.createObjectBuilder()
                                .add("result", answer)
                                .add("timestamp", Long.toString(System.currentTimeMillis()))
                                .add("userAgent", header)
                                .build();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>" + result.toString());

        return ResponseEntity.ok(result.toString());
    }
    
}
