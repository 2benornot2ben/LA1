/*
 * LAST CHANGED ON FEB 22
 * COMMENTS for future changes: include methods for searching. Test the code using main. 
 */



import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Song> songList;
	private ArrayList<Album> albumList;
	private ArrayList<PlayList> playListList;
	private ArrayList<String> artistList;
	public LibraryModel() {
		this.songList = new ArrayList<Song>();
		this.albumList = new ArrayList<Album>();
		this.playListList = new ArrayList<PlayList>();
		this.artistList = new ArrayList<String>();
	}
	
	public void addSongToList(String title, String artist, String albumName, String genre, String year) {
		songList.add(new Song(title, artist, albumName, genre, year));
    }
	
	public void addAlbumToList(String title, String artist, String genre, String year) {
		albumList.add(new Album(title, artist, genre, year));
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
            System.out.println((i + 1) + " " + favoriteSongs.get(i));
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
