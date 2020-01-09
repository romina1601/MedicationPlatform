package com.example.springdemo.dto.builders.RecommendationBuilders;

import com.example.springdemo.dto.RecommendationDTOs.RecommendationDTO;
import com.example.springdemo.entities.Recommendation;

public class RecommendationBuilder {

    public RecommendationBuilder() {
    }

    public static RecommendationDTO generateDTOFromEntity (Recommendation recommendation)
    {
        return new RecommendationDTO(recommendation.getPatientId(),
                                    recommendation.getDescription());
    }
}
