package visa.vttp.paf.day27transactions.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static visa.vttp.paf.day27transactions.repo.Queries.SQL_SELECT_UNIT_PRICE_FROM_SKU;

@Repository
public class SKURepository {

    @Autowired
    private JdbcTemplate jt;

    public float getUnitPriceBySKU(String sku) {
        final SqlRowSet rs = jt.queryForRowSet(SQL_SELECT_UNIT_PRICE_FROM_SKU, sku);
        if (!rs.next()) {
            DataAccessException ex = new DataAccessException("SKU does not exist") {};
            ex.printStackTrace();
            throw ex;
        }
        return rs.getFloat("unit_price");
    }
}
