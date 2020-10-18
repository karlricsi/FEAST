package hu.karlricsi;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import hu.karlricsi.dao.DAO;
import hu.karlricsi.dao.DAOException;
import hu.karlricsi.entities.*;

@Controller
public class FeastController {

	@Autowired
	DAO<User> usersDAO;

	@RequestMapping("/menu")
	public String test(ModelMap model) {
		try {
			List<User> users;
			users = usersDAO.findAll();
			JSONArray usersArray=new JSONArray(users);
			model.addAttribute("users",usersArray.toString());

		} catch (DAOException e) {
			return "errorPage";
		}

		return "menu";
	}

}
