import java.io.Serializable;

/**
 * This class is the implementation of a sopose product
 * completing the exercise 1 for the assigment
 * 
 * @author Radwan
 *
 */
public class ItemProduct implements Serializable{
    
    // A unique ID for this product
    private int itemProductId = 0;

    //Product name space
    private String name = null;

    //Product price
    private float price = 0;

    /**
     * Sets a new integer as an id for the product
     * 
     * @param pItemProductId new integer id to be assing to the product
     */
    public void setItemProductId(int pItemProductId){
        this.itemProductId = pItemProductId;
    }

    /**
     * Gets the id of the product as integer
     * 
     * @return integer of product's ID
     */
    public int getItemProductId(){
        return this.itemProductId;
    }

    /**
     * Sets a string as the name for this product
     * 
     * @param pName new string to be assign as product name
     */
    public void setName(String pName){
        this.name = pName;
    }

    /**
     * Gets the name of the product as string
     * 
     * @return string of product's NAME
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets a float as the price for this product
     * 
     * @param pPrice new float to be assign as product price
     */
    public void setPrice(float pPrice){
        this.price = pPrice;
    }

    /**
     * Gets the price of the product as float
     * @return float of product's PRICE
     */
    public float getPrice(){
        return this.price;
    }
}