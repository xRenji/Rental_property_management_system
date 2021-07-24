
public class Client_19876896 {
  private int clientID; // Unique client identifier
  private String firstName; // client's first name
  private String lastName; // client's last name
  private String streetAddress; // client's street address
  private String suburbAddress; // client's suburb address
  private String stateAddress; // client's state address
  private int postcodeAddress;// client's postcode

  // MUTATORS

  /**
   * @param clientID - Unique client identifier
   */
  public void setClientID(int clientID) {
    this.clientID = clientID;
  }

  /**
   * @param firstName - client's first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @param lastname - client's last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @param streetAddress - client's street address
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  /**
   * @param suburbAddress - client's suburb address
   */
  public void setSuburbAddress(String suburbAddress) {
    this.suburbAddress = suburbAddress;
  }

  /**
   * @param stateAddress - client's state address
   */
  public void setStateAddress(String stateAddress) {
    this.stateAddress = stateAddress;
  }

  /**
   * @param postcodeAddress - client's postcode
   */
  public void setPostcodeAddress(int postcodeAddress) {
    this.postcodeAddress = postcodeAddress;
  }

  // ACCESSORS

  /**
   * @return clientID - Unique client identifier
   */
  public int getClientID() {
    return clientID;
  }

  /**
   * @return firstName - client's first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @return lastName - client's last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @return streetAddress - client's street address
   */
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
   * @return suburbAddress - client's suburb address
   */
  public String getSuburbAddress() {
    return suburbAddress;
  }

  /**
   * @return stateAddress - client's state address
   */
  public String getStateAddress() {
    return stateAddress;
  }

  /**
   * @return postcodeAddress - client's postcode
   */
  public int getPostcodeAddress() {
    return postcodeAddress;
  }

  // CONSTRUCTORS

  /**
   * Constructor - create the new client object using the values from the
   * parameters as listed
   *
   * @param clientID        - Unique client identifier
   * @param firstName       - client's first name
   * @param lastname        - client's last name
   * @param streetAddress   - client's street address
   * @param suburbAddress   - client's suburb address
   * @param stateAddress    - client's state address
   * @param postcodeAddress - client's postcode
   */
  public Client_19876896(int clientID, String firstName, String lastName, String streetAddress, String suburbAddress,
      String stateAddress, int postcodeAddress) {
    this.clientID = clientID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.streetAddress = streetAddress;
    this.suburbAddress = suburbAddress;
    this.stateAddress = stateAddress;
    this.postcodeAddress = postcodeAddress;

  }

}
