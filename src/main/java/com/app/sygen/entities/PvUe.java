package com.app.sygen.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PvUe 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int semestre;
    private Date annee;
    
    @ManyToOne
    private Ue ue;
    @OneToMany(cascade = CascadeType.ALL)
    private List<DetailPvUe> ListDetails = new ArrayList<>();
	
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public Date getAnnee() {
		return annee;
	}
	public void setAnnee(Date annee) {
		this.annee = annee;
	}
	public Ue getUe() {
		return ue;
	}
	public void setUe(Ue ue) {
		this.ue = ue;
	}
	public List<DetailPvUe> getListDetails() {
		return ListDetails;
	}
	public void setListDetails(List<DetailPvUe> listDetails) {
		ListDetails = listDetails;
	}
}
