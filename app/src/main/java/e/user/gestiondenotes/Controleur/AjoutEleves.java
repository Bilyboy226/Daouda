package e.user.gestiondenotes.Controleur;

public class AjoutEleves {
    private Integer id;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String lieu_naissance;
    private String classe;
    private String annee_academique;

    public AjoutEleves( String nom, String prenom, String date_naissance, String lieu_naissance, String classe, String annee_academique) {

        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
        this.classe = classe;
        this.annee_academique = annee_academique;

        System.out.println("Enregistrement dans AjoutEleves");
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getLieu_naissance() {
        return lieu_naissance;
    }

    public String getClasse() {
        return classe;
    }

    public String getAnnee_academique() {
        return annee_academique;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setAnnee_academique(String annee_academique) {
        this.annee_academique = annee_academique;
    }
}
