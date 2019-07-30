package okhi;

public class OkHiError {
  public String code;
  public String message;

  public OkHiError(String code, String error) {
    this.code = code;
    this.message = error;
  }

  public OkHiError(OkHiTransmission transmission) {
    this.code = transmission.message;
    this.message = (String) transmission.payload.get("body");
  }
}
