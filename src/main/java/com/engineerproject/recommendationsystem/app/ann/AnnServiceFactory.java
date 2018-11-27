package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.infrastructure.AnnRepositoryImpl;
import com.engineerproject.recommendationsystem.infrastructure.rest.ReviewRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AnnServiceFactory {
    @Bean
    private AnnService getAnnService() {
        ReviewRestClient reviewRestClient = new ReviewRestClient();
        AnnRepositoryImpl annRepository = new AnnRepositoryImpl(reviewRestClient);
        return new AnnService(annRepository);
    }
}
