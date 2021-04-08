package e.user.gestiondenotes.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import e.user.gestiondenotes.R;

import e.user.gestiondenotes.fragments.dummy.DummyContent;
import e.user.gestiondenotes.fragments.dummy.DummyContent4;

/**
 * A fragment representing a list of Items.
 */
public class ListeEleves extends Fragment implements MyItemRecyclerViewAdapter4.ItemClickListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public ListeEleves() {
    }

    @SuppressWarnings("unused")
    public static ListeEleves newInstance(int columnCount) {
        ListeEleves fragment = new ListeEleves();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_eleves_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter4(DummyContent4.ITEMS, (MyItemRecyclerViewAdapter4.ItemClickListener) this));
        }
        return view;
    }


    @Override
    public void onItemClick(DummyContent4.DummyItem4 dummyItem) {
        Fragment fragment = VoirListeEleve.newInstance(dummyItem.getNom(),dummyItem.getPrenom(),dummyItem.getDate_naissance(),dummyItem.getLieu_naissance(),dummyItem.getClasse(),dummyItem.getAnnee_academique());

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment,"fragment_voir_liste_eleves");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}