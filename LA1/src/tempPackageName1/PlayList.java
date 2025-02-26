package tempPackageName1;

import java.util.ArrayList;

public class PlayList {
	private String playListName;
	private ArrayList<Song> songs;
	
	public PlayList(String playListName) {
		this.playListName = playListName;
		this.songs = new ArrayList<Song>();
	}
	
	public PlayList(PlayList playlist) {
		this.playListName = playlist.getPlayListName();
		this.songs = playlist.getSongList();
	}
	
	public void addSong(Song songInst) {
		songs.add(songInst);
	}

	public boolean canAddSongToList(Song song) {
		for(int i = 0; i < songs.size(); i++) {
			if(songs.get(i).getSongName().toLowerCase().equals(song.getSongName()) && songs.get(i).getArtist().toLowerCase().equals(song.getArtist())) {
				return false;
			}
		}
		return true;
	}
    
	public boolean canRemoveSong(String title, String artist) {
		for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getSongName().toLowerCase().equals(title.toLowerCase()) && songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())){
                return true;
            }
        }
		return false;
	}
	
	public void removeSong(String title, String artist){
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getSongName().toLowerCase().equals(title.toLowerCase()) && songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())){
                songs.remove(i);
                break;
            }
        }
    }

	public String getPlayListName() {
		return this.playListName;
	}
	
	// This method might not be used.
	// This now uses classes, so this really might not be used.
	public ArrayList<Song> getSongList() {
		ArrayList<Song> copy = new ArrayList<Song>();
	    for (Song song : songs) {
	        copy.add(new Song(song));
	    }
	    return copy;
	}
	
	// We may need this to handle getting a specific song.
}
