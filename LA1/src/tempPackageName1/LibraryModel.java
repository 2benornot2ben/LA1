/*
 * LAST CHANGED ON FEB 24
 * COMMENTS for future changes: change the methods according to the view. Test the code. 
 */

package tempPackageName1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LibraryModel{
	private ArrayList<Song> songList;
	private ArrayList<Album> albumList;
	private ArrayList<PlayList> playListList;
	private ArrayList<String> artistList;
	private MusicStore database;
	public LibraryModel() throws FileNotFoundException{
		this.songList = new ArrayList<Song>();
		this.albumList = new ArrayList<Album>();
		this.playListList = new ArrayList<PlayList>();
		this.artistList = new ArrayList<String>();
		this.database = new MusicStore();
	}
	
	public ArrayList<Song> searchByIndicatorSong(String input, String category, String indicator, boolean precise) {
		ArrayList<Song> resultList = new ArrayList<Song>();
		ArrayList<Song> songs;
		if (category.equals("musicstore")) {
			songs = database.getSongs();
		} else {
			songs = new ArrayList<Song>();
		    for (Song song : this.songList) {
		        songs.add(new Song(song));
		    }
		}
		for(int i = 0; i < songs.size(); i++) {
			if (precise) {
				if (indicator.equals("title")) {
					if (songs.get(i).getSongName().toLowerCase().equals(input.toLowerCase())) {
						resultList.add(songs.get(i));
					}
				} else {
					if (songs.get(i).getArtist().toLowerCase().equals(input.toLowerCase())) {
						resultList.add(songs.get(i));
					}
				}
			} else {
				if (indicator.equals("title")) {
					if (songs.get(i).getSongName().toLowerCase().contains(input.toLowerCase())) {
						resultList.add(songs.get(i));
					}
				} else {
					if (songs.get(i).getArtist().toLowerCase().contains(input.toLowerCase())) {
						resultList.add(songs.get(i));
					}
				}
			}
		}
		return resultList;
	}
	
	public ArrayList<Album> searchByIndicatorAlbum(String input, String category, String indicator, boolean precise) {
		ArrayList<Album> resultList = new ArrayList<Album>();
		ArrayList<Album> albums;
		if (category.equals("musicstore")) {
			albums = database.getAlbums();
		} else {
			albums = new ArrayList<Album>();
		    for (Album album : this.albumList) {
		        albums.add(new Album(album));
		    }
		}
		for(int i = 0; i < albums.size(); i++) {
			// As much as I wish to simplify this, it simply cannot be done...
			if (precise) {
				if (indicator.equals("title")) {
					if (albums.get(i).getAlbumName().toLowerCase().equals(input.toLowerCase())) {
						resultList.add(albums.get(i));
					}
				} else {
					if (albums.get(i).getArtist().toLowerCase().equals(input.toLowerCase())) {
						resultList.add(albums.get(i));
					}
				}
			} else {
				if (indicator.equals("title")) {
					if (albums.get(i).getAlbumName().toLowerCase().contains(input.toLowerCase())) {
						resultList.add(albums.get(i));
					}
				} else {
					if (albums.get(i).getArtist().toLowerCase().contains(input.toLowerCase())) {
						resultList.add(albums.get(i));
					}
				}
			}
		}
		return resultList;
	}
	
	public PlayList searchByNamePlayList(String name) {
		PlayList result = new PlayList("");
		for(int i = 0; i < playListList.size(); i++) {
			if(playListList.get(i).getPlayListName().toLowerCase().equals(name.toLowerCase())) {
				result = new PlayList(playListList.get(i));
				break;
			}
		}
		return result;
	}
	
	public boolean addSongToPlaylist(String playlistName, Song song) {
		for(int i = 0; i < playListList.size(); i++) {
			if(playListList.get(i).getPlayListName().toLowerCase().equals(playlistName.toLowerCase())) {
				if(playListList.get(i).canAddSongToList(song)) {
					playListList.get(i).addSong(song);
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	public boolean removeSongFromPlaylist(String playlistName, String title, String artist) {
		for(int i = 0; i < playListList.size(); i ++) {
			if(playListList.get(i).getPlayListName().toLowerCase().equals(playlistName.toLowerCase())) {
				boolean canRemove = playListList.get(i).canRemoveSong(title, artist);
				if(canRemove) {
					playListList.get(i).removeSong(title, artist);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canAddSongToList(Song song) {
		for(int i = 0; i < songList.size(); i++) {
			if(songList.get(i).getSongName().equals(song.getSongName()) && songList.get(i).getArtist().equals(song.getArtist())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canAddAlbumToList(Album album) {
		for(int i = 0; i < albumList.size(); i++) {
			if(albumList.get(i).getAlbumName().equals(album.getAlbumName()) && albumList.get(i).getArtist().equals(album.getArtist())) {
				return false;
			}
		}
		return true;
	}
	
	public void addSongToList(Song song) {
		songList.add(new Song(song));
		if(!artistList.contains(song.getArtist())){
			artistList.add(song.getArtist());
		}
    }
	
	public void addAlbumToList(Album album) {
		albumList.add(new Album(album));
		for (Song song : album.getSongList()) {
	        if (!songList.contains(song)) {
	            songList.add(song);
	        }
	    }
		if(!artistList.contains(album.getArtist())){
			artistList.add(album.getArtist());
		}
		
	}
	
	public ArrayList<String> getLibrarySongList() {
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < this.songList.size(); i++){
            list.add(songList.get(i).getSongName());
        }
		return list;
	}
	
	public ArrayList<String> getLibraryArtistList(){
		return artistList;
	}
	
	public ArrayList<String> getLibraryAlbumList(){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < this.albumList.size(); i++){
            list.add(albumList.get(i).getAlbumName());
        }
		return list;
	}
	
	public ArrayList<String> getLibraryPlaylistList(){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < this.playListList.size(); i++){
            list.add(playListList.get(i).getPlayListName());
        }
		return list;
	}
	
	public ArrayList<String> getLibraryFavoriteSongs() {
        ArrayList<String> favoriteSongs = new ArrayList<String>();
		for(int i = 0; i < this.songList.size(); i++){
            if(songList.get(i).getFavorited() == true){
                favoriteSongs.add(songList.get(i).getSongName());
            }
        }
        return favoriteSongs;
	}
	
	public boolean addPlayList(String name) {
		for(int i = 0; i < playListList.size(); i++) {
			if(playListList.get(i).getPlayListName().toLowerCase().equals(name.toLowerCase())) {
				return false;
			}
		}
		playListList.add(new PlayList(name));
		return true;
	}
	
    //added playListName because we should know to which playlist we should add a song
	public void addToPlayList(Song songInst, String playListName) {
		//i dont know if we should to validation for playListName
        for(int i = 0; i < playListList.size(); i++){
            if(playListList.get(i).getPlayListName().equals(playListName)){
                playListList.get(i).addSong(songInst);
            }
        }
	}

    // song names might be similar so we need to check other attributes like artist name ig.
    // i dont know.
    public void markSongAsFavorite(Song songInst){
        for(int i = 0; i < songList.size(); i++){
            if(songList.get(i).getSongName().equals(songInst.getSongName())){
                songList.get(i).favorite();
            }
        }
    }

    public void rateSong(Song songInst, int rating){
        for(int i = 0; i < songList.size(); i++){
            if(songList.get(i).getSongName().equals(songInst.getSongName())){
                songList.get(i).setRating(rating);
            }
        }
    }
}