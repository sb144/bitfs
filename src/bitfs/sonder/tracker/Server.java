package bitfs.sonder.tracker;

import java.io.File;
import java.io.FilenameFilter;
import java.net.InetSocketAddress;

import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

import bitfs.sonder.client.Log;
import bitfs.sonder.shared.Data;

public class Server {
	
	public static final File torrentDirectory = new File("/var/www/html/torrents");
	public static Tracker tracker;

	public static void main(String[] args) throws Exception {
		Log.write("Starting tracker...");
		tracker = new Tracker(new InetSocketAddress(Data.TRACKER_PORT));
		FilenameFilter filter = new FilenameFilter() {
		  @Override
		  public boolean accept(File dir, String name) {
			   return name.endsWith(".torrent");
			}
		};
		
		for (File f : torrentDirectory.listFiles(filter)) {
			tracker.announce(TrackedTorrent.load(f));
		}
		
		tracker.start();
		Log.write("Tracker started!");
	}
	
	public static void halt() {
		tracker.stop();
		Log.write("Tracker halted!");
	}

}
