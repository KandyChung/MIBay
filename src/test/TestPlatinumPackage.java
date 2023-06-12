package test;

import exception.NotNumberException;
import exception.NullValueException;
import exception.ValidMemberNumber;
import mibay.Address;
import mibay.Customer;
import mibay.PlatinumPackage;
import mibay.Product;
/**
 * <h1>TestPlatinumPackage Class</h1>
 * <p><b>This class is testing some business rule of platinum package<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class TestPlatinumPackage {

	public static void main(String[] args) throws NullValueException, NotNumberException, ValidMemberNumber {
		/*
		 * Valid construction
		 */
		Address a = new Address("285", "La trobe st", "mel", "3000");
		Customer c = new Customer("Kandy", "Chung", a);
		Product p = new Product("Phone", 4.3, 1000.3);
		PlatinumPackage pp = new PlatinumPackage(c, p, "D0B4M4S7Y3");
		System.out.println(pp.getDetails());
		/*
		 * Invalid construction - empty string
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "3000");
			Customer c2 = new Customer("", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4M4S7Y3");
			System.out.println(pp2.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty string
		 */
		try {
			Address a3 = new Address("285", "La trobe st", "mel", "3000");
			Customer c3 = new Customer("Kandy", "", a3);
			Product p3 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp3 = new PlatinumPackage(c3, p3, "D0B4M4S7Y3");
			System.out.println(pp3.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty string
		 */
		try {
			Address a2 = new Address("", "La trobe st", "mel", "3000");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4M4S7Y3");
			System.out.println(pp2.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty string
		 */
		try {
			Address a2 = new Address("285", "", "mel", "3000");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4M4S7Y3");
			System.out.println(pp2.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty string
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "", "3000");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4M4S7Y3");
			System.out.println(pp2.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty string
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4M4S7Y3");
			System.out.println(pp2.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty member number
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "3000");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "");
			System.out.println(pp2.getDetails());
		}
		catch (ValidMemberNumber e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - invalid member number (9 characters)
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "3000");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4MS7Y3");
			System.out.println(pp2.getDetails());
		}
		catch (ValidMemberNumber e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - not alternate between alphabetical and numerical.
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "3000");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4M4S7Y34");
			System.out.println(pp2.getDetails());
		}
		catch (ValidMemberNumber e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - invalid member number (9 characters)
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "3000");
			Customer c2 = new Customer("Kandy", "Chung", a2);
			Product p2 = new Product("Phone", 4.3, 1000.3);
			PlatinumPackage pp2 = new PlatinumPackage(c2, p2, "D0B4MS7Y3");
			System.out.println(pp2.getDetails());
		}
		catch (ValidMemberNumber e)
		{
			System.out.println(e.getMessage());
		}

	}

}
