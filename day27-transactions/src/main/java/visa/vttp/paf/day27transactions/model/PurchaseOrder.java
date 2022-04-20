package visa.vttp.paf.day27transactions.model;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;

public class PurchaseOrder {
    private String orderID;
    private String name;
    private List<LineItem> lineItems = new LinkedList<>();

    private static Logger logger = LoggerFactory.getLogger(PurchaseOrder.class);

    public static PurchaseOrder create(String jsonStr) throws JsonException {
        return create(Json.createReader(new StringReader(jsonStr)).readObject());
    }

    public static PurchaseOrder create(JsonObject json) throws JsonException {
        PurchaseOrder po = new PurchaseOrder();
        try {
            String uuid = UUID.randomUUID().toString().substring(0,8);
            logger.info(uuid);
            po.setOrderID(uuid);
            po.setName(json.getString("name"));

            json.getJsonArray("lineItems")
                .stream()
                .filter(v -> v != null)
                .map(v -> v.asJsonObject())
                .forEach((JsonObject v) -> {
                    LineItem item = LineItem.create(uuid, v);
                    po.addLineItem(item);
                });
        } catch (Exception e) { e.printStackTrace(); }
        return po;
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
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return List<LineItem> return the lineItems
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * @param lineItems the lineItems to set
     */
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public boolean addLineItem(LineItem i) {
        return this.lineItems.add(i);
    }

}
