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
	public MusicStore(){
		this.fileNames = new ArrayList<String>();
	}
	
	//method for creating filenames from the content of the albums.txt
	public void creatingFileNames(){
		// we can delete try catch and write throws FileNotFoundException to the method
		try (Scanner scanner = new Scanner(new File("albums.txt"))){
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String[] data = line.split(",");
				String albumFile = data[0] + "_" + data[1] + ".txt";
				fileNames.add(albumFile);
			}
			for(int i = 0; i < fileNames.size(); i++) {
				System.out.println(fileNames.get(i));
			}
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
				songs.add(new Song(songName, artist, albumTitle, genre, year));
			}
			myReader.close();
		}
	}
}
