package mibay;

import exception.InvalidOperation;
import exception.NullValueException;
/**
 * <h1>Package Class</h1>
 * <p><b>This class represents a single package by following customer and product 
 * and set the package getDetails and toString.<b><p>
 * 
 * @author CheukLamCHUNG (s3655395)
 * @version 1.0
 * @since 2019-10-11
 */
public class Package {
	protected Customer customer;
	protected Product[] product = new Product[0];
	protected String memberNumber = null;
	
	public Package(Customer customer, Product product) throws NullValueException
	{
		if (customer == null)
		{
			throw new NullValueException("* Error - Customer cannot be emtpy value *");
		}
		else
		{
			this.customer = customer;
		}
		
		if (product == null)
		{
			throw new NullValueException("* Error - Product cannot be emtpy value*");
		}
		else
		{
			this.product = putProductArray(product);
		}
		
	}
	/**
	 * <p>This method is put product in the product array.</p>
	 * @param product
	 * @return
	 */
	public Product[] putProductArray(Product product)
	{
		Product[] newProduct = new Product[this.product.length + 1];
		for (int i = 0 ; i < this.product.length; i++)
		{
			newProduct[i] = this.product[i];
		}
		newProduct[newProduct.length - 1] = product;
		
		return newProduct;
	}
	/**
	 * <p>This method is removing product from the product array.</p>
	 * @param product
	 * @return
	 */
	public Product[] takeProductArray(Product product)
	{
		Product[] newProduct = new Product[this.product.length - 1];
		int newProductIndex = 0;
		boolean done = true;
		for (int i = 0 ; i < this.product.length ; i++)
		{
			if (this.product[i].getName().equalsIgnoreCase(product.getName()) 
					&& this.product[i].getWeight() == product.getWeight()
					&& this.product[i].getCost() == product.getCost())
			{
				if (done)
				{
					done = false;
				}
				else
				{
					newProduct[newProductIndex] = this.product[i];
					newProductIndex++;
				}
			}
			else
			{
				newProduct[newProductIndex] = this.product[i];
				newProductIndex++;
			}
			
		}
		return newProduct;
	}
	/**
	 * <p>This method is for checking the rule of adding product. </p>
	 * @param product
	 * @return true or false
	 * @throws NullValueException
	 * @throws InvalidOperation
	 */
	public boolean addProduct(Product product) throws NullValueException, InvalidOperation
	{
		if (product == null)
		{
			throw new NullValueException("* Error - Product cannot be null *");
		}
		for (int i = 0; i < this.product.length; i++)
		{
			if (this.product[i].getName().equalsIgnoreCase(product.getName()) 
					&& this.product[i].getWeight() == product.getWeight()
					&& this.product[i].getCost() == product.getCost())
			{
				throw new InvalidOperation("* Error - Cannot add the same product *");
			}
		}
		this.product = putProductArray(product);
		return true;
	}
	/**
	 * <p>This method is for checking the rule of removing product</p>
	 * 
	 * @param product
	 * @return true or false
	 * @throws NullValueException
	 * @throws InvalidOperation
	 */
	public boolean removeProduct(Product product) throws NullValueException, InvalidOperation
	{
		if (product == null) 
		{
			throw new NullValueException("* Error - Product cannot be null *");
		}
		if (this.product.length <= 1)
		{
			throw new InvalidOperation("* Error - At less have one product");
		}
		else
		{
			for (int i = 0; i < this.product.length ; i++)
			{
				if (this.product[i].getName().equalsIgnoreCase(product.getName()))
				{
					this.product = takeProductArray(product);
					return true;
				}
			}
		}
		throw new InvalidOperation("* Erre - Product does not exist *");
	}
	
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("-------------------------------------\n");
		sb.append(customer.getDetails());
		for (int i = 0 ; i < product.length ; i++)
		{
			sb.append(product[i].getDetails());
		}
		return sb.toString();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(customer.toString() + "\n" );
		for (int i = 0 ; i < product.length ; i++)
		{
			if(i == product.length-1)
			{
				sb.append(product[i].toString());
			}
			else
			{
				sb.append(product[i].toString() + ":");
			}
		}
		sb.append("\n");
		sb.append(memberNumber + "\n");
		return sb.toString();
	}
	/*
	 * This getting method is for sorting package
	 */
	public String getCustomerFirstName()
	{
		return this.customer.getFirstName();
	}
	/*
	 * This getting method is for sorting package
	 */
	public String getCustomerLastName()
	{
		return this.customer.getLastName();
	}
	/*
	 * This getting method is for taking product array from package class
	 */
	public Product[] getProductList()
	{
		return product;
	}
	

}
