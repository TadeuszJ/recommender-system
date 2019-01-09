package com.engineerproject.recommendationsystem.app.ann.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSetRowDTO {
    private String businessId;
    private Double businessAvgRate;
    private Double businessReviewsCount;
    private Double fansCount;
    private Double friendsCount;
    private Double daysYelping;
    private Double receivedComplimentsCount;
    private Double userSentVotesCount;
    private Double userReviewsCount;
    private Double reviewAvgDays;
    private Double userReceivedUseful;
    private Double userReceivedCool;
    private Double userReceivedFunny;
    private Double rate;
    private Double businessUseful;
    private Double businessFunny;
    private Double businessCool;

    public DataSetRowDTO max(DataSetRowDTO row) {
        return DataSetRowDTO.builder()
                .businessAvgRate(Math.max(this.businessAvgRate, row.businessAvgRate))
                .businessReviewsCount(Math.max(this.businessReviewsCount, row.businessReviewsCount))
                .fansCount(Math.max(this.fansCount, row.fansCount))
                .friendsCount(Math.max(this.friendsCount, row.friendsCount))
                .daysYelping(Math.max(this.daysYelping, row.daysYelping))
                .receivedComplimentsCount(Math.max(this.receivedComplimentsCount, row.receivedComplimentsCount))
                .userSentVotesCount(Math.max(this.userSentVotesCount, row.userSentVotesCount))
                .userReviewsCount(Math.max(this.userReviewsCount, row.userReviewsCount))
                .reviewAvgDays(Math.max(this.reviewAvgDays, row.reviewAvgDays))
                .userReceivedUseful(Math.max(this.userReceivedUseful, row.userReceivedUseful))
                .userReceivedCool(Math.max(this.userReceivedCool, row.userReceivedCool))
                .userReceivedFunny(Math.max(this.userReceivedFunny, row.userReceivedFunny))
                .rate(Math.max(this.rate, row.rate))
                .build();
    }

    public DataSetRowDTO min(DataSetRowDTO row) {
        return DataSetRowDTO.builder()
                .businessAvgRate(Math.min(this.businessAvgRate, row.businessAvgRate))
                .businessReviewsCount(Math.min(this.businessReviewsCount, row.businessReviewsCount))
                .fansCount(Math.min(this.fansCount, row.fansCount))
                .friendsCount(Math.min(this.friendsCount, row.friendsCount))
                .daysYelping(Math.min(this.daysYelping, row.daysYelping))
                .receivedComplimentsCount(Math.min(this.receivedComplimentsCount, row.receivedComplimentsCount))
                .userSentVotesCount(Math.min(this.userSentVotesCount, row.userSentVotesCount))
                .userReviewsCount(Math.min(this.userReviewsCount, row.userReviewsCount))
                .reviewAvgDays(Math.min(this.reviewAvgDays, row.reviewAvgDays))
                .userReceivedUseful(Math.min(this.userReceivedUseful, row.userReceivedUseful))
                .userReceivedCool(Math.min(this.userReceivedCool, row.userReceivedCool))
                .userReceivedFunny(Math.min(this.userReceivedFunny, row.userReceivedFunny))
                .rate(Math.min(this.rate, row.rate))
                .build();
    }

    public void normalize() {
        this.businessAvgRate = this.businessAvgRate / 5.0;
        this.businessReviewsCount = this.businessReviewsCount / 7362;
        this.fansCount = this.fansCount / 7009;
        this.friendsCount = this.friendsCount / 4685;
        this.daysYelping = this.daysYelping / 5199;
        this.receivedComplimentsCount = this.receivedComplimentsCount / 188209;
        this.userSentVotesCount = this.userSentVotesCount / 659887;
        this.userReviewsCount = this.userReviewsCount / 2209;
        this.reviewAvgDays = this.reviewAvgDays / 5281.0;
        this.userReceivedUseful = this.userReceivedUseful / 16365;
        this.userReceivedCool = this.userReceivedCool / 11321;
        this.userReceivedFunny = this.userReceivedFunny / 8790;
        this.businessUseful = this.businessUseful / 61488;
        this.businessFunny = this.businessFunny / 9278;
        this.businessCool = this.businessCool / 49396;
        this.rate = this.rate / 5.0;
    }
}