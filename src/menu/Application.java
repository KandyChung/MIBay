package menu;

import exception.NotNumberException;
import exception.NullValueException;
import mibay.Address;
import mibay.Customer;
import mibay.Delivery;
import mibay.Package;
import mibay.PlatinumPackage;
import mibay.Product;

/**
 * <h1>Application Class</h1>
 * <p><b>This class is for store and manage all the method of applicant<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Application {
	
	private Customer[] customers = new Customer[20];
	private int customerCount = 0;
	private Product[] products = new Product[10];
	private int productCount = 0;
	private Package[] packages = new Package[10];
	private int packagesCount = 0;
	private Delivery[] deliveries = new Delivery[10];
	private int deliveryCount = 0;
	/**
	 * <p>Adding single customer into array.</p>
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @return
	 * @throws NullValueException
	 */
	public String addCustomer(String firstName, String lastName, Address address) throws NullValueException
	{
		if(!checkIfCustomerExists(firstName)) 
		{
			customers[customerCount] = new Customer(firstName, lastName, address);
			customerCount++;
			return customers[customerCount-1].getFirstName() + " was successfully added to the system " ;
		}
		return "Error: Already exists in the system.";
		
	}
	/**
	 * <p>Adding single customer into array.</p>
	 * @param customer
	 * @return
	 */
	public String addCustomer(Customer customer)
	{
		if(!checkIfCustomerExists(customer.getFirstName())) 
		{
			customers[customerCount] = customer;
			customerCount++;
			return customers[customerCount-1].getFirstName() + " was successfully added to the system " ;
		}
		return "Error: Already exists in the system.";
	}
	/**
	 * <p>Adding product into array.</p>
	 * @param name
	 * @param wegiht
	 * @param cost
	 * @return
	 * @throws NullValueException
	 * @throws NotNumberException
	 */
	public String addProduct(String name, double wegiht, double cost) throws NullValueException, NotNumberException 
	{
		products[productCount] = new Product(name, wegiht, cost);
		productCount++;
		return products[productCount-1].getName() + " was successfully added to the system " ;
	}
	/**
	 * <p>Adding product into array.</p>
	 * @param product
	 * @return
	 */
	public String addProduct(Product product)
	{
		products[productCount] = product;
		productCount++;
		return products[productCount-1].getName() + " was successfully added to the system " ;
	}
	/**
	 * <p>Adding single package into array.</p>
	 * @param packages
	 * @return
	 */
	public String addPackage(Package packages)
	{
		this.packages[packagesCount] = packages;
		packagesCount++;
		return "Package for" + this.packages[packagesCount - 1].getCustomerFirstName() +
				this.packages[packagesCount - 1].getCustomerLastName() + " was successfully prepared. ";
	}
	/**
	 * <p>Adding single delivery into array.</p>
	 * @param delivery
	 */
	public void addDelivery(Delivery delivery)
	{
		deliveries[deliveryCount] = delivery;
		System.out.println(deliveries[deliveryCount].getDetails());
		deliveryCount++;
		
	}
	

	public void loadCustomer(Customer[] custList)
	{
		this.customers = custList;
	}
	
	public void loadProduct(Product[] prodList) 
	{
		this.products = prodList;
	}
	/**
	 * <p>Checking is the customer already exist.</p>
	 * @param firstName
	 * @return
	 */
	public boolean checkIfCustomerExists(String firstName)
	{
		Customer customer = null;
		
		customer = getCustomerByFirstName(firstName);
		if (customer == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	/**
	 * <p>Need this when running checking method</p>
	 * @param firstName
	 * @return
	 */
	private Customer getCustomerByFirstName(String firstName)
	{
		Customer customer = null;

		for (int i = 0; i < customers.length; i++)
		{
			if(customers[i] != null)
			{
				if (customers[i].getFirstName().equals(firstName))
				{
					customer = customers[i];
					return customer;
				}
			}
		}
		return customer;
	}
	
	
	/**
	 * <p>Sorting package</p>
	 * @param sortOrder
	 * @return
	 */
	private Package[] sort(String sortOrder)
	{
		boolean sorted = false;
		while (!sorted)
		{
			sorted = true;
			
			for (int i = 0; i < packages.length - 1; i++)
			{
				if (packages[i + 1] != null)
				{
					String currentPackageName = packages[i].getCustomerLastName();
					String nextPackageName = packages[i + 1].getCustomerLastName();
	
					boolean sortedAscending = currentPackageName.compareTo(nextPackageName) < 0 && sortOrder.toUpperCase().equals("A");
					boolean sortedDescending = currentPackageName.compareTo(nextPackageName) > 0 && sortOrder.toUpperCase().equals("D");
					boolean areEqual = currentPackageName.compareTo(nextPackageName) == 0;
					boolean isSorted = sortedAscending || sortedDescending || areEqual;
				

					if (!isSorted)
					{
						Package temp = packages[i];
						packages[i] = packages[i + 1]; 
						packages[i + 1] = temp;
						sorted = false;
					}
				}
			}
		}
		return packages;
	}
	/**
	 * <p>Get package's details</p>
	 * @param sortedPackages
	 * @return
	 */
	private String getAllPackagesDetails(Package[] sortedPackages)
	{
		String allPackagesDetails = "";
		for (int i = 0; i < sortedPackages.length; i++)
		{
			if (sortedPackages[i] != null)
			{
				
				allPackagesDetails += sortedPackages[i].getDetails();
			}
		}
		return allPackagesDetails;
	}
	/**
	 * <p>Display all package details.</p>
	 * @param sortOrder
	 * @return
	 */
	public String displayAllPackages(String sortOrder)
	{
		Package[] sortedPackages = sort(sortOrder);

		String allPackageDetails = getAllPackagesDetails(sortedPackages);
		
		if (allPackageDetails.equals("") )
		{
			allPackageDetails = "Sorry we don't currently have any packages.";
		}
		return allPackageDetails;
	}
	
	public Customer[] getCustomerArray()
	{
		return customers;
	}
	public Product[] getProductArray()
	{
		return products;
	}
	public Package[] getPackageArray()
	{
		return packages;
	}
	public Delivery[] getDeliveryArray()
	{
		return deliveries;
	}

}
