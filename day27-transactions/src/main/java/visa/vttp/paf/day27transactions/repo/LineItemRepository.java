package visa.vttp.paf.day27transactions.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import visa.vttp.paf.day27transactions.model.LineItem;

import static visa.vttp.paf.day27transactions.repo.Queries.*;

import java.util.Collection;
import java.util.List;

@Repository
public class LineItemRepository {

    @Autowired
    private JdbcTemplate jt;
    
    public int[] addLineItem(String orderID, Collection<LineItem> lineItems) {
        List<Object[]> params = lineItems.stream()
            .map(v -> {
                Object[] row = new Object[3];
                row[0] = v.getQuantity();
                row[1] = orderID;
                row[2] = v.getProdID();
                return row;
            })
            .toList();
        return jt.batchUpdate(SQL_INSERT_LINE_ITEM, params);
    }
}
