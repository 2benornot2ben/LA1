package backend;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LibraryModelTEST {

    private LibraryModel libraryModel;

    @Before
    public void setUp() throws FileNotFoundException {
        libraryModel = new LibraryModel();
    }

    @Test
    public void testAddSongToList() {
        Song song1 = new Song("1", "1", "1", "1", "1");
        Song song2 = new Song("1", "1", "1", "1", "1");
        libraryModel.addSongToList(song1);
        libraryModel.addSongToList(song2);
        assertTrue(libraryModel.getLibrarySongList().contains("1"));
        assertTrue(libraryModel.getLibraryArtistList().contains("1"));
        assertFalse(libraryModel.getLibraryArtistList().contains("2"));
    }

    // does not cover fully.
    @Test
    public void testAddAlbumToList() {
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("1", "1", "1", "1", "1"));
        songs.add(new Song("2", "2", "2", "2", "2"));
        Album album = new Album("al1", "al1", "al1", "al1");
        libraryModel.addAlbumToList(album);
        assertTrue(libraryModel.getLibraryAlbumList().contains("al1"));
        assertTrue(libraryModel.getLibraryArtistList().contains("al1"));
    }
    
    // cannot cover all the branches because some statements are working with database.
    @Test
    public void testSearchByIndicatorSong() {
        Song song1 = new Song("1", "1", "1", "1", "1");
        Song song2 = new Song("2", "2", "2", "2", "2");
        libraryModel.addSongToList(song1);
        libraryModel.addSongToList(song2);

        ArrayList<Song> result = libraryModel.searchByIndicatorSong("1", "library", "title", true);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getSongName());

        result = libraryModel.searchByIndicatorSong("2", "library", "artist", true);
        assertEquals(1, result.size());
        assertEquals("2", result.get(0).getArtist());
    }
    
    // cannot cover all the branches because some statements are working with database.
    @Test
    public void testSearchByIndicatorAlbum() {
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("1", "1", "1", "1", "1"));
        Album album1 = new Album("a1", "a1", "a1", "a1");
        Album album2 = new Album("a2", "a2", "a2", "a2");
        libraryModel.addAlbumToList(album1);
        libraryModel.addAlbumToList(album2);
        ArrayList<Album> result = libraryModel.searchByIndicatorAlbum("a1", "library", "title", true);
        assertEquals(1, result.size());
        assertEquals("a1", result.get(0).getAlbumName());
        result = libraryModel.searchByIndicatorAlbum("a2", "library", "artist", true);
        assertEquals(1, result.size());
        assertEquals("a2", result.get(0).getArtist());
    }

    @Test
    public void testAddPlayList() {
        assertTrue(libraryModel.addPlayList("1"));
        assertFalse(libraryModel.addPlayList("1"));
        assertFalse(libraryModel.addPlayList("1".toUpperCase()));
        assertFalse(libraryModel.addPlayList("1".toLowerCase()));
        assertTrue(libraryModel.addPlayList("2"));
    }

    @Test
    public void testMarkSongAsFavorite() {
        Song song1 = new Song("1", "1", "1", "1", "1");
        libraryModel.addSongToList(song1);
        assertTrue(libraryModel.markSongAsFavorite(song1));
        assertFalse(libraryModel.markSongAsFavorite(song1));
        Song song2 = new Song("2", "2", "2", "2", "2");
        assertFalse(libraryModel.markSongAsFavorite(song2));
    }


    @Test
    public void testRateSong() {
        Song song1 = new Song("1", "1", "1", "1", "1");
        Song song2 = new Song("2", "2", "2", "2", "2");
        libraryModel.addSongToList(song1);
        libraryModel.rateSong(song1, 5);
        assertTrue(libraryModel.getLibraryFavoriteSongs().contains("1"));
        libraryModel.rateSong(song1, 3);
        assertFalse(libraryModel.getLibraryFavoriteSongs().contains("1"));
        libraryModel.rateSong(song2, 4);
        assertFalse(libraryModel.getLibraryFavoriteSongs().contains("2"));
    }

    @Test
    public void testAddSongToPlaylist() {
    	Song song = new Song("1", "1", "1", "1", "1");
        PlayList playlist = new PlayList("p1");
        playlist.addSong(song);
        libraryModel.addPlayList("p1");
        libraryModel.addSongToList(song);
        assertTrue(libraryModel.addSongToPlaylist("p1", song));
        assertFalse(libraryModel.addSongToPlaylist("p2", song));
        assertFalse(libraryModel.addSongToPlaylist("p1", song));
    }

    @Test
    public void testRemoveSongFromPlaylist() {
    	Song song = new Song("1", "1", "1", "1", "1");
        libraryModel.addSongToList(song);
        libraryModel.addPlayList("p1");
        libraryModel.addPlayList("p2");
        libraryModel.addSongToPlaylist("p1", song);
        assertTrue(libraryModel.removeSongFromPlaylist("p1", "1", "1"));
        assertFalse(libraryModel.removeSongFromPlaylist("p1", "1", "1"));
        assertFalse(libraryModel.removeSongFromPlaylist("p3", "1", "1"));
        assertFalse(libraryModel.removeSongFromPlaylist("p2", "1", "1"));
    }
    
    @Test
    public void testGetLibraryPlayListList() {
        libraryModel.addPlayList("p1");
        libraryModel.addPlayList("p2");
        ArrayList<String> playlists = libraryModel.getLibraryPlayListList();
        assertTrue(playlists.contains("p1"));
        assertTrue(playlists.contains("p2"));
        assertEquals(2, playlists.size());
    }
    
    @Test
    public void testSearchByNamePlayList() {
        libraryModel.addPlayList("Playlist1");
        PlayList result = libraryModel.searchByNamePlayList("Playlist1");
        assertNotNull(result);
        assertEquals("Playlist1", result.getPlayListName());
        result = libraryModel.searchByNamePlayList("NonexistentPlaylist");
        assertEquals("", result.getPlayListName());
        result = libraryModel.searchByNamePlayList("playlist1");
        assertNotNull(result);
        assertEquals("Playlist1", result.getPlayListName());
    }
    
    @Test
    public void testCanAddSongToList() {
        Song song1 = new Song("Song1", "Artist1", "Genre1", "Album1", "2023");
        Song song2 = new Song("Song2", "Artist2", "Genre2", "Album2", "2024");
        libraryModel.addSongToList(song1);
        libraryModel.addSongToList(song2);
        assertFalse(libraryModel.canAddSongToList(new Song("Song1", "Artist1", "Genre1", "Album1", "2023")));
        assertTrue(libraryModel.canAddSongToList(new Song("Song3", "Artist1", "Genre1", "Album3", "2025")));
        assertTrue(libraryModel.canAddSongToList(new Song("Song1", "Artist3", "Genre1", "Album1", "2023")));
        assertTrue(libraryModel.canAddSongToList(new Song("Song3", "Artist3", "Genre1", "Album3", "2025")));
    }

    @Test
    public void testCanAddAlbumToList() {
        Album album1 = new Album("Album1", "Artist1", "Genre1", "2023");
        Album album2 = new Album("Album2", "Artist2", "Genre2", "2024");
        libraryModel.addAlbumToList(album1);
        libraryModel.addAlbumToList(album2);
        assertFalse(libraryModel.canAddAlbumToList(new Album("Album1", "Artist1", "Genre1", "2023")));
        assertTrue(libraryModel.canAddAlbumToList(new Album("Album3", "Artist1", "Genre1", "2025")));
        assertTrue(libraryModel.canAddAlbumToList(new Album("Album1", "Artist3", "Genre1", "2023")));
        assertTrue(libraryModel.canAddAlbumToList(new Album("Album3", "Artist3", "Genre1", "2025")));
    }


}
