package com.example.fronttttttttttttttttttt;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class RDVV {
    String IDR,typdV,LOC,IDP;
     ArrayList<String> listV=new ArrayList<String>();

    Timestamp dateR;

    public String getIDR() {
        return IDR;
    }

    public String getTypdV() {
        return typdV;
    }

    public String getLOC() {
        return LOC;
    }

    public RDVV(String IDR , String typdV, String LOC ,String IDP) {
        this.IDR = IDR;
        this.typdV = typdV;
        this.LOC = LOC;
        this.IDP =IDP;

    }

    public ArrayList<String> getListV() {
        return listV;
    }
    public static ArrayList<String> fillRDV(){
        ArrayList<String> listV=new ArrayList<>();
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("Ambulancier").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for (DocumentChange documentChange : documentSnapshots.getDocumentChanges())
                {
                    String ID = documentChange.getDocument().get("id").toString();
                    listV.add(ID);
                }
            }
        });
        return listV;

    }
    public void setListV(ArrayList<String> listV) {
        this.listV = listV;
    }

    public RDVV() {

    }


    public Timestamp getDateR() {
        return dateR;
    }
}
