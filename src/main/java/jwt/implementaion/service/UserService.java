package jwt.implementaion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import jwt.implementaion.model.User;

@Service
public class UserService {

	
	List<User> store = new ArrayList<>();
	public UserService() {
		store.add(new User(UUID.randomUUID().toString(),"amol","amol.@mail.com"));
		store.add(new User(UUID.randomUUID().toString(),"radhika","radhika.@mail.com"));
	}
	
	public List<User> getUser()
	{
		return store;
	}
}
