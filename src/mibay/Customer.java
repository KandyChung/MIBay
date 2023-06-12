package mibay;

import exception.NullValueException;
/**
 * <h1>Customer Class</h1>
 * <p><b>This class represents a single customer by following attributes 
 * and set the customer getDetails and toString.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Customer {
	private String firstName;
	private String lastName;
	private Address addressMain;
	private Address addressSecond;
	
	public Customer(String firstName, String lastName, Address addressMain) throws NullValueException
	{
		if (firstName == null || firstName.equals(""))
		{
			throw new NullValueException("* Error - First Name cannot be emtpy *");
		}
		else
		{
			this.firstName = firstName;
		}
		
		if (lastName == null || lastName.equals(""))
		{
			throw new NullValueException("* Error - Last Name cannot be emtpy *");
		}
		else
		{
			this.lastName = lastName;
		}
		
		if (addressMain == null)
		{
			throw new NullValueException("Address cannot be emtpy");
		}
		else
		{
			this.addressMain = addressMain;
		}
	}
	/**
	 * <p>This method is for adding second address
	 * </p>
	 * @param address
	 * @return
	 * @throws NullValueException
	 */
	public boolean addAddress(Address address) throws NullValueException
	{
		if (!(this.addressSecond == null))
		{
			throw new NullValueException("* Error - Addess cannot more than two *");
		}
		if (address == null)
		{
			throw new NullValueException("* Error - Addess cannot be null *");
		}
		else
		{
			this.addressSecond = address;
			return true;
		}
	}
	/**
	 * <p>This method is for setting second to main address.
	 * </p>
	 * @return
	 */
	public boolean setSecondToMain()
	{
		Address newAddress;
		if (addressSecond == null)
		{
			return false;
		}
		else
		{
			newAddress = addressSecond;
			addressSecond = addressMain;
			addressMain = newAddress;
			return true;
		}
	}
	
	
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-15s %s\n", "Name:", firstName + " " + lastName));
		sb.append(String.format("%-15s %s\n", "Main Address", addressMain.getDetails()));
		if(addressSecond != null) 
		{
			sb.append(String.format("%-15s %s\n", "Second Address", addressSecond.getDetails()));
		}
		return sb.toString();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(firstName + " " + lastName + ":" + addressMain + ":"+addressSecond);
		return sb.toString();
	}
	/*
	 * Need to get first name when choosing customer
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	/*
	 * Need to get last name when choosing customer
	 */
	public String getLastName()
	{
		return this.lastName;
	}

}
