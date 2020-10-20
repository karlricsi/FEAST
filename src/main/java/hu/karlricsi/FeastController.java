package hu.karlricsi;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/")
	public String menu(ModelMap model) {
		return "menu";
	}

	@RequestMapping("/order")
	public String order(ModelMap model, HttpServletResponse response,
			@CookieValue(value = "userId", defaultValue = "0") int userId) {// , @RequestParam(value = "ppp",
																			// defaultValue = "0") String ppp) {
		response.addCookie(new Cookie("userId", "2"));
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
