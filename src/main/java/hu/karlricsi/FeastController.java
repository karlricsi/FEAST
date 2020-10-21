package hu.karlricsi;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import hu.karlricsi.dao.DAO;
import hu.karlricsi.dao.DAOException;
import hu.karlricsi.entities.*;

@Controller
public class FeastController {

	@Autowired
	private DAO<User> usersDAO;
	@Autowired
	private DAO<MenuCategory> categoriesDAO;
	@Autowired
	private DAO<MenuElement> foodsDAO;

	@RequestMapping(value = "/process/addfood", method = RequestMethod.POST)
	public ModelAndView addFood(@RequestBody String request) {
		ModelAndView model = new ModelAndView();

		System.out.println("addFood: " + request);

		return model;
	}

	@RequestMapping(value = "/process/userselect", method = RequestMethod.POST)
	public ModelAndView userSelect(@RequestBody String request) {
		ModelAndView model = new ModelAndView();

		System.out.println("userSelect: " + request);

		return model;
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
			List<BasketItem> basket = new ArrayList<>();

			JSONArray basketArray = new JSONArray(basket);
			model.addAttribute("basket", basketArray.toString());
		} catch (DAOException e) {
			return "errorPage";
		}
		return "order";
	}

}
