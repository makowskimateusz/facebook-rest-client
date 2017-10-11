package pl.makowski.mateusz.infrastructure.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
public class RestTemplateFactory {

    @Autowired
    HttpClientProperties httpClientProperties;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(createRequestFactory());
    }

    private ClientHttpRequestFactory createRequestFactory() {
        OkHttpClientHttpRequestFactory okHttpClientHttpRequestFactory = new OkHttpClientHttpRequestFactory();
        okHttpClientHttpRequestFactory.setReadTimeout(httpClientProperties.getReadTimeout());
        okHttpClientHttpRequestFactory.setConnectTimeout(httpClientProperties.getConnectTimeout());
        return okHttpClientHttpRequestFactory;
    }
}
