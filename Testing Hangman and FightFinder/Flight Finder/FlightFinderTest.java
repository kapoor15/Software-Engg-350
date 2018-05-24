package edu.upenn.cis350.hwk5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlightFinderTest {
	
	private FlightFinder finder;

    @Before
    public void testSetup(){
        finder = new FlightFinder();
    }
    
    @Test
	public void testDirectOnlyTrueAddedToDirectList() {
		assertEquals(1, finder.findFlights(true, "sFo", "dFw", 999));
		assertTrue(finder.getDirectFlights().contains(new Flight("SFO", "DFW", 150, 220)));
	}
	
	@Test
	public void testDirectOnlyTrueNotAddedToIndirectList() {
		assertEquals(1, finder.findFlights(true, "sFo", "dFw", 999));
		assertTrue(finder.getIndirectFlights().isEmpty());
	}

	@Test
	public void testIllegalHome() {
		assertEquals(-1, finder.findFlights(true, "XXX", "PHL", 900));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testIllegalDest() {
		assertEquals(-1, finder.findFlights(true, "PHL", "XXX", 900));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testIllegalDestAndHome() {
		assertEquals(-1, finder.findFlights(true, "XXX", "XXX", 900));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testIllegalTime() {
		assertEquals(-1, finder.findFlights(true, "PHL", "BOS", -1));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyTrueFlightAvailable() {
		assertEquals(1, finder.findFlights(true, "SFO", "DET", 900));
		assertTrue(finder.getDirectFlights().contains(new Flight("SFO", "DET", 180, 305)));
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyTrueFlightNotAvailableForLessTime() {
		assertEquals(0, finder.findFlights(true, "SFO", "DET", 5));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyTrueFlightNotAvailable() {
		assertEquals(0, finder.findFlights(true, "SFO", "PHL", 999));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testFlightsCaseSensitivity() {
		assertEquals(1, finder.findFlights(true, "sFo", "dFw", 999));
		assertTrue(finder.getDirectFlights().contains(new Flight("SFO", "DFW", 150, 220)));
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	
	@Test
	public void testDirectOnlyTrueWhenHomeAndDestAreEqual() {
		assertEquals(1, finder.findFlights(true, "sfo", "sfo", 999));
		assertTrue(finder.getDirectFlights().contains(new Flight("SFO", "DFW", 150, 220)));
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyFalseSingleFlightAvailable() {
		assertEquals(1, finder.findFlights(false, "ORD", "IAD", 9999));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("ORD", "PHL", 100, 285), 
				new Flight("PHL", "IAD", 60, 185)}));
	}
	
	@Test
	public void testDirectOnlyFalseMultipleFlightsAvailable() {
		assertEquals(2, finder.findFlights(false, "JFK", "BOS", 9999));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("JFK", "LHR", 400, 605), 
				new Flight("LHR", "BOS", 345, 580)}));
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("JFK", "PHL", 70, 110), 
				new Flight("PHL", "BOS", 95, 185)}));
	}
	
	@Test
	public void testDirectOnlyFalseFlightsNotAvailable() {
		assertEquals(0, finder.findFlights(false, "ORD", "LHR", 9999));
		assertTrue(finder.getIndirectFlights().isEmpty());
		assertTrue(finder.getDirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyFalseAddedToDirectListOnly() {
		assertEquals(1, finder.findFlights(false, "SEA", "ORD", 9999));
		assertTrue(finder.getIndirectFlights().isEmpty());
		assertTrue(finder.getDirectFlights().contains(new Flight("SEA", "ORD", 225, 300)));
	}
	
	@Test
	public void testDirectOnlyFalseAddedToIndirectListOnly() {
		assertEquals(1, finder.findFlights(false, "ORD", "IAD", 9999));
		assertTrue(finder.getDirectFlights().isEmpty());
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("ORD", "PHL", 100, 285), 
				new Flight("PHL", "IAD", 60, 185)}));
	}
	
	@Test
	public void testDirectOnlyFalseDirectAndIndirectFlightsAddedToLists() {
		assertEquals(4, finder.findFlights(false, "ATL", "CDG", 9999));
		assertTrue(finder.getDirectFlights().contains(new Flight("ATL", "CDG", 580, 440)));
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("ATL", "PHL", 120, 400), 
				new Flight("PHL", "CDG", 605, 535)}));
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("ATL", "BOS", 140, 220), 
				new Flight("BOS", "PHL", 95, 205), new Flight("PHL", "CDG", 605, 535)}));
	}
	
	@Test
	public void testDirectOnlyFalseMoreThan2Indirect() {
		assertEquals(4, finder.findFlights(false, "ATL", "CDG", 9999));
		assertTrue(finder.getDirectFlights().contains(new Flight("ATL", "CDG", 580, 440)));
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("ATL", "PHL", 120, 400), 
				new Flight("PHL", "CDG", 605, 535)}));
		assertTrue(finder.getIndirectFlights().contains(new Flight[] {new Flight("ATL", "BOS", 140, 220), 
				new Flight("BOS", "PHL", 95, 205), new Flight("PHL", "CDG", 605, 535)}));
	}
	
	@Test
	public void testDirectOnlyFalseLessThanTimeSpecified() {
		assertEquals(0, finder.findFlights(false, "ATL", "CDG", 2));
		assertTrue(finder.getIndirectFlights().isEmpty());
		assertTrue(finder.getDirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyFalseZeroTimeSpecified() {
		assertEquals(0, finder.findFlights(false, "ATL", "CDG", 0));
		assertTrue(finder.getIndirectFlights().isEmpty());
		assertTrue(finder.getDirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyFalseHomeAndDestAreEqual() {
		assertEquals(-1, finder.findFlights(false, "ATL", "ATL", 0));
		assertTrue(finder.getIndirectFlights().isEmpty());
		assertTrue(finder.getDirectFlights().isEmpty());
	}
	
	@Test
	public void testDirectOnlyFalseFlightEqualToTime() {
		assertEquals(1, finder.findFlights(false, "ORD", "SEA", 225));
		assertTrue(finder.getDirectFlights().contains(new Flight("ORD", "SEA", 225, 300)));
	}
	
	@Test
	public void testNumSearchesInitial() {
		assertEquals(0, FlightFinder.getNumSearches());
		assertTrue(finder.getIndirectFlights().isEmpty());
		assertTrue(finder.getDirectFlights().isEmpty());
	}
	
	@Test
	public void testNumSearchesAfterMultipleTrue() {
		finder.findFlights(false, "ORD", "SEA", 9999);
		finder.findFlights(false, "PHL", "BOS", 9999);
		finder.findFlights(false, "BOS", "PHL", 9999);
		assertEquals(3, FlightFinder.getNumSearches());
	}
	
	@Test
	public void testNumSearchesAfterIllegalHome() {
		finder.findFlights(false, "XXX", "SEA", 9999);
		finder.findFlights(false, "PHL", "BOS", 9999);
		finder.findFlights(false, "BOS", "PHL", 9999);
		assertEquals(2, FlightFinder.getNumSearches());
	}
	
	@Test
	public void testNumSearchesAfterIllegalDest() {
		finder.findFlights(false, "ORD", "XXX", 9999);
		finder.findFlights(false, "PHL", "BOS", 9999);
		finder.findFlights(false, "BOS", "PHL", 9999);
		assertEquals(2, FlightFinder.getNumSearches());
	}
	
	@Test
	public void testNumSearchesAfterIllegalTime() {
		finder.findFlights(false, "ORD", "PHL", 9999);
		finder.findFlights(false, "PHL", "BOS", -1);
		finder.findFlights(false, "BOS", "PHL", 9999);
		assertEquals(2, FlightFinder.getNumSearches());
	}
	
	@Test
	public void testNumSearchesAfterNoFlightsAvailable() {
		finder.findFlights(true, "SEA", "LHR", 9999);
		assertEquals(1, FlightFinder.getNumSearches());
	}
	
	@Test
	public void testDirectListInitiallyEmpty() {
		assertTrue(finder.getDirectFlights().isEmpty());
	}
	
	@Test
	public void testIndirectListInitiallyEmpty() {
		assertTrue(finder.getIndirectFlights().isEmpty());
	}
	

}
