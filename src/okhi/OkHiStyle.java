package okhi;

import org.json.JSONObject;

public class OkHiStyle {
  public String color;
  public String name;
  public String logo;

  public OkHiStyle(String color, String name, String logo) {
    this.color = color;
    this.name = name;
    this.logo = logo;
  }

  public JSONObject toJSONObject() {
    JSONObject base = new JSONObject();
    if(color != null) {
      base.put("color", color);
    }
    if(name != null) {
      base.put("name", name);
    }
    if(logo != null) {
      base.put("logo", logo);
    }
    return new JSONObject().put("base", base);
  }
}
