package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by User on 3/5/16.
 */
public class RegularServiceRecord extends AbstractServiceRecord {
    public static int REG_CALLOUT = 80;
    public static int REG_SERVICE_HOURLY = 80;
    public static int REG_SERVICEPTS_BASE = 10;
    public static int REG_SERVICEPTS_HOURLY = 2;

    public RegularServiceRecord(int hours) {
        super(hours,ServiceType.REGULAR);
        this.recordID = ++nextRecordID;
        buildInvoice();
    }

    @Override
    public int getServiceFee() {
        int serviceFee = 0;
        serviceFee = REG_SERVICE_HOURLY * hours;
        return serviceFee;
    }

    @Override
    public int getCalloutFee() {

        int calloutFee = 0;
        calloutFee = REG_CALLOUT;
        return calloutFee;
    }

    @Override
    public int getServicePoints() {
        int servicePoints = 0;
        servicePoints = REG_SERVICEPTS_BASE + hours * REG_SERVICEPTS_HOURLY;
        return servicePoints;
    }
}
