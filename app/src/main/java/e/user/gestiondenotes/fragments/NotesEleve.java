package e.user.gestiondenotes.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import e.user.gestiondenotes.R;

public class NotesEleve extends Fragment {
    private Button bouton;
    private Spinner matiere;
    private  Spinner trimestre;
    private  TextView note;
    private  TextView coefficient;
    private String Matiere = "";
    private  String Trimestre;
    private Double Note;
    private Double Coefficient;

    private NotesEleveListener listener;
    public  interface NotesEleveListener{
        void  onDataSentNote(String nom,String prenom,String date_naissance,String matiere,Double note,Double coefficient,String trimestre);
    }
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";


    // TODO: Rename and change types of parameters
    private String Nom;
    private String Prenom;
    private String Date_naissance;

    public NotesEleve() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NotesEleve newInstance(String nom,String prenom,String date_naissance) {
        NotesEleve fragment = new NotesEleve();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nom);
        args.putString(ARG_PARAM2, prenom);
        args.putString(ARG_PARAM3, date_naissance);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Nom = getArguments().getString(ARG_PARAM1);
            Prenom = getArguments().getString(ARG_PARAM2);
            Date_naissance = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_eleve, container, false);

        matiere = (Spinner) view.findViewById(R.id.matieres);
        trimestre =  (Spinner) view.findViewById(R.id.trimestre);
        note =  (TextView) view.findViewById(R.id.note);
        coefficient =  (TextView) view.findViewById(R.id.coefficient);
        bouton = (Button)view.findViewById(R.id.valider);
        bouton.setOnClickListener(boutonlister);
        return view;
    }
    private View.OnClickListener boutonlister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Matiere = matiere.getSelectedItem().toString();
            Trimestre = trimestre.getSelectedItem().toString();
            if(!note.getText().toString().equals("") && !coefficient.getText().toString().equals("")) {

                Note = Double.parseDouble(note.getText().toString());
                Coefficient = Double.parseDouble(coefficient.getText().toString());
                listener.onDataSentNote(Nom,Prenom,Date_naissance,Matiere, Note,Coefficient,Trimestre);
                note.setText("");
                coefficient.setText("");
            }
            else
            {
                Note = -1.0;
                Coefficient = -1.0;
                listener.onDataSentNote("","","","", -1.0,-1.0,"");
            }


        }
    };

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ChoixClasse.ChoixClasseListener)
        {
            listener = (NotesEleve.NotesEleveListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    +"Implémentation de fragment");
        }
    }
    public void onDetach() {
        super.onDetach();
        listener = null;
    }




}