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
	
	public ArrayList<Song> searchByTitleSong(String title, String category) {
		ArrayList<Song> resultList = new ArrayList<Song>();
		ArrayList<Song> songs;
		if(category.equals("musicstore")) {
			songs = database.getSongs();
		}else {
			songs = new ArrayList<Song>();
		    for (Song song : this.songList) {
		        songs.add(new Song(song));
		    }
		}
		
		for(int i = 0; i < songs.size(); i++) {
			if(songs.get(i).getSongName().toLowerCase().equals(title.toLowerCase())) {
				resultList.add(songs.get(i));
			}
		}
		return resultList;
	}
	
	public ArrayList<Song> searchByArtistSong(String artist, String category){
		ArrayList<Song> resultList = new ArrayList<Song>();
		ArrayList<Song> songs;
		if(category.equals("musicstore")) {
			songs = database.getSongs();
		}else {
			songs = new ArrayList<Song>();
		    for (Song song : this.songList) {
		        songs.add(new Song(song));
		    }
		}
		for(int i = 0; i < songs.size(); i++) {
			if(songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
				resultList.add(songs.get(i));
			}
		}
		return resultList;
	}
	
	public ArrayList<Album> searchByTitleAlbum(String title, String category){
		ArrayList<Album> resultList = new ArrayList<Album>();
		ArrayList<Album> albums;
		if(category.equals("musicstore")) {
			albums = database.getAlbums();
		}else {
			albums = new ArrayList<Album>();
		    for (Album album : this.albumList) {
		        albums.add(new Album(album));
		    }
		}
		for(int i = 0; i < albums.size(); i++) {
			if(albums.get(i).getAlbumName().toLowerCase().equals(title.toLowerCase())) {
				resultList.add(albums.get(i));
			}
		}
		return resultList;
	}
	
	public ArrayList<Album> searchByArtistAlbum(String artist, String category){
		ArrayList<Album> resultList = new ArrayList<Album>();
		ArrayList<Album> albums;
		if(category.equals("musicstore")) {
			albums = database.getAlbums();
		}else {
			albums = new ArrayList<Album>();
		    for (Album album : this.albumList) {
		        albums.add(new Album(album));
		    }
		}
		for(int i = 0; i < albums.size(); i++) {
			if(albums.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
				resultList.add(albums.get(i));
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
	
	
	public void addSongToList(Song song) {
		songList.add(new Song(song));
    }
	
	public void addAlbumToList(String title, String artist, String genre, String year) {
		albumList.add(new Album(title, artist, genre, year)); // >:CCCCCCCCCCCCCCCCCCCCCCCCCCc
	}
	
	public void printLibrarySongList() {
		for(int i = 0; i < this.songList.size(); i++){
            System.out.println((i + 1) + " " + songList.get(i).getSongName());
        }
	}
	
	public void printLibraryArtistList() {
		for(int i = 0; i < this.artistList.size(); i++){
            System.out.println((i + 1) + " " + artistList.get(i));
        }
	}
	
	public void printLibraryAlbumList() {
		for(int i = 0; i < this.albumList.size(); i++){
            System.out.println((i + 1) + " " + albumList.get(i).getAlbumName());
        }
	}
	
	public void printLibraryPlayLists() {
		for(int i = 0; i < this.playListList.size(); i++){
            System.out.println((i + 1) + " " + playListList.get(i).getPlayListName());
        }
	}
	
	public void printLibraryFavoriteSongs() {
        ArrayList<String> favoriteSongs = new ArrayList<String>();
		for(int i = 0; i < this.songList.size(); i++){
            if(songList.get(i).getFavorited() == true){
                favoriteSongs.add(songList.get(i).getSongName());
            }
        }
        for(int i = 0; i < favoriteSongs.size(); i++){
            System.out.println((i + 1) + " " + favoriteSongs.get(i)); // >:C
        }
	}
	
	public void addPlayList(String name) {
		playListList.add(new PlayList(name));
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

    public void removeFromPlayList(Song songInst, String playListName){
        for(int i = 0; i < playListList.size(); i++){
            if(playListList.get(i).getPlayListName().equals(playListName)){
                playListList.get(i).removeSong(songInst);
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
