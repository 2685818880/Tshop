package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import bean.Book;

/**
 * 购物车类,本质是包装了一个Map集合
 * @author soft
 *
 */
public class ShoppingCart {
	//完成存放书的集合属性
	private Map<Integer,Book> cart ;

	public Map<Integer, Book> getCart() {
		return cart;
	}
	//map.put("1",book1);map.put("2",book2);

	public void setCart(Map<Integer, Book> cart) {
		this.cart = cart;
	}
	public ShoppingCart(){
		cart = new HashMap<Integer,Book>();
	}
	/**
	 * 购买图书 功能方法
	 */
	public  void  addCart(Book b){
		if(cart.containsKey(b.getId())){//说明b在cart中存在  数量+1
			//从车中取出id相同的书对象
			Book b2 = cart.get(b.getId());
			b2.setQuantity(b2.getQuantity()+1);
		}else{//不存在
			cart.put(b.getId(), b);
		}
	}
	/**
	 * 删除图书
	 * @param id
	 */
	public void deleteCart(int id){
		if(cart.containsKey(id)){
			Book b = cart.get(id);
			if(b.getQuantity()>1){
				b.setQuantity(b.getQuantity()-1);
			}else{
				cart.remove(id);
			}
		}
	}
	/**
	 * 清空购物车
	 */
	public void clearCart(){
		cart.clear();
	}
	/**
	 * 获得购物车商品总价钱
	 */
	public double getTotalPrice(){
		double totalPrice=0.0;
		Collection<Book> col = cart.values();
		Iterator<Book> it = col.iterator();
		while(it.hasNext()){
			Book b = it.next();
			totalPrice += b.getPrice()*b.getQuantity();
		}
		return totalPrice;
	}
	/**
	 * 获取商品集合
	 */
	public Collection<Book> getItems(){
		return cart.values();
	}
}
