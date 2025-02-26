package tempPackageName2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * LAST UPDATED 25 FEB, 10 P.M
 * ADD ALBUM, GET LISTS ARE DONE.
 * METHODS ARE TESTED, EVERYTHING IS WORKING WELL
 * 
 * 
 * ATTENTION!!
 * WE NEED TO VALIDATE THE INPUTS.
 * 
 * DUPLICATED CODE WILL BE FIXED AFTER WE FINISH EVERY METHOD.
 * 
 */

import tempPackageName1.*; 

public class View {
	private ArrayList<Song> songStorage = new ArrayList<Song>();
	private ArrayList<Album> albumStorage = new ArrayList<Album>();
	private ArrayList<PlayList> playListStorage = new ArrayList<PlayList>();
	private LibraryModel myLibrary;
	Scanner getInput = new Scanner(System.in);
	String holdInput = "";
	String holdInputLower = "";
	public View() throws FileNotFoundException{
		myLibrary = new LibraryModel();
		boolean running = true;
		while (running) {
			songStorage.clear();
			albumStorage.clear();
			playListStorage.clear();
			System.out.println("State: Default");
			System.out.println("Enter: \"Search\" \"MusicStore OR Library\" to begin SEARCHING (Library has Playlists)"); // This will also handle LISTING
			System.out.println("Enter: \"Add\" \"Album OR Song\" to add to Library or Playlists");
			System.out.println("Enter: \"Create\" \"{PlaylistName}\" to create a new playlist. No spaces in the name."); 
			System.out.println("Enter: \"Rate\" \"{SongName}\" to rate a song. You only need to type the first few letters."); // This will also handle FAVORITING
			System.out.println("Enter: \"Get\" to get a list."); 
			System.out.println("Enter: \"Exit\" to kill the program. You will need to rerun it after.");
			System.out.println("This is not case sensitive, but is spelling sensitive.");
			holdInput = getInput.nextLine();
			holdInputLower = holdInput.toLowerCase();
			if (holdInputLower.split(" ")[0].equals("search") && holdInputLower.split(" ")[1].equals("musicstore")) {
				System.out.println("");
				System.out.println("YOU CAN SEARCH FOR THE ...");
				System.out.println("1. Song by title");
				System.out.println("2. Song by artist");
				System.out.println("3. Album by title");
				System.out.println("4. Album by artist");
				System.out.print("Enter the number of an option you want: ");
				String searching = getInput.nextLine();
				
				if(searching.equals("1")) {
					System.out.print("Please enter the title of the song: ");
					String titleOfSong = getInput.nextLine();
					ArrayList<Song> resultList = myLibrary.searchByTitleSong(titleOfSong, "musicstore");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + titleOfSong + " is not in the database.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ": " +resultList.get(i).getSongName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getAlbumName());
						}
					}
				}else if(searching.equals("2")) {
					System.out.print("Please enter the artist of the song: ");
					String artistOfSong = getInput.nextLine();
					ArrayList<Song> resultList = myLibrary.searchByArtistSong(artistOfSong, "musicstore");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + artistOfSong + " is not in the database.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ": " +resultList.get(i).getSongName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getAlbumName());
						}
					}
				}
				else if(searching.equals("3")) {
					System.out.print("Please enter the title of the album: ");
					String titleOfAlbum = getInput.nextLine();
					ArrayList<Album> resultList = myLibrary.searchByTitleAlbum(titleOfAlbum, "musicstore");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + titleOfAlbum + " is not in the database.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ") " +resultList.get(i).getAlbumName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getGenre() + " " + resultList.get(i).getYear());
							for(int j = 0; j < resultList.get(i).getSongList().size(); j++) {
								System.out.println((j+1) + ": " + resultList.get(i).getSongList().get(j).getSongName());
							}
						}
					}
				}
				else if(searching.equals("4")) {
					System.out.print("Please enter the artist of the album: ");
					String artistOfAlbum = getInput.nextLine();
					ArrayList<Album> resultList = myLibrary.searchByArtistAlbum(artistOfAlbum, "musicstore");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + artistOfAlbum + " is not in the database.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ") " +resultList.get(i).getAlbumName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getGenre() + " " + resultList.get(i).getYear());
							for(int j = 0; j < resultList.get(i).getSongList().size(); j++) {
								System.out.println((j+1) + ": " + resultList.get(i).getSongList().get(j).getSongName());
							}
							System.out.println("");
						}
					}
				}
				
			} else if (holdInputLower.split(" ")[0].equals("search") && holdInputLower.split(" ")[1].equals("library")) {
				System.out.println("");
				System.out.println("YOU CAN SEARCH FOR THE ...");
				System.out.println("1. Song by title");
				System.out.println("2. Song by artist");
				System.out.println("3. Album by title");
				System.out.println("4. Album by artist");
				System.out.println("5. Playlist by name");
				System.out.print("Enter the number of an option you want: ");
				String searching = getInput.nextLine();
				
				if(searching.equals("1")) {
					System.out.print("Please enter the title of the song: ");
					String titleOfSong = getInput.nextLine();
					ArrayList<Song> resultList = myLibrary.searchByTitleSong(titleOfSong, "library");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + titleOfSong + " is not in the library.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ": " +resultList.get(i).getSongName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getAlbumName());
						}
					}
				}else if(searching.equals("2")) {
					System.out.print("Please enter the artist of the song: ");
					String artistOfSong = getInput.nextLine();
					ArrayList<Song> resultList = myLibrary.searchByArtistSong(artistOfSong, "library");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + artistOfSong + " is not in the library.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ": " +resultList.get(i).getSongName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getAlbumName());
						}
					}
				}
				else if(searching.equals("3")) {
					System.out.print("Please enter the title of the album: ");
					String titleOfAlbum = getInput.nextLine();
					ArrayList<Album> resultList = myLibrary.searchByTitleAlbum(titleOfAlbum, "library");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + titleOfAlbum + " is not in the library.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ") " +resultList.get(i).getAlbumName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getGenre() + " " + resultList.get(i).getYear());
							for(int j = 0; j < resultList.get(i).getSongList().size(); j++) {
								System.out.println((j+1) + ": " + resultList.get(i).getSongList().get(j).getSongName());
							}
						}
					}
				}
				else if(searching.equals("4")) {
					System.out.print("Please enter the artist of the album: ");
					String artistOfAlbum = getInput.nextLine();
					ArrayList<Album> resultList = myLibrary.searchByArtistAlbum(artistOfAlbum, "library");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + artistOfAlbum + " is not in the library.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ") " +resultList.get(i).getAlbumName() + " " + 
								resultList.get(i).getArtist() + " " + resultList.get(i).getGenre() + " " + resultList.get(i).getYear());
							for(int j = 0; j < resultList.get(i).getSongList().size(); j++) {
								System.out.println((j+1) + ": " + resultList.get(i).getSongList().get(j).getSongName());
							}
							System.out.println("");
						}
					}
				}
				else if(searching.equals("5")) {
					System.out.print("Please enter the name of the playlist: ");
					String nameOfPlayList = getInput.nextLine();
					PlayList result = myLibrary.searchByNamePlayList(nameOfPlayList);
					if (result.getPlayListName().equals("")) {
						System.out.println("Sorry " + nameOfPlayList + " is not in the library.");
					} else {
						System.out.println("Search result: ");
						System.out.println(result.getPlayListName());
						for(int i = 0; i < result.getSongList().size(); i++) {
							System.out.println((i+1) + ": " + result.getSongList().get(i).getSongName() 
									+ " " + result.getSongList().get(i).getArtist());
						}
					}
				}
			} else if (holdInputLower.split(" ")[0].equals("add") && (holdInputLower.split(" ")[1].equals("album")
					|| holdInputLower.split(" ")[1].equals("song"))) {
				System.out.println("");
				System.out.print("Enter a title of the " + holdInputLower.split(" ")[1] + " you want to add: ");
				String title = getInput.nextLine();
				System.out.print("Enter an artist of the " + holdInputLower.split(" ")[1] + " you want to add: ");
				String artist = getInput.nextLine();
				
				
				if(holdInputLower.split(" ")[1].equals("song")) {
					ArrayList<Song> songs = myLibrary.searchByTitleSong(title, "musicstore");
					if(songs.size() == 0) {
						System.out.println(title + " is not found in the store");
					}else {
						boolean added = false;
						boolean exists = false;
						for(int i = 0; i < songs.size(); i++) {
							if(songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
								if(myLibrary.canAddSongToList(songs.get(i))) {
									myLibrary.addSongToList(songs.get(i));
									added = true;
									break;
								} else {
									exists = true;
								}
							}
						}
						if(!added) {
							if(exists) {
								System.out.println(title + " by " + artist + " is already in the library");
							} else {
								System.out.println(title + " by " + artist + " is not found in the store");
							}
						}
						else {
							System.out.println(title + " by " + artist + " has been added to the library");
						}
					}
				} else {
					ArrayList<Album> albums = myLibrary.searchByTitleAlbum(title, "musicstore");
					if(albums.size() == 0) {
						System.out.println(title + " is not found in the store");
					}else {
						boolean added = false;
						boolean exists = false;
						for(int i = 0; i < albums.size(); i++) {
							if(albums.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
								if(myLibrary.canAddAlbumToList(albums.get(i))) {
									myLibrary.addAlbumToList(albums.get(i));
									added = true;
									break;
								} else {
									exists = true;
								}
								
							}
						}
						if(!added) {
							if(exists) {
								System.out.println(title + " by " + artist + " is already in the library");
							} else {
								System.out.println(title + " by " + artist + " is not found in the store");
							}
						}
						else {
							System.out.println(title + " by " + artist + " has been added to the library");
						}
					}
				}
				
			} else if (holdInputLower.split(" ")[0].equals("get")) {
				System.out.println("");
				System.out.println("YOU CAN GET A LIST OF ...");
				System.out.println("1. Song titles");
				System.out.println("2. Artists");
				System.out.println("3. Albums");
				System.out.println("4. Playlists");
				System.out.println("5. Favorite Songs");
				System.out.println("Enter the number of an option you want: ");
				String option = getInput.nextLine();
				if(option.equals("1")) {
					ArrayList<String> songNames = myLibrary.getLibrarySongList();
					if(songNames.size() == 0) {
						System.out.println("No songs in the library");
					} else {
						for(int i = 0; i < songNames.size(); i++) {
							System.out.println((i+1) + ". " +songNames.get(i));
						}
					}
				} else if(option.equals("2")) {
					ArrayList<String> artists = myLibrary.getLibraryArtistList();
					if(artists.size() == 0) {
						System.out.println("No artists in the library");
					} else {
						for(int i = 0; i < artists.size(); i++) {
							System.out.println((i+1) + ". " + artists.get(i));
						}
					}
				} else if(option.equals("3")) {
					ArrayList<String> albums = myLibrary.getLibraryAlbumList();
					if(albums.size() == 0) {
						System.out.println("No albums in the library");
					} else {
						for(int i = 0; i < albums.size(); i++) {
							System.out.println((i+1) + ". " + albums.get(i));
						}
					}
				} else if(option.equals("4")) {
					ArrayList<String> playlists = myLibrary.getLibraryPlaylistList();
					if(playlists.size() == 0) {
						System.out.println("No playlists in the library");
					} else {
						for(int i = 0; i < playlists.size(); i++) {
							System.out.println((i+1) + ". " +playlists.get(i));
						}
					}
				} else if(option.equals("5")) {
					ArrayList<String> favorite = myLibrary.getLibraryFavoriteSongs();
					if(favorite.size() == 0) {
						System.out.println("No favorite songs in the library");
					} else {
						for(int i = 0; i < favorite.size(); i++) {
							System.out.println((i+1) + ". " + favorite.get(i));
						}
					}
				} else {
					// do validation here!!!!!!!!
					System.out.println("Wrong input");
				}
				
			} else if (holdInputLower.split(" ")[0].equals("create") && holdInputLower.length() > 7
					&& !(holdInputLower.substring(7).contains(" "))) {
				
				System.out.println("DEBUG: Create Playlist");
			} else if (holdInputLower.split(" ")[0].equals("rate") && holdInputLower.length() > 5) {
				// This will not allow you to search for nothing. Sorry. (Unless you consider spaces as "nothing")
				rateSong(holdInputLower.split(" ")[1]);
				System.out.println("DEBUG: Rate Song");
			} else if (holdInputLower.split(" ")[0].equals("exit")) {
				// Kills the program
				running = false;
				System.out.println("DEBUG: EXITED");
			} else {
				System.out.println("Invalid instruction! Did you spell everything right? Resetting.");
				//System.out.println(""); // Extra whitespace
			}
			System.out.println(""); // Extra whitespace
			
		} // This one ends the loop, do NOT put code below here unless you're making an exit program message (or something).
	}
	
	// Functions.
	
	public static void main(String[] args) throws FileNotFoundException{
		View bleh = new View();
	}
	
	private void rateSong(String name) {
		// So, this is going to pick into musicstore, it's going to pass it the name,
		// musicstore will figure out which it COULD be, and will create a new custom
		// array list with copies of the songs to give back to us.
		// This will then ask the user for further validation.
		// songStorage = askMusicStoreForArrayListOfCopiedSongs(name) // Don't actually use that name.
		
	}
	
	private void printList() {
		// This method will be used by basically every other method that wants input except for main.
		// This takes the 3 lists, figures out which has something, and then prints it out so you can just
		// grab the user input.
		int type = 0;
		ArrayList<Object> looper = new ArrayList<Object>();
		// This code's probably as flimsy as my programming schedule
		if (songStorage.size() != 0) { type = 1;
			looper = new ArrayList<Object>(songStorage);
		} else if (albumStorage.size() != 0) { type = 2;
			looper = new ArrayList<Object>(albumStorage);
		} else if (playListStorage.size() != 0) { type = 3;
			looper = new ArrayList<Object>(playListStorage);
		} else {
		    System.out.println("Oh shit that's not good");
		    looper = new ArrayList<Object>();
		}
		for (int i = 0; i < looper.size(); i++) {
			System.out.print(i + ": ");
			if (type == 1) System.out.print(((Song) looper.get(i)).getSongName());
			// I... Don't know if this will work. In fact, i'm so unsure that i'll stop here.
			// Let's make the database able to return things for rateSong before we continue...	
		}
		
		
	}
}
