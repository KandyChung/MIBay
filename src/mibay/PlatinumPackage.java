package mibay;

import exception.NullValueException;
import exception.ValidMemberNumber;
/**
 * <h1>PlatinumPackage Class</h1>
 * <p><b>Is a sub class of Package. 
 * <p>This class represents a single platinum package by following attributes 
 * and set the platinum package getDetails and toString.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class PlatinumPackage extends Package{
	
	public PlatinumPackage(Customer customer, Product product, String memberNumber) throws NullValueException, ValidMemberNumber
	{
		super(customer, product);
		updateMemberNumber(memberNumber);
		
	}
	/**
	 * <p>This method is some business rule for member number.</p>
	 * 
	 * @param memberNumber
	 * @return true or false
	 * @throws ValidMemberNumber
	 */
	public boolean updateMemberNumber(String memberNumber) throws ValidMemberNumber
	{
		boolean haveMemberNumber = memberNumber != null || !(memberNumber.equals(""));
		boolean tenNumber = memberNumber.length() == 10;
		
		if (haveMemberNumber && tenNumber
			&& Character.isLetter(memberNumber.charAt(0))      
			&& Character.isDigit(memberNumber.charAt(1)) 
			&& Character.isLetter(memberNumber.charAt(2)) 
			&& Character.isDigit(memberNumber.charAt(3)) 
			&& Character.isLetter(memberNumber.charAt(4)) 
			&& Character.isDigit(memberNumber.charAt(5))
			&& Character.isLetter(memberNumber.charAt(6))      
			&& Character.isDigit(memberNumber.charAt(7)) 
			&& Character.isLetter(memberNumber.charAt(8)) 
			&& Character.isDigit(memberNumber.charAt(9)))
		{
			this.memberNumber = memberNumber;
			return true;
		}
		else
		{
			throw new ValidMemberNumber("* Error - Invalid member number, wrong format or null value *");
		}
	}
	
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-15s %s\n", "Member number:", memberNumber));
		sb.append(super.getDetails());
		
		return sb.toString();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}
