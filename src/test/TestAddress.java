package test;

import exception.NullValueException;
import mibay.Address;
/**
 * <h1>TestAddress Class</h1>
 * <p><b>This class is testing some business rule of address<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class TestAddress {
	public static void main(String[] args) throws NullValueException
	{
		/*
		 * Valid construction
		 */
		Address a = new Address("285", "La trobe st", "mel", "3000");
		System.out.println(a.getDetails() + a.toString());
		
		/*
		 * Invalid construction - empty value
		 */
		try {
			Address a2 = new Address("", "La trobe st", "mel", "3000");
			System.out.println(a2.getDetails() + a2.toString());
		}catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty value
		 */
		try {
			Address a2 = new Address("285", "", "mel", "3000");
			System.out.println(a2.getDetails() + a2.toString());
		}catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty value
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "", "3000");
			System.out.println(a2.getDetails() + a2.toString());
		}catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - empty value
		 */
		try {
			Address a2 = new Address("285", "La trobe st", "mel", "");
			System.out.println(a2.getDetails() + a2.toString());
		}catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - wrong post code format
		 */
		try {
			Address a3 = new Address("285", "La trobe st", "mel", "30000");
			System.out.println(a3.getDetails() + a3.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - wrong post code format
		 */
		try {
			Address a4 = new Address("285", "La trobe st", "mel", "300");
			System.out.println(a4.getDetails() + a4.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		/*
		 * Invalid construction - wrong post code format
		 */
		try {
			Address a5 = new Address("285", "La trobe st", "mel", "0000");
			System.out.println(a5.getDetails() + a5.toString());
		}
		catch (NullValueException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
