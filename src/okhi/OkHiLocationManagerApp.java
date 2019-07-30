package okhi;

public class OkHiLocationManagerApp implements OkHiLocationManagerBridge {

  private OkHiLocationHandler okHiLocationHandler;

  public String name = this.getClass().getSimpleName();

  public OkHiLocationManagerApp(OkHiLocationHandler okHiLocationHandler) {
    this.okHiLocationHandler = okHiLocationHandler;
  }

  public void receiveMessage(String message) {
    OkHiTransmission transmission = new OkHiTransmission(message);
    handleTransmission(transmission);
  }

  private void handleTransmission(OkHiTransmission transmission) {
    switch (transmission.message) {
      case "location_created":
      case "location_updated":
      case "location_selected":
        handleSuccess(transmission);
        break;
      case "fatal_exit":
        handleFailure(transmission);
        break;
      default:
        break;
    }
  }

  private void handleSuccess(OkHiTransmission transmission) {
    OkHiLocation location = new OkHiLocation(transmission);
    OkHiUser user = new OkHiUser(transmission);
    okHiLocationHandler.onSuccess(location, user);
  }

  private void handleFailure(OkHiTransmission transmission) {
    OkHiError error = new OkHiError(transmission);
    okHiLocationHandler.onError(error);
  }
}