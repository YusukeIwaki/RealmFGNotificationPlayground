package io.github.yusukeiwaki.realmfgnotificationplayground;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bolts.Task;
import bolts.TaskCompletionSource;
import io.github.yusukeiwaki.realmfgnotificationplayground.model.Dog;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;

/**
 */
public class SimpleDogListFragment extends BaseFragment {
    @Override
    protected OrderedRealmCollection<Dog> getData() {
        return getRealm().where(Dog.class).findAll();
    }

    @Override
    protected Task<Void> refresh() {
        final TaskCompletionSource<Void> tcs = new TaskCompletionSource<>();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final JSONArray data = new JSONArray();
                try {
                    data
                            .put(new JSONObject()
                                    .put("id","ewrl")
                                    .put("name", "pochi")
                                    .put("color", "white"))
                            .put(new JSONObject()
                                    .put("id","fdg9")
                                    .put("name", "shiro")
                                    .put("color", "white"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.createOrUpdateAllFromJson(Dog.class, data);
                    }
                });

                tcs.setResult(null);
            }
        }.start();

        return tcs.getTask();
    }
}
