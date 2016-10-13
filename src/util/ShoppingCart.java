package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import bean.Book;

/**
 * ���ﳵ��,�����ǰ�װ��һ��Map����
 * @author soft
 *
 */
public class ShoppingCart {
	//��ɴ����ļ�������
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
	 * ����ͼ�� ���ܷ���
	 */
	public  void  addCart(Book b){
		if(cart.containsKey(b.getId())){//˵��b��cart�д���  ����+1
			//�ӳ���ȡ��id��ͬ�������
			Book b2 = cart.get(b.getId());
			b2.setQuantity(b2.getQuantity()+1);
		}else{//������
			cart.put(b.getId(), b);
		}
	}
	/**
	 * ɾ��ͼ��
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
	 * ��չ��ﳵ
	 */
	public void clearCart(){
		cart.clear();
	}
	/**
	 * ��ù��ﳵ��Ʒ�ܼ�Ǯ
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
	 * ��ȡ��Ʒ����
	 */
	public Collection<Book> getItems(){
		return cart.values();
	}
}
