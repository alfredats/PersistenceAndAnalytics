package visa.vttp.paf.day27transactions.exception;

import visa.vttp.paf.day27transactions.model.PurchaseOrder;

public class OrderTooLargeException extends Exception{
    private PurchaseOrder po;

    public OrderTooLargeException() { super(); }
    public OrderTooLargeException(String msg) { super(msg); }
    

    /**
     * @return PurchaseOrder return the po
     */
    public PurchaseOrder getPo() {
        return po;
    }

    /**
     * @param po the po to set
     */
    public void setPo(PurchaseOrder po) {
        this.po = po;
    }

}
