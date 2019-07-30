package okhi;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class OkHiUser {
  public String firstName;
  public String lastName;
  public String phone;

  public OkHiUser(String firstName, String lastName, String phone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
  }

  public OkHiUser(String phone) {
    this(null, null, phone);
  }

  public OkHiUser(OkHiTransmission transmission) {
    Map<String, Object> payload = transmission.payload;
    Map<String, Object> user = (Map<String, Object>) payload.get("user");
    this.firstName = (String) user.get("firstName");
    this.lastName = (String) user.get("lastName");
    this.phone = (String) user.get("phone");
  }

  public Map<String, String> toMap() {
    Map<String, String> output = new HashMap<String, String>();
    if(firstName != null) {
      output.put("firstName", firstName);
    }
    if(lastName != null) {
      output.put("lastName", lastName);
    }
    if(phone != null) {
      output.put("phone", phone);
    }
   return output;
  }

  public JSONObject toJSONObject() {
    JSONObject jsonObject = new JSONObject();
    if(firstName != null) {
      jsonObject.put("firstName", firstName);
    }
    if(lastName != null) {
      jsonObject.put("lastName", lastName);
    }
    if(phone != null) {
      jsonObject.put("phone", phone);
    }
    return jsonObject;
  }

}
