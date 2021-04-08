package e.user.gestiondenotes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;

import e.user.gestiondenotes.Controleur.AjoutEleves;
import e.user.gestiondenotes.Controleur.AjoutNotes;
import e.user.gestiondenotes.Controleur.Controle;
import e.user.gestiondenotes.SQLite.AccesLocal;
import e.user.gestiondenotes.fragments.AjoutEleve;
import e.user.gestiondenotes.fragments.ChoixClasse;
import e.user.gestiondenotes.fragments.ChoixClasse2;
import e.user.gestiondenotes.fragments.ChoixClasse3;
import e.user.gestiondenotes.fragments.ChoixClasse4;
import e.user.gestiondenotes.fragments.ChoixTrimestre;
import e.user.gestiondenotes.fragments.ChoixTrimestre2;
import e.user.gestiondenotes.fragments.NotesEleve;
import e.user.gestiondenotes.fragments.dummy.DummyContent;
import e.user.gestiondenotes.fragments.dummy.DummyContent.DummyItem;
import e.user.gestiondenotes.fragments.dummy.DummyContent2.DummyItem2;
import e.user.gestiondenotes.fragments.dummy.DummyContent2;
import e.user.gestiondenotes.fragments.dummy.DummyContent3;
import e.user.gestiondenotes.fragments.dummy.DummyContent3.DummyItem3;
import e.user.gestiondenotes.fragments.dummy.DummyContent4;

