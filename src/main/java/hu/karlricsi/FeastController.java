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
	private DAO<Category> categoriesDAO;
	@Autowired
	private DAO<Food> foodsDAO;
	@Autowired
	private OrderDAO<Order> ordersDAO;
	@Autowired
	private BasketDAO<BasketItem> basketDAO;

	@PostMapping(value = "/process/addfood", produces = "application/json")
	public @ResponseBody AjaxResponse<List<BasketItem>> addFood(@RequestBody AddOrRemoveFood food) {
		List<BasketItem> basketItems = new ArrayList<>();
		try {
			Order order = ordersDAO.findOpenedOrder(food.getUserId());
			if (order.getOrderId() == 0) {
				ordersDAO.insert(new Order(food.getUserId()));
				order = ordersDAO.findOpenedOrder(food.getUserId());
			}
			basketDAO.udateBasketItem(food, order.getOrderId(), foodsDAO.find(food.getFoodId()).getPrice(), false);
			basketItems = basketDAO.findAsOrderId(order.getOrderId());
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(basketItems.isEmpty() ? "Empty" : "OK", basketItems);
	}

	@PostMapping(value = "/process/removefood", produces = "application/json")
	public @ResponseBody AjaxResponse<List<BasketItem>> removeFood(@RequestBody AddOrRemoveFood food) {
		List<BasketItem> basketItems = new ArrayList<>();
		try {
			Order order = ordersDAO.findOpenedOrder(food.getUserId());
			basketDAO.udateBasketItem(food, order.getOrderId(), foodsDAO.find(food.getFoodId()).getPrice(), true);
			basketItems = basketDAO.findAsOrderId(order.getOrderId());
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(basketItems.isEmpty() ? "Empty" : "OK", basketItems);
	}

	@PostMapping(value = "/process/closeorder", produces = "application/json")
	public @ResponseBody AjaxResponse<String> closeOrder(@RequestBody UserSelect user) {
		int result = 0;
		try {
			Order order = ordersDAO.findOpenedOrder(user.getUserId());
			order.setClosed(true);
			result = ordersDAO.update(order);
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(result == 0 ? "Error" : "OK", "[]");
	}

	@PostMapping(value = "/process/removeorder", produces = "application/json")
	public @ResponseBody AjaxResponse<String> removeOrder(@RequestBody UserSelect user) {
		int result = 0;
		try {
			Order order = ordersDAO.findOpenedOrder(user.getUserId());
			result = ordersDAO.delete(order);
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(result == 0 ? "Error" : "OK", "[]");
	}

	@PostMapping(value = "/process/userselect", produces = "application/json")
	public @ResponseBody AjaxResponse<List<BasketItem>> userSelect(@RequestBody UserSelect user) {
		List<BasketItem> basketItems = new ArrayList<>();
		try {
			Order order = ordersDAO.findOpenedOrder(user.getUserId());
			basketItems = basketDAO.findAsOrderId(order.getOrderId());
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(basketItems.isEmpty() ? "Empty" : "OK", basketItems);
	}

	@PostMapping(value = "/process/yearselect", produces = "application/json")
	public @ResponseBody AjaxResponse<List<Month>> yearSelect(@RequestBody Year year) {
		List<Month> months = new ArrayList<>();
		try {
			months = ordersDAO.findMonthsContainOrders(year);
		} catch (DAOException e) {
		}
		return new AjaxResponse<>(months.isEmpty() ? "Empty" : "OK", months);
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
			List<Category> categories;
			categories = categoriesDAO.findAll();
			JSONArray categoriesArray = new JSONArray(categories);
			model.addAttribute("categories", categoriesArray.toString());
			List<Food> foods;
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

	@RequestMapping("/workerconsumptionreport")
	public String workerConsumptionReport(ModelMap model) {
		try {
			List<User> users;
			users = usersDAO.findAll();
			JSONArray usersArray = new JSONArray(users);
			model.addAttribute("users", usersArray.toString());
			List<Year> years;
			years = ordersDAO.findYearsContainOrders();
			JSONArray yearsArray = new JSONArray(years);
			model.addAttribute("years", yearsArray.toString());
		} catch (DAOException e) {
		}
		return "workerConsumptionReport";
	}

}