package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.controller.ManageStructures;
import it.ispw.efco.nottitranquille.model.DAO.AccessDAO;
import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.model.enumeration.StructureType;
import it.ispw.efco.nottitranquille.view.LocationBean;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.SearchBean;
import it.ispw.efco.nottitranquille.view.StructureBean;
import junitparams.JUnitParamsRunner;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 17/07/2016.
 */
@RunWith(JUnitParamsRunner.class)
public class SearchTest {

    @Test
    public void searchTest() {

        //searchBean initialization
        SearchBean searchBean = new SearchBean();

        searchBean.setCity("");
        searchBean.setNation("");
        searchBean.setCheckin("01-07-2016");
        searchBean.setCheckout("19-07-2016");
        searchBean.setSearch("search");
        searchBean.setMaxtenant("5");
        searchBean.setPricerange("max100");
//        searchBean.setCommodities();
        searchBean.setStructuretype("Hotel");

        Person manager = new Person("Mario", "Rossi", "mario.rossi@gmail.com");
        manager.setUsername("mario");
        manager.setPassword("mario");

        AccessDAO accessDAO = new AccessDAO();
        try {
            accessDAO.register(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Person updatedManager = accessDAO.selectUserByEmail("mario.rossi@gmail.com");

        accessDAO.addManagerRole(updatedManager.getId());

        updatedManager = accessDAO.selectUserByEmail("mario.rossi@gmail.com");

        StructureBean structureBean = new StructureBean();
        structureBean.setCheckIn("01-07-2016");
        structureBean.setCheckOut("02-07-2016");
        structureBean.setCity("Roma");
        structureBean.setNation("Italia");
        structureBean.setOwner(true);
        structureBean.setAddress("");
        structureBean.setPostalcode("");
        structureBean.setType(StructureType.Hotel);

        try {
            ManageStructures.addNewStructure(structureBean, updatedManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocationBean locationBean1 = new LocationBean();

        locationBean1.setDescription("Prima location aggiunta");

        List<Interval> intervalList1 = new ArrayList<Interval>();
        intervalList1.add(new Interval(DateTime.parse("01-07-2016", DateTimeFormat.forPattern("dd-MM-yyyy")), DateTime.parse("30-07-2016", DateTimeFormat.forPattern("dd-MM-yyyy"))));
        locationBean1.setIntervalList(intervalList1);

        locationBean1.setNumberOfBathrooms("1");
        locationBean1.setNumberOfBedrooms("2");
        locationBean1.setMaxGuestsNumber("5");
        locationBean1.setNumberOfBeds("3");
        locationBean1.setNumberOfRooms("5");
        locationBean1.setType("SingleRoom");

        LoginBean loginBean = new LoginBean();
        loginBean.setUsername("mario");
        loginBean.setPassword("mario");
        loginBean.setCookie("XYZ");

        accessDAO.saveLogin(loginBean);
        loginBean = accessDAO.getLoggedUser("mario", "mario");

        List<Structure> managedStructures = ((Manager) loginBean.getUser().getRole("Manager")).getManagedStructures();

        Assert.assertEquals(1, managedStructures.size(), 0);

        Structure structure = managedStructures.get(0);

        locationBean1.setCurrentStructure(structure);
        locationBean1.validate();

        LocationBean locationBean2 = new LocationBean();
        locationBean2.setDescription("Seconda location aggiunta");

        List<Interval> intervalList2 = new ArrayList<Interval>();
        intervalList2.add(new Interval(DateTime.parse("20-07-2016", DateTimeFormat.forPattern("dd-MM-yyyy")), DateTime.parse("20-08-2016", DateTimeFormat.forPattern("dd-MM-yyyy"))));
        locationBean2.setIntervalList(intervalList2);

        locationBean2.setNumberOfBathrooms("1");
        locationBean2.setNumberOfBedrooms("2");
        locationBean2.setMaxGuestsNumber("5");
        locationBean2.setNumberOfBeds("3");
        locationBean2.setNumberOfRooms("5");
        locationBean2.setType("SingleRoom");

        managedStructures = ((Manager) loginBean.getUser().getRole("Manager")).getManagedStructures();
        structure = managedStructures.get(0);

        locationBean2.setCurrentStructure(structure);
        locationBean2.validate();

        managedStructures = ((Manager) loginBean.getUser().getRole("Manager")).getManagedStructures();
        System.out.println(managedStructures.get(0).getLocations().get(0).getDescription());
        System.out.println(managedStructures.get(0).getLocations().get(1).getDescription());

        try {
            searchBean.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, searchBean.getResult().size(), 0); //only one structure
        for (Structure s : searchBean.getResult().keySet()) {
            Assert.assertEquals(1, searchBean.getResult().get(s).size(), 0);
        }
        //only one location for this request

        searchBean = new SearchBean();

        searchBean.setCity("");
        searchBean.setNation("");
        searchBean.setCheckin("21-07-2016");
        searchBean.setCheckout("19-08-2016");
        searchBean.setSearch("advsearch");
        searchBean.setMaxtenant("5");
        searchBean.setPricerange("max100");
//        searchBean.setCommodities();
        searchBean.setStructuretype("Hotel");

        try {
            searchBean.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, searchBean.getResult().size(), 0); //only one structure
        for (Structure s : searchBean.getResult().keySet()) {
            Assert.assertEquals(1, searchBean.getResult().get(s).size(), 0);
        }

        searchBean = new SearchBean();

        searchBean.setCity("");
        searchBean.setNation("");
        searchBean.setCheckin("21-07-2016");
        searchBean.setCheckout("29-07-2016");
        searchBean.setSearch("advsearch");
        searchBean.setMaxtenant("4");
        searchBean.setPricerange("max100");
//        searchBean.setCommodities();
        searchBean.setStructuretype("Hotel");

        try {
            searchBean.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, searchBean.getResult().size(), 0); //only one structure
        for (Structure s : searchBean.getResult().keySet()) {
            Assert.assertEquals(2, searchBean.getResult().get(s).size(), 0);
        }
    }

}
