package tempPackageName2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import tempPackageName1.*;

public class View {
	private LibraryModel myLibrary;
	boolean minimize = false;
	Scanner getInput = new Scanner(System.in);
	String holdInput = "";
	String holdInputLower = "";
	public View() throws FileNotFoundException{
		myLibrary = new LibraryModel();
		boolean running = true;
		while (running) {
			if (minimize) {
				System.out.println("You can enter a new command now, or type anything else for the list again.");
			} else {
				System.out.println("Enter: \"Search\" \"MusicStore OR Library\" to begin SEARCHING (Library has Playlists)");
				System.out.println("Enter: \"Add\" \"Album OR Song\" to add to Library or Playlists");
				System.out.println("Enter: \"Create\" \"{PlaylistName}\" to create a new playlist. No spaces in the name."); 
				System.out.println("Enter: \"Rate\" to rate a song.");
				System.out.println("Enter: \"Get\" to get a list from the library."); 
				System.out.println("Enter: \"AddP\" to add a song to a playlist");
				System.out.println("Enter: \"RemoveP\" to remove a song from a playlist");
				System.out.println("Enter: \"Markf\" to mark a song favorite");
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
				if (locationHolder.equals("library")) System.out.println("5. Playlist by name");
				
				
				System.out.print("Enter the number of an option you want: ");
				String searching = getInput.nextLine();
				
				if (searching.equals("1") || searching.equals("2")) {
					searchSong_Function(searching, locationHolder);
				}
				
				else if (searching.equals("3") || searching.equals("4")) {
					searchAlbum_Function(searching, locationHolder);
				}
				else if(searching.equals("5") && locationHolder.equals("library")) {
					searchPlayList_Function();
				}
				else {
					System.out.println("Invalid number");
				}
			
			} else if (holdInputLower.split(" ")[0].equals("add") && holdInputLower.split(" ").length > 1 &&
					(holdInputLower.split(" ")[1].equals("album") || holdInputLower.split(" ")[1].equals("song"))) {
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
				System.out.print("Enter the number of an option you want: ");
				String option = getInput.nextLine();
				System.out.println("");
				if(option.equals("1")) {
					ArrayList<String> songNames = myLibrary.getLibrarySongList();
					if(songNames.size() == 0) System.out.println("No songs in the library");
					else getPrintText(songNames);
				} else if(option.equals("2")) {
					ArrayList<String> artists = myLibrary.getLibraryArtistList();
					if(artists.size() == 0) System.out.println("No artists in the library");
					else getPrintText(artists);
				} else if(option.equals("3")) {
					ArrayList<String> albums = myLibrary.getLibraryAlbumList();
					if (albums.size() == 0) System.out.println("No albums in the library");
					else getPrintText(albums);
				} else if(option.equals("4")) {
					ArrayList<String> playLists = myLibrary.getLibraryPlayListList();
					if(playLists.size() == 0) System.out.println("No playlists in the library");
					else getPrintText(playLists);
				} else if(option.equals("5")) {
					ArrayList<String> favorite = myLibrary.getLibraryFavoriteSongs();
					if(favorite.size() == 0) System.out.println("No favorite songs in the library");
					else getPrintText(favorite);
				} else {
					System.out.println("Wrong input");
				}
				
			} else if (holdInputLower.split(" ")[0].equals("create") && holdInputLower.length() > 7
					&& !(holdInputLower.substring(7).contains(" "))) {
				// This will STILL block spaces. It's probably too late to change that.
				String name = holdInputLower.split(" ")[1];
				boolean added = myLibrary.addPlayList(name);
				System.out.println("");
				if (added) System.out.println("The playlist " + name + " has been added to the library.");
				else System.out.println("The playlist " + name + " is already in the library.");
				
			} else if (holdInputLower.split(" ")[0].equals("rate")) {
				System.out.println("");
				System.out.print("Enter a title of the song you want to rate: ");
        		String title = getInput.nextLine();
				System.out.print("Enter an artist of the song you want to rate: ");
				String artist = getInput.nextLine();
				ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "library", "title", true);
				if(songs.size() == 0) {
					System.out.println("");
					System.out.println(title + " is not found in the library");
				} else {
					System.out.print("Enter a rating (between 1 and 5): ");
					String number = getInput.nextLine();
					System.out.println("");
					rateSong_Function(songs, title, artist, number);
				}	
			} else if (holdInputLower.split(" ")[0].equals("markf")) {
				System.out.println("");
				System.out.print("Enter a title of the song you want to mark as favorite: ");
        		String title = getInput.nextLine();
				System.out.print("Enter an artist of the song you want to mark as favorite: ");
				String artist = getInput.nextLine();
				System.out.println("");
				ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "library", "title", true);
				if(songs.size() == 0) {
					System.out.println(title + " is not found in the library");
				} else {
					markFavorite_Function(songs, title, artist);
				}
				
			} else if (holdInputLower.split(" ")[0].equals("addp")) {
				System.out.println("");
				ArrayList<String> playlists = myLibrary.getLibraryPlayListList();
				if(playlists.size() == 0) {
					System.out.println("No playlists in the library");
				} else {
					System.out.println("SELECT A PLAYLIST WHERE YOU WANT TO ADD A SONG...");
					for(int i = 0; i < playlists.size(); i++) {
						System.out.println((i+1) + ". " + playlists.get(i));
					}
					System.out.println("Enter a number of a playlist where you want to add: ");
					String number = getInput.nextLine();
					System.out.println("");
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
							String playListName = playlists.get(num-1);
							System.out.println("");
							ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "library", "title", true);
							if(songs.size() == 0) {
								System.out.println(title + " is not found in the library");
							} else {
								addToPlayList_Function(songs, title, artist, playListName);
							}
							
							
						}
			        } else {
			        	System.out.println("Your input is invalid");
			        }
				}	
			} else if (holdInputLower.split(" ")[0].equals("removep")) {
				System.out.println("");
				ArrayList<String> playlists = myLibrary.getLibraryPlayListList();
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
							String playListName = playlists.get(num-1);
							System.out.println("");
							removeFromPlayList_Function(title, artist, playListName);
						}
			        }
					else {
			        	System.out.println("Your input is invalid");
			        }
				}
			} else if (holdInputLower.split(" ")[0].equals("exit")) {
				// Kills the program
				running = false;
				System.out.println("Program exited.");
			} else {
				System.out.println("Invalid instruction! Did you spell everything right? Resetting.");
				minimize = false;
				//System.out.println(""); // Extra whitespace
			}
			System.out.println(""); // Extra whitespace
			
		} 
	}
	
	// General Functions.
	
	private boolean isNumeric(String num) {
		boolean numeric = true;
        try {
            Double.parseDouble(num);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		new View();
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
	
	private void getPrintText(ArrayList<String> listToRepeat) {
		for(int i = 0; i < listToRepeat.size(); i++) {
			System.out.println((i+1) + ". " + listToRepeat.get(i));
		}
	}
	
	// Specific Functions
	private void searchSong_Function(String searching, String locationHolder) {
		String indicator = "";
		if (searching.equals("1")) indicator = "title";
		else indicator = "artist";
		System.out.print("Please enter the " +  indicator + " of the song: ");
		holdInput = getInput.nextLine();
		System.out.println("");
		ArrayList<Song> resultList = myLibrary.searchByIndicatorSong(holdInput, locationHolder, indicator, false);
		if (resultList.size() == 0) {
			System.out.println("Sorry " + holdInput + " is not in the " + locationHolder + ".");
		} else {
			System.out.println("Search result: ");
			for(int i = 0; i < resultList.size(); i++) {
				System.out.println((i+1) + ": " + resultList.get(i).getPrintFormatted());
			}
		}
	}
	
	private void searchAlbum_Function(String searching, String locationHolder) {
		String indicator = "";
		if (searching.equals("3")) indicator = "title";
		else indicator = "artist";
		System.out.print("Please enter the " +  indicator + " of the album: ");
		holdInput = getInput.nextLine();
		ArrayList<Album> resultList = myLibrary.searchByIndicatorAlbum(holdInput, locationHolder, indicator, false);
		System.out.println("");
		if (resultList.size() == 0) {
			System.out.println("Sorry " + holdInput + " is not in the " + locationHolder + ".");
		} else {
			System.out.println("Search result: ");
			for(int i = 0; i < resultList.size(); i++) {
				System.out.println("   ~ " + resultList.get(i).getPrintFormatted());
				for(int j = 0; j < resultList.get(i).getSongList().size(); j++) {
					System.out.println((j+1) + ": " + resultList.get(i).getSongList().get(j).getSongName());
				}
				System.out.println("");
			}
		}
	}
	
	private void searchPlayList_Function() {
		System.out.print("Please enter the name of the playlist: ");
		String nameOfPlayList = getInput.nextLine();
		PlayList result = myLibrary.searchByNamePlayList(nameOfPlayList);
		System.out.println("");
		if (result.getPlayListName().equals("")) {
			System.out.println("Sorry " + nameOfPlayList + " is not in the library.");
		} else {
			System.out.println("Search result: ");
			System.out.println("   ~ " + result.getPlayListName());
			for (int i = 0; i < result.getSongList().size(); i++) {
				System.out.println((i+1) + ": " + result.getSongList().get(i).getSongName() 
						+ ", " + result.getSongList().get(i).getArtist());
			}
		}
	}
	
	
	private void addSong_Function(String title, String artist) {
		ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "musicstore", "title", true);
		System.out.println("");
		if(songs.size() == 0) {
			System.out.println(title + " is not found in the store");
		}else {
			boolean added = false;
			boolean exists = false;
			for (int i = 0; i < songs.size(); i++) {
				if (songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
					if (myLibrary.canAddSongToList(songs.get(i))) {
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
		ArrayList<Album> albums = myLibrary.searchByIndicatorAlbum(title, "musicstore", "title", true);
		System.out.println("");
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
	
	private void rateSong_Function(ArrayList<Song> songs, String title, String artist, String number) {
        if(isNumeric(number)) {
        	int num = Integer.parseInt(number);
        	if(num > 0 && num < 6) {
        		boolean rated = false;
				boolean exist = false;
				for(int i = 0; i < songs.size(); i++) {
					if(songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
						myLibrary.rateSong(songs.get(i), num);
						exist = true;
						rated = true;
					}
				}
				if (exist) {
					if (rated) System.out.println(title + " by " + artist + " has been rated.");
					else System.out.println(title + " by " + artist + " not found.");
				} else System.out.println(title + " by " + artist + " is not in the library");
        	} else System.out.println("Rating should be between 1 and 5!");
        } else System.out.println("Invalid input. Enter a number between 1 and 5!");
	}
	
	private void markFavorite_Function(ArrayList<Song> songs, String title, String artist) {
		boolean marked = false;
		boolean exist = false;
		for(int i = 0; i < songs.size(); i++) {
			if(songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
				marked = myLibrary.markSongAsFavorite(songs.get(i));
				exist = true;
			}
		}
		if (exist) {
			if (marked) System.out.println(title + " by " + artist + " has been marked as favorite");
			else System.out.println(title + " by " + artist + " is already marked as favorite");
		} else System.out.println(title + " by " + artist + " is not in the library");
	}
	
	private void addToPlayList_Function(ArrayList<Song> songs, String title, String artist, String playListName) {
		boolean added = false;
		boolean exist = false;
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
				added = myLibrary.addSongToPlaylist(playListName, songs.get(i));
				exist = true;
			}
		}
		if (exist) {
			if (added) System.out.println(title + " by " + artist + " has been added to " + playListName);
			else System.out.println(title + " by " + artist + " is already in " + playListName);
		} else System.out.println(title + " by " + artist + " is not in the library");
	}
	
	private void removeFromPlayList_Function(String title, String artist, String playListName) {
		boolean removed = false;
		boolean exist = false;
		ArrayList<Song> songs = myLibrary.searchByIndicatorSong(title, "musicstore", "title", true);
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getArtist().toLowerCase().equals(artist.toLowerCase())) {
				exist = true;
			}
		}
		removed = myLibrary.removeSongFromPlaylist(playListName, title, artist);
		if (exist) {
			if (removed) System.out.println(title + " by " + artist + " has been removed from " + playListName);
			else System.out.println(title + " by " + artist + " is not in " + playListName);
		} else System.out.println(title + " by " + artist + " is not in the store");
	}
}