public class MainActivity2<nom_frag> extends AppCompatActivity implements AjoutEleve.AjoutEleveListener, ChoixClasse.ChoixClasseListener, NotesEleve.NotesEleveListener, ChoixClasse2.ChoixClasse2Listener, ChoixTrimestre.ChoixTrimestreListener, ChoixTrimestre2.ChoixTrimestre2Listener, ChoixClasse3.ChoixClasse3Listener, ChoixClasse4.ChoixClasse4Listener {
    Controle controle;
    AccesLocal accesLocal;
    private  String Classe;
    private  String Annee_academique;
    private  String Nom;
    private String Prenom;
    int cpt = 0;
   DrawerLayout drawerLayout;
   ActionBarDrawerToggle toggle;
   Toolbar toolbar;
   private Boolean exit = false;
   private  static int  nb_frag = 0;
   ArrayList<Fragment> list_frag = new ArrayList<Fragment>();
   NavigationView navigationView;
   Fragment fragment = new AjoutEleve();
   AjoutEleve ajoutEleve = new AjoutEleve();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controle = Controle.getInstance(this);
        accesLocal = new AccesLocal(this);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        list_frag.add(fragment);
        loadFragment(fragment);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                switch (id)
                {
                    case R.id.nav_eleve:
                        fragment = new AjoutEleve();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_moyenne:
                        fragment = new  ChoixClasse();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_notes:
                        fragment = new ChoixClasse2();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_liste_moyenne:
                        fragment = new ChoixClasse3();
                        loadFragment(fragment);
                        break;
                    case R.id.nav_liste_eleves:
                        fragment = new ChoixClasse4();
                        loadFragment(fragment);
                        break;
                    default:
                        return  true;
                }
                return  true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
        for (int i =0;i < list_frag.size();i++) {
            if (list_frag.get(i).equals(fragment) ) {
                 cpt++;
            }
        }
        if(cpt == 0)
        {
            list_frag.add(fragment);
            nb_frag++;
        }
        cpt = 0;
    }


    @Override
    public void onDataSent(String nom, String prenom, String date_naissance, String lieu_naissance, String classe, String annee_academique) {
        if(!nom.equals("vide") && !prenom.equals("vide")) {
            controle.ajouterEleve(nom, prenom, date_naissance, lieu_naissance, classe, annee_academique, controle);
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Ajout éffectué avec succès" );
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();
        }
        else
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Veuillez remplir tous les champs" );
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });

            final AlertDialog alert = dialog.create();
            alert.show();
        }


    }

    @Override
    public void onDataSentChoix(String classe, String annee_academique) {
        this.Classe = classe;
        this.Annee_academique = annee_academique;
        ArrayList<AjoutEleves> listeEleves = accesLocal.recuper(classe, annee_academique);

        if(listeEleves !=null) {
            DummyContent.delete();
            for (int i = 0; i < listeEleves.size(); i++) {

                DummyItem dummyItem = DummyContent.createDummyItem(listeEleves.get(i).getNom(), listeEleves.get(i).getPrenom(), listeEleves.get(i).getDate_naissance());
                DummyContent.addItem(dummyItem);
            }
        }
        else
        {
            fragment = new ChoixClasse();
            loadFragment(fragment);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Il n'y a aucun élève dans cette classe");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();
        }


    }

    @Override
    public void onDataSentNote(String nom, String prenom, String date_naissance, String matiere, Double note,Double coefficient,String trimestre) {
        if (note != -1.0 && coefficient != -1.0) {
            controle.ajouterNote(nom, prenom, date_naissance, Classe, Annee_academique, matiere, note, coefficient,trimestre, controle);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Ajout de note éffectué avec succès");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();
        }
        else
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Veuillez remplir tous les champs");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();
        }
    }


    @Override
    public void onBackPressed() {
        if (nb_frag == 0) {
            finish(); // finish activity
        } else {

            nb_frag--;
            loadFragment(list_frag.get(nb_frag));

        }

    }

    @Override
    public void onDataSentChoix2(String classe, String annee_academique) {

        this.Classe = classe;
        this.Annee_academique = annee_academique;
        ArrayList<AjoutEleves> listeEleves = accesLocal.recuper(classe, annee_academique);

        if(listeEleves !=null) {
            DummyContent2.delete();
            for (int i = 0; i < listeEleves.size(); i++) {

                DummyItem2 dummy= DummyContent2.createDummyItem(listeEleves.get(i).getNom(), listeEleves.get(i).getPrenom(), listeEleves.get(i).getDate_naissance());
                DummyContent2.addItem(dummy);
            }
        }
        else
        {
            fragment = new ChoixClasse2();
            loadFragment(fragment);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Il n'y a aucun élève dans cette classe");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();
        }

    }

    @Override
    public void onDataSentTrimestre(String nom, String prenom, String date_naissance, String trimestre) {
        ArrayList<AjoutNotes> listeNotes = accesLocal.recuper(nom,prenom,date_naissance,trimestre,Classe, Annee_academique);
        this.Nom = nom;
        this.Prenom = prenom;
        Double noteMATHS = 0.0;
        Double notePC = 0.0;
        Double noteSVT = 0.0;
        Double noteFR = 0.0;
        Double noteANG = 0.0;
        Double noteHG = 0.0;
        Double noteALLEMAND = 0.0;
        Double notePHILO = 0.0;
        Double noteEPS = 0.0;
        int cptMath = 0;
        int cptPC = 0;
        int cptSVT = 0;
        int cptFR = 0;
        int cptANG = 0;
        int cptHG = 0;
        int cptALLEMAND = 0;
        int cptPHILO = 0;
        int cptEPS = 0;

        if(listeNotes !=null) {

            for (int i = 0; i < listeNotes.size(); i++) {

                 String  Matieres =  listeNotes.get(i).getMatiere();
                 Double  Notes = listeNotes.get(i).getNote();
                 if(Matieres.equals("Maths"))
                 {
                     if(cptMath != 0)
                     {
                         cptMath++;
                         noteMATHS += Notes;
                     }
                     else
                     {
                         cptMath++;
                         noteMATHS = Notes;
                     }
                 }

                if(Matieres.equals("PC"))
                {
                    if(cptPC != 0)
                    {
                        cptPC++;
                        notePC += Notes;
                    }
                    else
                    {
                        cptPC++;
                        notePC = Notes;
                    }
                }

                if(Matieres.equals("SVT"))
                {
                    if(cptSVT != 0)
                    {
                        cptSVT++;
                        noteSVT += Notes;
                    }
                    else
                    {
                        cptSVT++;
                        noteSVT = Notes;
                    }
                }

                if(Matieres.equals("FR"))
                {
                    if(cptFR != 0)
                    {
                        cptFR++;
                        noteFR += Notes;
                    }
                    else
                    {
                        cptFR++;
                        noteFR = Notes;
                    }
                }

                if(Matieres.equals("ANG"))
                {
                    if(cptANG != 0)
                    {
                        cptANG++;
                        noteANG += Notes;
                    }
                    else
                    {
                        cptANG++;
                        noteANG = Notes;
                    }
                }

                if(Matieres.equals("HG"))
                {
                    if(cptHG != 0)
                    {
                        cptHG++;
                        noteHG += Notes;
                    }
                    else
                    {
                        cptHG++;
                        noteHG = Notes;
                    }
                }

                if(Matieres.equals("ALLEMAND"))
                {
                    if(cptALLEMAND != 0)
                    {
                        cptALLEMAND++;
                        noteALLEMAND += Notes;
                    }
                    else
                    {
                        cptALLEMAND++;
                        noteALLEMAND = Notes;
                    }
                }

                if(Matieres.equals("PHILO"))
                {
                    if(cptPHILO != 0)
                    {
                        cptPHILO++;
                        notePHILO += Notes;
                    }
                    else
                    {
                        cptPHILO++;
                        notePHILO = Notes;
                    }
                }

                if(Matieres.equals("EPS"))
                {
                    if(cptEPS != 0)
                    {
                        cptEPS++;
                        noteEPS += Notes;
                    }
                    else
                    {
                        cptEPS++;
                        noteEPS = Notes;
                    }
                }


            }

            if(cptMath != 0) {
                noteMATHS = noteMATHS / (double)cptMath;
                cptMath = 0;
            }
            if(cptPC != 0) {
                notePC = notePC / (double) cptPC;
                cptPC = 0;
            }
            if(cptSVT != 0) {
                noteSVT = noteSVT /  (double)cptSVT;
                cptSVT = 0;
            }
            if(cptFR != 0) {
                noteFR = noteFR / (double) cptFR;
                cptFR = 0;
            }
            if(cptANG != 0) {
                noteANG = noteANG / (double) cptANG;
                cptANG = 0;
            }
            if(cptHG != 0) {
                noteHG = noteHG /  (double)cptHG;
                cptHG = 0;
            }
            if(cptALLEMAND != 0) {
                noteALLEMAND = noteALLEMAND /  (double)cptALLEMAND;
                cptALLEMAND = 0;
            }
            if(cptPHILO != 0) {
                notePHILO = notePHILO /  (double)cptPHILO;
                cptPHILO = 0;
            }
            if(cptEPS != 0) {
                noteEPS = noteEPS /  (double)cptEPS;
                cptEPS = 0;
            }
        }
        alertDialog(noteMATHS, notePC, noteSVT, noteFR, noteHG, noteANG, noteALLEMAND, notePHILO,noteEPS);
    }

   public void alertDialog(Double noteMATHS,Double notePC,Double noteSVT, Double noteFR, Double noteHG, Double noteANG,Double noteALLEMAND, Double notePHILO,Double noteEPS) {

       LayoutInflater li = LayoutInflater.from(MainActivity2.this);
       View promptsView = li.inflate(R.layout.listenotes, null);
       AlertDialog.Builder  alertDialogBuilder = new AlertDialog.Builder(MainActivity2.this);
       alertDialogBuilder.setView(promptsView);

       TextView notemath = (TextView) promptsView.findViewById(R.id.notemaths);
       TextView notepc = (TextView) promptsView.findViewById(R.id.notepc);
       TextView notesvt = (TextView) promptsView.findViewById(R.id.notesvt);
       TextView notefr = (TextView) promptsView.findViewById(R.id.notefr);
       TextView noteang = (TextView) promptsView.findViewById(R.id.noteang);
       TextView notehg = (TextView) promptsView.findViewById(R.id.notehg);
       TextView noteallemand = (TextView) promptsView.findViewById(R.id.noteallemand);
       TextView notephilo = (TextView) promptsView.findViewById(R.id.notephilo);
       TextView noteeps = (TextView) promptsView.findViewById(R.id.noteeps);

       notemath.setText(String.valueOf(noteMATHS));
       notepc.setText(String.valueOf(notePC));
       notesvt.setText(String.valueOf(noteSVT));
       notefr.setText(String.valueOf(noteFR));
       notehg.setText(String.valueOf(noteHG));
       noteang.setText(String.valueOf(noteANG));
       noteallemand.setText(String.valueOf(noteALLEMAND));
       notephilo.setText(String.valueOf(notePHILO));
       noteeps.setText(String.valueOf(noteEPS));

       alertDialogBuilder.setCancelable(false);
       alertDialogBuilder.setTitle("Notes de : "+Nom+" "+Prenom);
       alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

           public void onClick(DialogInterface dialog, int id) {
           }
       });
       final AlertDialog alert =  alertDialogBuilder.create();
       alert.show();
    }
    @Override
    public void onDataSentChoix3(String classe, String annee_academique) {
        this.Classe = classe;
        this.Annee_academique = annee_academique;
        ArrayList<AjoutEleves> listeEleves = accesLocal.recuper(classe, annee_academique);

        if(listeEleves !=null) {
            DummyContent3.delete();
            for (int i = 0; i < listeEleves.size(); i++) {

                DummyItem3 dummy= DummyContent3.createDummyItem(listeEleves.get(i).getNom(), listeEleves.get(i).getPrenom(), listeEleves.get(i).getDate_naissance());
                DummyContent3.addItem(dummy);
            }
        }
        else
        {
            fragment = new ChoixClasse3();
            loadFragment(fragment);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Il n'y a aucun élève dans cette classe");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();
        }


    }
    @Override
    public void onDataSentTrimestre2(String nom, String prenom, String date_naissance, String trimestre) {

        ArrayList<AjoutNotes> listeNotes = accesLocal.recuper2(nom,prenom,date_naissance,trimestre,Classe, Annee_academique);
        this.Nom = nom;
        this.Prenom = prenom;
        Double noteMATHS = 0.0;
        Double notePC = 0.0;
        Double noteSVT = 0.0;
        Double noteFR = 0.0;
        Double noteANG = 0.0;
        Double noteHG = 0.0;
        Double noteALLEMAND = 0.0;
        Double notePHILO = 0.0;
        Double noteEPS = 0.0;

        int cptMath = 0;
        int cptPC = 0;
        int cptSVT = 0;
        int cptFR = 0;
        int cptANG = 0;
        int cptHG = 0;
        int cptALLEMAND = 0;
        int cptPHILO = 0;
        int cptEPS = 0;

        Double coeffMATH = 0.0;
        Double coeffSVT = 0.0;
        Double coeffPC = 0.0;
        Double coeffFR = 0.0;
        Double coeffANG = 0.0;
        Double coeffHG = 0.0;
        Double coeffALLEMAND = 0.0;
        Double coeffEPS = 0.0;
        Double coeffTOTAL = 0.0;
        Double Moyenne = 0.0;
        Double coeffPHILO = 0.0;

        Double pondMATH = 0.0;
        Double pondPC = 0.0;
        Double pondSVT = 0.0;
        Double pondFR = 0.0;
        Double pondANG = 0.0;
        Double pondHG = 0.0;
        Double pondALLEMAND = 0.0;
        Double pondPHILO = 0.0;
        Double pondEPS = 0.0;
        Double pondTOTAL = 0.0;

        String  Matieres;
        Double  Notes;
        Double  Coefficient;

        if(listeNotes !=null) {

            for (int i = 0; i < listeNotes.size(); i++) {

                Matieres =  listeNotes.get(i).getMatiere();
                Notes = listeNotes.get(i).getNote();
                Coefficient = listeNotes.get(i).getCoefficient();
                if(Matieres.equals("Maths"))
                {
                    if(cptMath != 0)
                    {
                        cptMath++;
                        noteMATHS += Notes;
                    }
                    else
                    {
                        cptMath++;
                        noteMATHS = Notes;
                        coeffMATH = Coefficient;
                    }
                }

                if(Matieres.equals("PC"))
                {
                    if(cptPC != 0)
                    {
                        cptPC++;
                        notePC += Notes;
                    }
                    else
                    {
                        cptPC++;
                        notePC = Notes;
                        coeffPC = Coefficient;
                    }
                }

                if(Matieres.equals("SVT"))
                {
                    if(cptSVT != 0)
                    {
                        cptSVT++;
                        noteSVT += Notes;
                    }
                    else
                    {
                        cptSVT++;
                        noteSVT = Notes;
                        coeffSVT = Coefficient;
                    }
                }

                if(Matieres.equals("FR"))
                {
                    if(cptFR != 0)
                    {
                        cptFR++;
                        noteFR += Notes;
                    }
                    else
                    {
                        cptFR++;
                        noteFR = Notes;
                        coeffFR = Coefficient;
                    }
                }

                if(Matieres.equals("ANG"))
                {
                    if(cptANG != 0)
                    {
                        cptANG++;
                        noteANG += Notes;
                    }
                    else
                    {
                        cptANG++;
                        noteANG = Notes;
                        coeffANG = Coefficient;
                    }
                }

                if(Matieres.equals("HG"))
                {
                    if(cptHG != 0)
                    {
                        cptHG++;
                        noteHG += Notes;
                    }
                    else
                    {
                        cptHG++;
                        noteHG = Notes;
                        coeffHG = Coefficient;
                    }
                }

                if(Matieres.equals("ALLEMAND"))
                {
                    if(cptALLEMAND != 0)
                    {
                        cptALLEMAND++;
                        noteALLEMAND += Notes;
                    }
                    else
                    {
                        cptALLEMAND++;
                        noteALLEMAND = Notes;
                        coeffALLEMAND = Coefficient;
                    }
                }

                if(Matieres.equals("PHILO"))
                {
                    if(cptPHILO != 0)
                    {
                        cptPHILO++;
                        notePHILO += Notes;

                    }
                    else
                    {
                        cptPHILO++;
                        notePHILO = Notes;
                        coeffPHILO = Coefficient;
                    }
                }


                if(Matieres.equals("EPS"))
                {
                    if(cptEPS != 0)
                    {
                        cptEPS++;
                        noteEPS += Notes;

                    }
                    else
                    {
                        cptEPS++;
                        noteEPS = Notes;
                        coeffEPS = Coefficient;
                    }
                }

            }

            if(cptMath != 0) {
                noteMATHS = noteMATHS / (double)cptMath;
                cptMath = 0;
            }
            if(cptPC != 0) {
                notePC = notePC / (double) cptPC;
                cptPC = 0;
            }
            if(cptSVT != 0) {
                noteSVT = noteSVT /  (double)cptSVT;
                cptSVT = 0;
            }
            if(cptFR != 0) {
                noteFR = noteFR / (double) cptFR;
                cptFR = 0;
            }
            if(cptANG != 0) {
                noteANG = noteANG / (double) cptANG;
                cptANG = 0;
            }
            if(cptHG != 0) {
                noteHG = noteHG /  (double)cptHG;
                cptHG = 0;
            }
            if(cptALLEMAND != 0) {
                noteALLEMAND = noteALLEMAND /  (double)cptALLEMAND;
                cptALLEMAND = 0;
            }
            if(cptPHILO != 0) {
                notePHILO = notePHILO /  (double)cptPHILO;
                cptPHILO = 0;
            }
            if(cptEPS != 0) {
                noteEPS = noteEPS /  (double)cptEPS;
                cptEPS = 0;
            }

            pondMATH = noteMATHS * coeffMATH;
            pondPC = notePC * coeffPC;
            pondSVT = noteSVT * coeffSVT;
            pondFR = noteFR * coeffFR;
            pondHG = noteHG * coeffHG;
            pondANG = noteANG * coeffANG;
            pondALLEMAND = noteALLEMAND * coeffALLEMAND;
            pondPHILO = notePHILO * coeffPHILO;
            pondEPS = noteEPS * coeffEPS;

            coeffTOTAL = coeffMATH + coeffSVT + coeffPC + coeffFR + coeffHG + coeffANG + coeffALLEMAND + coeffPHILO + coeffEPS;
            pondTOTAL = pondMATH + pondPC + pondSVT + pondFR + pondHG + pondANG +pondALLEMAND + pondPHILO +  pondEPS;

            Moyenne = pondTOTAL / coeffTOTAL;
        }
        alertDialog2(pondMATH, pondPC, pondSVT, pondFR, pondHG, pondANG, pondALLEMAND, pondPHILO,pondEPS,pondTOTAL,Moyenne);

    }

    public void alertDialog2(Double pondMATHS,Double pondPC,Double pondSVT, Double pondFR, Double pondHG, Double pondANG,Double pondALLEMAND, Double pondPHILO,Double pondEPS,Double pondTOTAL,Double Moyenne) {

        LayoutInflater li = LayoutInflater.from(MainActivity2.this);
        View promptsView = li.inflate(R.layout.listenotes2, null);
        AlertDialog.Builder  alertDialogBuilder = new AlertDialog.Builder(MainActivity2.this);
        alertDialogBuilder.setView(promptsView);

        TextView notemath = (TextView) promptsView.findViewById(R.id.notemaths);
        TextView notepc = (TextView) promptsView.findViewById(R.id.notepc);
        TextView notesvt = (TextView) promptsView.findViewById(R.id.notesvt);
        TextView notefr = (TextView) promptsView.findViewById(R.id.notefr);
        TextView noteang = (TextView) promptsView.findViewById(R.id.noteang);
        TextView notehg = (TextView) promptsView.findViewById(R.id.notehg);
        TextView noteallemand = (TextView) promptsView.findViewById(R.id.noteallemand);
        TextView notephilo = (TextView) promptsView.findViewById(R.id.notephilo);
        TextView noteeps = (TextView) promptsView.findViewById(R.id.noteeps);
        TextView notetotale = (TextView) promptsView.findViewById(R.id.notetotale);
        TextView moyenne = (TextView) promptsView.findViewById(R.id.moyenne);

        notemath.setText(String.valueOf(pondMATHS));
        notepc.setText(String.valueOf(pondPC));
        notesvt.setText(String.valueOf(pondSVT));
        notefr.setText(String.valueOf(pondFR));
        notehg.setText(String.valueOf(pondHG));
        noteang.setText(String.valueOf(pondANG));
        noteallemand.setText(String.valueOf(pondALLEMAND));
        notephilo.setText(String.valueOf(pondPHILO));
        noteeps.setText(String.valueOf(pondEPS));
        notetotale.setText(String.valueOf(pondTOTAL));
        moyenne.setText(String.valueOf(Moyenne));

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Moyenne de : "+Nom+" "+Prenom);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
            }
        });
        final AlertDialog alert =  alertDialogBuilder.create();
        alert.show();
    }


    @Override
    public void onDataSentChoix4(String classe, String annee_academique) {
        this.Classe = classe;
        this.Annee_academique = annee_academique;
        ArrayList<AjoutEleves> listeEleves = accesLocal.recuper_eleves(classe, annee_academique);

        if(listeEleves !=null) {
            DummyContent4.delete();
            for (int i = 0; i < listeEleves.size(); i++) {

                DummyContent4.DummyItem4 dummy= DummyContent4.createDummyItem(listeEleves.get(i).getNom(), listeEleves.get(i).getPrenom(), listeEleves.get(i).getDate_naissance(),listeEleves.get(i).getLieu_naissance(),Classe,Annee_academique);
                DummyContent4.addItem(dummy);
            }
        }
        else
        {
            fragment = new ChoixClasse4();
            loadFragment(fragment);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity2.this);
            dialog.setCancelable(false);
            dialog.setTitle("Information");
            dialog.setMessage("Il n'y a aucun élève dans cette classe");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();
        }


    }
}