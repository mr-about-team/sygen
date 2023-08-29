package com.app.sygen.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.sql.Date;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Participe;
import com.app.sygen.repositories.EtudiantRepository;
import com.app.sygen.repositories.EvaluationRepository;

@Service
public class ParticipeService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;


    public Participe createParticipeXLSX(Row row, Map<String, Integer> columnIndexMap,String code,String type,int noteSur) throws ParseException{
        LocalDate currentDate= LocalDate.now();
        String matricule = getColumnValue(row, columnIndexMap, "Matricule");
        String Nom = getColumnValue(row, columnIndexMap, "Nom");
        float note = Float.parseFloat(getColumnValue(row, columnIndexMap, "note"));

        String codes = code;
        String typeE = type;
        Etudiant etu = etudiantRepository.findByMatricule(matricule);

        Evaluation  evaluation = evaluationRepository.findByTypeEvalAndUe_Code(typeE, codes);
        if(evaluation.getNoteSur() == noteSur){
        
        if (Nom != null &&  matricule != null) {
            Participe participe= new Participe();
            participe.setMatricule(matricule);
            participe.setNomEtudiant(Nom);
            participe.setNote(note);
            participe.setEvaluation(evaluation);
            participe.setEtudiant(etu);
            participe.setDateImportation(Date.valueOf(currentDate)); 
            return participe;

        }}
        else{
            float notes = (note*evaluation.getNoteSur())/noteSur;
            if (Nom != null &&  matricule != null) {
            Participe participe= new Participe();
            participe.setMatricule(matricule);
            participe.setNomEtudiant(Nom);
            participe.setNote(notes);
            participe.setEvaluation(evaluation);
            participe.setEtudiant(etu);
            participe.setDateImportation(Date.valueOf(currentDate)); 
            return participe;

        }

        }

        return null;

    }

        public String getColumnValue(Row row, Map<String, Integer> columnIndexMap, String columnName) {
        Integer columnIndex = columnIndexMap.get(columnName);
        if (columnIndex != null) {
            Cell cell = row.getCell(columnIndex);
            if (cell != null) {
                cell.setCellType(CellType.STRING); // Convertir la cellule en type chaîne de caractères
                //cell.setCellType(CellType.STRING);
                return cell.getStringCellValue();
            }
        }
        return null;
    }


    public Map<String, Integer> getColumnIndexMap(Row headerRow) {
        Map<String, Integer> columnIndexMap = new HashMap<>();
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        int columnIndex = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            columnIndexMap.put(cell.getStringCellValue(), columnIndex);
            columnIndex++;
        }
        return columnIndexMap;
    }
}
