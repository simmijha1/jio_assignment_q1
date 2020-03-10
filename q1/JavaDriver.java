import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JavaDriver {
    public static void main(String[] args) throws Exception {

       String fileName = "C:\\\\tools\\\\goland2\\\\goland2\\\\config\\\\scratches\\\\auto_fill_data.json";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Integer noOfTestCases = Integer.parseInt(bufferedReader.readLine());

        JSONArray jsonArray = (JSONArray) readJson(fileName);
        
        String search= bufferedReader.readLine();
       while(noOfTestCases>0) 
       {
        List<String> output = getAutoFillResponse(jsonArray, search);
        
        for(String  item:output)
        {
        	System.out.println(item);
        }
        noOfTestCases--;
       }
    }

    private static List<String> getAutoFillResponse(JSONArray jsonArray, String query) {
        //String val = (String) ((JSONObject) jsonArray.get(2)).get("question");
    	List<JSONObject> outpt= new JSONArray();
        for(int i=0;i<jsonArray.size();i++) 
        {
        	String val = (String) ((JSONObject) jsonArray.get(i)).get("question");
        	if(val.contains(query))
        		outpt.add((JSONObject)jsonArray.get(i)); 			
        }
      outpt.sort((JSONObject b1, JSONObject b2) ->Integer.parseInt(b1.get("rank").toString())-Integer.parseInt(b2.get("rank").toString()));
        
        /*for(JSONObject item:outpt)
        {
        	System.out.println(item);
        }*/ 
      	List<String> out=new JSONArray();
        for(JSONObject item:outpt) {
        	out.add(item.get("question").toString());
        }
        return out;
    }

    public static Object readJson(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser parser = new JSONParser();
        return parser.parse(reader);
    }
    
}
