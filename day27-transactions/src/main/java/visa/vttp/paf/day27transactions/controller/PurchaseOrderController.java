package visa.vttp.paf.day27transactions.controller;

import java.io.StringReader;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import visa.vttp.paf.day27transactions.exception.OrderTooLargeException;
import visa.vttp.paf.day27transactions.model.PurchaseOrder;
import visa.vttp.paf.day27transactions.service.PurchaseOrderService;

@RestController
@RequestMapping("")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService poSvc;

    @PostMapping(path="/order", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> submitPurchaseOrder(@RequestBody String jsonStr) throws OrderTooLargeException {
        PurchaseOrder po = PurchaseOrder.create(jsonStr);
        String orderID = poSvc.createPurchaseOrder(po);

        JsonObjectBuilder respBuilder = Json.createObjectBuilder().add("orderID", orderID);

        return ResponseEntity.ok().body(respBuilder.build().toString());
    }


    
}
