package bitfs.sonder.client;

import java.io.File;
import java.net.InetAddress;
import java.util.concurrent.ThreadLocalRandom;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class Package {
	
	private String name;
	private long id;
	private File torrent, outputDirectory;
	private Client client;
	private Status status;
	
	public Package(String name, File torrent, File outputDirectory) throws Exception {
		this.name = name;
		this.torrent = torrent;
		this.outputDirectory = outputDirectory;
		this.id = Package.nextID();
		this.client = Package.initClient(this);
		this.status = Status.INIT;
	}
	
	public static Client initClient(Package in) throws Exception {
		SharedTorrent torrent = SharedTorrent.fromFile(in.getTorrent(), in.getOutputDirectory());
		Client c = new Client(InetAddress.getLocalHost(), torrent);
		return c;
	}
	
	public static long nextID() {
		return ThreadLocalRandom.current().nextLong(0, 100000000);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public File getTorrent() {
		return torrent;
	}
	
	public void setTorrent(File torrent) {
		this.torrent = torrent;
	}
	
	public File getOutputDirectory() {
		return outputDirectory;
	}
	
	public void setOutputDirectory(File outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
