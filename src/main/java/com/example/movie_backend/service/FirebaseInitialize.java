package com.example.movie_backend.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInitialize {

    @PostConstruct //has to be Run during the Start of
    public void initialize() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("./src/main/resources/movie-vupring-firebase-adminsdk-exp9e-7c7b23a48a.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://movie-vupring-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println(options);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
