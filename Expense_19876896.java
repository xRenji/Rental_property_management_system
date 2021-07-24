
public class Expense_19876896 {
  private int propertyID; // Unique property identifier
  private String expenseDescription; // description for the expense
  private double expenseAmount; // amount of the expense
  private String expenseDate; // date of the expense

  // MUTATORS

  /**
   * @param propertyID - Unique property identifier
   */
  public void setPropertyID(int propertyID) {
    this.propertyID = propertyID;
  }

  /**
   * @param expenseDecription - description for the expense
   */
  public void setExpenseDecription(String expenseDecription) {
    this.expenseDescription = expenseDecription;
  }

  /**
   * @param expenseAmount - amount of the expense
   */
  public void setExpenseAmount(double expenseAmount) {
    this.expenseAmount = expenseAmount;
  }

  /**
   * @param expenseDate - date of the expense
   */
  public void setExpenseDate(String expenseDate) {
    this.expenseDate = expenseDate;
  }

  // ACCESSORS

  /**
   * @return propertyID - Unique property identifier
   */
  public int getPropertyID() {
    return propertyID;
  }

  /**
   * @return expenseDecription - description for the expense
   */
  public String getExpenseDecription() {
    return expenseDescription;
  }

  /**
   * @return expenseAmount - amount of the expense
   */
  public double getExpenseAmount() {
    return expenseAmount;
  }

  /**
   * @return expenseDate - date of the expense
   */
  public String getExpenseDate() {
    return expenseDate;
  }

  // CONSTRUCTORS

  /**
   * Constructor - create the new expense object using the values from the
   * parameters as listed
   *
   * @param propertyID        - Unique client identifier
   * @param expenseDecription - description for the expense
   * @param expenseAmount     - amount of the expense
   * @param expenseDate       - date of the expense
   * 
   */
  public Expense_19876896(int propertyID, String expenseDescription, double expenseAmount, String expenseDate) {
    this.propertyID = propertyID;
    this.expenseDescription = expenseDescription;
    this.expenseAmount = expenseAmount;
    this.expenseDate = expenseDate;

  }
}
