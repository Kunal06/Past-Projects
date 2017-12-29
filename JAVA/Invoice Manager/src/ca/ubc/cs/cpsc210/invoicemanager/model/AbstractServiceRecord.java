package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by User on 3/5/16.
 */
public abstract class AbstractServiceRecord implements Comparable<AbstractServiceRecord> {
    protected static int nextRecordID = 0;
    protected ServiceType serviceType;
    protected int hours;
    protected int recordID;
    private Invoice invoice;

    public AbstractServiceRecord(int hours, ServiceType serviceType) {
        this.hours = hours;
        this.serviceType = serviceType;
    }

    public static AbstractServiceRecord createServiceRecord(ServiceType serviceType, int hours) {

        switch(serviceType) {
            case REGULAR:
                RegularServiceRecord regular = new RegularServiceRecord(hours);
                return regular;
            case AFTER_HOURS:
                AfterHoursServiceRecord after = new AfterHoursServiceRecord(hours);
                return after;
            case EMERGENCY:
                EmergencyServiceRecord emergency = new EmergencyServiceRecord(hours);
                return emergency;
            case DISCOUNT:
                DiscountServiceRecord discount = new DiscountServiceRecord(hours);
                return discount;
        }

        return null;  // shouldn't get here
    }

    public int getRecordID() {
        return recordID;
    }

    public int getHours() {
        return hours;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    // EFFECTS: returns number of service points earned with this service record
    public abstract int getServicePoints();

    // EFFECTS: returns callout fee in $ for this service record
    public abstract int getCalloutFee();

    // EFFECTS: returns service fee in $ for this service record
    public abstract int getServiceFee();

    @Override
    // NOTE: this class has a natural ordering that is inconsistent with equals()
    public int compareTo(AbstractServiceRecord other) {
        return recordID - other.recordID;
    }

    // MODIFIES: this
    // EFFECTS:  create invoice for this service record
    protected void buildInvoice() {
        invoice = new Invoice(recordID, getCalloutFee(), getServiceFee(), hours);
    }


}
