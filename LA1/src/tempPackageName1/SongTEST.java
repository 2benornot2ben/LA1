package tempPackageName1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongTEST {

	@Test
	void test_01() {
		Song initSong = new Song("Songname", "Artist", "Albumname", "Genre", "Year");
		assertEquals(initSong.getSongName(), "Songname");
		assertEquals(initSong.getArtist(), "Artist");
		assertEquals(initSong.getAlbumName(), "Albumname");
		assertEquals(initSong.getGenre(), "Genre");
		assertEquals(initSong.getYear(), "Year");
	}
	
	@Test
	void test_02() {
		Song initSong = new Song("Songname", "Artist", "Albumname", "Genre", "Year");
		initSong.favorite();
		assertEquals(initSong.getRating(), 0);
		assertTrue(initSong.getFavorited());
		initSong.unfavorite();
		assertFalse(initSong.getFavorited());
	}
	
	@Test
	void test_03() {
		Song initSong = new Song("Songname", "Artist", "Albumname", "Genre", "Year");
		assertEquals(initSong.getRating(), 0);
		initSong.setRating(4);
		assertEquals(initSong.getRating(), 4);
		assertFalse(initSong.getFavorited());
		initSong.setRating(5);
		assertEquals(initSong.getRating(), 5);
		assertTrue(initSong.getFavorited());
	}
	
	@Test
	void test_04() {
		Song initSong = new Song("1", "2", "3", "4", "");
		assertEquals(initSong.getArtist(), "2");
		assertEquals(initSong.getYear(), "");
		initSong.setRating(-420);
		assertEquals(initSong.getRating(), 1);
		assertFalse(initSong.getFavorited());
		initSong.setRating(72157195);
		assertEquals(initSong.getRating(), 5);
		assertTrue(initSong.getFavorited());
	}
	
	@Test
	void test_05() {
		Song initSong = new Song("Hello", "there you", "lovely people!", "Don't print this", "or this!");
		assertEquals(initSong.getPrintFormatted(), "Hello there you lovely people!");
	}
	
	@Test
	void test_06() {
		Song initSong = new Song("Songname", "Artist", "Albumname", "Genre", "Year");
		initSong.setRating(3);
		Song duplicatedSong = new Song(initSong);
		assertEquals(duplicatedSong.getSongName(), "Songname");
		assertEquals(duplicatedSong.getArtist(), "Artist");
		assertEquals(duplicatedSong.getAlbumName(), "Albumname");
		assertEquals(duplicatedSong.getGenre(), "Genre");
		assertEquals(duplicatedSong.getYear(), "Year");
		assertEquals(duplicatedSong.getRating(), 3);
		initSong.setRating(5);
		assertEquals(duplicatedSong.getRating(), 3);
		assertFalse(duplicatedSong.getFavorited());
		assertTrue(initSong.getFavorited());
	}

}
