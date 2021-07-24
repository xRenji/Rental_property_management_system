
/**
 * <h1>PropertyManagementDemo Documentation</h1>
 * The PropertyManagement program allows users to read multiple text files which
 * contain data about properties, clients, rents and expenses. This data is read as objects into 4 arrays.
 * Consequentially, users can add records as well as retrieve specific information from this data.
 * If the user adds new records, these records can also be appended to the original files, and 
 * therefore saved.
 * <p>
 * Furthermore, .txt extensions are automatically added by the program,
 * and do not need to be specified if the user needs to select what file to read. 
 * <p>
 * Finally, i'd like to specify how queries have been handled as per
 * Project documentation: 
 * 
 * When user selects property to add a new record (either rent or expense), the search
 * looks for matches in the street address. Lower case and Upper case queries are allowed.
 * 
 * When user selects a specific client in the portfolio section, the search looks for matches
 * in the last name as well as in the first name. However, in this case the search is case sensitive.
 * 
 * 
 * 
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class PropertyManagementDemo_19876896 {

	static Date date = new Date();
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Date's format
	static DecimalFormat decimalFormatter = new DecimalFormat("0.00"); // Formatter for amounts
	static Scanner kb = new Scanner(System.in);

	static final String CLIENTFILE = "clients.txt"; // File containing clients to read
	static final String PROPERTYFILE = "properties.txt"; // File containing properties to read
	static final String EXPENSEFILE = "expenses.txt"; // File containing expenses to read
	static final String RENTFILE = "rents.txt"; // File containing rents to read

	public static void main(String[] args) {
		ArrayList<Client_19876896> clientList = new ArrayList<>(); // ArrayList containing clients
		ArrayList<Property_19876896> propertyList = new ArrayList<>(); // ArrayList containing properties
		ArrayList<Expense_19876896> expenseList = new ArrayList<>(); // ArrayList containing expenses
		ArrayList<Rent_19876896> rentList = new ArrayList<>(); // ArrayList containing rents
		String answer = "10"; // answer from user input, to navigate main menu
		// READ FILES
		clientList = readClientFile(clientList, CLIENTFILE);
		propertyList = readPropertyFile(propertyList, PROPERTYFILE);
		expenseList = readExpenseFile(expenseList, EXPENSEFILE);
		rentList = readRentFile(rentList, RENTFILE);
		// LOAD CHANGES CONTROL
		int expenseChanges = 0; // Number of changes in expenseList
		int rentChanges = 0; // Number of changes in rentList
		Change_19876896 changes = new Change_19876896(expenseChanges, rentChanges);

		// User selects action
		while (answer != "6") {
			// MAIN MENU
			System.out.println("\n------- MAIN MENU -------\n");
			System.out.println("1. Record Rent Collection. ");
			System.out.println("2. Record Expense.");
			System.out.println("3. Generate Portfolio Report.");
			System.out.println("4. Save.");
			System.out.println("5. Exit Program.");

			answer = kb.nextLine(); // Get user selection

			if (answer.equals("1")) { // Select 1 to record rent
				rentList = recordRent(rentList, propertyList, clientList, changes); // Method to record new rent

				// Select 2 to record expense
			} else if (answer.equals("2")) {
				expenseList = recordExpense(expenseList, propertyList, clientList, changes); // Method to record new
																								// expense

				// Select 3 to generate portfolio report
			} else if (answer.equals("3")) {
				portfolioMenu(expenseList, rentList, propertyList, clientList); // Method for portfolio menu

				// Select 4 to save
			} else if (answer.equals("4")) {
				save(expenseList, rentList, changes); // Method to save changes
				// Select 0 to exit program
			} else if (answer.equals("5")) {
				answer = exit(changes, answer); // Method to exit application
				// Handling invalid selection
			} else {
				System.out.println("\nThis selection is not valid, please select your next action.\n");
				System.out.println("Navigate menu using numbers 1 to 5.");

			}

		}
	}

	/**********************************************************************************************************
	 * 
	 * SAVE & EXIT METHODS
	 * 
	 **********************************************************************************************************/

	/**
	 * Method to warn user of unsaved changes before exit
	 * 
	 * @param Change_19876896 changes = object of changes in lists
	 * @param String          answer = user input to navigate menu
	 * @return String answer = user input to navigate menu
	 */
	public static String exit(Change_19876896 changes, String answer) {
		int expenseChanges = changes.getExpenseChanges(); // Number of changes in expenseList
		int rentChanges = changes.getRentChanges(); // Number of changes in rentList
		String option; // User input action to exit or go back
		if (rentChanges > 0 || expenseChanges > 0) { // If there were some changes alert user
			System.out.println("\nYou have unsaved changes.");
			System.out.println("\nPress 1 to go back to the main menu.");
			System.out.println("Press 2 to exit the program.");
			option = kb.nextLine(); // Get user next action

			if (option.equals("1")) { // If 1 selected then go back to main menu
				return answer;
			} else if (option.equals("2")) { // if 2 selected exit program
				System.out.println("Program terminated.");
				answer = "6";
			} else { // else check for invalid selection
				System.out.println("\nThis selection is not valid, please select your next action.\n");
			}

			return answer;
		} else { // if there were no changes then exit the program
			System.out.println("Program terminated.");
			answer = "6";
		}

		return answer;
	}

	/**
	 * Method to save/append expenses and rent to file.
	 * 
	 * @param ArrayList<Espense_19876896> expenseList = the array of expenses
	 * @param ArrayList<Rent_19876896>    rentList = the array of rents
	 * @param Change_19876896             changes = object of changes in lists
	 */
	public static void save(ArrayList<Expense_19876896> expenseList, ArrayList<Rent_19876896> rentList,
			Change_19876896 changes) {
		int expenseChanges = changes.getExpenseChanges(); // Number of changes in expenseList
		int rentChanges = changes.getRentChanges(); // Number of changes in rentList

		if (rentChanges > 0 || expenseChanges > 0) { // If there was any new record/changes
			// APPEND EXPENSES CHANGES
			int index = expenseList.size() - expenseChanges; // Index of expense changes starting point
			try { // Check for errors in file appending

				FileWriter append = new FileWriter(EXPENSEFILE, true); // Append data on each iteration
				PrintWriter outFile = new PrintWriter(append);

				for (int i = index; i < expenseList.size(); i++) { // Iterate list starting form last saved index
					// Print changes on expenses file
					outFile.println(expenseList.get(i).getPropertyID() + "," + expenseList.get(i).getExpenseDecription()
							+ "," + expenseList.get(i).getExpenseAmount() + "," + expenseList.get(i).getExpenseDate());
				}
				changes.setExpenseChanges(0); // Reset expenses changes
				outFile.close();
			} catch (IOException e) { // If error, alert user
				System.out.println("Error with saving expense changes.");
			}
			// APPEND RENT CHANGES
			int indexRent = rentList.size() - rentChanges; // Index of rent changes starting point
			try { // Check for errors in file appending

				FileWriter appendRent = new FileWriter(RENTFILE, true); // Append data on each iteration
				PrintWriter outFileRent = new PrintWriter(appendRent);

				for (int i = indexRent; i < rentList.size(); i++) { // Iterate list starting form last saved index
					// Print changes on rent file
					outFileRent.println(rentList.get(i).getPropertyID() + "," + rentList.get(i).getRentAmount() + ","
							+ rentList.get(i).geteRentDate());
				}
				System.out.println("Changes saved.");
				changes.setRentChanges(0); // Reset changes
				outFileRent.close();
			} catch (IOException e) { // If error, alert user
				System.out.println("Error with saving rent changes.");
			}
		} else {
			System.out.println("No changes were made.");
		}

	}

	/**********************************************************************************************************
	 * 
	 * PORTFOLIO METHODS
	 * 
	 **********************************************************************************************************/

	/**
	 * Method to display Portfolio Menu.
	 * 
	 * @param ArrayList<Espense_19876896>  expenseList = the array of expenses
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @param ArrayList<Client_19876896>   clientList = the array of the
	 *                                     clients/owners
	 * @param ArrayList<Rent_19876896>     rentList = the array of rents
	 */
	public static void portfolioMenu(ArrayList<Expense_19876896> expenseList, ArrayList<Rent_19876896> rentList,
			ArrayList<Property_19876896> propertyList, ArrayList<Client_19876896> clientList) {

		String answer = "10"; // answer from user input, to navigate portfolio menu
		while (answer != "6") {
			// PORTOFOLIO MENU
			System.out.println("\n------- PORTOFOLIO MENU -------\n");
			System.out.println("1. Specific client portfolio. ");
			System.out.println("2. All clients portfolio.");
			System.out.println("3. All properties portfolio by postcode.");
			System.out.println("4. Back to main menu.");

			answer = kb.nextLine();

			if (answer.equals("1")) { // Select 1 to generate specific client portfolio
				portfolioClient(expenseList, rentList, propertyList, clientList);
				// Select 2 to generate all clients portfolio
			} else if (answer.equals("2")) {
				portfolioAllClients(expenseList, rentList, propertyList, clientList);
				// Select 3 to generate all properties portfolio
			} else if (answer.equals("3")) {
				portfolioByPostcode(expenseList, rentList, propertyList, clientList);

				// Select 4 to go back to main menu
			} else if (answer.equals("4")) {
				answer = "6";

			} else {
				System.out.println("\nThis selection is not valid, please select your next action.\n");
			}
		}
	}

	/**
	 * Method to display Portfolio for specific client.
	 * 
	 * @param ArrayList<Espense_19876896>  expenseList = the array of expenses
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @param ArrayList<Client_19876896>   clientList = the array of the
	 *                                     clients/owners
	 * @param ArrayList<Rent_19876896>     rentList = the array of rents
	 */
	public static void portfolioClient(ArrayList<Expense_19876896> expenseList, ArrayList<Rent_19876896> rentList,
			ArrayList<Property_19876896> propertyList, ArrayList<Client_19876896> clientList) {

		ArrayList<Integer> clients = new ArrayList<Integer>(); // an array of matching clients

		// Get clients ID
		clients = getClientID(clientList);

		// Generate portfolio report for each client in the array
		for (int i = 0; i < clients.size(); i++) {
			portfolioReport(expenseList, rentList, propertyList, clientList, clients.get(i));
		}
	}

	/**
	 * Method to display Portfolio for all clients.
	 * 
	 * @param ArrayList<Espense_19876896>  expenseList = the array of expenses
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @param ArrayList<Client_19876896>   clientList = the array of the
	 *                                     clients/owners
	 * @param ArrayList<Rent_19876896>     rentList = the array of rents
	 */
	public static void portfolioAllClients(ArrayList<Expense_19876896> expenseList, ArrayList<Rent_19876896> rentList,
			ArrayList<Property_19876896> propertyList, ArrayList<Client_19876896> clientList) {

		ArrayList<Integer> clients = new ArrayList<Integer>(); // an array of clients IDs
		ArrayList<String> names = new ArrayList<String>(); // an array of clients names
		String temp; // temporary variable for swapping elements

		// GET ALL CLIENTS' NAMES
		for (int i = 0; i < clientList.size(); i++) { // For each client
			names.add(clientList.get(i).getLastName()); // Add client's last name to array of names
		}

		// SORTING NAMES
		for (int i = 0; i < names.size() - 1; i++) { // Iterate array of names,for all names but last one
			for (int j = i + 1; j < names.size(); j++) { // For each name check all other names left except name itself
				if (names.get(i).compareTo(names.get(j)) > 0) { // compare lexicographically, if positive swap
					temp = names.get(i); // store name in temp
					names.set(i, names.get(j)); // perform swap name
					names.set(j, temp); // complete the swap
				}
			}
		} // end of sorting

		// RETRIEVE IDs FROM SORTED NAMES
		for (int i = 0; i < names.size(); i++) { // For each name sorted
			for (int k = 0; k < clientList.size(); k++) { // Check each name in clients list
				if (names.get(i).equals(clientList.get(k).getLastName())) { // if matches the client's last name

					for (int m = 0; m < clientList.size(); m++) { // Check if ID has been added
						if (!clients.contains(clientList.get(k).getClientID())) { // If it has not
							clients.add(clientList.get(k).getClientID()); // add ID to array of sorted IDs
						}
					}
				}
			}
		} // end of get sorted IDs iterations

		// Generate portfolio report for each client in the array
		for (int i = 0; i < clients.size(); i++) {
			portfolioReport(expenseList, rentList, propertyList, clientList, clients.get(i));
		}

	}

	/**
	 * Method to display Portfolio by postcode area.
	 * 
	 * @param ArrayList<Espense_19876896>  expenseList = the array of expenses
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @param ArrayList<Client_19876896>   clientList = the array of the
	 *                                     clients/owners
	 * @param ArrayList<Rent_19876896>     rentList = the array of rents
	 */
	public static void portfolioByPostcode(ArrayList<Expense_19876896> expenseList, ArrayList<Rent_19876896> rentList,
			ArrayList<Property_19876896> propertyList, ArrayList<Client_19876896> clientList) {

		int postcode;
		ArrayList<Integer> properties = new ArrayList<Integer>(); // an array of properties by postcode
		double rentAmount; // Rent amount for the property
		double expenseAmount; // Expense amount for the property
		double fees; // rent multiplied by fee rate
		double net; // rent minus expenses and fees

		double totalRent = 0; // total rent recorded for all properties
		double totalExpense = 0; // total expenses recorded for all properties
		double totalFees = 0; // total fees recorded for all properties
		double totalNet = 0; // total net recorded for all properties
		String address = ""; // the full address
		int propertyID; // the property ID

		// Get expense postcode and validate
		System.out.println("Please select postcode area:");
		postcode = kb.nextInt();
		postcode = postcodeValidation(postcode); // Validate postcode (must be 4 digits)

		// Get properties with postcode input
		for (int i = 0; i < propertyList.size(); i++) { // for each property in the list
			if (postcode == propertyList.get(i).getPostcodeAddress()) { // if a match is found
				properties.add(propertyList.get(i).getPropertyID()); // add match to property
			}
		}

		System.out.println();
		System.out.println("\nPORTOFOLIO REPORT by postcode\n");

		// Print postcode info
		System.out.println("Postcode: " + postcode);
		// Print date
		System.out.println("Report Generated: " + date);
		// Print Portfolio's header
		System.out.println("\n---------------------------------------------------------------------------"
				+ "--------------------------------------------");
		System.out.printf("%1s %50s %1s %10s %1s %10s %1s %10s %1s %10s %1s %10s %1s", "|", "Property", "|", "Rent",
				"|", "Expenses", "|", "Fee Rate", "|", "Fees", "|", "Net", "|");
		System.out.println("\n---------------------------------------------------------------------------"
				+ "--------------------------------------------");
		// PRINT PORTFOLIO
		if (properties.isEmpty()) { // If there is no properties for that postcode
			System.out.printf("%70s %1s", "NO PROPERTIES FOUND FOR POSTCODE", postcode);
			System.out.println();
		}

		for (int i = 0; i < properties.size(); i++) { // Iterate through the properties of the client

			rentAmount = getRent(rentList, properties.get(i)); // Get total rent recorded for each property
			expenseAmount = getExpense(expenseList, properties.get(i)); // Get total expense recorded for each property
			fees = propertyList.get(properties.get(i) - 1).getManagementFee() * rentAmount; // Get fees
			net = rentAmount - expenseAmount - fees; // get net
			propertyID = properties.get(i);

			totalRent = totalRent + rentAmount; // get total rent recorded for all properties
			totalExpense = totalExpense + expenseAmount; // get total expenses recorded for all properties
			totalFees = totalFees + fees; // get total fees recorded for all properties
			totalNet = totalNet + net; // get total net recorded for all properties

			address = address.concat(propertyList.get(propertyID - 1).getStreetAddress()).concat(" ")
					.concat(propertyList.get(propertyID - 1).getSuburbAddress()).concat(" ")
					.concat(propertyList.get(propertyID - 1).getStateAddress()).concat(" ")
					.concat(Integer.toString(propertyList.get(propertyID - 1).getPostcodeAddress()));

			System.out.printf("%1s %50s %1s %10s %1s %10s %1s %10s %1s %10s %1s %10s %1s", "|", address, "|",
					decimalFormatter.format(rentAmount), "|", decimalFormatter.format(expenseAmount), "|",
					propertyList.get(properties.get(i) - 1).getManagementFee(), "|", decimalFormatter.format(fees), "|",
					decimalFormatter.format(net), "|");
			System.out.println();
			address = ""; // Reset address

		} // end of properties iteration

		// Print columns total
		System.out.println("---------------------------------------------------------------------------"
				+ "--------------------------------------------");
		System.out.printf("%1s %50s %1s %10s %1s %10s %1s %10s %1s %10s %1s %10s %1s", "|", "TOTAL", "|",
				decimalFormatter.format(totalRent), "|", decimalFormatter.format(totalExpense), "|", " ", "|",
				decimalFormatter.format(totalFees), "|", decimalFormatter.format(totalNet), "|");
		System.out.println("\n---------------------------------------------------------------------------"
				+ "--------------------------------------------");

		kb.nextLine(); // consume line

	}

	/**
	 * Method to generate Portfolio.
	 * 
	 * @param ArrayList<Espense_19876896>  expenseList = the array of expenses
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @param ArrayList<Client_19876896>   clientList = the array of the
	 *                                     clients/owners
	 * @param ArrayList<Rent_19876896>     rentList = the array of rents
	 * @param Int                          client = client selected
	 */
	public static void portfolioReport(ArrayList<Expense_19876896> expenseList, ArrayList<Rent_19876896> rentList,
			ArrayList<Property_19876896> propertyList, ArrayList<Client_19876896> clientList, int client) {

		ArrayList<Integer> properties = new ArrayList<Integer>(); // an array of client's properties IDs
		double rentAmount; // Rent amount for the property
		double expenseAmount; // Expense amount for the property
		double fees; // rent multiplied by fee rate
		double net; // rent minus expenses and fees

		double totalRent = 0; // total rent recorded for all properties
		double totalExpense = 0; // total expenses recorded for all properties
		double totalFees = 0; // total fees recorded for all properties
		double totalNet = 0; // total net recorded for all properties
		String address = ""; // the full address
		int propertyID; // the property ID

		// Get client's properties
		properties = clientToProperty(client, clientList, propertyList);
		System.out.println();

		System.out.println("\nPORTOFOLIO REPORT\n");
		// Print client info
		System.out.println("Client: " + clientList.get(client - 1).getFirstName() + " "
				+ clientList.get(client - 1).getLastName() + ",  " + clientList.get(client - 1).getStreetAddress() + " "
				+ clientList.get(client - 1).getSuburbAddress() + " " + clientList.get(client - 1).getStateAddress()
				+ " " + clientList.get(client - 1).getPostcodeAddress());
		// Print date
		System.out.println("Report Generated: " + date);

		// Print Portfolio's header
		System.out.println("\n---------------------------------------------------------------------------"
				+ "--------------------------------------------");
		System.out.printf("%1s %50s %1s %10s %1s %10s %1s %10s %1s %10s %1s %10s %1s", "|", "Property", "|", "Rent",
				"|", "Expenses", "|", "Fee Rate", "|", "Fees", "|", "Net", "|");
		System.out.println("\n---------------------------------------------------------------------------"
				+ "--------------------------------------------");
		// Print Portfolio
		if (properties.isEmpty()) { // If client has no properties
			System.out.printf("%70s", "NO RECORDS FOUND");
			System.out.println();
		}
		for (int i = 0; i < properties.size(); i++) { // Iterate through the properties of the client

			rentAmount = getRent(rentList, properties.get(i)); // Get total rent recorded for each property
			expenseAmount = getExpense(expenseList, properties.get(i)); // Get total expense recorded for each property
			fees = propertyList.get(properties.get(i) - 1).getManagementFee() * rentAmount; // Get fees
			net = rentAmount - expenseAmount - fees; // get net
			propertyID = properties.get(i);

			totalRent = totalRent + rentAmount; // get total rent recorded for all properties
			totalExpense = totalExpense + expenseAmount; // get total expenses recorded for all properties
			totalFees = totalFees + fees; // get total fees recorded for all properties
			totalNet = totalNet + net; // get total net recorded for all properties

			address = address.concat(propertyList.get(propertyID - 1).getStreetAddress()).concat(" ")
					.concat(propertyList.get(propertyID - 1).getSuburbAddress()).concat(" ")
					.concat(propertyList.get(propertyID - 1).getStateAddress()).concat(" ")
					.concat(Integer.toString(propertyList.get(propertyID - 1).getPostcodeAddress()));

			System.out.printf("%1s %50s %1s %10s %1s %10s %1s %10s %1s %10s %1s %10s %1s", "|", address, "|",
					decimalFormatter.format(rentAmount), "|", decimalFormatter.format(expenseAmount), "|",
					propertyList.get(properties.get(i) - 1).getManagementFee(), "|", decimalFormatter.format(fees), "|",
					decimalFormatter.format(net), "|");
			System.out.println();
			address = ""; // Reset address

		} // end of properties iteration

		// Print columns total
		System.out.println("---------------------------------------------------------------------------"
				+ "--------------------------------------------");
		System.out.printf("%1s %50s %1s %10s %1s %10s %1s %10s %1s %10s %1s %10s %1s", "|", "TOTAL", "|",
				decimalFormatter.format(totalRent), "|", decimalFormatter.format(totalExpense), "|", " ", "|",
				decimalFormatter.format(totalFees), "|", decimalFormatter.format(totalNet), "|");
		System.out.println("\n---------------------------------------------------------------------------"
				+ "--------------------------------------------");
	}

	/**
	 * Method to get total amount of rent recorded for a property.
	 * 
	 * @param int                      propertyID = the ID of the property
	 * @param ArrayList<Rent_19876896> rentList = the list of the rents
	 * @return double rentAmount = the total rent of the property
	 */
	public static double getRent(ArrayList<Rent_19876896> rentList, int propertyID) {

		double rentAmount = 0; // Rent amount for the property

		for (int i = 0; i < rentList.size(); i++) { // Iterate through rent records
			if (rentList.get(i).getPropertyID() == propertyID) { // If a rent record matches the property
				rentAmount = rentAmount + rentList.get(i).getRentAmount(); // rent amount added
			}
		}

		return rentAmount;

	}

	/**
	 * Method to get amount of expense recorded for a property.
	 * 
	 * @param int                         propertyID = the ID of the property
	 * @param ArrayList<Expense_19876896> expenseList = the list of the expenses
	 * @return double expenseAmount = the total expense of the property
	 */
	public static double getExpense(ArrayList<Expense_19876896> expenseList, int propertyID) {

		double expenseAmount = 0; // Expense total amount for the property

		for (int i = 0; i < expenseList.size(); i++) { // Iterate through rent records
			if (expenseList.get(i).getPropertyID() == propertyID) { // If an expense record matches the property
				expenseAmount = expenseAmount + expenseList.get(i).getExpenseAmount(); // expense amount added
			}
		}

		return expenseAmount;

	}

	/**********************************************************************************************************
	 * 
	 * NEW RECORD METHODS
	 * 
	 **********************************************************************************************************/

	/**
	 * Method to record expense to a property.
	 * 
	 * @param ArrayList<Espense_19876896>  expenseList = the array of expenses
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @param ArrayList<Client_19876896>   clientList = the array of the
	 *                                     clients/owners
	 * @param Change_19876896              changes = object of changes in lists
	 * @return expenseList = the array of expenses
	 */
	public static ArrayList<Expense_19876896> recordExpense(ArrayList<Expense_19876896> expenseList,
			ArrayList<Property_19876896> propertyList, ArrayList<Client_19876896> clientList, Change_19876896 changes) {

		// IDENTIFIERS
		int propertyID; // Unique property identifier
		String clientName; // Client's full name
		String todaysDate; // Today's date
		String description; // Description of the expense
		double expenseAmount; // Amount of the expense
		String address = ""; // Full Address

		propertyID = getPropertyID(propertyList); // Get property ID
		if (propertyID == 0) { // If no property was selected
			return expenseList; // Back to main menu
		}

		clientName = propertyToClient(propertyID, clientList, propertyList); // Get client's full name

		// Display property info
		System.out.println("You have selected this property:");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("| " + propertyList.get(propertyID - 1).getStreetAddress() + " "
				+ propertyList.get(propertyID - 1).getSuburbAddress() + " "
				+ propertyList.get(propertyID - 1).getStateAddress() + " "
				+ propertyList.get(propertyID - 1).getPostcodeAddress());

		System.out.println("| Weekly Rent: " + propertyList.get(propertyID - 1).getWeeklyRent());
		System.out.println("| Owner: " + clientName);
		System.out.println("-----------------------------------------------------------------");

		// Get expense amount and validate
		System.out.println("Please select amount of the expense:");
		expenseAmount = kb.nextDouble();
		expenseAmount = expenseValidation(expenseAmount); // Validate expense ( expense > 0 )

		// Get expense's description
		System.out.println("Please specify expense's description:");
		kb.nextLine(); // consume line
		description = kb.nextLine();
		description = descriptionValidation(description); // Validate description ( can't be empty)
		// Get today's date
		todaysDate = formatter.format(date).toString();
		// Get Full Address

		address = address.concat(propertyList.get(propertyID - 1).getStreetAddress()).concat(" ")
				.concat(propertyList.get(propertyID - 1).getSuburbAddress()).concat(" ")
				.concat(propertyList.get(propertyID - 1).getStateAddress()).concat(" ")
				.concat(Integer.toString(propertyList.get(propertyID - 1).getPostcodeAddress()));
		// Record new expense
		expenseList.add(new Expense_19876896(propertyID, description, expenseAmount, todaysDate));
		changes.incrementExpenseChanges(); // Keep track of expenses changes
		// Screen report
		System.out.println("\nYou have recorded the following expense transaction:\n");
		System.out.printf("%1s %50s %1s %25s %1s %15s %1s %15s %1s %15s", "|", "Property Address", "|",
				"Expense Amount", "|", "Owner", "|", "Date", "|", "Description");
		System.out.println("\n--------------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
		System.out.printf("%1s %50s %1s %25s %1s %15s %1s %15s %1s %15s", "|", address, "|", expenseAmount, "|",
				clientName, "|", todaysDate, "|", description);
		System.out.println("\n--------------------------------------------------------------------------"
				+ "------------------------------------------------------------------\n");

		System.out.println("Press any key to go back to the main menu.");

		kb.nextLine(); // consume line

		return expenseList;
	}

	/**
	 * Method to record rents to a property.
	 * 
	 * @param ArrayList<Rent_19876896>     rentList = the array of rents
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @param ArrayList<Client_19876896>   clientList = the array of the
	 *                                     clients/owners
	 * @param Change_19876896              changes = object of changes in lists
	 * @return rentList = the array of rents
	 */
	public static ArrayList<Rent_19876896> recordRent(ArrayList<Rent_19876896> rentList,
			ArrayList<Property_19876896> propertyList, ArrayList<Client_19876896> clientList, Change_19876896 changes) {

		// IDENTIFIERS
		int propertyID; // Unique property identifier
		String clientName; // Client's full name
		String todaysDate; // Today's date
		int weeks; // Weeks of rent
		double rentAmount; // The amount of the rent
		String address = ""; // Full Address

		propertyID = getPropertyID(propertyList); // Get property ID
		if (propertyID == 0) { // If no property was selected
			return rentList; // Back to main menu
		}

		clientName = propertyToClient(propertyID, clientList, propertyList); // Get client's full name

		// Display property info
		System.out.println("You have selected this property:");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("| " + propertyList.get(propertyID - 1).getStreetAddress() + " "
				+ propertyList.get(propertyID - 1).getSuburbAddress() + " "
				+ propertyList.get(propertyID - 1).getStateAddress() + " "
				+ propertyList.get(propertyID - 1).getPostcodeAddress());

		System.out.println("| Weekly Rent: " + propertyList.get(propertyID - 1).getWeeklyRent());
		System.out.println("| Owner: " + clientName);
		System.out.println("-----------------------------------------------------------------");

		// Get and validate weeks
		System.out.println("Please select number of weeks:");
		weeks = kb.nextInt();
		weeks = weekValidation(weeks);
		// Calculate rent amount
		rentAmount = weeks * propertyList.get(propertyID - 1).getWeeklyRent();
		// Get today's date
		todaysDate = formatter.format(date).toString();
		// Get Full Address
		address = address.concat(propertyList.get(propertyID - 1).getStreetAddress()).concat(" ")
				.concat(propertyList.get(propertyID - 1).getSuburbAddress()).concat(" ")
				.concat(propertyList.get(propertyID - 1).getStateAddress()).concat(" ")
				.concat(Integer.toString(propertyList.get(propertyID - 1).getPostcodeAddress()));
		// Record new rent
		rentList.add(new Rent_19876896(propertyID, rentAmount, todaysDate));
		changes.incrementRentChanges(); // Keep track of changes
		// Generate on screen summary
		System.out.println("\nYou have recorded the following rent transaction:\n");
		System.out.printf("%1s %50s %1s %15s %1s %25s %1s %15s %1s %15s", "|", "Property Address", "|", "Rent Amount",
				"|", "Weeks", "|", "Owner", "|", "Date");
		System.out.println("\n--------------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
		System.out.printf("%1s %50s %1s %15s %1s %25s %1s %15s %1s %15s", "|", address, "|", rentAmount, "|", weeks,
				"|", clientName, "|", todaysDate);
		System.out.println("\n--------------------------------------------------------------------------"
				+ "------------------------------------------------------------------\n");

		kb.nextLine(); // consume line

		return rentList;
	}

	/**********************************************************************************************************
	 * 
	 * VALIDATION METHODS
	 * 
	 **********************************************************************************************************/

	/**
	 * Method to validate weeks input.
	 * 
	 * @param int weeks = the number of weeks
	 * @return int weeks = the validated number of weeks selected
	 */
	public static int weekValidation(int weeks) {
		while (weeks <= 0) {
			System.out.println("The input for weeks is not valid.");
			System.out.println("Please select number of weeks (must be between 1 to 5 inclusive):");
			weeks = kb.nextInt();

		}

		return weeks;
	}

	/**
	 * Method to validate expenses input.
	 * 
	 * @param double expense = the amount of the expense
	 * @return double expense = the validated expense amount
	 */
	public static double expenseValidation(double expense) {
		while (expense <= 0) {
			System.out.println("The input for expense amount is not valid.");
			System.out.println("Please select an amount greater than 0:");
			expense = kb.nextDouble();

		}

		return expense;
	}

	/**
	 * Method to validate description input.
	 * 
	 * @param String description = the description of the expense
	 * @return String description = the validated description amount
	 */
	public static String descriptionValidation(String description) {
		while (description.isEmpty()) {
			System.out.println("The expense's description cannot be empty.");
			System.out.println("Please specify expense's description:");

			description = kb.nextLine();

		}

		return description;
	}

	/**
	 * Method to validate postcode input.
	 * 
	 * @param Int postcode = postcode input
	 * @return Int postcode = validated postcode
	 */
	public static int postcodeValidation(int postcode) {
		while (postcode < 1000 || postcode > 9999) {
			System.out.println("Postcode must be 4 digits");
			System.out.println("Please specify postcode area:");

			postcode = kb.nextInt();

		}
		System.out.println(postcode);

		return postcode;
	}

	/**********************************************************************************************************
	 * 
	 * RETRIEVE IDs METHODS
	 * 
	 **********************************************************************************************************/

	/**
	 * Method to get properties IDs from client.
	 * 
	 * @param int                          client = the ID of the client
	 * @param ArrayList<Client_19876896>   clientList = the list of clients/owners
	 * @param ArrayList<Property_19876896> propertyList = the list of the properties
	 * @return properties = array containing the IDs of the properties
	 */
	public static ArrayList<Integer> clientToProperty(int client, ArrayList<Client_19876896> clientList,
			ArrayList<Property_19876896> propertyList) {

		ArrayList<Integer> properties = new ArrayList<Integer>(); // an array of client's properties IDs

		for (int i = 0; i < propertyList.size(); i++) { // Iterate through properties
			if (propertyList.get(i).getClientID() == client) { // If a property match the client ID
				properties.add(propertyList.get(i).getPropertyID()); // add the property to matched properties
			}
		}

		return properties;

	}

	/**
	 * Method to select a specific client from the clients list.
	 * 
	 * @param ArrayList<Client_19876896> clientList= the array of the clients
	 * @return clientID = the ID of the client selected
	 */
	public static ArrayList<Integer> getClientID(ArrayList<Client_19876896> clientList) {

		String clientName; // the user input for client's name
		ArrayList<Integer> clients = new ArrayList<Integer>(); // an array of matching clients

		int counter = 0; // counter for user selection

		System.out.println("Please specify client's first name or last name:");
		clientName = kb.nextLine(); // get user input for property address

		for (int i = 0; i < clientList.size(); i++) { // For each client in the array list
			if (clientList.get(i).getFirstName().contains(clientName) || // output matches for client's first name
					clientList.get(i).getLastName().contains(clientName)) { // matches for client's last name
				counter++;
				clients.add(clientList.get(i).getClientID()); // add matches to array

			}
		} // end of iteration

		if (counter == 0) { // if no match is found back to the portfolio menu
			System.out.println("No client matches this name.");
			System.out.println("You are back to the portfolio menu.");
			return clients;
		}

		return clients;
	}

	/**
	 * Method to select a property from the properties list.
	 * 
	 * @param ArrayList<Property_19876896> propertyList = the array of the
	 *                                     properties
	 * @return propertyID = the ID of the property selected
	 */
	public static int getPropertyID(ArrayList<Property_19876896> propertyList) {
		int propertyID = 0; // the property selected by the user
		String propertyAddress; // the user input for property address
		int counter = 0; // counter for user selection
		int answer = 0; // to select property ID
		ArrayList<Integer> properties = new ArrayList<Integer>(); // an array of matching properties

		System.out.println("Please specify property's address:");

		propertyAddress = kb.nextLine(); // get user input for property address

		for (int i = 0; i < propertyList.size(); i++) { // For each property in the array list
			if (propertyList.get(i).getStreetAddress().contains(propertyAddress) || // output matches for property's
																					// address
					propertyList.get(i).getStreetAddress().toLowerCase().contains(propertyAddress)) { // matches for
																										// lower case

				System.out.println("\nPress " + counter + " to select this property: ");
				System.out.println("Property at: " + propertyList.get(i).getStreetAddress() + " "
						+ propertyList.get(i).getSuburbAddress() + " " + propertyList.get(i).getStateAddress() + " "
						+ propertyList.get(i).getPostcodeAddress());
				properties.add(propertyList.get(i).getPropertyID()); // add matches to array
				counter++;
			}

		} // end of iteration
		if (counter == 0) { // if no match is found back to the main menu
			System.out.println("No property matches this address.");
			System.out.println("You are back to the main menu.");
			return propertyID;
		}

		answer = kb.nextInt(); // get selection input from the user

		if (answer > counter - 1 || answer < 0) { // If invalid selection, back to the main menu
			System.out.println("Invalid selection.");
			System.out.println("You are back to the main menu.");
			kb.nextLine();
			return propertyID;
		} else { // else get property ID
			propertyID = properties.get(answer);

		}

		return propertyID;
	}

	/**
	 * Method to get client's full name from property ID.
	 * 
	 * @param int                          propertyID = the ID of the property
	 * @param ArrayList<Client_19876896>   clientList = the list of clients/owners
	 * @param ArrayList<Property_19876896> propertyList = the list of the properties
	 * @return clientName = the full name of the client/owner selected
	 */
	public static String propertyToClient(int propertyID, ArrayList<Client_19876896> clientList,
			ArrayList<Property_19876896> propertyList) {

		String firstName;
		String lastName;
		String clientName; // Full name of the client/owner

		int clientID = propertyList.get(propertyID - 1).getClientID(); // get client ID

		firstName = clientList.get(clientID - 1).getFirstName(); // get client's first name
		lastName = clientList.get(clientID - 1).getLastName(); // get client's last name
		clientName = firstName + " " + lastName;

		return clientName;

	}

	/**********************************************************************************************************
	 * 
	 * READ FILES METHODS
	 * 
	 **********************************************************************************************************/

	/**
	 * Method to read data from the file containing clients information into an
	 * array.
	 * 
	 * @param ArrayList<Client_19876896> clientList = the array of clients
	 * @param String                     name = the name of the file
	 * @return clientList = the array of clients
	 */
	public static ArrayList<Client_19876896> readClientFile(ArrayList<Client_19876896> clientList, String fileName) {

		// IDENTIFIERS
		String newLocation = ""; // new location if file was not found
		String str; // Each string in file
		String[] tokens; // Each word in file (split by "," )
		String[] nameTokens; // Each name contained in the full name inside file (split by a space: " " )
		int clientID; // Unique client identifier
		String firstName; // client's first name
		String lastName; // client's last name
		String streetAddress; // client's street address
		String suburbAddress; // client's suburb address
		String stateAddress; // client's state address
		int postcodeAddress;// client's postcode

		File inFile = new File(fileName);
		Scanner inputFile;

		try { // Check if file exists
			inputFile = new Scanner(inFile);

			while (inputFile.hasNext()) { // Iterate line by line

				str = inputFile.nextLine(); // read a line of text from the file
				tokens = str.split(","); // split the line using commas as delimiter

				// Each token defines a variable to create an instance of the client class
				clientID = Integer.parseInt(tokens[0]);

				nameTokens = tokens[1].split(" ");

				firstName = nameTokens[0];
				lastName = nameTokens[1];
				streetAddress = tokens[2];
				suburbAddress = tokens[3];
				stateAddress = tokens[4];
				postcodeAddress = Integer.parseInt(tokens[5]);

				// Create new object in array list
				clientList.add(new Client_19876896(clientID, firstName, lastName, streetAddress, suburbAddress,
						stateAddress, postcodeAddress));

			} // end while
		} catch (FileNotFoundException e) { // File does not exist
			System.out.println("The file " + fileName + " was not found. Please select a different file for clients.");
			System.out.println("Extension .txt is added automatically.");
			newLocation = kb.nextLine();
			clientList = readClientFile(clientList, newLocation + ".txt"); // Read file from new location

		} // end try and catch statement

		return clientList;
	}

	/**
	 * Method to read data from the file containing properties information into an
	 * array.
	 * 
	 * @param ArrayList<Property_19876896> propertyList = the array of properties
	 * @param String                       name = the name of the file
	 * @return propertyList = the array of properties
	 */
	public static ArrayList<Property_19876896> readPropertyFile(ArrayList<Property_19876896> propertyList,
			String fileName) {

		// IDENTIFIERS
		String newLocation = ""; // new location if file was not found
		String str; // Each string in file
		String[] tokens; // Each word in file (split by "," )
		int propertyID; // Unique property identifier
		String streetAddress; // client's street address
		String suburbAddress; // client's suburb address
		String stateAddress; // client's state address
		int postcodeAddress;// client's postcode
		double weeklyRent; // property's weekly rent
		double managementFee; // property's management fee
		int clientID; // Unique client identifier

		File inFile = new File(fileName);
		Scanner inputFile;

		try { // Check if file exists
			inputFile = new Scanner(inFile);

			while (inputFile.hasNext()) { // Iterate line by line

				str = inputFile.nextLine(); // read a line of text from the file
				tokens = str.split(","); // split the line using commas as delimiter

				// Each token defines a variable to create an instance of the property class
				propertyID = Integer.parseInt(tokens[0]);
				streetAddress = tokens[1];
				suburbAddress = tokens[2];
				stateAddress = tokens[3];
				postcodeAddress = Integer.parseInt(tokens[4]);
				weeklyRent = Double.parseDouble(tokens[5]);
				managementFee = Double.parseDouble(tokens[6]);
				clientID = Integer.parseInt(tokens[7]);

				// Create new object in array list
				propertyList.add(new Property_19876896(propertyID, streetAddress, suburbAddress, stateAddress,
						postcodeAddress, weeklyRent, managementFee, clientID));

			} // end while
		} catch (FileNotFoundException e) { // File does not exist
			System.out
					.println("The file " + fileName + " was not found. Please select a different file for properties.");
			System.out.println("Extension .txt is added automatically.");
			newLocation = kb.nextLine();
			propertyList = readPropertyFile(propertyList, newLocation + ".txt"); // Read file from new location

		} // end try and catch statement

		return propertyList;
	}

	/**
	 * Method to read data from the file containing expenses information into an
	 * array.
	 * 
	 * @param ArrayList<Expense_19876896> expenseList = the array of expenses
	 * @param String                      name = the name of the file
	 * @return expenseList = the array of expenses
	 */
	public static ArrayList<Expense_19876896> readExpenseFile(ArrayList<Expense_19876896> expenseList,
			String fileName) {

		// IDENTIFIERS
		String newLocation = ""; // new location if file was not found
		String str; // Each string in file
		String[] tokens; // Each word in file (split by "," )
		int propertyID; // Unique property identifier
		String expenseDescription; // description for the expense
		double expenseAmount; // amount of the expense
		String expenseDate; // date of the expense

		File inFile = new File(fileName);
		Scanner inputFile;

		try { // Check if file exists
			inputFile = new Scanner(inFile);

			while (inputFile.hasNext()) { // Iterate line by line

				str = inputFile.nextLine(); // read a line of text from the file
				tokens = str.split(","); // split the line using commas as delimiter

				// Each token defines a variable to create an instance of the expense class
				propertyID = Integer.parseInt(tokens[0]);
				expenseDescription = tokens[1];
				expenseAmount = Double.parseDouble(tokens[2]);
				;
				expenseDate = tokens[3];

				// Create new object in array list
				expenseList.add(new Expense_19876896(propertyID, expenseDescription, expenseAmount, expenseDate));

			} // end while
		} catch (FileNotFoundException e) { // File does not exist
			System.out.println("The file " + fileName + " was not found. Please select a different file for expenses.");
			System.out.println("Extension .txt is added automatically.");
			newLocation = kb.nextLine();
			expenseList = readExpenseFile(expenseList, newLocation + ".txt"); // Read file from new location

		} // end try and catch statement

		return expenseList;
	}

	/**
	 * Method to read data from the file containing rents information into an array.
	 * 
	 * @param ArrayList<Rent_19876896> rentList = the array of rents
	 * @param String                   name = the name of the file
	 * @return rentList = the array of rents
	 */
	public static ArrayList<Rent_19876896> readRentFile(ArrayList<Rent_19876896> rentList, String fileName) {

		// IDENTIFIERS
		String newLocation = ""; // new location if file was not found
		String str; // Each string in file
		String[] tokens; // Each word in file (split by "," )

		int propertyID; // Unique property identifier
		double rentAmount; // amount of the rent
		String rentDate; // date of the expense

		File inFile = new File(fileName);
		Scanner inputFile;

		try { // Check if file exists
			inputFile = new Scanner(inFile);

			while (inputFile.hasNext()) { // Iterate line by line

				str = inputFile.nextLine(); // read a line of text from the file
				tokens = str.split(","); // split the line using commas as delimiter

				// Each token defines a variable to create an instance of the rent class
				propertyID = Integer.parseInt(tokens[0]);
				rentAmount = Double.parseDouble(tokens[1]);
				rentDate = tokens[2];

				// Create new object in array list
				rentList.add(new Rent_19876896(propertyID, rentAmount, rentDate));

			} // end while
		} catch (FileNotFoundException e) { // File does not exist
			System.out.println("The file " + fileName + " was not found. Please select a different file for rents.");
			System.out.println("Extension .txt is added automatically.");
			newLocation = kb.nextLine();
			rentList = readRentFile(rentList, newLocation + ".txt"); // Read file from new location

		} // end try and catch statement

		return rentList;
	}

}
