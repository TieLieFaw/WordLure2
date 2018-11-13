import base.ApplicationContainer;
import base.GraphicalInterface.initialScreen.initialScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class builds the program
 * @author Kirill(github.com/TieLieFaw)
 *
 */
public final class WordLureApplication extends ApplicationContainer {
		
		public static void main(String[] args) {
			launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			stage = primaryStage;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/base/GraphicalInterface/initialScreen/initialScreen.fxml"));
			initialScreenController c = new initialScreenController(this);
			loader.setController(c);
			
			Parent pane = loader.load();
			
			startScene = new Scene(pane);
			setPreferredScene(startScene);
			stage.setTitle("WordLure");
			stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/base/GraphicalInterface/WordLureIcon.png")));
			stage.show();
		}

		@Override
		public ApplicationContainer getApplicationContainer() {
			return this;
		}

		@Override
		public void setPreferredScene(Scene scene) {
			this.scene = scene;
			this.stage.setScene(this.scene);
		}

}