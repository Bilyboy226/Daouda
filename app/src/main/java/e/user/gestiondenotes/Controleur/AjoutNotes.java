package e.user.gestiondenotes.Controleur;

public class AjoutNotes {
    private String nom;
    private String prenom;
    private String date_naissance;
    private String classe;
    private String annee_academique;
    private String matiere;
    private  String trimestre;
    private Double note;
    private Double coefficient;

    public AjoutNotes(String nom, String prenom, String date_naissance, String classe, String annee_academique, String matiere, Double note,Double coefficient,String trimestre) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.classe = classe;
        this.annee_academique = annee_academique;
        this.matiere = matiere;
        this.note = note;
        this.trimestre = trimestre;
        this.coefficient = coefficient;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAnnee_academique() {
        return annee_academique;
    }

    public void setAnnee_academique(String annee_academique) {
        this.annee_academique = annee_academique;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }


}
