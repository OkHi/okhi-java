package okhi;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OkHiTransmission {
  public String message;
  public Map<String, Object> payload;

  public OkHiTransmission(String jsonString) {
    JSONObject decoded = new JSONObject(jsonString);
    this.message = decoded.getString("message");

    if(this.message.equals("fatal_exit")) {
      this.payload = new HashMap<>();
      this.payload.put("body", decoded.getString("payload"));
    } else {
      this.payload = decoded.getJSONObject("payload").toMap();
    }
  }
}
