package menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.BadFileException;
import exception.InvalidInputException;
import exception.InvalidOperation;
import exception.NotNumberException;
import exception.NullValueException;
import exception.ValidMemberNumber;
import io.Persistence;
import mibay.Address;
import mibay.Customer;
import mibay.Delivery;
import mibay.Product;
import utilites.DateTime;
import mibay.Package;
import mibay.PlatinumPackage;
/**
 * <h1>Menu Class</h1>
 * <p><b>This class is display menu and user interface.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Menu {
	
	public static void main(String[] args) throws NullValueException, NotNumberException, InvalidInputException, ValidMemberNumber, InvalidOperation, IOException, BadFileException
	{
		Menu menu = new Menu();
		menu.run();
	}
	
	private Scanner console = new Scanner(System.in);
	private Application app = new Application();
	private Persistence per = new Persistence();
	
	public void run() throws NullValueException, NotNumberException, InvalidInputException, ValidMemberNumber, InvalidOperation, IOException, BadFileException
	{
		String input;
		try 
		{
			per.readData("data.txt", app);
		}
		catch(IOException e) {
			
		}
		
		do
		{
			printMenu();
			input = console.nextLine().toUpperCase();
			
			if (input.length() != 2)
			{
				System.out.println("Invalid selection - must be two characters!");
			} 
			else
			{
				System.out.println();

				switch (input)
				{
				case "AC":
					try 
					{
						addCustomer();
					}
					catch (NullValueException e)
					{
						System.out.println(e.getMessage());
						
					}
					break;
				case "AP":
					try 
					{
						addProduct();
					}
					catch (NullValueException e)
					{
						System.out.println(e.getMessage());
					}
					catch (NotNumberException e)
					{
						System.out.println(e.getMessage());
					}
					break;
				case "RP":
					try 
					{
						removeProduct();
					}
					catch (NullValueException e)
					{
						System.out.println(e.getMessage());
					}
					catch (NotNumberException e)
					{
						System.out.println(e.getMessage());
					}
					break;
				case "PP":
					try 
					{
						prepareOrder();
					}
					catch (NullValueException e)
					{
						System.out.println(e.getMessage());
					}
					break;
				case "DA":
					System.out.println("Enter Sort Oder (A/D): ");
					String sort = console.nextLine();
					System.out.println(app.displayAllPackages(sort));
					break;
				case "DS":
					deliverySearch();
					break;
				case "SD":
					seedData();
					break;
				case "EX":
					per.saveAllInfo(app.getCustomerArray(), app.getProductArray());
					break;
					
				default:
					System.out.println("Sorry, we do not have this selection!");
					System.out.println("Try Again...");
				}
			}
		}while (input != "EX");
	
	}
	/**
	 * <p>Adding customer interface.</p>
	 * @throws NullValueException
	 */
	private void addCustomer() throws NullValueException
	{
		System.out.println("Enter customer details:");
		System.out.println("Enter first name:");
		String firstName = console.nextLine();
		System.out.println("Enter last name:");
		String lastName = console.nextLine();
		System.out.println("Enter street number:");
		String streetNumber = console.nextLine();
		System.out.println("Enter street name:");
		String streetName = console.nextLine();
		System.out.println("Enter suburb:");
		String suburb = console.nextLine();
		System.out.println("Enter postcode:");
		String postCode = console.nextLine();
		
		Address address = new Address(streetNumber, streetName, suburb, postCode);
		
		boolean result = app.checkIfCustomerExists(firstName);
		
		if (!result)
		{
			String customerADD = app.addCustomer(firstName, lastName, address);
			System.out.println(customerADD);
		} else
		{
			System.out.println("Error - Already exists in the system");
		}
		
	}
	/**
	 * <p>Adding product interface.</p>
	 * @throws NullValueException
	 * @throws NotNumberException
	 */
	private void addProduct() throws NullValueException, NotNumberException
	{
		System.out.println("Enter product details:");
		System.out.println("Enter name:");
		String productName = console.nextLine();
		System.out.println("Enter weight:");
		double weight = console.nextDouble();
		System.out.println("Enter cost:");
		double cost = console.nextDouble();
		console.nextLine();
		
		String productADD = app.addProduct(productName, weight, cost);
		System.out.println(productADD);
		
	}
	/**
	 * <p>Remove product interface.</p>
	 * @throws NullValueException
	 * @throws NotNumberException
	 * @throws InvalidOperation
	 */
	private void removeProduct() throws NullValueException, NotNumberException, InvalidOperation
	{
		int packListLength = 0;
		int proListLength = 0;
		Package[] packList = app.getPackageArray();
		if (packList[0] == null)
		{
			throw new NullValueException("Sorry no packages available. ");
		}
		for (int i = 0; i < packList.length; i++)
		{
			if (packList[i] != null)
			{
				packListLength++;
				System.out.println(i + 1 + ". " + packList[i].getCustomerFirstName() + " " + packList[i].getCustomerLastName());
			}
		}
		if (packList.length != 0)
		{
			try {
				System.out.println("Please choose package from the list: ");
				int input = console.nextInt();
				console.nextLine();
				if (input <= packListLength && input >= 0)
				{
					Package packages = packList[input - 1];
					for (int i = 0; i < packages.getProductList().length; i++)
					{
						proListLength++;
						System.out.println(i + 1 + ". " + packages.getProductList()[i].getName());
					}
					input = console.nextInt();
					console.nextLine();
					if (input <= proListLength && input >= 0)
					{
						packages.removeProduct(packages.getProductList()[input - 1]);
						System.out.println("Remove successful! ");
					}
					else
					{
						throw new InvalidInputException("* Error - The selection not is list *");
					}
				}
				else
				{
					throw new InvalidInputException("* Error - The selection not is list *");
				}
				
			}
			catch (InputMismatchException e)
			{
				System.out.println("Error - Invalid input");
			}
			catch (InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			catch (InvalidOperation e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * <p>Prepare Order interface.</p>
	 * @throws InvalidInputException
	 * @throws NullValueException
	 * @throws ValidMemberNumber
	 * @throws InvalidOperation
	 */
	private void prepareOrder() throws InvalidInputException, NullValueException, ValidMemberNumber, InvalidOperation
	{
		Customer[] cusList = app.getCustomerArray();
		if (cusList[0] == null)
		{
			throw new NullValueException("Sorry no customers available.");
		}
		Product[] productList = app.getProductArray();
		if (productList[0] == null)
		{
			throw new NullValueException("Sorry no products available.");
		}
		
		Delivery delivery;
		Customer customer;
		Product[] userProduct = new Product[100];
		int userProductCount = 0;
		int cusListLength = 0;
		for (int i = 0; i < cusList.length; i++)
		{
			if (cusList[i] != null)
			{
				cusListLength++;
				System.out.println(i + 1 + ". " + cusList[i].getFirstName() + " " + cusList[i].getLastName());
			}
		}
		if (cusList.length != 0)
		{
			try
			{
				System.out.println("Please choose a customer from the list:");
				int inputCus = console.nextInt();
				console.nextLine();
				if (inputCus <= cusListLength && inputCus >= 0)
				{
					customer = cusList[inputCus - 1];
					String askAnotherProduct = "";
					do
					{
						userProduct[userProductCount] = chooseProduct();
						userProductCount++;
						do
						{
							System.out.println("Would you like to add another product? (Y/N)");
							askAnotherProduct = console.nextLine();
							if (!(askAnotherProduct.equalsIgnoreCase("Y") || askAnotherProduct.equalsIgnoreCase("N")))
							{
								System.out.println("Input must be Y or N !");
							}
						}
						while(!(askAnotherProduct.equalsIgnoreCase("Y") || askAnotherProduct.equalsIgnoreCase("N")));
					}
					while (!(askAnotherProduct.equalsIgnoreCase("N")));
					
					
					
					System.out.println("Please enter the delivery date: ");
					System.out.println("Enter Day:");
					int day = console.nextInt();
					System.out.println("Enter Month:");
					int mon = console.nextInt();
					System.out.println("Enter Year: ");
					int year = console.nextInt();
					console.nextLine();
					delivery = new Delivery(day, mon, year);
					
					String packageInput = "";
					
					do
					{
						System.out.println("Is this a Platinum Package? (Y/N)");
						packageInput = console.nextLine();
						if (!(packageInput.equalsIgnoreCase("Y") || packageInput.equalsIgnoreCase("N")))
						{
							System.out.println("Input must be Y or N !");
						}
					}
					while(!(packageInput.equalsIgnoreCase("Y") || packageInput.equalsIgnoreCase("N")));
					
					if (packageInput.equalsIgnoreCase("Y"))
					{
						System.out.println("Please input your member number: ");
						String memberNo = console.nextLine();
						
						delivery.addPlatinumPackage(customer, userProduct, memberNo, app);
					}
					else
					{
						delivery.addPackage(customer, userProduct, app);
					}
					System.out.println("Package for " + customer.getFirstName() + " " + customer.getLastName() + "was succssfully prepared.");
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Error - Invalid input");
			}
			catch (InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	}
	/**
	 * <p>Delivery search interface.</p>
	 */
	private void deliverySearch()
	{
		System.out.println("Enter Day:   ");
		int day = console.nextInt();
		System.out.println("Enter Month: ");
		int month = console.nextInt();
		System.out.println("Enter Year:  ");
		int year = console.nextInt();
		console.nextLine();
		DateTime userInput = new DateTime(day, month, year);
		Delivery[] deliveryList = app.getDeliveryArray();
		System.out.println(deliveryList[0].getDetails());
		int diffDate = 0;
		boolean found = false;
		for (int i = 0; i < deliveryList.length; i++)
		{
			if (deliveryList[i] != null)
			{
				diffDate = DateTime.diffDays(deliveryList[i].getDateTime(), userInput);
				
				if (diffDate == 0)
				{
					found = true;
					System.out.println(deliveryList[i].getDetails());
				}
			}
		}
		if (found == false)
		{
			System.out.println("Sorry no delivery found. ");
		}
	}	
    /**
     * <p>Seeding data.</p>
     * @throws NullValueException
     * @throws NotNumberException
     * @throws InvalidInputException
     * @throws ValidMemberNumber
     * @throws InvalidOperation
     */
	private void seedData() throws NullValueException, NotNumberException, InvalidInputException, ValidMemberNumber, InvalidOperation
	{
		
		Address address = new Address("285", "La trobe st", "mel", "3000");
		
		Customer kandyC = new Customer("Kandy", "Chung", address);
		Customer peterC = new Customer("Peter", "Meng", address);
		Customer lucyC = new Customer("Lucy", "Bieber", address);
		Customer helenC = new Customer("Helen", "Yuk", address);
		
		app.addCustomer(kandyC);
		app.addCustomer(peterC);
		app.addCustomer(lucyC);
		app.addCustomer(helenC);
		
		Product kandyP = new Product("Phone", 4.4, 1000.3);
		Product kandyP2 = new Product("ppp", 3.4, 123.3);
		Product peterP = new Product("Paper", 1.2, 20.3);
		Product lucyP = new Product("Table", 10.3, 277.2);
		Product helenP = new Product("Laptop", 5.6, 2097.1);
		
		app.addProduct(kandyP);
		app.addProduct(peterP);
		app.addProduct(lucyP);
		app.addProduct(helenP);
		
		Package kandyPa = new Package(kandyC, kandyP);
		Package peterPa = new Package(peterC, peterP);
		Package lucyPa = new Package(lucyC, lucyP);
		PlatinumPackage helenPa = new PlatinumPackage(helenC, helenP, "D4D5D6G6G5");
		kandyPa.addProduct(kandyP2);
		
		app.addPackage(kandyPa);
		app.addPackage(peterPa);
		app.addPackage(lucyPa);
		app.addPackage(helenPa);
		
		DateTime kandyT = new DateTime(3);
		DateTime peterT = new DateTime(4);
		DateTime lucyT = new DateTime(5);
		DateTime helenT = new DateTime(6);
		
		Delivery kandyD = new Delivery(kandyT);
		kandyD.addPackage(kandyPa, app);
		Delivery peterD = new Delivery(peterT);
		peterD.addPackage(peterPa, app);
		Delivery lucyD = new Delivery(lucyT);
		lucyD.addPackage(lucyPa, app);
		Delivery helenD = new Delivery(helenT);
		helenD.addPlatinumPackage(helenPa, app);
		
		for (int i = 0; i < app.getCustomerArray().length; i++)
		{
			if (app.getCustomerArray()[i] != null)
			{
				System.out.println(app.getCustomerArray()[i].toString());
			}
		}
		for (int i = 0; i < app.getProductArray().length; i++)
		{
			if (app.getProductArray()[i] != null)
			{
				System.out.println(app.getProductArray()[i].toString());
			}
		}
		
		
		app.addDelivery(kandyD);
		app.addDelivery(peterD);
		app.addDelivery(lucyD);
		app.addDelivery(helenD);
		
		System.out.println("Seed data successful");
	}
	/**
	 * <p>Printing menu.</p>
	 */
	private void printMenu()
	{
		System.out.println("");
		System.out.println("*** MiBayApplication System Menu ***");
		System.out.println("Add Customer                      AC");
		System.out.println("Add Product                       AP");
		System.out.println("Remove Product                    RP");
		System.out.println("Prepare Order                     PP");
		System.out.println("Display ALL Deliveries            DA");
		System.out.println("Delivery Search                   DS");
		System.out.println("Seed Data                         SD");
		System.out.println("Exit Program                      EX");
		System.out.println("");
		System.out.println("Enter selection:--------------------");
	}
	/**
	 * <p>Method of choosing product.</p>
	 * @return
	 * @throws InvalidInputException
	 */
	private Product chooseProduct() throws InvalidInputException
	{
		Product[] proList = app.getProductArray();
		int proListLength = 0 ;
		for (int i = 0; i < proList.length; i++)
		{
			if (proList[i] != null)
			{
				proListLength++;
				System.out.println(i + 1 + ". " + proList[i].getName());
			}
		}
		try
		{
			System.out.println("Please choose a product from the list:");
			int inputPro = console.nextInt();
			console.nextLine();
			if (!(inputPro <= proListLength && inputPro >= 0))
			{
				throw new InvalidInputException("Error - Invalid input");
			}
			return proList[inputPro - 1];
		}
		catch (InputMismatchException e)
		{
			System.out.println("Error - Invalid input");
			chooseProduct();
		}
		catch (InvalidInputException e)
		{
			System.out.println(e.getMessage());
			chooseProduct();
		}
		return proList[0];
		
	}

}

