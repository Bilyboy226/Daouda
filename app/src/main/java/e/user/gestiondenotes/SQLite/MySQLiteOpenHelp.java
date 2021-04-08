package e.user.gestiondenotes.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelp extends SQLiteOpenHelper {


    private String creation = "create table eleve("
            + "id INTEGER PRIMARY KEY NOT NULL,"
            + "Nom VARCHAR(50) ,"
            + "Prenom VARCHAR(50) ,"
            + "Date_naissance VARCHAR(50) ,"
            + "Lieu_naissance VARCHAR(50) ,"
            + "Classe VARCHAR(50) ,"
            + "Annee_academique VARCHAR(50));";

    private String creation2 = "create table notes("
            + "id INTEGER PRIMARY KEY NOT NULL,"
            + "Nom VARCHAR(50) ,"
            + "Prenom VARCHAR(50) ,"
            + "Date_naissance VARCHAR(50) ,"
            + "Classe VARCHAR(50) ,"
            + "Annee_academique VARCHAR(50), "
            + "Matieres  VARCHAR(50),"
            + "Notes DOUBLE(5) , "
            + "Coefficient DOUBLE(5),"
            + "Trimestre  VARCHAR(50));";




    /**
     * constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Si changement de BD
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);
        db.execSQL(creation2);

    }

    /**
     *Si changement de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
