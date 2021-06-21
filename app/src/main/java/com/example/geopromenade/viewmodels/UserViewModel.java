/**
 * Le ViewModel : Le contrôleur implémente une classe de type ViewModel, dont le rôle est de fournir au contrôleur les données utilisées par l'interface graphique.
 * Une des spécificités de la classe ViewModel est sa capacité à "survivre" aux changements de configuration comme la rotation de l'écran par exemple,
 * sans perdre ses données... *
 */

package com.example.geopromenade.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.geopromenade.models.User;
import com.example.geopromenade.repositories.UserRepository;

public class UserViewModel extends ViewModel {
    UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public User createUser(User user){
        return userRepository.createUserInFirestore(user);
    }
}
