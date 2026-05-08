package com.example.lab18;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private CounterViewModel viewModel;
    private TextView tvCount;
    private Button btnIncrement, btnDecrement, btnReset, btnBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des composants UI
        tvCount = findViewById(R.id.tvCount);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);
        btnReset = findViewById(R.id.btnReset);
        btnBackground = findViewById(R.id.btnBackground);

        // 1. Récupération (ou création) du ViewModel
        // Le ViewModelProvider par défaut utilise une factory capable d'injecter 
        // le SavedStateHandle automatiquement dans le constructeur de CounterViewModel.
        viewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        // 2. Observation du LiveData (lifecycle-aware)
        // L'observer n'est appelé QUE si l'Activity est active (STARTED ou RESUMED).
        viewModel.getCount().observe(this, newCount -> {
            // Mise à jour automatique de l'UI dès que la donnée change
            tvCount.setText(String.valueOf(newCount));
        });

        // 3. Actions utilisateur
        btnIncrement.setOnClickListener(v -> viewModel.increment());
        btnDecrement.setOnClickListener(v -> viewModel.decrement());
        btnReset.setOnClickListener(v -> viewModel.reset());

        // BONUS 1 : Appel asynchrone (postValue)
        btnBackground.setOnClickListener(v -> viewModel.incrementFromBackground());
    }
}
