package io.github.yusukeiwaki.realmfgnotificationplayground.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject {
    @PrimaryKey
    public String id;
    public String name;
    public RealmList<Dog> dogs;
}