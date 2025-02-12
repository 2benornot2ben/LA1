import java.util.ArrayList;

public class Album {
	private String albumName;
	private String artist;
	private ArrayList<String> songs;
	public Album(String albumName, String artist) {
		this.albumName = albumName;
		this.artist = artist;
		this.songs = new ArrayList<String>();
	}
	
	public void addSong(String title) {
		songs.add(title);
	}
	
	public String getAlbumName() {
		return albumName;
	}
	
	public String getArtist() {
		return artist;
	}
	
	// This method might not be used.
	public ArrayList<String> getSongList() {
		return new ArrayList<String>(songs);
	}
}
