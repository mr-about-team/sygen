package com.app.sygen.entities;

import java.sql.Date;

public class Param {
    
      String newMatricule  ;
        String newNomEtudiant  ;
        Float newNote  ;
        Date newDate  ;
        // int newUser = participations.getUserId();
        int newAnonymat  ;
        public String getNewMatricule() {
            return newMatricule;
        }
        public void setNewMatricule(String newMatricule) {
            this.newMatricule = newMatricule;
        }
        public String getNewNomEtudiant() {
            return newNomEtudiant;
        }
        public void setNewNomEtudiant(String newNomEtudiant) {
            this.newNomEtudiant = newNomEtudiant;
        }
        public Float getNewNote() {
            return newNote;
        }
        public void setNewNote(Float newNote) {
            this.newNote = newNote;
        }
        public Date getNewDate() {
            return newDate;
        }
         public void setNewDate(Date newDate) {
             this.newDate = newDate;
        }
        public Integer getNewAnonymat() {
            return newAnonymat;
        }
        public void setNewAnonymat(Integer newAnonymat) {
            this.newAnonymat = newAnonymat;
        }
        public Param() {
        }
       
        

}
