package ca.ubc.cs.cpsc210.resourcefinder.tests.model;

import ca.ubc.cs.cpsc210.resourcefinder.model.Resource;
import ca.ubc.cs.cpsc210.resourcefinder.model.ResourceRegistry;
import ca.ubc.cs.cpsc210.resourcefinder.model.SelectionState;
import ca.ubc.cs.cpsc210.resourcefinder.model.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

// unit tests for SelectionState class
public class SelectionStateTest {
    private SelectionState testSelectionState;
    private ResourceRegistry registry;
    private boolean isAnySelected;
    private Resource r1;
    private Resource r2;
    private Resource r3;
    private Resource r4;

    @Before
    public void runBefore() {
        registry = new ResourceRegistry();
        loadResources();

        testSelectionState = new SelectionState(registry);
    }


    @Test
    public void testgetResourcesWselectedservices() {
        r1.addService(Service.SENIOR);
        r1.addService(Service.FOOD);

        r2.addService(Service.COUNSELLING);

        r3.addService(Service.YOUTH);

        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r1);
        registry1.add(r2);
        registry1.add(r3);
        registry1.add(r4);

        registry.addResource(r1);
        registry.addResource(r2);
        registry.addResource(r3);
        registry.addResource(r4);


        assertEquals(testSelectionState.getResourcesWithSelectedServices(), registry1);
    }

    @Test
    public void testgetResourcesWselectedservices1() {
        Set<Service> requestedservices = new HashSet<Service>();
        requestedservices.add(Service.FOOD);
        requestedservices.add(Service.SHELTER);
        requestedservices.add(Service.COUNSELLING);

        testSelectionState.setSelectedServices(requestedservices);


        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r1);
        registry1.add(r2);
        registry1.add(r3);
        registry1.add(r4);

        testSelectionState.setSelectAny();
        assertEquals(testSelectionState.getResourcesWithSelectedServices(), registry1);
    }

    @Test
    public void testgetResourcesWselectedservices2() {
        Set<Service> requestedservices = new HashSet<Service>();
        requestedservices.add(Service.SENIOR);
        requestedservices.add(Service.COUNSELLING);

        testSelectionState.setSelectedServices(requestedservices);

        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r3);
        testSelectionState.setSelectAll();
        assertEquals(testSelectionState.getResourcesWithSelectedServices(), registry1);
    }

    @Test
    public void testrSetSelectedervice() {
        Set<Service> requestedservices = new HashSet<Service>();
        requestedservices.add(Service.FOOD);

        testSelectionState.setSelectedServices(requestedservices);
        testSelectionState.selectService(Service.SHELTER);
        testSelectionState.selectService(Service.LEGAL);
        testSelectionState.deselectService(Service.LEGAL);

        Set<Resource> registry1 = new HashSet<Resource>();
        registry1.add(r1);
        registry1.add(r4);

        testSelectionState.setSelectAll();

        assertEquals(testSelectionState.getResourcesWithSelectedServices(), registry1);

    }

    @Test
    public void testDeselectService() {
        // template for unit tests
    }

    @Test
    public void testXXXXXX() {
        // template for unit tests
    }



    // MODIFIES: this
    // EFFECTS:  adds services to resources and resources to resource registry
    private void loadResources() {
        r1 = new Resource("Res 1", null);
        r2 = new Resource("Res 2", null);
        r3 = new Resource("Res 3", null);
        r4 = new Resource("Res 4", null);

        r1.addService(Service.FOOD);
        r1.addService(Service.SHELTER);
        r2.addService(Service.YOUTH);
        r2.addService(Service.FOOD);
        r3.addService(Service.SENIOR);
        r3.addService(Service.COUNSELLING);
        r4.addService(Service.SHELTER);
        r4.addService(Service.FOOD);
        r4.addService(Service.LEGAL);

        registry.addResource(r1);
        registry.addResource(r2);
        registry.addResource(r3);
        registry.addResource(r4);
    }
}