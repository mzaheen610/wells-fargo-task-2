package com.wellsfargo.counselor.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Portfolio {
    @Id
    @GeneratedValue
    private long portfolioId;

    @OneToOne(mappedBy = "portfolio")
    private Client client;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<Security> securities;

    @Column
    private LocalDate creationDate;


    protected Portfolio() {

    }

    public Portfolio(Client client, LocalDate creationDate) {
        this.client = client;
        this.securities = new ArrayList<>();
        this.creationDate = creationDate;
    }

    public Long getId() {
        return portfolioId;
    }

    public void setId(Long id) {
        this.portfolioId = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Security> getSecurities() {
        return securities;
    }

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }


    public void addSecurity(Security security) {
        securities.add(security);
        security.setPortfolio(this);
    }

    public void removeSecurity(Security security) {
        securities.remove(security);
        security.setPortfolio(null);
    }
}
