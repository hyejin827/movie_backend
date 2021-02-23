package com.example.movie_backend.controller;

import com.example.movie_backend.model.User;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.util.List;

//@RestController
@Slf4j
@RestController
public class TestController {

    private DatabaseReference mDatabase;

    public static final String COLLECTION_NAME="user";

    @RequestMapping("/welcome")
    public User welcome() {
        try {

            FileInputStream serviceAccount =
                    new FileInputStream("./src/main/resources/movie-vupring-firebase-adminsdk-exp9e-7c7b23a48a.json");

            FirebaseApp firebaseApp = null;

            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if(firebaseApps!=null && !firebaseApps.isEmpty()){
                for(FirebaseApp app : firebaseApps){
                    if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                        firebaseApp = app;
                }
            }else{
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://movie-vupring-default-rtdb.firebaseio.com/")
                        .build();

                firebaseApp = FirebaseApp.initializeApp(options);
                System.out.println(options);
            }

//            mDatabase = FirebaseDatabase.getInstance().getReference("user");


            Firestore firestore = FirestoreClient.getFirestore();

            DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document("hyejin");

            ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();

            DocumentSnapshot documentSnapshot = apiFuture.get();

            User user = null;

            if(documentSnapshot.exists()) {

                user = documentSnapshot.toObject(User.class);

                return user;

            } else {

                return null;

            }

//            log.info("firebaseApp==========> {}", mDatabase);

//            Firestore dbFirestore = FirestoreClient.getFirestore();
//            DocumentReference documentReference = dbFirestore.collection("user").document();
//            ApiFuture<DocumentSnapshot> future = documentReference.get();
//
//            DocumentSnapshot document = future.get();
//
//            User user = null;
//
//            if(document.exists()) {
//                user = document.toObject(User.class);
//                return user.getName();
//            }else {
//                return null;
//            }


//            FileInputStream serviceAccount =
//                    new FileInputStream("./src/main/resources/movie-vupring-firebase-adminsdk-exp9e-7c7b23a48a.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://movie-vupring-default-rtdb.firebaseio.com/")
//                    .build();
//
//            FirebaseApp.initializeApp(options);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void writeNewUser(String userId, String name, String email) {
//        FirebaseDatabase.getInstance().getReference().push().setValue("name" + cnt);

//        mDatabase.child("name").child(userId).setValue("???");
    }
}
