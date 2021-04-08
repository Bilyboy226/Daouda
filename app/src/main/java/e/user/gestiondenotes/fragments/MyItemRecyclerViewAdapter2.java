package e.user.gestiondenotes.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e.user.gestiondenotes.R;
import e.user.gestiondenotes.fragments.dummy.DummyContent2.DummyItem2;
import e.user.gestiondenotes.fragments.dummy.DummyContent2;

import java.util.List;

public class MyItemRecyclerViewAdapter2 extends RecyclerView.Adapter<MyItemRecyclerViewAdapter2.ViewHolder> {

    private final List<DummyItem2> mValues;
    private ItemClickListener clickListener;
    public MyItemRecyclerViewAdapter2(List<DummyItem2> items, ItemClickListener clickListener) {

        mValues = items;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_liste_des_notes, parent, false);
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
        public TextView Date_naissance;
        public DummyItem2 mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Nom = (TextView) view.findViewById(R.id.nom);
            Prenom = (TextView) view.findViewById(R.id.prenom);
            Date_naissance = (TextView) view.findViewById(R.id.date_naissance);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + Prenom.getText() + "'";
        }
    }

    public  interface ItemClickListener
    {
        public  void onItemClick(DummyItem2 dummyItem);
    }

}