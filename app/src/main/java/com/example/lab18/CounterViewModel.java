package com.example.lab18;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private static final String COUNT_KEY = "count";
    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<Integer> countLiveData;

    public CounterViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        // Initialisation à partir du SavedStateHandle ou 0 par défaut
        Integer initialCount = savedStateHandle.get(COUNT_KEY);
        countLiveData = new MutableLiveData<>(initialCount != null ? initialCount : 0);
    }

    // Mise à jour de la valeur et sauvegarde immédiate dans le StateHandle
    private void updateCount(int newValue) {
        countLiveData.setValue(newValue);
        savedStateHandle.set(COUNT_KEY, newValue);
    }

    public void increment() {
        Integer current = countLiveData.getValue();
        if (current != null) {
            updateCount(current + 1);
        }
    }

    public void decrement() {
        Integer current = countLiveData.getValue();
        if (current != null) {
            updateCount(current - 1);
        }
    }

    public void reset() {
        updateCount(0);
    }

    // BONUS 1 : postValue depuis un thread background
    public void incrementFromBackground() {
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulation d'un traitement long
                Integer current = countLiveData.getValue();
                if (current != null) {
                    // postValue est thread-safe et "pousse" la donnée vers le thread UI
                    int newValue = current + 1;
                    countLiveData.postValue(newValue);
                    savedStateHandle.set(COUNT_KEY, newValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public LiveData<Integer> getCount() {
        return countLiveData;
    }
}
