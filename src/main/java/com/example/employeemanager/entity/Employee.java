package com.example.employeemanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nom")
    private String name;

    @Column(name = "Prenom")
    private String prenom;

    @Column(name = "Poste")
    private String poste;

    @Column(name = "Numéro")
    private String num;


    @Column(name = "Email")
    private String email;

    @Column(name = "Salaire")
    private String salaire;


    public Employee() {
    }

    public Employee(Long id, String name, String prenom, String poste, String num, String email, String salaire) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.poste = poste;
        this.num = num;
        this.email = email;
        this.salaire = salaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }
}