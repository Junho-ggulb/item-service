package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
	
	private Long id;
	private String itemName;
	private Integer price; //가격이 0이면 이상하니까 null쓸 수있게 
	private Integer quantity; //수량 0이면 이상하니까 null쓸 수있게
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item( String itemName, Integer price, Integer quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
	

}
