package tempPackageName2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/*
 * LAST UPDATED 24 Feb, 11 p.m:
 * I did searching from store and library, adding a song.
 * I will finish everything tomorrow hopefully. 
 * 
 * 
 * ATTENTION!!!
 * I know there are a lot of duplicated code. I will edit them by adding helper methods.
 * 
 * AND:
 * I did not use storages because I did not really understand its use.
 * 
 * 
 * The methods are tested everything is working.
 * 
 * 
 * 
 * I updated most of the file. 
 */





import tempPackageName1.*; // New stuff here, hope I got this...

// Here we go.

// Oh yeah, no need for a constructor this time. I hope?

// Alright, the general plan is to have this main just be a neverending loop of asking for shit.
// Then the main will call subfunctions of view to make everything work as intended.
// These subfunctions will be private.

public class View {
	// These three lists are to be WIPED after usage! They will only hold CLONES, NEVER pointers into it!
	// Because of this, we need CUSTOM equal functions (or similar) for most end-of-function usage!
	// We are wiping them because we want most functions to handle usage across ALL THREE of these!
	// Thus, they will not know which one to look at - and will use size() to determine. (Most likely.)
	// Also, use copy constructors for making these, please don't hard code it... (I mean you can but it'd be painful)
	private ArrayList<Song> songStorage = new ArrayList<Song>();
	private ArrayList<Album> albumStorage = new ArrayList<Album>();
	private ArrayList<PlayList> playListStorage = new ArrayList<PlayList>();
	private LibraryModel myLibrary;
	// We'll be reusing these 3 too. I hope this works.
	Scanner getInput = new Scanner(System.in);
	String holdInput = "";
	String holdInputLower = "";
	public View() throws FileNotFoundException{
		myLibrary = new LibraryModel();
		boolean running = true;
		while (running) {
			// Whenever it gets reset to having NO current input, it gets reset here.
			// To make our life easier, i'll make it wipe everything too.
			songStorage.clear();
			albumStorage.clear();
			playListStorage.clear();
			// Alright. Let's make the first question here, just because it'd be annoying otherwise.
			System.out.println("State: Default");
			//System.out.println("Enter: \"Search\" \"Song OR Album OR Playlist\" to begin SEARCHING");
			System.out.println("Enter: \"Search\" \"MusicStore OR Library\" to begin SEARCHING (Library has Playlists)"); // This will also handle LISTING
			System.out.println("Enter: \"Add\" \"Album OR Song\" to add to Library or Playlists");
			System.out.println("Enter: \"Create\" \"{PlaylistName}\" to create a new playlist. No spaces in the name."); 
			System.out.println("Enter: \"Rate\" \"{SongName}\" to rate a song. You only need to type the first few letters."); // This will also handle FAVORITING
			System.out.println("Enter: \"Exit\" to kill the program. You will need to rerun it after.");
			System.out.println("This is not case sensitive, but is spelling sensitive.");
			holdInput = getInput.nextLine();
			holdInputLower = holdInput.toLowerCase();
			if (holdInputLower.split(" ")[0].equals("search") && holdInputLower.split(" ").length > 1 &&
					holdInputLower.split(" ")[1].equals("musicstore") || holdInputLower.split(" ")[1].equals("library")) {
				String locationHolder = holdInputLower.split(" ")[1];
				System.out.println("");
				System.out.println("YOU CAN SEARCH FOR THE ...");
				System.out.println("1. Song by title");
				System.out.println("2. Song by artist");
				System.out.println("3. Album by title");
				System.out.println("4. Album by artist");
				System.out.print("Enter the number of an option you want: ");
				String searching = getInput.nextLine();
				
				//the code below uses duplicated code. We should write a separate function to escape duplicated codes.
				// i will leave them as it is for now.
				
				/*
				 * These two methods should be retired. Keeping them around, just incase.
				 * 
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
				*/
				
				if (searching.equals("1") || searching.equals("2")) {
					String indicator = "";
					if (searching.equals("1")) indicator = "title";
					else indicator = "artist";
					System.out.print("Please enter the " +  indicator + " of the song: ");
					holdInput = getInput.nextLine();
					ArrayList<Song> resultList = myLibrary.searchByIndicatorSong(holdInput, locationHolder, indicator);
					if (resultList.size() == 0) {
						System.out.println("Sorry " + holdInput + " is not in the database.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ": " + resultList.get(i).getPrintFormatted());
						}
					}
				}
				/*
				 * Retired?
				 * 
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
				*/
				if (searching.equals("3") || searching.equals("4")) {
					String indicator = "";
					if (searching.equals("3")) indicator = "title";
					else indicator = "artist";
					System.out.print("Please enter the " +  indicator + " of the album: ");
					holdInput = getInput.nextLine();
					ArrayList<Album> resultList = myLibrary.searchByIndicatorAlbum(holdInput, locationHolder, indicator);
					// Literally ALL OF BELOW is the same as song! Make method to remove duplicating!
					if (resultList.size() == 0) {
						System.out.println("Sorry " + indicator + " is not in the database.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ") " + resultList.get(i).getPrintFormatted());
							for(int j = 0; j < resultList.get(i).getSongList().size(); j++) {
								System.out.println((j+1) + ": " + resultList.get(i).getSongList().get(j).getSongName());
							}
							System.out.println("");
						}
					}
				}
			/*
			 * NUCLEAR DEVISTATION
			 * 
			} else if (holdInputLower.split(" ")[0].equals("search") && holdInputLower.split(" ").length > 1 && 
					holdInputLower.split(" ")[1].equals("library")) {
				System.out.println("");
				System.out.println("YOU CAN SEARCH FOR THE ...");
				System.out.println("1. Song by title");
				System.out.println("2. Song by artist");
				System.out.println("3. Album by title");
				System.out.println("4. Album by artist");
				System.out.println("5. Playlist by name");
				System.out.print("Enter the number of an option you want: ");
				String searching = getInput.nextLine();
				
				//the code below uses duplicated code. We should write a separate function to escape duplicated codes.
				// i will leave them as it is for now.
				
				if(searching.equals("1")) {
					System.out.print("Please enter the title of the song: ");
					String titleOfSong = getInput.nextLine();
					ArrayList<Song> resultList = myLibrary.searchByTitleSong(titleOfSong, "library");
					if (resultList.size() == 0) {
						System.out.println("Sorry " + titleOfSong + " is not in the library.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ": " + resultList.get(i).getPrintFormatted());
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
							System.out.println((i+1) + ": " + resultList.get(i).getPrintFormatted());
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
				*/
			} else if (holdInputLower.split(" ")[0].equals("add") && holdInputLower.split(" ").length > 1 && 
					(holdInputLower.split(" ")[1].equals("album") || holdInputLower.split(" ")[1].equals("song"))) {
				System.out.println("");
//				System.out.println("YOU OPTIONS TO ADD TO THE LIBRARY: ");
//				System.out.println("1. Song");
//				System.out.println("1. Album");
//				System.out.print("Enter the number of an option you want: ");
				System.out.print("Enter a title of the " + holdInputLower.split(" ")[1] + " you want to add: ");
				String title = getInput.nextLine();
				System.out.print("Enter an artist of the " + holdInputLower.split(" ")[1] + " you want to add: ");
				String artist = getInput.nextLine();
				
				
				
				//I guess we need to validate the input, idk
				
				if(holdInputLower.split(" ")[1].equals("song")) {
					//ArrayList<Song> songs = myLibrary.searchByTitleSong(title, "musicstore");
					ArrayList<Song> songs = new ArrayList<Song>(); // 
					if(songs.size() == 0) {
						System.out.println(title + " is not found in the store");
					}
					else {
						boolean added = false;
						for(int i = 0; i < songs.size(); i++) {
							if(songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
								
								//i am not sure if this causes an escaping reference.
								myLibrary.addSongToList(songs.get(i));
								added = true;
								break;
							}
						}
						if(!added) {
							System.out.println(title + " by " + artist + " is not found in the store");
						}
						else {
							System.out.println(title + " by " + artist + " has been added to the library");
						}
					}
				}
				
				
			} else if (holdInputLower.split(" ")[0].equals("create") && holdInputLower.length() > 7
					&& !(holdInputLower.substring(7).contains(" "))) {
				// This will not allow you to make a playlist if there's a space anywhere in the name.
				// Function...
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