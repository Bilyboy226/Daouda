package e.user.gestiondenotes.SQLite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.health.SystemHealthManager;
import android.util.Log;

import java.util.ArrayList;

import e.user.gestiondenotes.Controleur.AjoutEleves;
import e.user.gestiondenotes.Controleur.AjoutNotes;
import e.user.gestiondenotes.Controleur.Controle;

public class AccesLocal {

    //propriétés
    private String nomBase = "gestioneleves";
    private Integer versionBase = 1;
    private MySQLiteOpenHelp accesBD;
    private SQLiteDatabase based;
    private Controle controle;

    /**
     * constructeur
     * @param context
     */
    public AccesLocal(Context context) {

        accesBD = new MySQLiteOpenHelp(context, nomBase, null, versionBase);

    }

    /**
     * ajout d'un élève
     * @param instance
     */
    @SuppressLint("LongLogTag")
    public void ajout(Controle instance){

        if(instance.getNom2()!=null) {
            System.out.println("Enregistrement éffectué avec succès");
            based = accesBD.getWritableDatabase();
            String req = "insert into eleve(Nom, Prenom, Date_naissance, Lieu_naissance, Classe, Annee_academique) values";
            req += "(\""+instance.getNom2()+"\",\""+ instance.getPrenom2()+"\",\""+instance.getDate_naissance2()+"\",\""+instance.getLieu_naissance2()+"\",\""+instance.getClasse2()+"\",\""+instance.getAnnee_academique2()+"\")";

            based.execSQL(req);


        }
        else
            System.out.println("Enregistrement échoué");

    }

    public void ajoutNote(Controle instance){

        if(instance.getNom3()!=null) {
            System.out.println("Ajout de note en cours");
            System.out.println(instance.getAnnee_academique3());
            String req = "insert into notes(Nom, Prenom, Date_naissance, Classe, Annee_academique,Matieres,Notes,Coefficient,Trimestre) values";
            req += "(\""+instance.getNom3()+"\",\""+ instance.getPrenom3()+"\",\""+instance.getDate_naissance3()+"\",\""+instance.getClasse3()+"\",\""+instance.getAnnee_academique3()+"\",\""+instance.getMatiere()+"\","+instance.getNote()+","+instance.getCoefficient()+",\""+instance.getTrimestre()+"\")";

            based.execSQL(req);
            System.out.println("Ajout de note éffectué avec succès");

        }
        else
            System.out.println("Ajout de notes échoué");

    }

    public ArrayList<AjoutEleves> recuper(String classe,String annee_academique){
        based = accesBD.getReadableDatabase();
        ArrayList<AjoutEleves> listeEleve = new ArrayList<AjoutEleves>();
        String req = "select Nom,Prenom,Date_Naissance from eleve where Classe = \""+classe+"\""+"and Annee_academique = \""+annee_academique+"\"";
        Cursor curseur  = based.rawQuery(req, null);

        if(curseur != null && curseur.moveToFirst()){

            do{


                String nom = curseur.getString(0);
                String prenom = curseur.getString(1);
                String date_naissance = curseur.getString(2);

                AjoutEleves ajoutEleves = new AjoutEleves(nom, prenom, date_naissance, "", "", "");
                listeEleve.add(ajoutEleves);
                curseur.moveToNext();
            }while(!curseur.isAfterLast());
            curseur.close();

            return listeEleve;
        }

        return null;

    }

    public ArrayList<AjoutEleves> recuper_eleves(String classe,String annee_academique){
        based = accesBD.getReadableDatabase();
        ArrayList<AjoutEleves> listeEleve = new ArrayList<AjoutEleves>();
        String req = "select Nom,Prenom,Date_Naissance,Lieu_naissance from eleve where Classe = \""+classe+"\""+"and Annee_academique = \""+annee_academique+"\"";
        Cursor curseur  = based.rawQuery(req, null);

        if(curseur != null && curseur.moveToFirst()){

            do{


                String nom = curseur.getString(0);
                String prenom = curseur.getString(1);
                String date_naissance = curseur.getString(2);
                String lieu_naissance = curseur.getString(3);

                AjoutEleves ajoutEleves = new AjoutEleves(nom, prenom, date_naissance, lieu_naissance, "", "");
                listeEleve.add(ajoutEleves);
                curseur.moveToNext();
            }while(!curseur.isAfterLast());
            curseur.close();

            return listeEleve;
        }

        return null;

    }

    public ArrayList<AjoutNotes> recuper(String nom, String prenom, String date_naissance, String trimestre,String classe, String annee_academique){
        based = accesBD.getReadableDatabase();
        ArrayList<AjoutNotes> listeNotes = new ArrayList<AjoutNotes>();
        String req = "select Matieres,Notes from notes where Classe = \""+classe+"\""+"and Annee_academique = \""+annee_academique+"\""+"and Nom = \""+nom+"\""+"and Prenom = \""+prenom+"\""+"and Date_naissance = \""+date_naissance+"\""+"and Trimestre = \""+trimestre+"\"";
        Cursor curseur  = based.rawQuery(req, null);

        if(curseur != null && curseur.moveToFirst()){

            do{


                String matiere = curseur.getString(0);
                Double note = Double.parseDouble(curseur.getString(1));

                AjoutNotes ajoutNotes = new AjoutNotes("", "", "", "", "", matiere,note,1.0,"");
                listeNotes.add(ajoutNotes);
                curseur.moveToNext();
            }while(!curseur.isAfterLast());
            curseur.close();

            return listeNotes;
        }

        return null;

    }

    public ArrayList<AjoutNotes> recuper2(String nom, String prenom, String date_naissance, String trimestre,String classe, String annee_academique){
        based = accesBD.getReadableDatabase();
        ArrayList<AjoutNotes> listeNotes = new ArrayList<AjoutNotes>();
        String req = "select Matieres,Notes,Coefficient from notes where Classe = \""+classe+"\""+"and Annee_academique = \""+annee_academique+"\""+"and Nom = \""+nom+"\""+"and Prenom = \""+prenom+"\""+"and Date_naissance = \""+date_naissance+"\""+"and Trimestre = \""+trimestre+"\"";
        Cursor curseur  = based.rawQuery(req, null);

        if(curseur != null && curseur.moveToFirst()){

            do{


                String matiere = curseur.getString(0);
                Double note = Double.parseDouble(curseur.getString(1));
                Double coefficient = Double.parseDouble(curseur.getString(2));

                AjoutNotes ajoutNotes = new AjoutNotes("", "", "", "", "", matiere,note,coefficient,"");
                listeNotes.add(ajoutNotes);
                curseur.moveToNext();
            }while(!curseur.isAfterLast());
            curseur.close();

            return listeNotes;
        }

        return null;

    }


}
