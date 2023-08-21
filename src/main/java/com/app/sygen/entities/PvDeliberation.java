package com.app.sygen.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class PvDeliberation 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
    @ManyToOne
    private DetailPvUe detailPvUe;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public DetailPvUe getDetailPvUe() {
		return detailPvUe;
	}
	public void setDetailPvUe(DetailPvUe detailPvUe) {
		this.detailPvUe = detailPvUe;
	}
}
