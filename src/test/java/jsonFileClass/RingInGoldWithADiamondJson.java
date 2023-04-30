package jsonFileClass;

import java.util.List;

public class RingInGoldWithADiamondJson {
public String name;
public Integer productArticle;
public String material;
public String weightInGrams;
public List<String> sizeInMillimeters;
public Boolean available;
public FullDescription fullDescription;

public static class FullDescription {
    public String brand;
    public List<String> paymentTypes;
    public String assurance;
}

}
