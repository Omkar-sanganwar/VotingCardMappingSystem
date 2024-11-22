package com.nov21;

import java.util.List;
import javax.persistence.*;

@Entity
public class Person {
    @Id
    private int aadharId;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private VotingCard votingCard;

    @ManyToMany
    private List<Address> addresses;

    public Person() {}

    public Person(int aadharId, String name, VotingCard votingCard, List<Address> addresses) {
        this.aadharId = aadharId;
        this.name = name;
        this.votingCard = votingCard;
        this.addresses = addresses;
    }

    // Getters and Setters
    public int getAadharId() {
        return aadharId;
    }

    public void setAadharId(int aadharId) {
        this.aadharId = aadharId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VotingCard getVotingCard() {
        return votingCard;
    }

    public void setVotingCard(VotingCard votingCard) {
        this.votingCard = votingCard;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
