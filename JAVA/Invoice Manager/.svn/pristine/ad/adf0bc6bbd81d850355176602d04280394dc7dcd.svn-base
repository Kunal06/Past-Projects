package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by User on 3/6/16.
 */
public class AfterHoursServiceRecord extends AbstractServiceRecord {

    public static int AFTER_HOURS_CALLOUT = 120;
    public static int AFTER_HOURS_SERVICE_HOURLY = 100;
    public static int AFTER_HOURS_SERVICEPTS_BASE = 5;
    public static int AFTER_HOURS_SERVICEPTS_HOURLY = 1;

    public AfterHoursServiceRecord(int hours) {
        super(hours,ServiceType.AFTER_HOURS);
        this.recordID = ++nextRecordID;
        buildInvoice();
    }

    @Override
    public int getServicePoints() {
        int servicePoints=0;
        servicePoints = AfterHoursServiceRecord.AFTER_HOURS_SERVICEPTS_BASE + hours * AfterHoursServiceRecord.AFTER_HOURS_SERVICEPTS_HOURLY;
        return servicePoints;
    }

    @Override
    public int getCalloutFee() {
        int calloutFee = 0;
        calloutFee = AfterHoursServiceRecord.AFTER_HOURS_CALLOUT;
        return calloutFee;
    }

    @Override
    public int getServiceFee() {
        int serviceFee = 0;
        serviceFee = AfterHoursServiceRecord.AFTER_HOURS_SERVICE_HOURLY * hours;
        return serviceFee;
    }
}
