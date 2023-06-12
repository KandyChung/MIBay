package test;

import exception.NullValueException;
import mibay.Address;
import mibay.Customer;
/**
 * <h1>TestCustomer Class</h1>
 * <p><b>This class is testing some business rule of customer<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class TestCustomer {
	public static void main(String[] args) throws NullValueException {
		/*
		 * Valid construction
		 */
		Address a = new Address("285", "La trobe st", "mel", "3000");
		Address aa = new Address("343", "Happy st", "mel", "3002");
		Customer c = new Customer("Kandy", "Chung", a);
		c.addAddress(aa);
		c.setSecondToMain();
		System.out.println(c.getDetails() + c.toString());
//		
//		/*
//		 * Invalid construction - empty string 
//		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "3000");
			Customer c2 = new Customer("", "Chung", a2);
			System.out.println(c2.getDetails() + c2.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
//		/*
//		 * Invalid construction - empty string
//		 */
		try {
			Address a3 = new Address("285", "La trobe st", "mel", "3000");
			Customer c3 = new Customer("Kany", "", a3);
			System.out.println(c3.getDetails() + c3.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - null values
		 */
		try {
			Address a5 = new Address("285", "La trobe st", "mel", "3000");
			Customer c5 = new Customer(null, "Chung", a5);
			System.out.println(c5.getDetails() + c5.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - null values
		 */
		try {
			Address a6 = new Address("285", "La trobe st", "mel", "3000");
			Customer c6 = new Customer("Kandy", null, a6);
			System.out.println(c6.getDetails() + c6.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
