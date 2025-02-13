
public class Song {
	private String songName;
	private String artist;
	private String albumName;
	private String genre;
	private int rating;
	private boolean favorited;
	
	public Song(String songName, String artist, String albumName, String genre) {
		this.songName = songName;
		this.albumName = albumName;
		this.artist = artist;
		this.genre = genre;
	}
	
	public String getSongName() {
		return songName;
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
	
	public int getRating() {
		return rating;
	}
	
	public boolean getFavorited() {
		return favorited;
	}
	
	public void setRating(int n) {
		if (n < 1) n = 1;
		if (n > 5) n = 5;
		if (n == 5) favorited = true;
		this.rating = n;
	}
	
	public void favorite() {
		favorited = true;
	}
	
	// Feels a bit weird splitting it up like this,
	// but it's probably more useful like this.
	public void unfavorite() {
		favorited = false;
	}
	
	
}
