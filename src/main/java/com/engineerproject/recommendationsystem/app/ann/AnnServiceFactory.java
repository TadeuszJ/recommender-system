package com.engineerproject.recommendationsystem.app.ann;

import com.engineerproject.recommendationsystem.infrastructure.AnnRepositoryImpl;
import com.engineerproject.recommendationsystem.infrastructure.mongodb.user.UserRepositoryDB;
import com.engineerproject.recommendationsystem.infrastructure.properties.AnnProperties;
import com.engineerproject.recommendationsystem.infrastructure.rest.AnnRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AnnServiceFactory {
    @Bean
    private AnnService getAnnService(UserRepositoryDB userRepository, AnnProperties annProperties) {
        AnnRestClient annRestClient = new AnnRestClient();
        AnnRepositoryImpl annRepository = new AnnRepositoryImpl(annRestClient, userRepository, annProperties);
        return new AnnService(annRepository);
    }
}
