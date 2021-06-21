/**
 *
 * Le Repository : A l'intérieur de chaque ViewModel, nous allons retrouver une ou plusieurs classes de type Repository.
 * Il nous servira de médiateur entre le ViewModel et les différentes sources de données.
 * Le but du repository est d’isoler la source de données (DAO) du ViewModel, afin que ce dernier ne manipule pas directement la source de données.
 * Cela peut faciliter le changement de la source de données sans impacter l’ensemble des couches de l’appli.
 *
 *
 *
 */

package com.example.geopromenade.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.geopromenade.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRepository {
    private static final String TAG = "UserRepository";

    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersCollectionReference = rootRef.collection("Users");

    public User createUserInFirestore(User user) {
        //Set l 'id du user
        user.setId("EDREREF45XX");

        // Nouveau utilisateur Firesotre
        DocumentReference newUserRef = usersCollectionReference.document(user.getId());//Récupère le user par son uid

        newUserRef.set(user).addOnCompleteListener(userCreationTask -> {
            if (userCreationTask.isSuccessful()) {
                user.setCreated(true);
                Log.d(TAG, "createUserInFirestore: Success");
            } else {
                Log.d(TAG, "createUserInFirestoreIfNotExists: Error");
            }
        });
        return user;
    }


//    private static final String TAG = "UserRepository";
//    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference usersDbRef = firebaseDatabase.getReference("Users");
//
//    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
//    private CollectionReference usersCollectionReference = rootRef.collection("Users");
//
//    public MutableLiveData<User> firebaseSignInWithGoogle(AuthCredential googleAuthCredential) {
//        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();
//        firebaseAuth.signInWithCredential(googleAuthCredential).addOnCompleteListener(authTask -> {
//            if (authTask.isSuccessful()) {
//                boolean isNewUser = authTask.getResult().getAdditionalUserInfo().isNewUser();
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                if (firebaseUser != null) {
//                    String uid = firebaseUser.getUid();
//                    String name = firebaseUser.getDisplayName();
//                    String email = firebaseUser.getEmail();
//                    User user = new User(uid, name, email);
//                    user.setNew(isNewUser);
//                    authenticatedUserMutableLiveData.setValue(user);
//                }
//            } else {
//                Utils.logErrorMessage(authTask.getException().getMessage());
//            }
//        });
//        return authenticatedUserMutableLiveData;
//    }
//
//    public MutableLiveData<User> createUserInFirestoreIfNotExists(User user) {
//        MutableLiveData<User> newUserMutableLiveData = new MutableLiveData<>();
//        //----------
//        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()) //Créer l'utilisateur dans Firebase Authentication service
//                .addOnCompleteListener((Task<AuthResult> task) -> {
//                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                    task.addOnSuccessListener(unused -> {
//                        Log.d(TAG, "createUserWithEmail:Success in account registering - User id: " + FirebaseAuth.getInstance().getUid());
//                        //Set l'id du user
//                        user.setId(FirebaseAuth.getInstance().getUid());
//
//                        // Nouveau utilisateur Firesotre
//                        DocumentReference newUserRef = usersCollectionReference.document(user.getId());//Récupère le user par son uid
//
//                        newUserRef.set(user).addOnCompleteListener(userCreationTask -> {
//                            if (userCreationTask.isSuccessful()) {
//                                user.setCreated(true);
//                                newUserMutableLiveData.setValue(user);
//                            } else {
//                                Log.d(TAG, "createUserInFirestoreIfNotExists: Error in account creation - Message: " + userCreationTask.getException().getMessage());
//                                //Utils.logErrorMessage(userCreationTask.getException().getMessage());
//                            }
//                        });
//                    })
//                            .addOnFailureListener((Exception exception) -> {
//                                Log.d(TAG, "createUserInFirestoreIfNotExists: Error in account creation - Message: " + exception.getMessage());
//                            });
//                });
//
//        return newUserMutableLiveData;
//    }
//
//    //--------------------------------
//    public MutableLiveData<User> createUserInRealtimeDbIfNotExists(User user) {
//        MutableLiveData<User> newUserMutableLiveData = new MutableLiveData<>();
//        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
//                .addOnCompleteListener((Task<AuthResult> task) -> {
//                    if (task.isSuccessful()) {
////                        User user = new User();
////                        user.setFirstname("Sully");
////                        user.setBirthdate("23/07/1990");
//
//                        usersDbRef.child(firebaseAuth.getCurrentUser().getUid())
//                                .setValue(user).addOnCompleteListener((Task<Void> task1) -> {
//                            if (task1.isSuccessful()) {
//                                user.setCreated(true);
//                                newUserMutableLiveData.setValue(user);
////                                Toast.makeText(RegisterUserActivity.this, "Le compte utilisateur a été créé !", Toast.LENGTH_LONG).show();
////                                progressBar.setVisibility(View.VISIBLE);
//                            } else {
//                                user.setCreated(false);
//                                Utils.logErrorMessage(task1.getException().getMessage());
////                                Toast.makeText(RegisterUserActivity.this, "Erreur lors de la création du compte utilisateur " +
////                                        task1.getException().getMessage(), Toast.LENGTH_LONG).show();
////                                progressBar.setVisibility(View.GONE);
//                            }
//                        });
//                    } else {
//                        user.setCreated(false);
//                        Utils.logErrorMessage(task.getException().getMessage());
////                        Toast.makeText(RegisterUserActivity.this, "Erreur lors de la création du compte utilisateur " +
////                                task.getException().getMessage(), Toast.LENGTH_LONG).show();
////                        progressBar.setVisibility(View.GONE);
//                    }
//                });
//        return newUserMutableLiveData;
//    }
//
//    public MutableLiveData<FirebaseUser> signInWithEmailAndPassword(String email, String password) {
////        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();
//        MutableLiveData<FirebaseUser> authenticatedUserMutableLiveData = new MutableLiveData<>();
//        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Task<AuthResult> task) -> {
//            if (task.isSuccessful()) {
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//                if (firebaseUser != null) {
////                    String uid = firebaseUser.getUid();
////                    String name = firebaseUser.getDisplayName();
////                    String emailFromDbUser = firebaseUser.getEmail();
////                    User user = new User();
////                    user.setEmail(email);
////                    user.setFirstname(name);
////                    user.setUid(uid);
////                    authenticatedUserMutableLiveData.setValue(user);
//                    authenticatedUserMutableLiveData.setValue(firebaseUser);
//                }
//            } else {
//                Utils.logErrorMessage(task.getException().getMessage());
//                authenticatedUserMutableLiveData.setValue(null);
//            }
//        });
//        return authenticatedUserMutableLiveData;
//    }
//
//    public MutableLiveData<User> getUserById(String userId) {
//        MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();
//
//        DocumentReference userDocumentReference = usersCollectionReference.document(userId);
//        userDocumentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
//                task.addOnSuccessListener(unused -> {
//                    Log.d(TAG, "onComplete: Successfully get the user from firestore.");
//                    User user = task.getResult().toObject(User.class);
//                    authenticatedUserMutableLiveData.setValue(user);
//                }).addOnFailureListener((Exception exception) -> {
//                    authenticatedUserMutableLiveData.setValue(null);
//                    Log.d(TAG, "onComplete: Error to get user - Message: " + exception.getMessage());
//                });
//            }
//        });
//        return authenticatedUserMutableLiveData;
}
