package com.example.nour.injazplante;

import java.util.ArrayList;

public class Plant {

    private int id ;
    private  String nom ;
    private int prix ;
    private int quantiteEau ;
    private int[] dateArrosage ;
    private int dureeEnsoleillement;
    private String dateEnsoleillement ;
    private int image ;
    private boolean notifie = false;
    private boolean favoris = false;
    private ArrayList<String> biens = new ArrayList<>();


    public Plant(int id,String nom, int prix, int quantiteEau, int[] dateArrosage, int dureeEnsoleillement, String dateEnsoleillement, int image, ArrayList<String> biens) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantiteEau = quantiteEau;
        this.dateArrosage = dateArrosage;
        this.dureeEnsoleillement = dureeEnsoleillement;
        this.dateEnsoleillement = dateEnsoleillement;
        this.image = image;
        this.biens = biens;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean getFavoris(){
        return favoris;
    }
    public boolean getNotifie(){
        return notifie;
    }
    public void setFavoris(boolean favoris) {
        this.favoris = favoris;
    }

    public void setNotifie(boolean notifie) {
        this.notifie = notifie;
    }

    public int getPrix() {
        return prix;
    }

    public ArrayList<String> getBiens() {
        return biens;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantiteEau() {
        return quantiteEau;
    }

    public void setQuantiteEau(int quantiteEau) {
        this.quantiteEau = quantiteEau;
    }

    public int[] getDateArrosage() {
        return dateArrosage;
    }

    public void setDateArrosage(int[] dateArrosage) {
        this.dateArrosage = dateArrosage;
    }

    public int getDureeEnsoleillement() {
        return dureeEnsoleillement;
    }

    public void setDureeEnsoleillement(int dureeEnsoleillement) {
        this.dureeEnsoleillement = dureeEnsoleillement;
    }

    public String getDateEnsoleillement() {
        return dateEnsoleillement;
    }

    public void setDateEnsoleillement(String dateEnsoleillement) {
        this.dateEnsoleillement = dateEnsoleillement;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<String> getDescription() {
        return biens;
    }

    public void setDescription(ArrayList<String> biens) {
        this.biens = biens;
    }

    @Override
    public boolean equals(Object obj) {

        return Integer.valueOf(this.getId()).equals(((Plant) obj).getId());
    }
}
