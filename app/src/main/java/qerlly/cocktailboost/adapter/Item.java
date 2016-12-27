package qerlly.cocktailboost.adapter;

import java.util.ArrayList;
import java.util.List;

import qerlly.cocktailboost.R;

public class Item {

    private int id;
    private String image;
    private String name;
    private String details;
    private String recipe;

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        id = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String pImage) {
        image = pImage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String pDetails) {
        details = pDetails;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String pRecipe) {
        recipe = pRecipe;
    }

}
