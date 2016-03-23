package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.LoadFileListener;
import model.MainEngine;
import model.SaveDataEngine;

public class SaveLoadTesting {
	SaveDataEngine sde;
	MainEngine model = new MainEngine();
	String filepathT = "res/loadsavetest.txt";
	String filepathF = "res/does_not_exist/DO_NOT_WORK.txt";

	/*
	 * Testing to ensure that loading a file works correctly and that an
	 * incorrect filepath does not throw unexpected errors
	 */
	@Test
	public void testLoadingAFile() {

		assertTrue(SaveDataEngine.loadFile(filepathT, model));
		assertFalse(SaveDataEngine.loadFile(filepathF, model));

	}

	/*
	 * Testing to ensure that saving a file works correctly and that an
	 * incorrect filepath does not throw unexpected errors
	 */
	@Test
	public void testSavingAFile() {

		assertTrue(SaveDataEngine.saveFile(filepathT, model));
		assertFalse(SaveDataEngine.saveFile(filepathF, model));

	}

}
