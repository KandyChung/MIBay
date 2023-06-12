package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import exception.BadFileException;
import exception.InvalidOperation;
import exception.NotNumberException;
import exception.NullValueException;
import exception.ValidMemberNumber;
import menu.Application;
import mibay.Address;
import mibay.Customer;
import mibay.PlatinumPackage;
import mibay.Product;
import mibay.Package;

/**
 * <h1>Persistence Class</h1>
 * <p><b>This class is for handing file<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Persistence {
	
	
	public void saveAllInfo(Customer[] custArray, Product[] prodArray) throws IOException
	{
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data.txt")));
		PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter("backup.txt")));
		saveCustomer(custArray, pw);
		saveProduct(prodArray, pw);
		pw.close();
		saveCustomer(custArray, pw2);
		saveProduct(prodArray, pw2);
		pw2.close();
		
	}
	
	public void saveCustomer(Customer[] custArray, PrintWriter pw)
	{
		for(Customer cust: custArray)
		{
			if(cust != null)
			{
				String string = cust.toString();
				pw.println(string);
			}
			
		}
		pw.println("*");
	}
	
	public void saveProduct(Product[] prodArray, PrintWriter pw)
	{
		for(Product prod: prodArray)
		{
			if(prod != null)
			{
				String string = prod.toString();
				pw.println(string);
			}
		}
		pw.close();
	}
	
	public void readData(String fileName, Application app) throws IOException, BadFileException, NullValueException, ValidMemberNumber, NotNumberException
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = null;
		
		Customer[] recordsC = new Customer[20];
		Product[] recordsP = new Product[10];
		int recordsCCount = 0;
		int recordsPCount = 0;
		boolean productTurn = false;
		
		while ((line = br.readLine()) != null)
		{
			System.out.println(line);
			if(productTurn==false) 
			{
				if(!(line.charAt(0)=='*')) 
				{
					recordsC[recordsCCount] = createCustomer(line);
					recordsCCount ++;
				}
				else 
				{
				productTurn = true;
				}
			}
			else
			{
				recordsP[recordsPCount] = createProduct(line);
				recordsPCount++;
			}
			
			app.loadCustomer(recordsC);
			app.loadProduct(recordsP);
			
		}
		br.close();
	}
	
	private Customer createCustomer(String line) throws NullValueException {
		
		String[] custDetail = line.split(":");
		String[] nameArray = custDetail[0].split(" ");
		String firstName = nameArray[0];
		String lastName = nameArray[1];
		
		String[] addressMainArray = custDetail[1].split(",");
		String streetNumber = addressMainArray[0];
		String streetName = addressMainArray[1];
		String suburb = addressMainArray[2];
		String postCode = addressMainArray[3];
		
		System.out.println(streetNumber+ " " +streetName+suburb+postCode);
		
		Address addressSecond = null;
		if (!(custDetail[2].equals("null")||custDetail==null))
		{
			String[] addressSecondArray = custDetail[2].split(" ");
			String streetNumber2 = addressSecondArray[0];
			String streetName2 = addressSecondArray[1];
			String suburb2 = addressSecondArray[2];
			String postCode2 = addressSecondArray[3];
			addressSecond = new Address(streetNumber2, streetName2, suburb2, postCode2);
		}
		Address addressMain = new Address(streetNumber, streetName, suburb, postCode);
		Customer customer = new Customer(firstName, lastName, addressMain);
		if (addressSecond != null)
		{
			customer.addAddress(addressSecond);
		}
		
		return customer;	
	}
	
	private Product createProduct(String line) throws NotNumberException, NullValueException { 
		
		String[] prodInfo = line.split("_");
		
		String name = prodInfo[0];
		double weight = Double.parseDouble(prodInfo[1]);
		double cost = Double.parseDouble(prodInfo[2]);
		Product product = new Product(name, weight, cost);
		
		return product;
	
	}
	
	private Package createPackage(StringTokenizer inReader) throws NullValueException, ValidMemberNumber, NotNumberException, InvalidOperation
	{
		
		String customerDetail = inReader.nextToken();
		String[] custArray = customerDetail.split(":");
		String[] nameArray = custArray[0].split(" ");
		String firstName = nameArray[0];
		String lastName = nameArray[1];
		
		String[] addressMainArray = custArray[1].split(" ");
		String streetNumber = addressMainArray[0];
		String streetName = addressMainArray[1];
		String suburb = addressMainArray[2];
		String postCode = addressMainArray[3];
		
		Address addressSecond = null;
		if (custArray[2] != null)
		{
			String[] addressSecondArray = custArray[2].split(" ");
			String streetNumber2 = addressSecondArray[0];
			String streetName2 = addressSecondArray[1];
			String suburb2 = addressSecondArray[2];
			String postCode2 = addressSecondArray[3];
			addressSecond = new Address(streetNumber2, streetName2, suburb2, postCode2);
		}
		Address addressMain = new Address(streetNumber, streetName, suburb, postCode);
		Customer customer = new Customer(firstName, lastName, addressMain);
		if (addressSecond != null)
		{
			customer.addAddress(addressSecond);
		}
		
		String productDetail = inReader.nextToken();
		
		Product[] prodList = new Product[10];
		int proListCount = 0;
		String[] prodArray = productDetail.split(":");
		for (int i = 0; i < prodArray.length; i++)
		{
			String[] prodInfo = prodArray[i].split("_");
			String name = prodInfo[0];
			double weight = Double.parseDouble(prodInfo[1]);
			double cost = Double.parseDouble(prodInfo[2]);
			Product product = new Product(name, weight, cost);
			prodList[proListCount] = product;
			proListCount++;
		}
		
		String memeberNoDetail = inReader.nextToken();
		if (memeberNoDetail != null)
		{
			PlatinumPackage pp = new PlatinumPackage(customer, prodList[0], memeberNoDetail);
			for (int i = 1; i < prodList.length; i++)
			{
				if (prodList[i] != null)
				{
					pp.addProduct(prodList[i]);
				}
			}
			return pp;
		}
		else
		{
			Package p = new Package(customer, prodList[0]);
			for (int i = 1; i < prodList.length; i++)
			{
				if (prodList[i] != null)
				{
					p.addProduct(prodList[i]);
				}
			}
			return p;
		}
	}
}
