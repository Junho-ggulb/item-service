package hello.itemservice.web.basic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Slf4j
public class BasicItemController {
	//스프링이 생성자가 하나밖에 없으면 자동으로 주입해줘서 autowried 제거 final이 붙으면 lombok이 자동으로 생성자 메서드 만들어 줌
	private final ItemRepository itemRepository;
	
	
//	@Autowired
//	public BasicItemController(ItemRepository itemRepository) {
//		this.itemRepository= itemRepository;
//	}
	
	@GetMapping
	public String Items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		
		return "basic/items";
	}
	
	@GetMapping("/{itemId}")
	public String item(@PathVariable(name = "itemId") long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}
	
//	@PostMapping("/add")
	public String addItemV1(@RequestParam("itemName") String itemName, @RequestParam("price") Integer price, @RequestParam("quantity") Integer quantity, Model model) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		itemRepository.save(item);
		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String addItemV2(@ModelAttribute("item") Item item) {

		itemRepository.save(item);
		//@ModelAttribute("item") 이렇게 쓰면 자동 model에 해당이름으로 담아줌
//		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String addItemV3(@ModelAttribute Item item) {
		//이름 지우면 Item 객체명의 첫글자만 소문자로 바꿔서 model에 담음 Item -> item		
		itemRepository.save(item);
		//@ModelAttribute("item") 이렇게 쓰면 자동 model에 해당이름으로 담아줌
//		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String addItemV4(Item item) {
		//@ModelAttribute 이것도 생략 가능 Item -> item 으로 model 자동으로 담아줌
		//이름 지우면 Item 객체명의 첫글자만 소문자로 바꿔서 model에 담음 Item -> item		
		itemRepository.save(item);
		//@ModelAttribute("item") 이렇게 쓰면 자동 model에 해당이름으로 담아줌
//		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String addItemV5(Item item) {
		//@ModelAttribute 이것도 생략 가능 Item -> item 으로 model 자동으로 담아줌
		//이름 지우면 Item 객체명의 첫글자만 소문자로 바꿔서 model에 담음 Item -> item		
		itemRepository.save(item);
		//@ModelAttribute("item") 이렇게 쓰면 자동 model에 해당이름으로 담아줌
//		model.addAttribute("item", item);
		
		return "redirect:/basic/items/" +item.getId();
	}
	
	@PostMapping("/add")
	public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
		//@ModelAttribute 이것도 생략 가능 Item -> item 으로 model 자동으로 담아줌
		//이름 지우면 Item 객체명의 첫글자만 소문자로 바꿔서 model에 담음 Item -> item		
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		//안쓴거는 query param으로 들어감
//		model.addAttribute("item", item);
		
		return "redirect:/basic/items/{itemId}";
	}
	
	@GetMapping("/{itemId}/edit")
	public String ediForm(@PathVariable("itemId") Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute("item") Item item) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}
	
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 10000, 10));
	}
		
	
	
	

}
