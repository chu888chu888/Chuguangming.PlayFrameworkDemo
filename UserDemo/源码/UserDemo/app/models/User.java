package models;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Page;

import play.data.validation.Constraints.Required;
import play.db.ebean.*;

@Entity
public class User extends Model {
   @Id 
  public String email;
  @Required
  public String username;
  @Required
  public String password;
  
  public static Finder<Long,User> find = new Finder(
		    Long.class, User.class
		  );
  
  public static List<User> all() {
	  
	  return find.all();
	}
  

	public static void create(User usr) {
	  usr.save();
	}

	public static void delete(Long id) {
	  find.ref(id).delete();
	}
  
	public static Page<User> page(int page, int pageSize) {
        return 
            find.where()
                .findPagingList(pageSize)
                .getPage(page);
    }
}