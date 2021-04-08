package e.user.gestiondenotes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import e.user.gestiondenotes.R;

public class ChoixClasse4 extends Fragment {


    private Button bouton;
    private Spinner classe;
    private Spinner annee_academique;
    private String Classe = "";
    private String Annee_academique = "";

    private ChoixClasse4Listener listener;
    public  interface ChoixClasse4Listener{
        void  onDataSentChoix4(String classe,String annee_academique);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_choix_classe4, container, false);
        classe = (Spinner) root.findViewById(R.id.classe);
        annee_academique = (Spinner) root.findViewById(R.id.annee_academique);
        bouton = (Button)root.findViewById(R.id.valider);
        bouton.setOnClickListener(boutonlister);
        return root;
    }


    private View.OnClickListener boutonlister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Classe = classe.getSelectedItem().toString();
            Annee_academique = annee_academique.getSelectedItem().toString();
            Fragment fragment = new ListeEleves();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            listener.onDataSentChoix4(Classe, Annee_academique);
        }
    };

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ChoixClasse4Listener)
        {
            listener = (ChoixClasse4Listener) context;
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