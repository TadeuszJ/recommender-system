package com.engineerproject.recommendationsystem.app.ann.dto;

import lombok.Data;

@Data
public class RowDataSetDTO {
    private Double businessAvgRate;
    private Integer businessReviewsCount;
    private Integer fansCount;
    private Integer friendsCount;
    private Integer daysYelping;
    private Integer receivedComplimentsCount;
    private Integer userSentVotesCount;
    private Integer userReviewsCount;
    private Double reviewAvgDays;
    private Integer userReceivedUseful;
    private Integer userReceivedCool;
    private Integer userReceivedFunny;
}