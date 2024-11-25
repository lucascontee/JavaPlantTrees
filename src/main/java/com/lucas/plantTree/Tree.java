package com.lucas.plantTree;

public class Tree {

    private int id;
    private String treeName;
    private int idPlanter;
    private String plantationCity;
    private int amountPlanted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public int getIdPlanter() {
        return idPlanter;
    }

    public void setIdPlanter(int idPlanter) {
        this.idPlanter = idPlanter;
    }

    public String getPlantationCity() {
        return plantationCity;
    }

    public void setPlantationCity(String plantationCity) {
        this.plantationCity = plantationCity;
    }

    public int getAmountPlanted() {
        return amountPlanted;
    }

    public void setAmountPlanted(int amountPlanted) {
        this.amountPlanted = amountPlanted;
    }
}
