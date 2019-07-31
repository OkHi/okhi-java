package okhi;

public interface OkHiLocationHandler {
  public void onSuccess(OkHiLocation location, OkHiUser user);
  public void onError(OkHiError error);
  public void onCloseRequest();
}
