package visa.vttp.paf.day27transactions.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;

public class LineItem {
    private String orderID;
    private String prodID;
    private Integer quantity;

    public static LineItem create(String orderID, String jsonStr) throws JsonException {
        return create(orderID, Json.createReader(new StringReader(jsonStr)).readObject()); 
    }
    public static LineItem create(String orderID, JsonObject json) throws JsonException{
        LineItem li = new LineItem();
        li.setOrderID(orderID);
        li.setQuantity(json.getInt("quantity"));
        li.setProdID(json.getString("prodID"));
        return li;
    }

    /**
     * @return String return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return String return the prodID
     */
    public String getProdID() {
        return prodID;
    }

    /**
     * @param prodID the prodID to set
     */
    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    /**
     * @return Integer return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
