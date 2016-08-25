package org.openstack4j.api.trove;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.trove.Flavor;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * Created by sumigand on 8/22/2016.
 */

@Test(suiteName="trove/flavors")
public class DBFlavorServiceImplTest extends AbstractTest {

    private static final String DATABASE_INSTANCE_FLAVORS = "/trove/instance_flavors.json";
    private static final String DATABASE_INSTANCE_FLAVOR = "/trove/instance_flavor.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListDatabaseInstanceFlavors() throws Exception{
        respondWith(DATABASE_INSTANCE_FLAVORS);
        List<? extends Flavor> flavors = osv2().trove().flavorService().list();
        assertEquals(2, flavors.size());
        Preconditions.checkNotNull(flavors.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Flavor from List : "+flavors.get(0));
    }

    @Test
    public void testGetFlavor() throws Exception{
        respondWith(DATABASE_INSTANCE_FLAVOR);
        String id = "1";
        String name = "m1.tiny";
        Flavor flavor = osv2().trove().flavorService().get(id);
        Preconditions.checkNotNull(flavor);
        assertEquals(flavor.getId(), id);
        assertEquals(flavor.getName(), name);
        assertEquals(flavor.getRam(), 512);
    }


}
