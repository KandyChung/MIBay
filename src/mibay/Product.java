package mibay;

import exception.NotNumberException;
import exception.NullValueException;
/**
 * <h1>Product Class</h1>
 * <p><b>This class represents a single product by following attributes 
 * and set the product getDetails and toString.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Product {
	private String name;
	private double weight;
	private double cost;
	
	public Product(String name, double weight, double cost) throws NotNumberException, NullValueException
	{
		if (name == null || name.equals(""))
		{
			throw new NullValueException("* Error - Product name cannot be empty *");
		}
		else
		{
			this.name = name;
		}
		if (weight <= 0)
		{
			throw new NotNumberException("* Error - Weight cannot be less than 0g *");
		}
		else
		{
			this.weight = weight;
		}
		if (cost <= 1)
		{
			throw new NotNumberException("* Error - Cost cannot be less than $1 *");
		}
		else
		{
			this.cost = cost;
		}
	}
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Products Ordered:" + "\n");
		sb.append(String.format("%-15s %s\n", "Name:", name));
		sb.append(String.format("%-15s %s\n", "Weight:", weight + "g"));
		sb.append(String.format("%-15s %s\n", "Cost:", "$" + cost));
		sb.append("\n");
		return sb.toString();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name + "_" + weight + "_" + cost);
		return sb.toString();
	}
	
	/*
	 * Need to get product name when remove product and show message when product successful added.
	 */
	public String getName()
	{
		return this.name;
	}
	/*
	 * Need to get product weight when remove product.
	 */
	public double getWeight()
	{
		return this.weight;
	}
	/*
	 * Need to get product cost when remove product.
	 */
	public double getCost()
	{
		return this.cost;
	}

}
