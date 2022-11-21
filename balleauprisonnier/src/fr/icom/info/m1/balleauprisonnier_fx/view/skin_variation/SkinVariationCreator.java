package fr.icom.info.m1.balleauprisonnier_fx.view.skin_variation;

import fr.icom.info.m1.balleauprisonnier_fx.App;
import javafx.scene.image.Image;

public class SkinVariationCreator {

	private String relativePath;

	public SkinVariationCreator(SkinVariation skinVariation) {
		if (skinVariation.equals(SkinVariation.BLUE))
			this.relativePath = "PlayerBlue.png";
		else if (skinVariation.equals(SkinVariation.RED))
			this.relativePath = "PlayerRed.png";
		else if (skinVariation.equals(SkinVariation.ORC))
			this.relativePath = "orc.png";
		else if (skinVariation.equals(SkinVariation.SKELETON))
			this.relativePath = "skeleton.png";
		else
			System.err.println("SkinCreator ne connait pas le type de skin en argument");
	}

	public Image getTileSheetImage() {
		return App.ressourceLoader.fromRelativePath(relativePath);
	}

}
