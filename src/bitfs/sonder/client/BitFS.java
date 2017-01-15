package bitfs.sonder.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import com.turn.ttorrent.common.Torrent;
import com.turn.ttorrent.tracker.Tracker;

public class BitFS {
	
	 private static LinkedList<Package> packages;
	 private static Tracker tracker;
	 
	 private static long uuid;
	 
	 public static void start() throws Exception {
		 packages = new LinkedList<Package>();
		 tracker = BitFS.findTracker();
	 }
	 
	 public static void createTorrent(File input, File output) throws Exception {
		 Torrent torrent = null;
		 if(input.isFile()) {
			 torrent = Torrent.create(input, tracker.getAnnounceUrl().toURI(), "id:" + uuid);
		 } else if(input.isDirectory()) {
			 torrent = Torrent.create(input, Util.listFiles(input), tracker.getAnnounceUrl().toURI(), "id:" + uuid);
		 }
		 
		 FileOutputStream fos = new FileOutputStream(output);
         torrent.save( fos );            
         fos.close();
	 }
	 
	 public static Tracker findTracker() throws Exception {
		 URL page = new URL("https://sonder.click/t/conn.php");
		 BufferedReader br = new BufferedReader(new InputStreamReader(page.openStream()));
		 String host = br.readLine();
		 return new Tracker(InetAddress.getByName(host));
	 }
}
