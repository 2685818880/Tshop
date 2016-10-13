package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import bean.Book;

public class MapTest {
	
	Map map = new HashMap() ;
	
	public void setMap(Map<String ,Book> m){
		map =m;
	}
	
	public void setMap(Object key,Object value){
		map.put(key, value);
	}
	
	public void setMap(String key ,Book value){
		map.put(key, value);
	}
	
	public Map getMap(){
		return map;
	}
	
	
 
	public static void main(String[] args) {
		 Book b1 = new Book("java","admin1",99.9d);
		 Book b2 = new Book("c++","admin2",88.9d);
		 Book b3 = new Book("php","admin3",77.9d);
		 
		 MapTest m = new MapTest();
		 
		 //往map里面添加对象
		 m.setMap("1",b1);
		 m.setMap("2",b2);
		 m.setMap("3",b3);
		 m.getMap().containsKey("1");
		 if(m.getMap().containsValue(b2)){
			 System.out.println("有这本书。。。");
		 }
		 
		 //从map里面取对象
//		 Set  set = m.getMap().keySet();
//		 Iterator it  = set.iterator();
//		 while(it.hasNext()){
//			 String key = (String)it.next();
//			 Book book = (Book)m.getMap().get(key);
//			 System.out.println(book.getName());
//		 }
		 
		 
	}

}





