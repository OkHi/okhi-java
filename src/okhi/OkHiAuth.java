package okhi;

import org.json.JSONObject;

public class OkHiAuth {
  public String apiKey;

  public OkHiAuth(String apiKey) {
    this.apiKey = apiKey;
  }

  public JSONObject toJSONObject() {
    JSONObject jsonObject = new JSONObject();
    if(apiKey != null) {
      jsonObject.put("apiKey", apiKey);
    }
    return jsonObject;
  }

  // TODO: implement toJSON

}
