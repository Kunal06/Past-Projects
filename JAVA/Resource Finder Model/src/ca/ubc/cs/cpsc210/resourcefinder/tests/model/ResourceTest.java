package ca.ubc.cs.cpsc210.resourcefinder.tests.model;

import ca.ubc.cs.cpsc210.resourcefinder.model.ContactInfo;
import ca.ubc.cs.cpsc210.resourcefinder.model.Resource;
import ca.ubc.cs.cpsc210.resourcefinder.model.ResourceRegistry;
import ca.ubc.cs.cpsc210.resourcefinder.model.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

// unit tests for Resource class
public class ResourceTest {
    private Resource testResource;

    @Before
    public void runBefore() {
        testResource = new Resource("Family Services", null);
    }

    @Test
    public void testOffersService() {
        assertFalse(testResource.offersService(Service.COUNSELLING));
        testResource.addService(Service.COUNSELLING);
        assertTrue(testResource.offersService(Service.COUNSELLING));
    }

    @Test
    public void testOffersAllServicesinSetOne() {
        Set<Service> RequestedServices = new HashSet<>();

        RequestedServices.add(Service.LEGAL);
        testResource.addService(Service.LEGAL);

        assertTrue(testResource.offersAllServicesInSet(RequestedServices));
    }

    @Test
    public void testOffersAllServicesinSetMany() {
        Set<Service> RequestedServices = new HashSet<>();

        RequestedServices.add(Service.LEGAL);
        RequestedServices.add(Service.SHELTER);
        RequestedServices.add(Service.SENIOR);


        testResource.addService(Service.LEGAL);
        testResource.addService(Service.SHELTER);
        testResource.addService(Service.SENIOR);
        assertTrue(testResource.offersAllServicesInSet(RequestedServices));
    }

    @Test
    public void testOffersAllServicesinSetEmpty() {
        Set<Service> RequestedServices = new HashSet<>();
        RequestedServices.isEmpty();

        assertTrue(testResource.offersAllServicesInSet(RequestedServices));
    }

    @Test
    public void testOffersAllServicesinSetEmpty1() {
        Set<Service> RequestedServices = new HashSet<>();
        RequestedServices.add(Service.LEGAL);

        assertFalse(testResource.offersAllServicesInSet(RequestedServices));
    }

    @Test
    public void testOffersAllServicesinSetEmpty2() {
        Set<Service> RequestedServices = new HashSet<>();
        testResource.addService(Service.LEGAL);

        assertTrue(testResource.offersAllServicesInSet(RequestedServices));
    }

    @Test
    public void testOffersAllServicesinSet() {
        Set<Service> RequestedServices = new HashSet<>();
        RequestedServices.add(Service.LEGAL);

        testResource.addService(Service.SHELTER);
        testResource.addService(Service.SENIOR);
        assertFalse(testResource.offersAllServicesInSet(RequestedServices));
    }


    @Test
    public void testOffersAnyServicesinSet() {
        Set<Service> RequestedServices = new HashSet<>();

        RequestedServices.add(Service.LEGAL);

        testResource.addService(Service.LEGAL);
        testResource.addService(Service.SHELTER);
        testResource.addService(Service.SENIOR);

        assertTrue(testResource.offersAnyServicesInSet(RequestedServices));
    }

    @Test
    public void testOffersAnyServicesinSet2() {
        Set<Service> RequestedServices = new HashSet<>();

        RequestedServices.add(Service.LEGAL);

        testResource.addService(Service.SHELTER);
        testResource.addService(Service.SENIOR);

        assertFalse(testResource.offersAnyServicesInSet(RequestedServices));
    }

    @Test
    public void testOffersAnyServicesinSet3() {
        Set<Service> RequestedServices = new HashSet<>();

        testResource.addService(Service.SHELTER);
        testResource.addService(Service.SENIOR);

        assertFalse(testResource.offersAnyServicesInSet(RequestedServices));
    }

    @Test
    public void testAddService() {
        Set<Service> RequestedServices = new HashSet<>();
        RequestedServices.add(Service.SHELTER);
        testResource.addService(Service.SHELTER);

        assertEquals(testResource.getServices(), RequestedServices);
    }

    @Test
    public void testRemoveService() {
        Set<Service> RequestedServices = new HashSet<>();
        RequestedServices.add(Service.SENIOR);


        testResource.addService(Service.SHELTER);
        testResource.addService(Service.SENIOR);
        testResource.removeService(Service.SHELTER);

        assertEquals(testResource.getServices(), RequestedServices);

    }

    @Test
    public void testGetName() {
        assertEquals(testResource.getName(), "Family Services");
    }

    @Test
    public void testGetContactInfo() {
        assertNull(testResource.getContactInfo());
    }
    

    }
