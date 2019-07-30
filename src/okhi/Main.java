package okhi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

  private Stage stage;

  @Override
  public void start(Stage stage) throws Exception{
    this.stage = stage;
    Button button = new Button("Create OkHi Location");
    button.setOnAction(actionEvent ->  {
      this.launchOkHiLocationManager();
    });
    HBox hbox = new HBox(button);
    hbox.setPadding(new Insets(10, 5, 10, 5));
    this.changeScene(hbox);
  }

  public void launchOkHiLocationManager() {
    OkHiAuth auth = new OkHiAuth("r:b59a93ba7d80a95d89dff8e4c52e259a");
    OkHiUser user = new OkHiUser("Bob", "Markson", "0721856492");
    OkHiStyle style = new OkHiStyle("#ba0c2f", "Jubilee Insurance", "https://jubileeinsurance.com/ke/wp-content/themes/_ji/images/logo.svg");
    OkHi okhi = new OkHi(auth, user, style, OkHiDevMode.SANDBOX, new OkHiLocationHandler() {

      @Override
      public void onSuccess(OkHiLocation location, OkHiUser user) {
        // TODO: handle success here
      }

      @Override
      public void onError(OkHiError error) {
        // TODO: handle error here
      }
    });

    Parent locationManager = okhi.locationManager();
    changeScene(locationManager);
  }

  public void changeScene(Parent sceneType) {
    Scene scene = new Scene(sceneType, 960, 600);
    stage.setTitle("OkHi LocationManager Demo");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}