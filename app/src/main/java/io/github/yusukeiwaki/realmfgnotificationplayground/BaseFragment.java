package io.github.yusukeiwaki.realmfgnotificationplayground;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bolts.Continuation;
import bolts.Task;
import io.github.yusukeiwaki.realmfgnotificationplayground.databinding.FragmentMainBinding;
import io.github.yusukeiwaki.realmfgnotificationplayground.model.Dog;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;

abstract class BaseFragment extends Fragment {

    private FragmentMainBinding binding;
    private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh().continueWith(new Continuation() {
                    @Override
                    public Object then(Task task) throws Exception {
                        binding.swipeRefresh.setRefreshing(false);
                        return null;
                    }
                });
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(new DogListAdapter(getContext(), getData()));

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        if (realm != null) {
            realm.close();
            realm = null;
        }
        super.onDestroy();
    }

    protected final Realm getRealm() {
        return realm;
    }

    protected abstract OrderedRealmCollection<Dog> getData();
    protected abstract Task<Void> refresh();
}
