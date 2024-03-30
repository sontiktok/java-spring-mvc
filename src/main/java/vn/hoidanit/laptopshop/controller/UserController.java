package vn.hoidanit.laptopshop.controller;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

    //
    private final UserService userService;
    private final UserRepository userRepository;

    //DI
    public UserController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String getHomepage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("nguyentheson042@gmail.com");
        System.out.println("Array: "+ arrUsers);
        model.addAttribute("theson", "test");
        return "hello";
    }

    //View
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        //Gọi service để lấy data
        List<User> users = this.userService.getAllUsers();
        //Truyền data qua view
        model.addAttribute("usersView", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserbyId(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/show";
    }
    //View
    @RequestMapping("/admin/user/create")//Get
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    //Handle
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User thesonit) {
        this.userService.handleSaveUser(thesonit);
        return "redirect:/admin/user";
    }

     //Xử lí và trả về view
     @RequestMapping("/admin/user/update/{id}")//Get
     public String getUpdateUserPage(Model model,@PathVariable long id) {
        User currentUser = this.userService.getUserbyId(id);
         model.addAttribute("newUser",currentUser );
         return "admin/user/update";
     }

     @PostMapping("/admin/user/update")
     //Lấy User từ View về controller
     public String postUpdateUser(Model model, @ModelAttribute("newUser") User newUser) {
        // 
        User currentUser = this.userService.getUserbyId(newUser.getId());
        if(currentUser != null){
            currentUser.setAddress(newUser.getAddress());
            currentUser.setFullName(newUser.getFullName());
            currentUser.setPhone(newUser.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
     }
     @GetMapping("/admin/user/delete/{id}")
     public String getDeleteUserPage(Model model,@PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
     }

     @PostMapping("/admin/user/delete")
     public String postDeleteUser(Model model, @ModelAttribute("newUser") User theson) {

        this.userService.deleteOneUser(theson.getId());
        return "redirect:/admin/user";
        
     }
     
}
