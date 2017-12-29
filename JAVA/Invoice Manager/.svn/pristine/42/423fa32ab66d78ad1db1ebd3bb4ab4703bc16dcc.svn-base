package ca.ubc.cs.cpsc210.invoicemanager.model;

// Represents a service provided for a customer
public class ServiceRecord extends AbstractServiceRecord {

    // EFFECTS: constructs service record for service of given number of hours and given type
    public ServiceRecord(ServiceType serviceType, int hours) {
        super(hours, serviceType);
        this.recordID = ++nextRecordID;
        buildInvoice();
    }

    // EFFECTS: returns number of service points earned with this service record
    @Override
    public int getServicePoints() {
        int servicePoints = 0;

        switch(serviceType) {
            case REGULAR:
                servicePoints = REG_SERVICEPTS_BASE + hours * REG_SERVICEPTS_HOURLY;
                break;
            case AFTER_HOURS:
                servicePoints = AFTER_HOURS_SERVICEPTS_BASE + hours * AFTER_HOURS_SERVICEPTS_HOURLY;
                break;
            case EMERGENCY:
                servicePoints = EMERG_SERVICEPTS_BASE + hours * EMERG_SERVICEPTS_HOURLY;
                break;
        }

        return servicePoints;
    }

    // EFFECTS: returns callout fee in $ for this service record
    @Override
    public int getCalloutFee() {
        int calloutFee = 0;

        switch(serviceType) {
            case REGULAR:
                calloutFee = REG_CALLOUT;
                break;
            case AFTER_HOURS:
                calloutFee = AFTER_HOURS_CALLOUT;
                break;
            case EMERGENCY:
                calloutFee = EMERG_CALLOUT;
                break;
        }

        return calloutFee;
    }

    // EFFECTS: returns service fee in $ for this service record
    @Override
    public int getServiceFee() {
        int serviceFee = 0;

        switch(serviceType) {
            case REGULAR:
                serviceFee = REG_SERVICE_HOURLY * hours;
                break;
            case AFTER_HOURS:
                serviceFee = AFTER_HOURS_SERVICE_HOURLY * hours;
                break;
            case EMERGENCY:
                serviceFee = EMERG_SERVICE_HOURLY * hours;
                break;
        }

        return serviceFee;
    }

}
