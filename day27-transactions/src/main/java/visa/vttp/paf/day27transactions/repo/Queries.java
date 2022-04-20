package visa.vttp.paf.day27transactions.repo;

public interface Queries {

    public static final String SQL_INSERT_PURCHASE_ORDER = 
    """
    INSERT INTO purchase_order(
        order_id,
        name
    ) values 
        (?,?);
    """;

    public static final String SQL_INSERT_LINE_ITEM =
    """
    INSERT INTO line_item (
        quantity,
        order_id,
        prod_id
    ) values
        (?,?,?);
    """;

    public static final String SQL_PURCHASE_ORDER_TOTAL = 
    "SELECT COUNT(*) FROM purchase_order";
    public static final String SQL_SELECT_UNIT_PRICE_FROM_SKU =
    "SELECT unit_price FROM sku WHERE prod_id = ?;";
    
}
