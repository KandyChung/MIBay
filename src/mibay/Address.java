package mibay;

import exception.NullValueException;
/**
 * <h1>Address Class</h1>
 * <p><b>This class represents a single address by following attributes 
 * and set the address getDetails and toString.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Address {
	private String streetNumber;
	private String streetName;
	private String suburb;
	private String postCode;
	
	public Address(String streetNumber, String streetName, String suburb, String postCode) throws NullValueException
	{
		if (streetNumber == null || streetNumber.equals(""))
		{
			throw new NullValueException("* Error - Street number cannot be empty value *");
		}
		else
		{
			this.streetNumber = streetNumber;
		}
		
		if (streetName == null || streetName.equals(""))
		{
			throw new NullValueException("* Error - Street name cannot be empty value *");
		}
		else
		{
			this.streetName = streetName;
		}
		
		if (suburb == null || suburb.equals(""))
		{
			throw new NullValueException("* Error - Suburb cannot be empty *");
		}
		else
		{
			this.suburb = suburb;
		}
		
		if (postCode == null || postCode.equals("") || !(isPostCodeValid(postCode)))
		{
			throw new NullValueException("* Error - Post code cannot be empty or wrong post code format *");
		}
		else
		{
			this.postCode = postCode;
		}
		
	}
	/**
	 * <p>This method is for checking is postCode valid and some postCode business rule. </p>
	 * 
	 * @param postCode
	 * @return true or false
	 */
	public boolean isPostCodeValid(String postCode)
	{
		int postCodeLength = postCode.length();
		boolean postCodeLengthIs4 = postCodeLength == 4;
		
		boolean validFirstNo = postCode.substring(0,1).matches("[1-8]+");
		
		boolean isNumeric = postCode.substring(1,3).matches("[0-9]+");
		
		if (postCodeLengthIs4 && validFirstNo && isNumeric)
		{
			return true;
		}
		return false;		
     }	
	
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-15s\n", streetNumber + " " + streetName));
		sb.append(String.format("%-15s %s\n", "        ", suburb + " " + postCode));
		return sb.toString();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(streetNumber + "," + streetName + "," + suburb + "," + postCode);
		return sb.toString();
	}
}
