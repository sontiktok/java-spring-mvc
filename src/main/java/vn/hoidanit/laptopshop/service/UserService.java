package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello() {
        return "Hello from to service";
    }

    //get all user -> Hàm có sẵng 
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    //get user by Email -> tự định nghĩa
    public List<User> getAllUsersByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    //Hàm xử lí lưu User
    public User handleSaveUser(User user) {
        //gọi đến thằng repository để tiến hành lưu dưới csdl
        //Kết quả trả về là user đã được lưu
        User theson = this.userRepository.save(user);
        System.out.println(theson);
        return theson;
    }

    public User getUserbyId(long id){
        return this.userRepository.findById(id);
    }
    public void deleteOneUser(long id){
        this.userRepository.deleteById(id);
    }
}
