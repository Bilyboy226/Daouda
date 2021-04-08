package e.user.gestiondenotes.Controleur;


import android.annotation.SuppressLint;
import android.content.Context;

import e.user.gestiondenotes.SQLite.AccesLocal;

public final class Controle {
    private static Controle instance = null;
    private static AccesLocal accesLocal;
     AjoutEleves ajoutEleves;
     AjoutNotes ajoutNotes;
    private Controle(){
        super();
    }
    public static final Controle getInstance(Context context){

        if(Controle.instance == null){
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(context);
        }
        return Controle.instance;
    }

    @SuppressLint("LongLogTag")
    public void ajouterEleve(String nom, String prenom, String date_naissance, String lieu_naissance, String classe, String annee_academique, Controle controle){
        System.out.println("Enregistrement dans Controle");

        ajoutEleves = new AjoutEleves( nom, prenom, date_naissance, lieu_naissance, classe, annee_academique);

        System.out.println(ajoutEleves.getNom());
        System.out.println(ajoutEleves.getPrenom());
        accesLocal.ajout(controle);
    }
    public void ajouterNote(String nom, String prenom, String date_naissance, String classe, String annee_academique,String matiere,Double note,Double coefficient,String trimestre, Controle controle){
        System.out.println("Enregistrement dans Controle");
        ajoutNotes = new AjoutNotes( nom, prenom, date_naissance, classe, annee_academique,matiere,note,coefficient,trimestre);
        accesLocal.ajoutNote(controle);
    }


    public String getNom2(){
        if(ajoutEleves == null){
            return null;
        }else{
            return ajoutEleves.getNom();
        }
    }

    public String getPrenom2(){
        if(ajoutEleves == null){
            return null;
        }else{
            return ajoutEleves.getPrenom();
        }

    }
    public String getDate_naissance2(){
        if(ajoutEleves == null){
            return null;
        }else{
            return ajoutEleves.getDate_naissance();
        }

    }
    public String getLieu_naissance2(){
        if(ajoutEleves == null){
            return null;
        }else{
            return ajoutEleves.getLieu_naissance();
        }

    } public String getClasse2(){
        if(ajoutEleves == null){
            return null;
        }else{
            return ajoutEleves.getClasse();
        }

    }
    public String getAnnee_academique2(){
        if(ajoutEleves == null){
            return null;
        }else{
            return ajoutEleves.getAnnee_academique();
        }

    }
    public String getNom3(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getNom();
        }

    }
    public String getPrenom3(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getPrenom();
        }

    }
    public String getDate_naissance3(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getDate_naissance();
        }

    }
    public String getAnnee_academique3(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getAnnee_academique();
        }

    }
    public String getClasse3(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getClasse();
        }

    }
    public String getMatiere(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getMatiere();
        }

    }
    public Double getNote(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getNote();
        }

    }
    public Double getCoefficient(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getCoefficient();
        }

    }
    public String getTrimestre(){
        if(ajoutNotes == null){
            return null;
        }else{
            return ajoutNotes.getTrimestre();
        }

    }

}
