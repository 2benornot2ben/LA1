package tempPackageName2;

import java.util.ArrayList;
import java.util.Scanner;

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
	// We'll be reusing these 3 too. I hope this works.
	Scanner getInput = new Scanner(System.in);
	String holdInput = "";
	String holdInputLower = "";
	public void main(String args[]) {
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
			System.out.println("This is not case sensitive, but is spelling sensitive.");
			holdInput = getInput.nextLine();
			holdInputLower = holdInput.toLowerCase();
			if (holdInputLower.split(" ")[0].equals("search") && holdInputLower.split(" ")[1].equals("musicstore")) {
				// I unfortunately have to split these two because of playlists. Shame.
				// Function...
				System.out.println("DEBUG: Search MusicStore");
			} else if (holdInputLower.split(" ")[0].equals("search") && holdInputLower.split(" ")[1].equals("library")) {
				// Function...
				System.out.println("DEBUG: Search Library/Playlist");
			} else if (holdInputLower.split(" ")[0].equals("add") && (holdInputLower.split(" ")[1].equals("album")
					|| holdInputLower.split(" ")[1].equals("song"))) {
				// Input validation. Next function will handle the split between the two types. I think...
				// Function...
				System.out.println("DEBUG: Add Album/Song");
			} else if (holdInputLower.split(" ")[0].equals("create") && holdInputLower.length() > 7
					&& !(holdInputLower.substring(7).contains(" "))) {
				// This will not allow you to make a playlist if there's a space anywhere in the name.
				// Function...
				System.out.println("DEBUG: Create Playlist");
			} else if (holdInputLower.split(" ")[0].equals("rate") && holdInputLower.length() > 5) {
				// This will not allow you to search for nothing. Sorry. (Unless you consider spaces as "nothing")
				rateSong(holdInputLower.split(" ")[1]);
				System.out.println("DEBUG: Rate Song");
			} else {
				System.out.println("Invalid instruction! Did you spell everything right? Resetting.");
				//System.out.println(""); // Extra whitespace
			}
			System.out.println(""); // Extra whitespace
			
		} // This one ends the loop, do NOT put code below here unless you're making an exit program message (or something).
	}
	
	// Functions.
	
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
