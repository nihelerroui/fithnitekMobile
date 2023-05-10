/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Nihel
 */
public class Avis {
    private int id;
    private int idUser;
    private int id_offrecov ;
    private String commenraire;

    public Avis() {
    }

    public Avis(int id, int idUser, int id_offrecov, String commenraire) {
        this.id = id;
        this.idUser = idUser;
        this.id_offrecov = id_offrecov;
        this.commenraire = commenraire;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getId_offrecov() {
        return id_offrecov;
    }

    public String getCommenraire() {
        return commenraire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setId_offrecov(int id_offrecov) {
        this.id_offrecov = id_offrecov;
    }

    public void setCommenraire(String commenraire) {
        this.commenraire = commenraire;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", idUser=" + idUser + ", id_offrecov=" + id_offrecov + ", commenraire=" + commenraire + '}';
    }
    
    
    
}


