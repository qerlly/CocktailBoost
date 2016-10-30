package qerlly.cocktailboost;

import java.util.ArrayList;
import java.util.List;

public class Cocktails {

    private String image;
    private String name;
    private String details;

    public Cocktails(String image, String details, String name) {
        this.image = image;
        this.details = details;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public static List<Cocktails> getItems(){
        ArrayList<Cocktails> itemsList = new ArrayList<>();

        return  itemsList;
    }
}
