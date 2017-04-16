package io.github.yusukeiwaki.realmfgnotificationplayground;

import android.support.v7.widget.RecyclerView;

import io.github.yusukeiwaki.realmfgnotificationplayground.databinding.ListItemDogBinding;
import io.github.yusukeiwaki.realmfgnotificationplayground.model.Dog;

public class DogViewHolder extends RecyclerView.ViewHolder {
    private final ListItemDogBinding dogBinding;
    public DogViewHolder(ListItemDogBinding dogBinding) {
        super(dogBinding.getRoot());
        this.dogBinding = dogBinding;
    }

    public void bind(Dog dog) {
        dogBinding.setDog(dog);
    }
}
