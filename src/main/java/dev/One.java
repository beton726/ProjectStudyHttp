package dev;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class One {
    public static void main(String[] args) throws InterruptedException, IOException {

        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns:Request xmlns:ns=\"http://www.tempuri.org/types\"><Version>V1.1</Version><Address><Apartment>50</Apartment><House>7</House><Street>Sadovaya</Street><City>SPB</City><Country>Russia</Country><Index>123456</Index></Address></ns:Request>";

        URL url = new URL("http://localhost:8080/getBody");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setUseCaches(true);
        connection.setRequestMethod("POST");

        // Set Headers
        connection.setRequestProperty("Accept", "application/xml");
        connection.setRequestProperty("Content-Type", "application/xml");

        // Write XML
        try(OutputStream outputStream = connection.getOutputStream()) {
            byte[] b = request.getBytes("UTF-8");
            outputStream.write(b);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = connection.getResponseCode();
        System.out.println(code);

        // Read XML
        try (InputStream inputStream = connection.getInputStream();) {
            byte[] result = new byte[2048];
            int i = 0;
            StringBuilder response = new StringBuilder();
            while ((i = inputStream.read(result)) != -1) {
                response.append(new String(result, 0, i));
            }

            System.out.println("Response: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}