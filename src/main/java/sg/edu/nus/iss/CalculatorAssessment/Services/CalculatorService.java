package sg.edu.nus.iss.CalculatorAssessment.Services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class CalculatorService {


    public Integer performMath(JsonObject obj){

        Integer oper1 = obj.getInt("oper1");
        Integer oper2 = obj.getInt("oper2");
        String ops = obj.getString("ops");
        Integer answer = 0;

        switch(ops){
            case "plus":
                answer = oper1 + oper2;
                break;
            case "minus":
                answer = oper1 - oper2;
                break;

            case "multiply":
                answer = oper1 * oper2;
                break;
            case "divide":
                answer = oper1 / oper2;
                break;
            default:
                break;

        }

        return answer;
    }

    public JsonObject createJsonObj(String body){

        InputStream data = new ByteArrayInputStream(body.getBytes());
        JsonReader reader = Json.createReader(data);
        JsonObject reqBody = reader.readObject();

        return reqBody;
    }

    
    
}
