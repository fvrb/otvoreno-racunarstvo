package com.otvrac.backend;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "muzeji")
public class Muzej {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv")
    private String nazivMuzeja;

    private String drzava;

    private String grad;

    @Column(name = "godina_osnivanja")
    private Integer godinaOsnivanja;

    @Column(name = "tip")
    private String tipMuzeja;

    @Column(name = "velicina_kolekcije")
    private Integer velicinaKolekcije;

    private Integer posjetitelji;

    @Column(name = "izlozbeni_prostor")
    private Integer izlozbeniProstor;

    @Column(name = "web_stranica")
    private String webStranica;

    @Column(name = "online_setnja")
    private Boolean onlineSetnja;

    @OneToMany(mappedBy = "muzej", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Eksponat> eksponati;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivMuzeja() {
        return nazivMuzeja;
    }

    public void setNazivMuzeja(String nazivMuzeja) {
        this.nazivMuzeja = nazivMuzeja;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public Integer getGodinaOsnivanja() {
        return godinaOsnivanja;
    }

    public void setGodinaOsnivanja(Integer godinaOsnivanja) {
        this.godinaOsnivanja = godinaOsnivanja;
    }

    public String getTipMuzeja() {
        return tipMuzeja;
    }

    public void setTip(String tipMuzeja) {
        this.tipMuzeja = tipMuzeja;
    }

    public Integer getVelicinaKolekcije() {
        return velicinaKolekcije;
    }

    public void setVelicinaKolekcije(Integer velicinaKolekcije) {
        this.velicinaKolekcije = velicinaKolekcije;
    }

    public Integer getPosjetitelji() {
        return posjetitelji;
    }

    public void setPosjetitelji(Integer posjetitelji) {
        this.posjetitelji = posjetitelji;
    }

    public Integer getIzlozbeniProstor() {
        return izlozbeniProstor;
    }

    public void setIzlozbeniProstor(Integer izlozbeniProstor) {
        this.izlozbeniProstor = izlozbeniProstor;
    }

    public String getWebStranica() {
        return webStranica;
    }

    public void setWebStranica(String webStranica) {
        this.webStranica = webStranica;
    }

    public Boolean getOnlineSetnja() {
        return onlineSetnja;
    }

    public void setOnlineSetnja(Boolean onlineSetnja) {
        this.onlineSetnja = onlineSetnja;
    }

    public List<Eksponat> getEksponati() {
        return eksponati;
    }

    public void setEksponati(List<Eksponat> eksponati) {
        this.eksponati = eksponati;
    }
}
