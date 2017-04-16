package io.github.yusukeiwaki.realmfgnotificationplayground;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.github.yusukeiwaki.realmfgnotificationplayground.databinding.ListItemDogBinding;
import io.github.yusukeiwaki.realmfgnotificationplayground.model.Dog;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class DogListAdapter extends RealmRecyclerViewAdapter<Dog, DogViewHolder> {
    public DogListAdapter(Context context, @Nullable OrderedRealmCollection<Dog> data) {
        super(context, data, true);
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DogViewHolder(ListItemDogBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
