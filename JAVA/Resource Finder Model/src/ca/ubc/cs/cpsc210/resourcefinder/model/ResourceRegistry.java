package ca.ubc.cs.cpsc210.resourcefinder.model;


import java.util.*;

// Registry of available resources
public class ResourceRegistry {
    private List<Resource> resources;

    // EFFECTS: constructs empty resource registry
    public ResourceRegistry() {
        resources = new LinkedList<>();

        // stub
    }

    // MODIFIES: this
    // EFFECTS: add resource to registry, maintains resources in the order added to registry
    public void addResource(Resource resource) {
        resources.add(resource);
        // stub
    }

    // EFFECTS: returns resources in registry as unmodifiable list (in order that they were added
    // to registry)
    public List<Resource> getResources() {
        List<Resource> res= Collections.unmodifiableList(resources);
        return res;  // stub
    }

    // EFFECTS: returns set of resources in registry that offer the given service
    public Set<Resource> getResourcesOfferingService(Service service) {
        Set<Resource> Present = new HashSet<Resource>();

        for (Resource in : resources)
        {
            if (in.offersService(service))
            {
                //int loc= resources.lastIndexOf(in);
                Present.add(in);
            }
        }
        if (Present.size()>0)
        {
            return Present;
        }
        else {
            return Collections.emptySet();
        }
    }

    // EFFECTS: returns set of resources in registry that offer all the services in requestedServices set
    public Set<Resource> getResourcesOfferingAllServicesInSet(Set<Service> requestedServices)
    {
    Set<Resource> Present = new HashSet<Resource>();

    for (Resource in : resources)
    {
        if (in.offersAllServicesInSet(requestedServices))
        {
            //int loc= resources.lastIndexOf(in);
            Present.add(in);
        }
    }
    if (Present.size()>0)
    {
        return Present;
    }
    else {
        return Collections.emptySet();
    }
    }

    // EFFECTS: returns set of resources in registry that off any of the services in requestedServices set
    public Set<Resource> getResourcesOfferingAnyServicesInSet(Set<Service> requestedServices) {
        Set<Resource> Present = new HashSet<Resource>();

        for (Service inn : requestedServices) {
            for (Resource in : resources) {
                if (in.offersService(inn)) {
                    //int loc= resources.lastIndexOf(in);
                    Present.add(in);
                }
            }
        }
            if (Present.size() > 0) {
                return Present;
            } else {
                return Collections.emptySet();
            }
        }

}
