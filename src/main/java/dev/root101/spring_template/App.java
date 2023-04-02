package dev.root101.spring_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {

    public static void main(String[] args) {
        //java -Xms1024m -Xmx1524m -jar NOMBRE.jar
        //System.setProperty("http.proxyHost", "127.0.0.1");
        //System.setProperty("http.proxyPort", "3129");
        //System.setProperty("https.proxyHost", "127.0.0.1");
        //System.setProperty("https.proxyPort", "3129");

        SpringApplication.run(App.class, args);
    }

}
