package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by User on 3/6/16.
 */
public class DiscountServiceRecord extends AbstractServiceRecord {
    public static int DISCOUNT_SERVICE_HOURLY = 80;

    public DiscountServiceRecord(int hours) {
        super(hours, ServiceType.DISCOUNT);
        this.recordID = ++nextRecordID;
        buildInvoice();
    }

    @Override
    public int getServicePoints() {
        return 0;
    }

    @Override
    public int getCalloutFee() {
        return 0;
    }

    @Override
    public int getServiceFee() {
        int serviceFee = 0;
        serviceFee = DiscountServiceRecord.DISCOUNT_SERVICE_HOURLY * hours;
        return serviceFee;
    }
}
