package project1.com.messenger.controller;

//import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import project1.com.messenger.entities.User;
import project1.com.messenger.service.MessengerService;

@Controller
public class HomeController {

	@Autowired
	private MessengerService messengerService;
	

	@RequestMapping(value = {"/", "/login"}, method= RequestMethod.GET)
	public String directLogin(Model model) {
		model.addAttribute("user", new User());
		return "Login";
	}
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String directRegister(Model model) {
		model.addAttribute("user", new User());
		return "Register";
	}
	@RequestMapping(value = {"/processLogin"}, method= RequestMethod.POST)
	public String login(@ModelAttribute("User") User user, Model model,HttpSession session) {
		if(session.getAttribute("isLogin") != null && session.getAttribute("isLogin").equals(false)) {
			session.invalidate();
			return "redirect:/login";
		}
		User tuser = messengerService.login(user);
		if( tuser == null)
			return "redirect:/login";
		else {
			session.setAttribute("isLogin", true);
			session.setAttribute("id", tuser.getId());
			model.addAttribute("user", tuser);
			return "Profile";
		}
		
		
	}
	@RequestMapping("/processRegister")
	public String register(@ModelAttribute("User") User user, Model model) {
		User tuser = messengerService.register(user);
		if( tuser == null)
			return "Error";
		else {
			return "redirect:/login";
		}
	}
	@RequestMapping("/messenger")
	public String directMessenger(@ModelAttribute("User") User user, Model model1, Model model2, HttpSession session) {
		if(session.getAttribute("isLogin") != null && session.getAttribute("isLogin").equals(true)) {
			User tuser = messengerService.findById(user.getId());
			model2.addAttribute("listFriend", messengerService.findFriendOfUserId(user.getId()));
			model1.addAttribute("user", tuser);
			return "Messenger";
		}
		else{
				session.setAttribute("isLogin", false);
				return "redirect:/login";
			}		
	}
	
	@RequestMapping("/logout")
	public String directLogout(HttpSession session,Model model) {
		session.invalidate();
		model.addAttribute("User", new User());
		return "redirect:/login";
	}
	
	@RequestMapping("/update")
	public String directUpdate(@ModelAttribute("User") User user, Model model) {
		User tuser = messengerService.findById(user.getId());
		model.addAttribute("user", tuser);
		return "Update";
	}
	
	@RequestMapping("/profile")
	public String directProfile(@RequestParam("id") int id, Model model, HttpSession session) {
		User user = messengerService.findById(id);
		model.addAttribute("user", user);
		return "Profile";
	}
	
	@RequestMapping("/processUpdate")
	public String update(@ModelAttribute("User") User user, Model model) {
		/*messengerService.update(user);
	
		try {
	        MultipartFile multipartFile = user.getMultipartFile();
	        String fileName = multipartFile.getOriginalFilename();
	        user.setAvatar(fileName);
	        File file = new File("C:\\Users\\hongt\\Documents\\JAVA_WEB_PROJECT\\messenger\\src\\main\\webapp\\resources\\bootstrap\\images", fileName);
	        multipartFile.transferTo(file);
	    } catch (Exception e) {
	        return "Error";
	      }
		
		user = userService.findById(user.getId());
		model.addAttribute("user", user);*/
		return "Profile";	
		}	
	@RequestMapping("/search")
	public String search(@RequestParam("email") String email, Model model) {
		User user = messengerService.findByEmail(email);
		model.addAttribute("user", user);
		return "Messenger";
	}
	
	@RequestMapping("/addFriend")
	public String addFriend(@RequestParam("userId") int userId, @RequestParam("friendId") int friendId, Model model) {
		messengerService.addFriend(userId, friendId);
		User user = messengerService.findById(userId);
		model.addAttribute("user", user);
		return "Messenger";
	}
}


