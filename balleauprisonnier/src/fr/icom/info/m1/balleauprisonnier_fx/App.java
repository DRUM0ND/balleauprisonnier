package fr.icom.info.m1.balleauprisonnier_fx;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.icom.info.m1.balleauprisonnier_fx.controler.PlayerController;
import fr.icom.info.m1.balleauprisonnier_fx.controler.PlayerKeyboardMap;
import fr.icom.info.m1.balleauprisonnier_fx.model.Boundaries;
import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import fr.icom.info.m1.balleauprisonnier_fx.model.Team;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.HumanPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.PlayerBuilder;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;
import fr.icom.info.m1.balleauprisonnier_fx.util.ressource_loader.BaseRessourceLoader;
import fr.icom.info.m1.balleauprisonnier_fx.util.ressource_loader.RessourceLoader;
import fr.icom.info.m1.balleauprisonnier_fx.util.ressource_loader.RessourceLoaderProxy;
import fr.icom.info.m1.balleauprisonnier_fx.view.Field;
import fr.icom.info.m1.balleauprisonnier_fx.view.GameTimer;
import fr.icom.info.m1.balleauprisonnier_fx.view.GameView;
import fr.icom.info.m1.balleauprisonnier_fx.view.PlayerSkin;
import fr.icom.info.m1.balleauprisonnier_fx.view.ProjectileSkin;
import fr.icom.info.m1.balleauprisonnier_fx.view.VictoryPane;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.Collidable;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.CollisionConsequences;
import fr.icom.info.m1.balleauprisonnier_fx.view.component.GameMenuBar;
import fr.icom.info.m1.balleauprisonnier_fx.view.component.ScorePane;
import fr.icom.info.m1.balleauprisonnier_fx.view.skin_variation.SkinVariation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Classe principale de l'application s'appuie sur javafx pour le rendu
 */
public class App extends Application {
	public static RessourceLoader ressourceLoader = null;
	private Game game;
	private GameView gameView;
	private double screenWidth;
	private double screenHight;
	private Field field;
	private Boundaries boundaries;
	private GameTimer gameTimer;
	private Pane gameRoot;
	private Stage stage;

	/**
	 * En javafx start() lance l'application
	 *
	 * On cree le SceneGraph de l'application ici
	 * 
	 * @see http://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		
		URL url = App.class.getProtectionDomain().getCodeSource().getLocation();
		URI parent = url.toURI().resolve("./assets");
		App.ressourceLoader = new RessourceLoaderProxy(new BaseRessourceLoader(parent));
		
		
		stage.setTitle("BalleAuPrisonnier");
		
		VBox root = new VBox();
		
		boundaries = new Boundaries(0, 500, 500, 0, 0.2);
		field = new Field(boundaries);
		field.widthProperty().bind(root.widthProperty());
		field.heightProperty().bind(root.heightProperty());
		
		computeScreenSize();
		
		initGameAndGameView();
		boundaries.rightLimitProperty().bind(field.widthProperty());
		boundaries.bottomLimitProperty().bind(field.heightProperty());
		
		this.gameTimer = new GameTimer(game, gameView);
		MenuBar menuBar = new GameMenuBar(game, gameTimer);
		
		ScorePane scorePane = new ScorePane(game, gameTimer.getFrameRate());
		game.subscribe(scorePane);
		scorePane.maxHeightProperty().bind(field.heightProperty().subtract(120));
		
		
		this.gameRoot = new StackPane(gameView, scorePane);
		
		root.getChildren().addAll(menuBar, gameRoot);
		
		game.subscribe(gameView);
		Scene scene = new Scene(root);
		
		VictoryPane victoryPane = new VictoryPane(this, game);
		game.subscribe(victoryPane);

		stage.setScene(scene);
		stage.setHeight(screenHight);
		stage.setWidth(screenWidth);
		stage.setFullScreen(true);
		
		stage.show();
		
		gameTimer.start();
	}


	private void computeScreenSize() {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		screenWidth = screenBounds.getWidth();
		screenHight = screenBounds.getHeight();
	}

	private void initGameAndGameView() {
		
		Team team1 = new Team("team1");
		Team team2 = new Team("team2");
		
		final double gameSpeed = 2;
		
		Set<PlayerController> playerControllers = new HashSet<>();
		Map<Player, PlayerSkin> player2playerSkin = new HashMap<>();
		Set<HumanPlayer> humanPlayers = new HashSet<>();
		Set<AIPlayer> aiPlayers = new HashSet<>();
		Map<Collidable, CollisionConsequences> collidable2collisionConsequences = new HashMap<>();
		
		Projectile projectile = new Projectile(boundaries, gameSpeed*4);
		ProjectileSkin projectileSkin = new ProjectileSkin(field.getGraphicsContext2D());
		
		PlayerSkin playerSkin1 = new PlayerSkin(field.getGraphicsContext2D(), "top", SkinVariation.BLUE);
		PlayerKeyboardMap p1KeyboardMap = new PlayerKeyboardMap(KeyCode.Q, KeyCode.D, KeyCode.Z, KeyCode.S, KeyCode.SPACE, KeyCode.ESCAPE);
		HumanPlayer p1 = new PlayerBuilder().setX(screenWidth/2).setY(screenHight*0.2).setBoundaries(boundaries).setTeam(team1).buildHumanPlayer();
		PlayerController p1Controller = new PlayerController(p1, p1KeyboardMap);
		playerControllers.add(p1Controller);
		player2playerSkin.put(p1, playerSkin1);
		humanPlayers.add(p1);
		
		
		PlayerSkin playerSkin2 = new PlayerSkin(field.getGraphicsContext2D(), "bottom", SkinVariation.SKELETON);
		AIPlayer p2 = new PlayerBuilder().setX(screenWidth / 1.5).setY(screenHight-screenHight*0.2).setBoundaries(boundaries).setTeam(team2).setRequiredProjectile(projectile).buildAutoAIPlayer();
		player2playerSkin.put(p2, playerSkin2);
		aiPlayers.add(p2);
		
		PlayerSkin playerSkin3 = new PlayerSkin(field.getGraphicsContext2D(), "bottom", SkinVariation.ORC);
		AIPlayer p3 = new PlayerBuilder().setX(screenWidth / 1.5).setY(screenHight-screenHight*0.2).setBoundaries(boundaries).setTeam(team2).setRequiredProjectile(projectile).buildRandomAIPlayer();
		player2playerSkin.put(p3, playerSkin3);
		aiPlayers.add(p3);
		
		team1.setEnnemyTeam(team2);
		team2.setEnnemyTeam(team1);
		
		collidable2collisionConsequences.put(playerSkin1, p1);
		collidable2collisionConsequences.put(playerSkin2, p2);
		collidable2collisionConsequences.put(playerSkin3, p3);
		collidable2collisionConsequences.put(projectileSkin, projectile);
		
		Pair<Projectile, ProjectileSkin> projectile2projectileSkin = new Pair<>(projectile, projectileSkin);
		
		
		game = new Game(humanPlayers, aiPlayers, team1, team2, projectile, gameSpeed, this);
		gameView = new GameView(field, collidable2collisionConsequences, player2playerSkin, projectile2projectileSkin, playerControllers);
	}
	

	public static void main(String[] args) {
		Application.launch(args);
	}


	public void setGameRoot(Pane gameRoot) {
		BorderPane root = new BorderPane();
		root.setTop(new GameMenuBar(game, gameTimer));

		root.setCenter(gameRoot);
		stage.getScene().setRoot(root);
		this.gameRoot = root;
	}
	
	public GameTimer getGameTimer() {
		return this.gameTimer;
	}
}

