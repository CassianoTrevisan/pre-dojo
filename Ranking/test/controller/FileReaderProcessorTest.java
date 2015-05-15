package controller;

import static org.junit.Assert.*;

import java.util.List;

import model.Match;

import org.junit.Before;
import org.junit.Test;

public class FileReaderProcessorTest extends FileReaderProcessor {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testMain() {
		
		FileReaderProcessor.main(null);
		
		List<Match> matches = FileReaderProcessor.getMatches();
		assertEquals(matches.size(), 2);
		assertEquals(matches.get(0).getPlayers().size(), 3);
		assertEquals(matches.get(1).getPlayers().size(), 3);
		
		assertEquals(matches.get(0).getPlayers().get("Roman").getDeathCounter(), 0);
		assertEquals(matches.get(1).getPlayers().get("Roman").getDeathCounter(), 1);
		
		assertEquals(matches.get(0).getPlayers().get("Nick").getDeathCounter(), 10);
		assertEquals(matches.get(1).getPlayers().get("Nick").getDeathCounter(), 10);
		
		assertEquals(matches.get(0).getPlayers().get("Cassiano").getDeathCounter(), 3);
		assertEquals(matches.get(1).getPlayers().get("Cassiano").getDeathCounter(), 3);
		
		
		assertEquals(matches.get(0).getPlayers().get("Roman").getMurderCounter(), 9);
		assertEquals(matches.get(1).getPlayers().get("Roman").getMurderCounter(), 9);
		
		assertEquals(matches.get(0).getPlayers().get("Nick").getMurderCounter(), 3);
		assertEquals(matches.get(1).getPlayers().get("Nick").getMurderCounter(), 4);
		
		assertEquals(matches.get(0).getPlayers().get("Cassiano").getMurderCounter(), 0);
		assertEquals(matches.get(1).getPlayers().get("Cassiano").getMurderCounter(), 0);
	}

}
