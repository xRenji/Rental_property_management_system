
public class Property_19876896 {
  private int propertyID; // Unique property identifier
  private String streetAddress; // property's street address
  private String suburbAddress; // property's suburb address
  private String stateAddress; // property's state address
  private int postcodeAddress;// property's postcode
  private double weeklyRent; // property's weekly rent
  private double managementFee; // property's management fee
  private int clientID; // Unique client identifier

  // MUTATORS

  /**
   * @param propertyID - Unique property identifier
   */
  public void setPropertyID(int propertyID) {
    this.propertyID = propertyID;
  }

  /**
   * @param streetAddress - property's street address
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  /**
   * @param suburbAddress - property's suburb address
   */
  public void setSuburbAddress(String suburbAddress) {
    this.suburbAddress = suburbAddress;
  }

  /**
   * @param stateAddress - property's state address
   */
  public void setStateAddress(String stateAddress) {
    this.stateAddress = stateAddress;
  }

  /**
   * @param postcodeAddress - property's postcode
   */
  public void setPostcodeAddress(int postcodeAddress) {
    this.postcodeAddress = postcodeAddress;
  }

  /**
   * @param weeklyRent - property's weekly rent
   */
  public void setWeeklyRent(double weeklyRent) {
    this.weeklyRent = weeklyRent;
  }

  /**
   * @param managementFee - property's management fee
   */
  public void setManagementFee(double managementFee) {
    this.managementFee = managementFee;
  }

  /**
   * @param clientID - Unique client identifier
   */
  public void setClientID(int clientID) {
    this.clientID = clientID;
  }

  // ACCESSORS

  /**
   * @return propertyID - Unique property identifier
   */
  public int getPropertyID() {
    return propertyID;
  }

  /**
   * @return streetAddress - property's street address
   */
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
   * @return suburbAddress - property's suburb address
   */
  public String getSuburbAddress() {
    return suburbAddress;
  }

  /**
   * @return stateAddress - property's state address
   */
  public String getStateAddress() {
    return stateAddress;
  }

  /**
   * @return postcodeAddress - property's postcode
   */
  public int getPostcodeAddress() {
    return postcodeAddress;
  }

  /**
   * @return weeklyRent - property's weekly rent
   */
  public double getWeeklyRent() {
    return weeklyRent;
  }

  /**
   * @return managementFee - property's management fee
   */
  public double getManagementFee() {
    return managementFee;
  }

  /**
   * @return clientID - Unique client identifier
   */
  public int getClientID() {
    return clientID;
  }

  // CONSTRUCTORS

  /**
   * Constructor - create the new property object using the values from the
   * parameters as listed
   *
   * @param propertyID      - Unique property identifier
   * @param streetAddress   - property's street address
   * @param suburbAddress   - property's suburb address
   * @param stateAddress    - property's state address
   * @param postcodeAddress - property's postcode
   * @param weeklyRent      - property's weekly rent
   * @param managementFee   - property's management fee
   * @param clientID        - Unique client identifier
   */
  public Property_19876896(int propertyID, String streetAddress, String suburbAddress, String stateAddress,
      int postcodeAddress, double weeklyRent, double managementFee, int clientID) {
    this.propertyID = propertyID;
    this.streetAddress = streetAddress;
    this.suburbAddress = suburbAddress;
    this.stateAddress = stateAddress;
    this.postcodeAddress = postcodeAddress;
    this.weeklyRent = weeklyRent;
    this.managementFee = managementFee;
    this.clientID = clientID;

  }
}
