# ViewModelLiveDataDemoEnrichi (Lab18)

Ce projet est une démonstration pédagogique des composants **Android Jetpack** modernes (version 2.10.0 stable). L'objectif est de comprendre comment gérer l'état de l'interface utilisateur de manière robuste face aux changements de configuration et à la mort du processus système.

## 🚀 Objectifs d'apprentissage

1.  **Survie à la rotation** : Utilisation du `ViewModel` pour conserver les données lors d'un changement de configuration.
2.  **Architecture Réactive** : Utilisation de `LiveData` pour mettre à jour l'UI automatiquement en respectant le cycle de vie (`Lifecycle-aware`).
3.  **Thread Safety** : Différence entre `setValue()` (thread principal) et `postValue()` (thread background).
4.  **Persistance Avancée** : Utilisation de `SavedStateHandle` pour survivre au **Process Death** (quand Android tue l'app pour libérer de la RAM).

## 🛠 Technologies utilisées

- **Langage** : Java
- **SDK Minimum** : API 24 (Android 7.0)
- **Architecture** : MVVM (Model-View-ViewModel)
- **Bibliothèques Jetpack** :
    - `androidx.lifecycle:lifecycle-viewmodel`
    - `androidx.lifecycle:lifecycle-livedata`
    - `androidx.lifecycle:lifecycle-viewmodel-savedstate`

## 📱 Scénarios de test

### 1. Rotation d'écran
- Incrémentez le compteur à 10.
- Tournez l'appareil en mode paysage.
- **Résultat attendu** : Le compteur affiche toujours 10 (grâce au `ViewModel`).

### 2. Thread Background
- Cliquez sur le bouton **INCRÉMENTER (THREAD)**.
- Attendez 1 seconde (simulation d'un délai réseau).
- **Résultat attendu** : Le compteur s'incrémente de façon fluide grâce à `postValue()`.

### 3. Process Death (Mort du processus)
- Mettez l'application en arrière-plan (bouton Home).
- Lancez la commande suivante via le terminal :
  ```bash
  adb shell am kill com.example.lab18
  ```
- Relancez l'application depuis le menu des applications récentes.
- **Résultat attendu** : Le compteur a conservé sa valeur (grâce au `SavedStateHandle`).

## 📁 Structure du Projet

- `MainActivity.java` : La "Vue" qui observe le ViewModel et gère les clics.
- `CounterViewModel.java` : La logique métier et le stockage de l'état.
- `activity_main.xml` : Le layout utilisant un `LinearLayout` vertical simple.


[Screen_recording_20260508_162524.webm](https://github.com/user-attachments/assets/c63b338c-4152-470d-b722-69f1f3e872b6)

