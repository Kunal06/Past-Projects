package ca.ubc.cs.cpsc210.resourcefinder.tests.model;

import ca.ubc.cs.cpsc210.resourcefinder.model.Resource;
import ca.ubc.cs.cpsc210.resourcefinder.model.ResourceRegistry;
import ca.ubc.cs.cpsc210.resourcefinder.model.SelectionState;
import ca.ubc.cs.cpsc210.resourcefinder.model.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

// unit tests for ResourceRegistry class
public class ResourceRegistryTest {
    private ResourceRegistry testRegistry;
    private Resource r1;
    private Resource r2;
    private Resource r3;
    private Resource r4;

    @Before
    public void runBefore() {
        testRegistry = new ResourceRegistry();
        r1 = new Resource("Res 1", null);
        r2 = new Resource("Res 2", null);
        r3 = new Resource("Res 3", null);
        r4 = new Resource("Res 4", null);
    }


    @Test
    public void testGetResourcesOfferingService() {
        r1.addService(Service.COUNSELLING);
        testRegistry.addResource(r1);
        testRegistry.addResource(r2);

        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r1);

        assertEquals(testRegistry.getResourcesOfferingService(Service.COUNSELLING), registry1);
    }

    @Test
    public void testgetResourcesOfferingAllServicesInSet() {
        r1.addService(Service.SENIOR);
        r1.addService(Service.SHELTER);
        r1.addService(Service.LEGAL);

        r2.addService(Service.COUNSELLING);

        testRegistry.addResource(r1);
        testRegistry.addResource(r2);

        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r1);

        Set<Service> requestedservices = new HashSet<Service>();
        requestedservices.add(Service.SENIOR);
        requestedservices.add(Service.SHELTER);
        requestedservices.add(Service.LEGAL);

        assertEquals(testRegistry.getResourcesOfferingAllServicesInSet(requestedservices), registry1);
    }

    @Test
    public void testgetResourcesOfferingAnyServicesInSet() {
        Set<Service> requestedservices = new HashSet<Service>();
        requestedservices.add(Service.SENIOR);
        requestedservices.add(Service.SHELTER);
        requestedservices.add(Service.LEGAL);

        r1.addService(Service.SENIOR);
        r1.addService(Service.FOOD);
        r2.addService(Service.LEGAL);
        r3.addService(Service.YOUTH);

        testRegistry.addResource(r1);
        testRegistry.addResource(r2);
        testRegistry.addResource(r3);

        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r1);
        registry1.add(r2);

        assertEquals(testRegistry.getResourcesOfferingAnyServicesInSet(requestedservices), registry1);
    }

    @Test
    public void testGetResources() {
        testRegistry.addResource(r1);
        List<Resource> registry1 = new LinkedList<>();
        registry1.add(r1);


        assertEquals(testRegistry.getResources(), registry1);

    }

    @Test
    public void testempty() {
        r1.addService(Service.YOUTH);
        testRegistry.addResource(r1);

        assertEquals(testRegistry.getResourcesOfferingService(Service.COUNSELLING), Collections.emptySet());
    }

    @Test
    public void testempty1() {
        r1.addService(Service.SENIOR);
        r1.addService(Service.SHELTER);
        r2.addService(Service.COUNSELLING);

        testRegistry.addResource(r1);
        testRegistry.addResource(r2);

        Set<Service> requestedservices = new HashSet<Service>();
        requestedservices.add(Service.SENIOR);
        requestedservices.add(Service.SHELTER);
        requestedservices.add(Service.LEGAL);

        assertEquals(testRegistry.getResourcesOfferingAllServicesInSet(requestedservices), Collections.emptySet());
    }

    @Test
    public void testempty2() {
        Set<Service> requestedservices = new HashSet<Service>();

        r1.addService(Service.SENIOR);
        r1.addService(Service.FOOD);
        r2.addService(Service.LEGAL);
        r3.addService(Service.YOUTH);

        testRegistry.addResource(r1);
        testRegistry.addResource(r2);
        testRegistry.addResource(r3);

        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r1);
        registry1.add(r2);

        assertEquals(testRegistry.getResourcesOfferingAnyServicesInSet(requestedservices), Collections.emptySet());
    }


    // MODIFIES: this
    // EFFECTS:  adds services to resources and resources to registry
    private void loadResources() {
        r1.addService(Service.FOOD);
        r1.addService(Service.SHELTER);
        r2.addService(Service.YOUTH);
        r2.addService(Service.FOOD);
        r3.addService(Service.SENIOR);
        r4.addService(Service.SHELTER);
        r4.addService(Service.FOOD);
        r4.addService(Service.LEGAL);

        testRegistry.addResource(r1);
        testRegistry.addResource(r2);
        testRegistry.addResource(r3);
        testRegistry.addResource(r4);
    }
}