package com.farhanarrafi.app.cryptostash;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farhanarrafi.app.cryptostash.adapter.EthereumAdapter;
import com.farhanarrafi.app.cryptostash.adapter.EthereumJsonAdapter;
import com.farhanarrafi.app.cryptostash.databinding.ActivityMainBinding;
import com.farhanarrafi.app.cryptostash.events.EthereumItemsLoadEvent;
import com.farhanarrafi.app.cryptostash.model.Ethereum;
import com.farhanarrafi.app.cryptostash.utils.CSLog;
import com.farhanarrafi.app.cryptostash.utils.DownloadData;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private RecyclerView ethereumRecyclerView;
    private EthereumAdapter ethereumAdapter;
    private RecyclerView.LayoutManager horizontalLayoutManager;

    private ProgressBar progressBar;

    private final int INTERNET_REQUEST_CODE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fabRefreshData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Requesting updates", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                checkRequiredPermissionsAndDownloadData();
                showProgressBar();
            }
        });

        progressBar = findViewById(R.id.indeterminateProgressBar);

        ethereumRecyclerView = findViewById(R.id.ethereumRecyclerView);
        horizontalLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        ethereumRecyclerView.setLayoutManager(horizontalLayoutManager);
        ArrayList<Ethereum> ethereumArrayList = new ArrayList<>();
        Ethereum ethereum = new Ethereum();
        ethereum.setShortName("btc");
        ethereum.setFullName("Bitcoin");
        ethereum.setLastPrice("$487659.13");
        ethereum.setPriceChange("$24.11");
        ethereumArrayList.add(ethereum);
        ethereumAdapter = new EthereumAdapter(ethereumArrayList);
        ethereumRecyclerView.setAdapter(ethereumAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        showProgressBar();
        checkRequiredPermissionsAndDownloadData();
    }

    @Override
    protected void onPause() {
        hideProgressBar();
        super.onPause();
    }

    private void checkRequiredPermissionsAndDownloadData() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.INTERNET) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            DownloadData downloadData = new DownloadData();
            try {
                downloadData.downloadEthereumJSON("https://api.wazirx.com/sapi/v1/tickers/24hr");
            } catch (Exception e) {
                CSLog.e(TAG, e.getMessage());
                hideProgressBar();
                Toast.makeText(this, "Could not load data.", Toast.LENGTH_SHORT).show();
            }
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            showPermissionRationale();
        } else {
            // You can directly ask for the permission.
            requestPermissions(
                    new String[] { Manifest.permission.INTERNET },
                    INTERNET_REQUEST_CODE);
        }
    }

    private void showPermissionRationale() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("This permission is needed for accessing Cryptocurrency data from the internet.\n" +
                "This application cannot be used without internet access.")
                .setTitle("Internet Permission needed");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                requestPermissions(
                        new String[] { Manifest.permission.INTERNET },
                        INTERNET_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == INTERNET_REQUEST_CODE) {

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEthereumItemsLoadEvent(EthereumItemsLoadEvent ethereumItemsLoadEvent) {
        if(ethereumItemsLoadEvent.getError().isEmpty()) {
            String response = ethereumItemsLoadEvent.getJson();

            Moshi moshi = new Moshi.Builder()
                    .add(new EthereumJsonAdapter(this))
                    .build();

            Type type = Types.newParameterizedType(List.class, Ethereum.class);
            JsonAdapter<List<Ethereum>> adapter = moshi.adapter(type);
            try {
                List<Ethereum> ethereumList = adapter.fromJson(response);
                CSLog.d(TAG, "ethereumList count: " + ethereumList.size());
                ethereumAdapter = new EthereumAdapter(ethereumList);
                ethereumRecyclerView.setAdapter(ethereumAdapter);
                ethereumAdapter.notifyDataSetChanged();
                hideProgressBar();
            } catch (IOException e) {
                CSLog.e(e.getMessage());
            }
        } else {
            Toast.makeText(this, ethereumItemsLoadEvent.getError(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * How to disable user interaction while progressbar is showing:
     * https://stackoverflow.com/a/36927858/3148856
     *
     *
     */
    private void showProgressBar() {
        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void hideProgressBar() {
        if(progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


}