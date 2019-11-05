package project1.com.messenger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
	public String login(@ModelAttribute("User") User user, 
						Model model,
						HttpSession session) {
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
	public String register(@ModelAttribute("User") User user, 
						   Model model) {
		User tuser = messengerService.register(user);
		if( tuser == null)
			return "Error";
		else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/chat")
	public String directMessenger(@RequestParam(value = "userId", required = false) Integer userId, 
								  @RequestParam(value = "findFriend", required = false) String email, 
								  @RequestParam(value = "chatId", required = false) Integer chatId,
								  Model model, 
								  HttpSession session) {
		if (userId == null)
			return "redirect:/login";
		if(session.getAttribute("isLogin") != null && session.getAttribute("isLogin").equals(true)) {
			model.addAttribute("listFriend", messengerService.findFriendOfUserId(userId));
			model.addAttribute("findFriend", messengerService.findFriendByEmail(email));
			if(chatId == null) {
				chatId = messengerService.findNewestConversation(userId);
			}
			model.addAttribute("conversation", messengerService.findConversation(chatId));
			model.addAttribute("chatsentence", messengerService.findListChatSentence(chatId));
			model.addAttribute("user", messengerService.findById(userId));
			return "Messenger";
		} else {
			session.invalidate();
			return "redirect:/login";
		 }		
	}
	
	@RequestMapping("/logout")
	public String directLogout(HttpSession session,
							   Model model) {
		session.invalidate();
		model.addAttribute("User", new User());
		return "redirect:/login";
	}
	
	@RequestMapping("/update")
	public String directUpdate(@ModelAttribute("User") User user, 
							   Model model) {
		User tuser = messengerService.findById(user.getId());
		model.addAttribute("user", tuser);
		return "Update";
	}
	
	@RequestMapping("/profile")
	public String directProfile(@RequestParam("id") int id, 
								Model model, 
								HttpSession session) {
		User user = messengerService.findById(id);
		model.addAttribute("user", user);
		return "Profile";
	}
	
	@RequestMapping("/processUpdate")
	public String update(@ModelAttribute("User") User user, 
						 Model model) {
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
	public RedirectView search(@RequestParam("email") String email, 
							   @RequestParam("userId") int userId, 
							   RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("findFriend", email);
		redirectAttributes.addAttribute("userId", userId);
		return new RedirectView("chat");
		
	}
	
	@RequestMapping("/addFriend")
	public RedirectView addFriend(@RequestParam("userId") int userId, 
								  @RequestParam("friendId") int friendId, 
								  RedirectAttributes redirectAttributes) {
		messengerService.addFriend(userId, friendId);
		redirectAttributes.addAttribute("userId", userId);
		return new RedirectView("chat");
	}
	
	@RequestMapping("/chatRoom")
	public RedirectView chatRoom(@RequestParam("friendId") int friendId, 
						   		 @RequestParam("userId") int userId,
						   		 RedirectAttributes redirectAttributes,
						   		 Model model) {
		int chatId = messengerService.getChatId(userId, friendId);
		int date = 1;
		if (chatId == 0) {
			date = messengerService.setChatRoom(userId, friendId);
			chatId = messengerService.setMemberOfChatRoom(date, userId, friendId);
		}
		redirectAttributes.addAttribute("chatId", chatId);
		redirectAttributes.addAttribute("userId", userId);
		return new RedirectView("chat");
	}
}


