package test;

import exception.NotNumberException;
import exception.NullValueException;
import mibay.Product;
/**
 * <h1>TestProduct Class</h1>
 * <p><b>This class is testing some business rule of product<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class TestProduct {

	public static void main(String[] args) throws NotNumberException, NullValueException {
		/*
		 * Valid construction
		 */
		Product p = new Product("Phone", 4.4, 1000.3);
		System.out.println(p.getDetails());
		
		/*
		 * Invalid construction - empty string
		 */
		try {
			Product p1 = new Product("", 4.4, 1000.3);
			System.out.println(p1.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - weight lass than 0
		 */
		try {
			Product p2 = new Product("Phone", -2.4, 1000.3);
			System.out.println(p2.getDetails());
		}
		catch (NotNumberException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - cost lass than 1
		 */
		try {
			Product p3 = new Product("Phone", 4.4, 0.1);
			System.out.println(p3.getDetails());
		}
		catch (NotNumberException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - cost lass than 1
		 */
		try {
			Product p4 = new Product(null, 4.4, 1000.3);
			System.out.println(p4.getDetails());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}

	}

}
