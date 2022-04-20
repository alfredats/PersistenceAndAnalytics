package visa.vttp.paf.day27transactions.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import visa.vttp.paf.day27transactions.model.PurchaseOrder;

import static visa.vttp.paf.day27transactions.repo.Queries.*;

import java.sql.PreparedStatement;
import java.sql.Statement;


@Repository
public class PurchaseOrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderRepository.class);

    @Autowired
    private JdbcTemplate jt;

    public String insertPurchaseOrder(final PurchaseOrder po) {

        jt.update(conn -> {
           PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PURCHASE_ORDER,
            Statement.NO_GENERATED_KEYS);
            ps.setString(1, po.getOrderID());
            ps.setString(2, po.getName());
            return ps;
        });

        logger.info(">>> Purchase order inserted: " + po.getOrderID());
        return po.getOrderID();
    }
    
}
