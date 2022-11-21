package fr.icom.info.m1.balleauprisonnier_fx.util.ressource_loader;

import java.net.URI;

import javafx.scene.image.Image;

public class BaseRessourceLoader implements RessourceLoader {

	private URI absolutePathOrResFolder;

	public BaseRessourceLoader(URI absolutePathOfResFolder) {
		this.absolutePathOrResFolder = absolutePathOfResFolder;
	}

	@Override
	public Image fromRelativePath(String path) {
		return new Image(absolutePathOrResFolder.toString()+ "/" + path);
	}
}
