import java.util.ArrayList;

public class Album {
	private String albumName;
	private String artist;
	private String genre;
	private String year;
	private ArrayList<String> songs;
	public Album(String albumName, String artist, String genre, String year) {
		this.albumName = albumName;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		// May be replaced with a class in the future.
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
	
	public String getGenre() {
		return genre;
	}
	
	// May need to be converted to int.
	public String getYear() {
		return year;
	}
	
	// This method might not be used.
	public ArrayList<String> getSongList() {
		return new ArrayList<String>(songs);
	}
}
