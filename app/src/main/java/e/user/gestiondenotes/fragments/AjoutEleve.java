package e.user.gestiondenotes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import e.user.gestiondenotes.R;

public class AjoutEleve extends Fragment {

    private Button bouton;
    private EditText nom;
    private EditText prenom;
    private EditText date_naissance;
    private EditText lieu_naissance;
    private Spinner classe;
    private Spinner annee_academique;
    private String Nom = "";
    private String Prenom = "";
    private String Date_naissance = "";
    private String Lieu_naissance = "";
    private String Classe = "";
    private String Annee_academique = "";

    private  AjoutEleveListener listener;
    public  interface AjoutEleveListener{
        void  onDataSent(String nom,String prenom,String date_naissance,String lieu_naissance,String classe,String annee_academique);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ajout_eleve, container, false);
        bouton = (Button)root.findViewById(R.id.valider);
        nom = (EditText)root.findViewById(R.id.nom);
        prenom = (EditText)root.findViewById(R.id.prenom);
        date_naissance = (EditText)root.findViewById(R.id.date_naissance);
        lieu_naissance = (EditText)root.findViewById(R.id.lieu_naissance);
        classe = (Spinner) root.findViewById(R.id.classe);
        annee_academique = (Spinner) root.findViewById(R.id.annee_academique);

        bouton.setOnClickListener(boutonlister);
        return root;
    }

   private View.OnClickListener boutonlister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Nom = nom.getText().toString();
                Prenom = prenom.getText().toString();
                Date_naissance = date_naissance.getText().toString();
                Lieu_naissance = lieu_naissance.getText().toString();
                Classe = classe.getSelectedItem().toString();
                Annee_academique = annee_academique.getSelectedItem().toString();
                if(!Nom.equals("") && !Prenom.equals("") && !Date_naissance.equals("") && !Lieu_naissance.equals("")) {
                    listener.onDataSent(Nom, Prenom, Date_naissance, Lieu_naissance, Classe, Annee_academique);
                    nom.setText("");
                    prenom.setText("");
                    date_naissance.setText("");
                    lieu_naissance.setText("");
                }
                else
                    listener.onDataSent("vide", "vide", "vide", "vide", "vide", "vide");


            }catch(Exception e){
            }

        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AjoutEleveListener)
        {
            listener = (AjoutEleveListener) context;
        }
        else
        {
           throw new RuntimeException(context.toString()
           +"Implémentation de fragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}