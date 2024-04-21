package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
	
	//멀티 쓰레드 환경에서 해시맵 쓰면 안됨 컨커런트해시맵 써야함 long도 그냥 롱 쓰면 안됨
	private static final Map<Long, Item> store = new HashMap<>(); //static
	private static long sequence =  0L; //static
	
	public Item save (Item item) {
		item.setId(++sequence);
		store.put(item.getId(), item);
		return item;
	}
	
	public Item findById(long id) {
		return store.get(id);
	}
	
	public List<Item> findAll() {
		return new ArrayList<>(store.values());
	}
	
	public void update(Long itemId, Item updateParam) {
		Item findItem = findById(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}
	
	public void clearStore() {
		store.clear();
	}

}
