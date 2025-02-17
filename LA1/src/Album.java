import java.util.ArrayList;

public class Album {
	private String albumName;
	private String artist;
	private String genre;
	private String year;
	private ArrayList<Song> songs;
	
	public Album(String albumName, String artist, String genre, String year) {
		this.albumName = albumName;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		this.songs = new ArrayList<Song>();
	}
	
	public void addSong(Song songInst) {
		songs.add(songInst);
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
	// This now uses classes, so this really might not be used.
	public ArrayList<Song> getSongList() {
		return new ArrayList<Song>(songs);
	}
}
