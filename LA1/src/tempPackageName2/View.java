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
	// We'll be reusing these 4 too. I hope this works.
	boolean minimize = false;
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
			if (minimize) {
				System.out.println("You can enter a new command now, or type anything wrong for the list again.");
			} else {
				// Alright. Let's make the first question here, just because it'd be annoying otherwise.
				System.out.println("State: Default");
				//System.out.println("Enter: \"Search\" \"Song OR Album OR Playlist\" to begin SEARCHING");
				System.out.println("Enter: \"Search\" \"MusicStore OR Library\" to begin SEARCHING (Library has Playlists)");
				System.out.println("Enter: \"Add\" \"Album OR Song\" to add to Library or Playlists");
				System.out.println("Enter: \"Create\" \"{PlaylistName}\" to create a new playlist. No spaces in the name."); 
				System.out.println("Enter: \"Rate\" \"{SongName}\" to rate a song. You only need to type the first few letters."); // This will also handle FAVORITING
				System.out.println("Enter: \"Get\" to get a list from the library."); 
				System.out.println("Enter: \"AddP\" to add a song to a playlist");
				System.out.println("Enter: \"RemoveP\" to remove a song from a playlist");
				System.out.println("Enter: \"Exit\" to kill the program. You will need to rerun it after.");
				System.out.println("This is not case sensitive, but is spelling sensitive.");
				minimize = true;
			}
			holdInput = getInput.nextLine();
			holdInputLower = holdInput.toLowerCase();
			if (holdInputLower.split(" ")[0].equals("search") && holdInputLower.split(" ").length > 1 &&
					(holdInputLower.split(" ")[1].equals("musicstore") || holdInputLower.split(" ")[1].equals("library"))) {
				String locationHolder = holdInputLower.split(" ")[1];
				System.out.println("");
				System.out.println("YOU CAN SEARCH FOR THE ...");
				System.out.println("1. Song by title");
				System.out.println("2. Song by artist");
				System.out.println("3. Album by title");
				System.out.println("4. Album by artist");
				if(locationHolder.equals("library")) {
					System.out.println("5. Playlist by name");
				}
				
				
				System.out.print("Enter the number of an option you want: ");
				String searching = getInput.nextLine();
				
				if (searching.equals("1") || searching.equals("2")) {
					String indicator = "";
					if (searching.equals("1")) indicator = "title";
					else indicator = "artist";
					System.out.print("Please enter the " +  indicator + " of the song: ");
					holdInput = getInput.nextLine();
					ArrayList<Song> resultList = myLibrary.searchByIndicatorSong(holdInput, locationHolder, indicator, false);
					if (resultList.size() == 0) {
						System.out.println("Sorry " + holdInput + " is not in the database.");
					} else {
						System.out.println("Search result: ");
						for(int i = 0; i < resultList.size(); i++) {
							System.out.println((i+1) + ": " + resultList.get(i).getPrintFormatted());
						}
					}
				}
				
				else if (searching.equals("3") || searching.equals("4")) {
					String indicator = "";
					if (searching.equals("3")) indicator = "title";
					else indicator = "artist";
					System.out.print("Please enter the " +  indicator + " of the album: ");
					holdInput = getInput.nextLine();
					ArrayList<Album> resultList = myLibrary.searchByIndicatorAlbum(holdInput, locationHolder, indicator, false);
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
				else if(searching.equals("5") && locationHolder.equals("library")) {
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
				else {
					System.out.println("Invalid number");
				}
			
			} else if (holdInputLower.split(" ")[0].equals("add") && (holdInputLower.split(" ")[1].equals("album")
					|| holdInputLower.split(" ")[1].equals("song"))) {
				System.out.println("");
				System.out.print("Enter a title of the " + holdInputLower.split(" ")[1] + " you want to add: ");
				String title = getInput.nextLine();
				System.out.print("Enter an artist of the " + holdInputLower.split(" ")[1] + " you want to add: ");
				String artist = getInput.nextLine();
				
				if (holdInputLower.split(" ")[1].equals("song")) {
					addSong_Function(title, artist);
				} else if (holdInputLower.split(" ")[1].equals("album")){
					addAlbum_Function(title, artist);
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
							System.out.println((i+1) + ". " + playlists.get(i));
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
				// Note: If you want spaces, be sure to edit the line above & below.
				String name = holdInputLower.split(" ")[1];
				boolean added = myLibrary.addPlayList(name);
				if(added) {
					System.out.println("The playlist " + name + " has been added to the library.");
				} else {
					System.out.println("The playlist " + name + " is already in the library.");
				}
				
			} else if (holdInputLower.split(" ")[0].equals("rate") && holdInputLower.length() > 5) {
				// This will not allow you to search for nothing. Sorry. (Unless you consider spaces as "nothing")
				//rateSong(holdInputLower.split(" ")[1]);
				System.out.println("DEBUG: Rate Song");
				
			// addp changing --------------------------------------------------------------- remove when done
			} else if (holdInputLower.split(" ")[0].equals("addp")) {
				System.out.println("");
				ArrayList<String> playlists = myLibrary.getLibraryPlaylistList();
				if(playlists.size() == 0) {
					System.out.println("No playlists in the library");
				} else {
					System.out.println("SELECT A PLAYLIST WHERE YOU WANT TO ADD A SONG...");
					for(int i = 0; i < playlists.size(); i++) {
						System.out.println((i+1) + ". " + playlists.get(i));
					}
					System.out.println("Enter a number of a playlist where you want to add: ");
					String number = getInput.nextLine();
					if(isNumeric(number)) {
			        	int num = Integer.parseInt(number);
			        	if (!(num > 0 && num <= playlists.size())) {
			        		System.out.println("Your input is invalid");
			        	}
			        	else {
			        		System.out.print("Enter a title of the song you want to add to a playlist: ");
			        		String title = getInput.nextLine();
							System.out.print("Enter an artist of the song you want to add to a playlist: ");
							String artist = getInput.nextLine();
							String playlistName = playlists.get(num-1);
							ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "musicstore", "title", true);
							if(songs.size() == 0) {
								System.out.println(title + " is not found in the store");
							}else {
								boolean added = false;
								boolean exist = false;
								for(int i = 0; i < songs.size(); i++) {
									if(songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
										added = myLibrary.addSongToPlaylist(playlistName, songs.get(i));
										exist = true;
									}
								}
								if(exist) {
									if(added) {
										System.out.println(title + " by " + artist + " has been added to " + playlistName);
									}
									else {
										System.out.println(title + " by " + artist + " is already in " + playlistName);
									}
								} else {
									System.out.println(title + " by " + artist + " is not in the store");
								}
							}
							
							
						}
			        } else {
			        	System.out.println("Your input is invalid");
			        }
				}	
				// addp changing --------------------------------------------------------------- remove when done
			} else if (holdInputLower.split(" ")[0].equals("removep")) {
				System.out.println("");
				ArrayList<String> playlists = myLibrary.getLibraryPlaylistList();
				if(playlists.size() == 0) {
					System.out.println("No playlists in the library");
				} else {
					System.out.println("SELECT A PLAYLIST FROM WHERE YOU WANT TO REMOVE A SONG...");
					for(int i = 0; i < playlists.size(); i++) {
						System.out.println((i+1) + ". " + playlists.get(i));
					}
					System.out.println("Enter a number of a playlist where you want to remove: ");
					String number = getInput.nextLine();
					if(isNumeric(number)) {
			        	int num = Integer.parseInt(number);
			        	if (!(num > 0 && num <= playlists.size())) {
			        		System.out.println("Your input is invalid");
			        	}
			        	else {
			        		System.out.print("Enter a title of the song you want to remove from a playlist: ");
			        		String title = getInput.nextLine();
							System.out.print("Enter an artist of the song you want to remove to a playlist: ");
							String artist = getInput.nextLine();
							String playlistName = playlists.get(num-1);
							boolean removed = false;
							boolean exist = false;
							ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "musicstore", "title", true);
							for(int i = 0; i < songs.size(); i++) {
								if(songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
									exist = true;
								}
							}
							removed = myLibrary.removeSongFromPlaylist(playlistName, title, artist);
							if(exist) {
								if(removed) {
									System.out.println(title + " by " + artist + " has been removed from " + playlistName);
								}
								else {
									System.out.println(title + " by " + artist + " is not in " + playlistName);
								}
							} else {
								System.out.println(title + " by " + artist + " is not in the store");
							}
						}
			        }
					else {
			        	System.out.println("Your input is invalid");
			        }
				}
			} else if (holdInputLower.split(" ")[0].equals("exit")) {
				// Kills the program
				running = false;
				System.out.println("DEBUG: EXITED");
			} else {
				System.out.println("Invalid instruction! Did you spell everything right? Resetting.");
				minimize = false;
				//System.out.println(""); // Extra whitespace
			}
			System.out.println(""); // Extra whitespace
			
		} // This one ends the loop, do NOT put code below here unless you're making an exit program message (or something).
	}
	
	// General Functions.
	
	private boolean isNumeric(String num) {
		boolean numeric = true;
        try {
            Double number = Double.parseDouble(num);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		View bleh = new View();
	}
	
	private void printAdditionText(boolean added, boolean exists, String title, String artist) {
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
	
	// Specific Functions
	private void addSong_Function(String title, String artist) {
		ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "musicstore", "title", false);
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
			printAdditionText(added, exists, title, artist);
		}
	}
	
	private void addAlbum_Function(String title, String artist) {
		ArrayList<Album> albums = myLibrary.searchByIndicatorAlbum(title, "musicstore", "title", false);
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
			printAdditionText(added, exists, title, artist);
		}
	}
}