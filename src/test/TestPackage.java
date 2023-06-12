package test;

import exception.InvalidOperation;
import exception.NotNumberException;
import exception.NullValueException;
import mibay.Address;
import mibay.Customer;
import mibay.Product;
import mibay.Package;
/**
 * <h1>TestPackage Class</h1>
 * <p><b>This class is testing some business rule of package<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class TestPackage {

	public static void main(String[] args) throws NullValueException, NotNumberException, InvalidOperation {
		
		Address a = new Address("285", "La trobe st", "mel", "3000");
		Customer c = new Customer("Kandy", "Chung", a);
		/*
		 * Valid construction
		 */
		Product p = new Product("Phone", 4.4, 1000.3);
		Package pa = new Package(c, p);
		System.out.println(pa.getDetails() + pa.toString());
		
		/*
		 * Invalid construction - Customer are null value
		 */
		try {
			Product p2 = new Product("Phone", 4.4, 1000.3);
			Package pa2 = new Package(null, p2);
			System.out.println(pa2.getDetails() + pa2.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - Product are null value
		 */
		try {
			Product p3 = new Product("Phone", 4.4, 1000.3);
			Package pa3 = new Package(c, null);
			System.out.println(pa3.getDetails() + pa3.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Adding product
		 */
		Product p4 = new Product("Phone", 4.4, 1000.3);
		Product pAdd4 = new Product("Cup", 5.3, 21.3);
		Package pa4 = new Package(c, p4);
		pa4.addProduct(pAdd4);
		System.out.println(pa4.getDetails() + pa4.toString());
		/*
		 * Invalid construction - adding same product
		 */
		try {
			Product p5 = new Product("Phone", 4.4, 1000.3);
			Product pAdd5 = new Product("Cup", 5.3, 21.3);
			Package pa5 = new Package(c, p5);
			pa4.addProduct(pAdd5);
			pa4.addProduct(pAdd5);
			System.out.println(pa5.getDetails() + pa5.toString());
		}
		catch (InvalidOperation e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Removing product 
		 */
		Product p7 = new Product("Phone", 4.4, 1000.3);
		Product pAdd7 = new Product("Cup", 5.3, 21.3);
		Package pa7 = new Package(c, p7);
		pa7.addProduct(pAdd7);
		Product pRemove5 = new Product("Cup", 5.3, 21.3);
		pa7.removeProduct(pRemove5);
		System.out.println(pa7.getDetails() + pa7.toString());
		System.out.println(pa4.getDetails() + pa4.toString());
		/*
		 * Invalid construction - remove product is not exist
		 */
		try {
			Product p8 = new Product("Phone", 4.4, 1000.3);
			Package pa8 = new Package(c, p8);
			pa7.addProduct(pAdd7);
			pa7.addProduct(pAdd7);
			pa7.addProduct(pAdd7);
			Product pRemove8 = new Product("Cup", 5.3, 21.3);
			pa7.removeProduct(pRemove8);
			System.out.println(pa8.getDetails() + pa8.toString());
		}
		catch (InvalidOperation e)
		{
			System.out.println(e.getMessage());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		

	}

}
