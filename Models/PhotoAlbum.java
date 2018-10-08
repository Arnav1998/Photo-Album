package models;

import java.util.ArrayList;
import java.util.List;

public class PhotoAlbum {

	private String name;
	private String description;
	private List<Image> photos;
	
	public PhotoAlbum(String name, String description) {
		this.name = name;
		this.description = description;
		photos = new ArrayList<Image>();
	}
	
	public void addImage(Image img) {
		this.photos.add(img);
	}
	
	public void deleteImage(Image img) {
		this.photos.remove(img);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Image> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Image> photos) {
		this.photos = photos;
	}
	

}
