import java.util.ArrayList;

public class PlayList {
	private String playListName;
	private ArrayList<Song> songs;
	
	public PlayList(String playListName) {
		this.playListName = playListName;
		this.songs = new ArrayList<Song>();
	}
	
	public void addSong(Song songInst) {
		songs.add(songInst);
	}
	
	public String getPlayListName() {
		return playListName;
	}
	
	// This method might not be used.
	// This now uses classes, so this really might not be used.
	public ArrayList<Song> getSongList() {
		return new ArrayList<Song>(songs);
	}
	
	// We may need this to handle getting a specific song.
}
