package dev;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ExampleHttpClient {

    public static void main(String[] args) throws InterruptedException {
        String url = "http://localhost:8080/getBody";
        String example = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns:Request xmlns:ns=\"http://www.tempuri.org/types\"><Version>V1.1</Version><Address><Apartment>50</Apartment><House>7</House><Street>Sadovaya</Street><City>SPB</City><Country>Russia</Country><Index>123456</Index></Address></ns:Request>";
        try {
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(example));
            HttpResponse http = client.execute(post);
            System.out.println("Статус: " + http.getStatusLine().getStatusCode());

            System.out.println(http.getEntity().getContent());

            try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getEntity().getContent(), StandardCharsets.UTF_8))){
                String line;
                while((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}