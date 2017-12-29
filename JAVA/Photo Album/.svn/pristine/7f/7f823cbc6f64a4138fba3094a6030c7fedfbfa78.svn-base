package ca.ubc.cs.cpsc210.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cs.cpsc210.photo.Album;
import ca.ubc.cs.cpsc210.photo.Photo;

/**
 * Tests Album, except visual support (like display)
 */
public class AlbumTest {

	private Album album1;
    private Photo photo1;
    private Photo photo2;

	@Before
	public void setUp() {
		album1 = new Album("1");
		photo1 = new Photo("1");
		photo2 = new Photo("2");
	}

	@Test
	public void testAddPhoto()  {
		album1.addPhoto(photo1);	
		Set<Photo> photosInAlbum = album1.getPhotos();
		assertEquals(1, photosInAlbum.size());
		assertTrue(photosInAlbum.contains(photo1));	
		assertEquals(album1, photo1.getAlbum());
	}

    @Test
	public void testAddMoreThanOnePhoto()  {
		album1.addPhoto(photo1);
		album1.addPhoto(photo2);
		Set<Photo> photosInAlbum = album1.getPhotos();
		assertEquals(2, photosInAlbum.size());
		assertTrue(photosInAlbum.contains(photo1));
		assertTrue(photosInAlbum.contains(photo2));
		
		assertEquals(album1, photo1.getAlbum());
		assertEquals(album1, photo2.getAlbum());
	}
}
