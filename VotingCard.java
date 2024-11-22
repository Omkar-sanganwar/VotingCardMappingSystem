package com.nov21;

import javax.persistence.*;

@Entity
public class VotingCard {
    @Id
    private String voterId;
    private String constituency;

    public VotingCard() {}

    public VotingCard(String voterId, String constituency) {
        this.voterId = voterId;
        this.constituency = constituency;
    }

    // Getters and Setters
    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }
}
