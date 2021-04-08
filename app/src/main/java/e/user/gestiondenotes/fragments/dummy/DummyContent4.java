package e.user.gestiondenotes.fragments.dummy;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DummyContent4 {


    public static  List<DummyItem4> ITEMS = new ArrayList<DummyItem4>();



    public static void addItem(DummyItem4 item) {
        ITEMS.add(item);
    }
    public static void delete()
    {
        ITEMS.clear();
    }
    public static DummyItem4 createDummyItem(String nom ,String prenom, String date_naissance,String lieu_naissance,String classe,String annee_academique) {
        return new DummyItem4(nom,prenom,date_naissance,lieu_naissance,classe,annee_academique);
    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem4 {
        public  String nom = null;
        public  String prenom = null;
        public  String date_naissance = null;
        public String lieu_naissance ="";
        public  String classe = "";
        public  String annee_academique = "";

        String content = nom+" "+prenom+" "+date_naissance+" "+lieu_naissance;

        public DummyItem4( String nom, String prenom, String date_naissance,String lieu_naissance,String classe, String annee_academique) {
            this.nom = nom;
            this.prenom = prenom;
            this.date_naissance = date_naissance;
            this.lieu_naissance = lieu_naissance;
            this.classe = classe;
            this.annee_academique = annee_academique;
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

        public String getLieu_naissance() {
            return lieu_naissance;
        }

        public void setLieu_naissance(String lieu_naissance) {
            this.lieu_naissance = lieu_naissance;
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

        @Override
        public String toString() {
            return content;
        }
    }
}