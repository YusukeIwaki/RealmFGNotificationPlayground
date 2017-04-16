package io.github.yusukeiwaki.realmfgnotificationplayground.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Dog extends RealmObject {
    @PrimaryKey
    public String id;
    public String name;
    public String color;
}
