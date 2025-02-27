/*
 * Added 2 getters as we need them. Fixed some bugs.
 */
package tempPackageName1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicStore {
	//instance variables
	private ArrayList<String> fileNames;

	// we may write a code for storing them in a separate file
	private ArrayList<Album> albums;
	private ArrayList<Song> songs;

	//constructor
	public MusicStore() throws FileNotFoundException{
		this.fileNames = new ArrayList<String>();
	    this.albums = new ArrayList<Album>();
	    this.songs = new ArrayList<Song>();
		creatingFileNames();
		readingAlbums();
	}
	
	//method for creating filenames from the content of the albums.txt
	public void creatingFileNames(){
		// we can delete try catch and write throws FileNotFoundException to the method
		try (Scanner scanLine = new Scanner(new File("albums.txt"))){
			while(scanLine.hasNextLine()){
				String line = scanLine.nextLine();
				String[] data = line.split(",");
				String albumFile = data[0] + "_" + data[1] + ".txt";
				fileNames.add(albumFile);
			}
			scanLine.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void readingAlbums() throws FileNotFoundException{
		for(int i = 0; i < this.fileNames.size(); i++){
			File myFile = new File(fileNames.get(i));
			Scanner myReader = new Scanner(myFile);

			//reads only first line which is in the following format
			//Album Title,Artist,Genre,Year
			String header = myReader.nextLine();
			String[] headerInfo = header.split(",");

			//retrieves the data
			String albumTitle = headerInfo[0];
			String artist = headerInfo[1];
			String genre = headerInfo[2];
			String year = headerInfo[3];
			
			//creates and adds new album to an arrayList
			albums.add(new Album(albumTitle, artist, genre, year));

			//reading songs
			while(myReader.hasNextLine()){
				String songName = myReader.nextLine();
				Song storeSong = new Song(songName, artist, albumTitle, genre, year);
				songs.add(storeSong);
				albums.get(i).addSong(storeSong);
			}
			myReader.close();
		}
	}
	
	public ArrayList<Song> getSongs(){
		ArrayList<Song> copy = new ArrayList<Song>();
	    for (Song song : songs) {
	        copy.add(new Song(song));
	    }
	    return copy;
	}
	
	public ArrayList<Album> getAlbums(){
		ArrayList<Album> copy = new ArrayList<>();
	    for (Album album: albums) {
	        copy.add(new Album(album));
	    }
	    return copy;
	}
	
	public void rateSong(Song song, int rating) {
		for(int i = 0; i < songs.size(); i++) {
			if(songs.get(i).equals(song)) {
				songs.get(i).setRating(rating);
				songs.get(i).favorite();
				break;
			}
		}
	}
}