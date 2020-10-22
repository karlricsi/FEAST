package hu.karlricsi;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hu.karlricsi.dao.DAO;
import hu.karlricsi.dao.DAOException;
import hu.karlricsi.dao.OrderDAO;
import hu.annalog.entities.AjaxResponse;
import hu.karlricsi.dao.BasketDAO;
import hu.karlricsi.entities.*;

@Controller
public class FeastController {

	@Autowired
	private DAO<User> usersDAO;
	@Autowired
	private DAO<MenuCategory> categoriesDAO;
	@Autowired
	private DAO<MenuElement> foodsDAO;
	@Autowired
	private OrderDAO<Order> orderDAO;
	@Autowired
	private BasketDAO<BasketItem> basketDAO;

	@RequestMapping(value = "/process/addfood", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody AjaxResponse<List<BasketItem>> addFood(@RequestBody AddOrRemoveFood food) {
		List<BasketItem> basketItems = new ArrayList<>();
		try {
			Order order = orderDAO.findOpenedOrder(food.getUserId());
			if(order.getOrderId()==0) {
				orderDAO.insert(new Order(food.getUserId()));
			}
			
			
			basketItems = basketDAO.findAsOrderId(order.getOrderId());
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(basketItems.isEmpty() ? "Empty" : "OK", basketItems);
	}

	@PostMapping(value = "/process/userselect", produces = "application/json")
	public @ResponseBody AjaxResponse<List<BasketItem>> userSelect(@RequestBody UserSelect user) {
		List<BasketItem> basketItems = new ArrayList<>();
		try {
			Order order = orderDAO.findOpenedOrder(user.getUserId());
			basketItems = basketDAO.findAsOrderId(order.getOrderId());
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(basketItems.isEmpty() ? "Empty" : "OK", basketItems);
	}

	@RequestMapping("/")
	public String menu(ModelMap model) {
		return "menu";
	}

	@RequestMapping("/order")
	public String order(ModelMap model) {
		try {
			List<User> users;
			users = usersDAO.findAll();
			JSONArray usersArray = new JSONArray(users);
			model.addAttribute("users", usersArray.toString());
			List<MenuCategory> categories;
			categories = categoriesDAO.findAll();
			JSONArray categoriesArray = new JSONArray(categories);
			model.addAttribute("categories", categoriesArray.toString());
			List<MenuElement> foods;
			foods = foodsDAO.findAll();
			JSONArray foodsArray = new JSONArray(foods);
			model.addAttribute("foods", foodsArray.toString());
			List<OrderItem> basket = new ArrayList<>();
			JSONArray basketArray = new JSONArray(basket);
			model.addAttribute("basket", basketArray.toString());
		} catch (DAOException e) {
		}
		return "order";
	}

}