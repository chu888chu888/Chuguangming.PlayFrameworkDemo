package controllers;

import models.User;

import play.data.Form;
import play.mvc.*;



public class Application extends Controller {
  
	 public static Result index() {
    	 return redirect(routes.Application.users(0));
    }
  
    static Form<User> userForm = Form.form(User.class);
    static Form<User> userForm1 = Form.form(User.class);
    
    public static Result users(int page) {
    	return ok(
    		    views.html.index.render(User.page(page, 5))
    		  );
      }
    
    public static Result register(){
    	
    	return ok(
    	  views.html.register.render(userForm)
    			
    	);
    }
    
  public static Result login(){
    	
    	return ok(
    	  views.html.login.render(userForm1)
    			
    	);
    }
 
 
      
  public static Result doRegister() {
    	  Form<User> filledForm = userForm.bindFromRequest();
    	  if(filledForm.hasErrors()) {
    	    return badRequest(
    	      views.html.index.render(User.page(0, 5))
    	    );
    	  } else {
    	    User.create(filledForm.get());
    	    return redirect(routes.Application.users(0));  
    	  }
      }
  
  public static Result doLogin(){
	  Form<User> filledForm = userForm.bindFromRequest();
	  User user = filledForm.get();
	  for(User usr : User.all()){
		  
		  if(usr.username.equals(user.username)&&usr.password.equals(user.password)){
			  
			  return ok(
			    views.html.message.render("登陆成功！")  
		     );
		  }
		 
	  }
	  return ok(
			  views.html.message.render("用户名或密码错误！") 
			  );
	
  }
      public static Result deleteTask(Long id) {
    	  User.delete(id);
    	  return redirect(routes.Application.users(0));
      }
  
}
