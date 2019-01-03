package ce.pucmm.microserviciocliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroservicioclienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioclienteApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}



