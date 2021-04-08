package e.user.gestiondenotes.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e.user.gestiondenotes.R;
import e.user.gestiondenotes.fragments.dummy.DummyContent4.DummyItem4;
import e.user.gestiondenotes.fragments.dummy.DummyContent4;


import java.util.List;

public class MyItemRecyclerViewAdapter4 extends RecyclerView.Adapter<MyItemRecyclerViewAdapter4.ViewHolder> {

    private final List<DummyContent4.DummyItem4> mValues;
    private ItemClickListener clickListener;
    public MyItemRecyclerViewAdapter4(List<DummyItem4> items, ItemClickListener clickListener) {

        mValues = items;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_liste_eleves, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.Nom.setText(mValues.get(position).nom);
        holder.Prenom.setText(mValues.get(position).prenom);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(mValues.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView Nom;
        public TextView Prenom;
        public DummyItem4 mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Nom = (TextView) view.findViewById(R.id.nom);
            Prenom = (TextView) view.findViewById(R.id.prenom);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + Prenom.getText() + "'";
        }
    }

    public  interface ItemClickListener
    {
        public  void onItemClick(DummyItem4 dummyItem);
    }

}