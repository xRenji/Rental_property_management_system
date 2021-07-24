
public class Rent_19876896 {
  private int propertyID; // Unique property identifier
  private double rentAmount; // amount of the rent
  private String rentDate; // date of the expense

  // MUTATORS

  /**
   * @param propertyID - Unique property identifier
   */
  public void setPropertyID(int propertyID) {
    this.propertyID = propertyID;
  }

  /**
   * @param rentAmount - amount of the rent
   */
  public void setRentAmount(double rentAmount) {
    this.rentAmount = rentAmount;
  }

  /**
   * @param rentDate - date of the rent
   */
  public void setRentDate(String rentDate) {
    this.rentDate = rentDate;
  }

  // ACCESSORS

  /**
   * @return propertyID - Unique property identifier
   */
  public int getPropertyID() {
    return propertyID;
  }

  /**
   * @return rentAmount - amount of the rent
   */
  public double getRentAmount() {
    return rentAmount;
  }

  /**
   * @return rentDate - date of the rent
   */
  public String geteRentDate() {
    return rentDate;
  }

  // CONSTRUCTORS

  /**
   * Constructor - create the new rent object using the values from the parameters
   * as listed
   *
   * @param propertyID - Unique client identifier
   * @param rentAmount - amount of the rent
   * @param rentDate   - date of the rent
   * 
   */
  public Rent_19876896(int propertyID, double rentAmount, String rentDate) {
    this.propertyID = propertyID;
    this.rentAmount = rentAmount;
    this.rentDate = rentDate;

  }
}
