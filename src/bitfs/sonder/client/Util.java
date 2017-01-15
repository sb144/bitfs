package bitfs.sonder.client;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Util {
	
	public static LinkedList<File> listFiles(File directory) {
		LinkedList<File> files = new LinkedList<File>();
		for(File f : directory.listFiles()) {
			if(f.isFile()) {
				files.add(f);
			} else {
				files.addAll(listFiles(f));
			}
		}
		
		return files;
	}

}
