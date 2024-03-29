package edu.rit.csci622.data;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import edu.rit.csci622.auth.Role;
import edu.rit.csci622.model.Hire;
import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

/**
 * Useful cross-cutting functions
 * 
 * @author Nathaniel Cotton
 *
 */
public class Util {

	/**
	 * Removes the older renditions of products.
	 * 
	 * @param products
	 * @return
	 */
	public static List<Product> filterProductsForNow(List<Product> products) {
		Hashtable<Integer, Product> tree = new Hashtable<Integer, Product>();
		for (Product p : products) {
			System.out.println(p.getIdProduct());
			if (!tree.containsKey(p.getIdProduct()))
				tree.put(p.getIdProduct(), p);
			else {
				if (tree.get(p.getIdProduct()).getDate().before(p.getDate()))
					tree.put(p.getIdProduct(), p);
			}

		}
		ArrayList<Product> ret = new ArrayList<Product>();
		for (Entry<Integer, Product> p : tree.entrySet()) {
			ret.add(p.getValue());
		}
		return ret;
	}

	/**
	 * Gets the most recent product from a list.
	 * 
	 * @param product
	 * @return
	 */
	public static Product getMostRecent(List<Product> product) {
		return product.get(0);
	}

	/**
	 * Filters through hire requests that a manager has already signed off on.
	 * 
	 * @param hire
	 * @return
	 */
	public static List<Hire> filterManagerSignOff(List<Hire> hire) {
		Iterator<Hire> it = hire.iterator();
		while (it.hasNext()) {
			Hire h = it.next();
			if (h.getManagerSignOff() != 0)
				it.remove();
		}
		return hire;
	}

	/**
	 * Filters through all users searching for employees.
	 * 
	 * @param users
	 * @return
	 */
	public static List<User> filterForEmployees(List<User> users) {
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if (u.getRole() != Role.MANAGER.roleId && u.getRole() != Role.HR.roleId
					&& u.getRole() != Role.EMPLOYEE.roleId)
				it.remove();
		}
		return users;
	}

	/**
	 * Searches for hire requests that have not been signed off on.
	 * 
	 * @param hire
	 * @return
	 */
	public static List<Hire> filterHRSignoff(List<Hire> hire) {
		Iterator<Hire> it = hire.iterator();
		while (it.hasNext()) {
			Hire h = it.next();
			System.out.println(h);
			if (h.getHrSignOff() != 0)
				it.remove();
		}
		return hire;
	}

	/**
	 * Only returns the active products.
	 * 
	 * @param products
	 * @return
	 */
	public static List<Product> filterActive(List<Product> products) {
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product h = it.next();
			if (!h.isActive())
				it.remove();
		}
		return products;
	}

}
