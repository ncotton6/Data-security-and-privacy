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

public class Util {

	public static List<Product> filterProductsForNow(List<Product> products) {
		Hashtable<Integer, Product> tree = new Hashtable<Integer, Product>();
		for (Product p : products) {
			System.out.println(p);
			/*
			 * if (!tree.containsKey(p.getIdProduct()))
			 * tree.put(p.getIdProduct(), p); else { if
			 * (tree.get(p.getIdProduct()).getDate().before(p.getDate()))
			 * tree.put(p.getIdProduct(), p); }
			 */
		}
		ArrayList<Product> ret = new ArrayList<Product>();
		for (Entry<Integer, Product> p : tree.entrySet()) {
			ret.add(p.getValue());
		}
		return products;
	}

	public static Product getMostRecent(List<Product> product) {
		return product.get(0);
	}

	public static List<Hire> filterManagerSignOff(List<Hire> hire) {
		Iterator<Hire> it = hire.iterator();
		while (it.hasNext()) {
			Hire h = it.next();
			System.out.println(h);
			if (h.getManagerSignOff() != 0)
				it.remove();
		}
		return hire;
	}

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

}
