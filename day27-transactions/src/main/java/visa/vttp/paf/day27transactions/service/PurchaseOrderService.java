package visa.vttp.paf.day27transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import visa.vttp.paf.day27transactions.exception.OrderTooLargeException;
import visa.vttp.paf.day27transactions.model.LineItem;
import visa.vttp.paf.day27transactions.model.PurchaseOrder;
import visa.vttp.paf.day27transactions.repo.LineItemRepository;
import visa.vttp.paf.day27transactions.repo.PurchaseOrderRepository;
import visa.vttp.paf.day27transactions.repo.SKURepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository poRepo;

    @Autowired
    private LineItemRepository liRepo;

    @Autowired
    private SKURepository sRepo;

    public String createPurchaseOrder(final PurchaseOrder po) throws OrderTooLargeException {
        final String orderId = poRepo.insertPurchaseOrder(po);
        double totalUnitPrice = 0d;
        for (LineItem li : po.getLineItems()) {

            double unitPrice = sRepo.getUnitPriceBySKU(li.getProdID());
            totalUnitPrice += li.getQuantity() * unitPrice;
            if (totalUnitPrice > 1000d) {
                OrderTooLargeException ex = new OrderTooLargeException("Order %s too large! Price total: %.2f".formatted(orderId, totalUnitPrice));
                ex.printStackTrace();
                throw ex;
            }
        }

        liRepo.addLineItem(orderId, po.getLineItems());

        return orderId;
    }
    
}
