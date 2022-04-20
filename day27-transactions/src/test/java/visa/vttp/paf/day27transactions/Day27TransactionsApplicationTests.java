package visa.vttp.paf.day27transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import visa.vttp.paf.day27transactions.exception.OrderTooLargeException;
import visa.vttp.paf.day27transactions.model.LineItem;
import visa.vttp.paf.day27transactions.model.PurchaseOrder;
import visa.vttp.paf.day27transactions.repo.LineItemRepository;
import visa.vttp.paf.day27transactions.repo.PurchaseOrderRepository;
import visa.vttp.paf.day27transactions.repo.SKURepository;
import visa.vttp.paf.day27transactions.service.PurchaseOrderService;

@SpringBootTest
class Day27TransactionsApplicationTests {

	@Autowired
	private PurchaseOrderService poSvc;

	@MockBean
	private PurchaseOrderRepository poRepo;

	@MockBean
	private LineItemRepository liRepo;

	@MockBean
	private SKURepository sRepo;

	private String orderId = "1234";
	private LineItem li = new LineItem();
	private PurchaseOrder po = new PurchaseOrder();

	@PostConstruct 
	void setupMocks() {
		this.li.setOrderID(this.orderId);
		this.li.setProdID("-1");
		this.li.setQuantity(1);

		List<LineItem> items = Arrays.asList(li);
		this.po.setLineItems(items);
		this.po.setOrderID(orderId);
		this.po.setName("john");

		Mockito.when(sRepo.getUnitPriceBySKU("-1")).thenReturn(1f);
		Mockito.when(poRepo.insertPurchaseOrder(this.po)).thenReturn(this.orderId);
		Mockito.when(liRepo.addLineItem(this.orderId, items)).thenReturn(new int[0]);
	}

	@Test
	void contextLoads() {
	}
	 
	@Test 
	void shouldThrowOrderTooLargeException() {
		this.li.setQuantity(10000);

		try {
			poSvc.createPurchaseOrder(po);
		} catch (Exception ex) {
			assertTrue(true, "OrderTooLargeException thrown successfully");
			return;
		}
		assertTrue(false, "OrderTooLargeException not thrown");
	}

	@Test
	void shouldCreatePurchaseOrder() {
		this.li.setQuantity(1);
		try {
			assertEquals(poSvc.createPurchaseOrder(this.po), "1234");
		} catch (OrderTooLargeException ex) {
			assertTrue(false);
		}
	}

}
