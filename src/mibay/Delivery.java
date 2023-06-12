package mibay;

import exception.InvalidInputException;
import exception.InvalidOperation;
import exception.NullValueException;
import exception.ValidMemberNumber;
import utilites.DateTime;
import menu.Application;

/**
 * <h1>Delivery Class</h1>
 * <p><b>This class represents a single delivery by following package and date time 
 * and set the delivery getDetails and toString.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Delivery {
	private int day;
	private int month;
	private int year;
	private Package packages;
	private DateTime dateTime;
	
	public Delivery(int day, int month, int year) throws InvalidInputException    
	{
		DateTime today = new DateTime();
		DateTime deliveryDate = new DateTime(day, month, year);
	
		int diffDate = DateTime.diffDays(deliveryDate, today);
		if(diffDate > 0 && diffDate < 15)
		{
			this.day = day;
			this.month = month;
			this.year = year;
			this.dateTime = new DateTime(day, month, year);
		}
		else
		{
			throw new InvalidInputException("Delivery date cannot be past date or more than two weeks ");
		}
	}
	
	public Delivery(DateTime dateTime) throws InvalidInputException    
	{
		DateTime today = new DateTime();
	
		int diffDate = DateTime.diffDays(dateTime, today);
		if(diffDate > 0 && diffDate < 15)
		{
			this.dateTime = dateTime;
			this.day = Integer.parseInt(dateTime.getFormattedDate().split("/")[0]);
			this.month = Integer.parseInt(dateTime.getFormattedDate().split("/")[1]);
			this.year = Integer.parseInt(dateTime.getFormattedDate().split("/")[2]);
		}
		else
		{
			throw new InvalidInputException("Delivery date cannot be past date or more than two weeks ");
		}
	}
	/**
	 * <p>This method is for adding product.</p>
	 * @param customer
	 * @param proList
	 * @param app
	 * @throws NullValueException
	 * @throws InvalidOperation
	 */
	public void addPackage(Customer customer, Product[] proList, Application app) throws NullValueException, InvalidOperation
	{
		packages = new Package(customer, proList[0]);
		
		for (int i = 1; i < proList.length; i++)
		{
			if (proList[i] != null)
			{
				packages.addProduct(proList[i]);
			}
		}
		app.addPackage(packages);
	}
	/**
	 * <p>This method is for adding package of seed data.</p>
	 * @param packages
	 * @param app
	 * @throws NullValueException
	 */
	public void addPackage(Package packages, Application app) throws NullValueException
	{
		this.packages = packages;
	}
	/**
	 * <p>This method is for adding platinum package. </p>
	 * @param customer
	 * @param proList
	 * @param memberNumber
	 * @param app
	 * @throws NullValueException
	 * @throws ValidMemberNumber
	 * @throws InvalidOperation
	 */
	public void addPlatinumPackage(Customer customer, Product[] proList, String memberNumber,Application app) throws NullValueException, ValidMemberNumber, InvalidOperation 
	{
		packages = new PlatinumPackage(customer, proList[0], memberNumber);
		for (int i = 1; i < proList.length; i++)
		{
			if (proList[i] != null)
			{
				packages.addProduct(proList[i]);
			}
		}	
		app.addPackage(packages);
	}
	/**
	 * <p>This method is for adding platinum package of seed data.</p>
	 * @param platinumPackages
	 * @param app
	 * @throws NullValueException
	 * @throws ValidMemberNumber
	 */
	public void addPlatinumPackage(PlatinumPackage platinumPackages,Application app) throws NullValueException, ValidMemberNumber 
	{
		this.packages = platinumPackages;
	}
	
	
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(packages.getDetails());
		sb.append(String.format("%-15s %s\n", "Delivery Date:", dateTime.getFormattedDate()));
		sb.append("-------------------------------------\n");
		return sb.toString();		
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(packages.toString());
		sb.append(String.format(dateTime.getFormattedDate()));
		return sb.toString();
	}
	/*
	 * Need data time is other class.
	 */
	public DateTime getDateTime()
	{
		return dateTime;
	}
	

}
