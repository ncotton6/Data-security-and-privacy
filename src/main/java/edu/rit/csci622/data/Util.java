package edu.rit.csci622.data;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import edu.rit.csci622.model.Product;

public class Util {

	public static List<Product> filterProductsForNow(List<Product> products) {
		Hashtable<Integer, Product> tree = new Hashtable<Integer, Product>();
		for (Product p : products) {
			System.out.println(p);
			/*if (!tree.containsKey(p.getIdProduct()))
				tree.put(p.getIdProduct(), p);
			else {
				if (tree.get(p.getIdProduct()).getDate().before(p.getDate()))
					tree.put(p.getIdProduct(), p);
			}*/
		}
		ArrayList<Product> ret = new ArrayList<Product>();
		for (Entry<Integer, Product> p : tree.entrySet()) {
			ret.add(p.getValue());
		}
		return products;
	}

}
