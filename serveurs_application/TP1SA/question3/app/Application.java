package app;

import java.io.*;
import java.net.*;

public class Application 
{
    public static void main(String[] args) 
    {
        try {
            URL url = new URL("http://localhost:8080/tp1/hello");
            URLConnection uc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (Exception e) {}
    }
}
