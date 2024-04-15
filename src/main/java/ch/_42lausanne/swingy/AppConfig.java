package ch._42lausanne.swingy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
@ComponentScan({"ch._42lausanne.swingy"})
public class AppConfig {
    @Bean
    public JFrame mainFrame() {
        return new JFrame("Swingy");
    }

}
