package pcrc.caferecommendbackend.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() { //spring 3.0부터 지원
        //REST API 호출 이후 응답을 받을 때까지 기다리는 동기 방식
        return new RestTemplate();
    }
}
