package e.user.gestiondenotes.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import e.user.gestiondenotes.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VoirListeEleve#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoirListeEleve extends Fragment {

    private Button bouton;
    private TextView nom;
    private  TextView prenom;
    private TextView date_naissance;
    private  TextView lieu_naissance;
    private TextView classe;
    private  TextView annee_academique;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";

    // TODO: Rename and change types of parameters
    private String Nom;
    private String Prenom;
    private String Date_naissance;
    private String Lieu_naissance;
    private  String Classe;
    private  String Annee_academique;

    public VoirListeEleve() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VoirListeEleve newInstance(String nom,String prenom,String date_naissance,String lieu_naissance,String classe,String annee_academique) {
        VoirListeEleve fragment = new VoirListeEleve();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nom);
        args.putString(ARG_PARAM2, prenom);
        args.putString(ARG_PARAM3, date_naissance);
        args.putString(ARG_PARAM4, lieu_naissance);
        args.putString(ARG_PARAM5, classe);
        args.putString(ARG_PARAM6, annee_academique);
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
            Lieu_naissance = getArguments().getString(ARG_PARAM4);
            Classe = getArguments().getString(ARG_PARAM5);
            Annee_academique = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voir_liste_eleve, container, false);

        nom =  (TextView) view.findViewById(R.id.nom);
        prenom =  (TextView) view.findViewById(R.id.prenom);
        date_naissance =  (TextView) view.findViewById(R.id.date_naissance);
        lieu_naissance =  (TextView) view.findViewById(R.id.lieu_naissance);
        classe =  (TextView) view.findViewById(R.id.classe);
        annee_academique =  (TextView) view.findViewById(R.id.annee_academique);

        nom.setText(Nom);
        prenom.setText(Prenom);
        date_naissance.setText(Date_naissance);
        lieu_naissance.setText(Lieu_naissance);
        classe.setText(Classe);
        annee_academique.setText(Annee_academique);

        bouton = (Button)view.findViewById(R.id.retour);
        bouton.setOnClickListener(boutonlister);
        return view;
    }

    private View.OnClickListener boutonlister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Fragment fragment = new ListeEleves();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();

        }
    };
}