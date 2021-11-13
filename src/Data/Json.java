package Data;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Json {
    String urlJson;
    String motorcycleNumber;

    public Json(String motorcycleNumber) {
        //https://data.gov.il/api/3/action/datastore_search?resource_id=053cea08-09bc-40ec-8f7a-156f0677aff3&limit=5
        this.urlJson = "https://data.gov.il/api/3/action/datastore_search?q=" + motorcycleNumber + "&resource_id=053cea08-09bc-40ec-8f7a-156f0677aff3";
    }

    //35608801
    private String getJsonData() {
        String result = "";
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(urlJson).openConnection();
            BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = buff.readLine()) != null) {
                result += line;
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        } finally {
            connection.disconnect();
        }
        //System.out.println(result);
        return result;
    }

    public void printJsonData() {
        String ourData = getJsonData();
        JSONObject jsonObject = new JSONObject(ourData);
        JSONObject myResult = jsonObject.getJSONObject("result");
        JSONArray motorCycleArray = myResult.getJSONArray("records");
        JSONObject motorCycle = motorCycleArray.getJSONObject(0);

        System.out.println("Manufactor: " + motorCycle.getString("tozeret_nm"));
        System.out.println("Type: " + motorCycle.getString("kinuy_mishari"));
        System.out.println("Production year: " + motorCycle.getInt("shnat_yitzur"));
        System.out.println("Engine: " + motorCycle.getString("degem_manoa"));
        System.out.println("Fuel type: " + motorCycle.getString("sug_delek_nm"));
        System.out.println("Pollution group: " + motorCycle.getInt("kvutzat_zihum"));
        System.out.println("Last License test: " + motorCycle.getString("mivchan_acharon_dt"));
        System.out.println("License validity: " + motorCycle.getString("tokef_dt"));
        System.out.println("Ownership type: " + motorCycle.getString("baalut"));


    }
}

