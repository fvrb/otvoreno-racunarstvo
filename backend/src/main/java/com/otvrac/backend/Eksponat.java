package com.otvrac.backend;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "eksponati")
public class Eksponat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv")
    private String nazivEksponata;

    @Column(name = "tip")
    private String tipEksponata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_muzej")
    @JsonBackReference
    private Muzej muzej;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivEksponata() {
        return nazivEksponata;
    }

    public void setNazivEksponata(String nazivEksponata) {
        this.nazivEksponata = nazivEksponata;
    }

    public String getTipEksponata() {
        return tipEksponata;
    }

    public void setTipEksponata(String tipEksponata) {
        this.tipEksponata = tipEksponata;
    }

    public Muzej getMuzej() {
        return muzej;
    }

    public void setMuzej(Muzej muzej) {
        this.muzej = muzej;
    }
}
