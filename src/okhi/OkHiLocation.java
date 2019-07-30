package okhi;

import java.util.Map;

public class OkHiLocation {
  public String id;
  public double lat;
  public double lng;
  public String placeId;
  public String plusCode;
  public String propertyName;
  public String streetName;
  public String title;
  public String url;
  public String directions;
  public String otherInformation;

  OkHiLocation(OkHiTransmission transmission) {
    Map<String, Object> payload = transmission.payload;
    Map<String, Object> location = (Map<String, Object>) payload.get("location");
    this.id = (String) location.get("id");
    this.lat = (double) location.get("lat");
    this.lng = (double) location.get("lng");
    this.placeId = (String) location.get("placeId");
    this.plusCode = (String) location.get("plusCode");
    this.propertyName = (String) location.get("propertyName");
    this.streetName = (String) location.get("streetName");
    this.title = (String) location.get("title");
    this.url = (String) location.get("url");
    this.directions = (String) location.get("directions");
    this.otherInformation = (String) location.get("otherInformation");
  }
}
