/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollution;

/**
 *
 * @author SK MAMUN ARFIN
 * @author Yajnab
 */
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
 
public class Pollution {
 
    private static final String USER_AGENT = "Mozilla/5.0";
 
   private static final String POST_URL = "http://emis.wbpcb.gov.in/airquality/showaqdatanxt.do";
   //dist_code is for city check the source code for by inspect element for dist_code 
   //dt_part for date
   //mon_part for month
   //yr_part for year
    private static final String POST_PARAMS = "dist_code=013&dt_part=01&mon_part=01&yr_part=2005";
 
    public static void main(String[] args) throws IOException {
 
         sendPOST();
        System.out.println("POST DONE");
    }
 
     private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
 
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END
 
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
 
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
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
        } else {
            System.out.println("POST request not worked");
        }
    }
 
}
