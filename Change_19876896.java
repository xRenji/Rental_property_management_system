
/**
 * <h1>Change class Documentation</h1> The Change class aims to keep track of
 * the changes during the user session.
 * <p>
 * Consequentially, it includes 2 private variables to count respectively
 * changes in expenses and rents, as well as getters, setters and methods to
 * increment the changes counting.
 * 
 * 
 */
public class Change_19876896 {
  private int expenseChanges = 0; // Number of changes in expenseList
  private int rentChanges = 0; // Number of changes in rentList

  // MUTATORS

  /**
   * @param int expenseChanges = Number of changes in expenseList
   */
  public void setExpenseChanges(int expenseChanges) {
    this.expenseChanges = expenseChanges;
  }

  /**
   * @param int rentChanges = Number of changes in rentList
   */
  public void setRentChanges(int rentChanges) {
    this.rentChanges = rentChanges;
  }

  // ACCESSORS

  /**
   * @return int expenseChanges = Number of changes in expenseList
   */
  public int getExpenseChanges() {
    return expenseChanges;
  }

  /**
   * @return int rentChanges = Number of changes in rentList
   */
  public int getRentChanges() {
    return rentChanges;
  }

  // METHODS

  /**
   * This method increments the expense changes by 1
   */
  public void incrementExpenseChanges() {
    expenseChanges = expenseChanges + 1;
  }

  /**
   * This method increments the rent changes by 1
   */
  public void incrementRentChanges() {
    rentChanges = rentChanges + 1;
  }

  // CONSTRUCTORS

  /**
   * Constructor - create the new change object using the values from the
   * parameters as listed
   *
   * @param int         expenseChanges = Number of changes in expenseList
   * @param rentChanges = Number of changes in rentList
   * 
   */
  public Change_19876896(int expenseChanges, int rentChanges) {
    this.expenseChanges = expenseChanges;
    this.rentChanges = rentChanges;

  }

}
