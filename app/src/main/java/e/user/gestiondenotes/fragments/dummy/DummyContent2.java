package e.user.gestiondenotes.fragments.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DummyContent2 {


    public static  List<DummyItem2> ITEMS = new ArrayList<DummyItem2>();



    public static void addItem(DummyItem2 item) {
        ITEMS.add(item);
    }
    public static void delete()
    {
        ITEMS.clear();
    }
    public static DummyItem2 createDummyItem(String nom ,String prenom, String date_naissance) {
        return new DummyItem2(nom,prenom,date_naissance);
    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem2 {
        public  String nom = null;
        public  String prenom = null;
        public  String date_naissance = null;

        String content = nom+" "+prenom+" "+date_naissance;

        public DummyItem2( String nom, String prenom, String date_naissance) {
            this.nom = nom;
            this.prenom = prenom;
            this.date_naissance = date_naissance;

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

        @Override
        public String toString() {
            return content;
        }
    }
}