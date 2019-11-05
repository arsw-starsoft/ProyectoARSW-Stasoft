package edu.eci.arsw.synchdrive.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import org.springframework.stereotype.Component;

import edu.eci.arsw.synchdrive.model.Coordinate;
import edu.eci.arsw.synchdrive.model.Servicio;

@Component
public class HttpConnectionService {

    private static final String USER_AGENT = "Mozilla/5.0";
     
    public static String getUberApp(String name) throws IOException {
    	String url = "https://uber-backend-starsoft.herokuapp.com/users/" + name;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        
         return Integer.toString(responseCode);
    }
    public static String getUberAppDriver(String name) throws IOException {
    	String url = "https://uber-backend-starsoft.herokuapp.com/drivers/" + name;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        
         return Integer.toString(responseCode);
    }

    private static String getAPPS(String appName,Coordinate coordinate) throws IOException {
    	String url = "https://uber-backend-starsoft.herokuapp.com/servicios";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return null;
    }


    public static Servicio[] getCloseServices(String appName,Coordinate coordinate) throws IOException{

		String data = getAPPS(appName, coordinate);
		Servicio[] service = new Gson().fromJson(data,Servicio[].class);
			
	
		return service;

	}
        


}