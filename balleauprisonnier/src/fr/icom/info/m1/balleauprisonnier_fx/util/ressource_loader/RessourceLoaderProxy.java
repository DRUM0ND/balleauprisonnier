package fr.icom.info.m1.balleauprisonnier_fx.util.ressource_loader;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class RessourceLoaderProxy implements RessourceLoader {

	private RessourceLoader ressourceLoader;
	private Map<String, Image> imageStorage;
	
	public RessourceLoaderProxy(RessourceLoader ressourceLoader) {
		this.ressourceLoader = ressourceLoader;
		this.imageStorage = new HashMap<>();
	}

	@Override
	public Image fromRelativePath(String path) {
		if (imageStorage.containsKey(path))
			return imageStorage.get(path);
		else
			return ressourceLoader.fromRelativePath(path);
	}

}
