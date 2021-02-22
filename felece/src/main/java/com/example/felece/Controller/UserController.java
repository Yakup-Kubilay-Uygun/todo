package com.example.felece.Controller;

import com.example.felece.Model.Role;
import com.example.felece.Model.Todo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import com.example.felece.Model.Person;
//import com.example.felece.Model.PersonTodo;
import com.example.felece.Repository.PersonRepository;
//import com.example.felece.Repository.PersonTodoRepository;
import com.example.felece.Repository.RoleRepository;
import com.example.felece.Repository.TodoRepository;
import com.example.felece.Service.UserDetailService;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    PersonRepository userRep;
    @Autowired
    RoleRepository roleRep;
    @Autowired
    TodoRepository todoRep;

    @Resource
    private UserDetailsService userDetailsService;

    String e;

    @RequestMapping("/")
    public String addUser(Principal principal, Model model) {
        String email = principal.getName();
        e = principal.getName();
        Person user = userRep.findByEmail(email);

        if (user.getRole().getId() == 1) {

            return "redirect:admin";
        } else {
            return "redirect:user";

        }

    }

    @RequestMapping("/admin")
    public String admin() {
        return "cssandjs/admin";
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam("email") String email, @RequestParam("username") String username,
            @RequestParam("password") String password, @RequestParam("role") String role) {

        Person p = new Person();
        p.setEmail(email);
        p.setUsername(username);
        p.setPassword(password);
        Role r = roleRep.findByName(role);
        p.setRole(r);
        userRep.save(p);

        return "cssandjs/admin";
    }

    @GetMapping("/list")
    public String listUser(Model model) {
        List<Person> persons = (List<Person>) userRep.findAll();

        model.addAttribute("persons", persons);

        return "cssandjs/adminlist";
    }

    @PostMapping("/list")
    public String deleteUser(@RequestParam("email") String email) {
        Person p = new Person();
        p = userRep.findByEmail(email);
        userRep.delete(p);
        return "cssandjs/admin";
    }

    @GetMapping("/update")
    public String updateUser(Model model) {
        List<Person> persons = (List<Person>) userRep.findAll();
        model.addAttribute("persons", persons);
        return "cssandjs/adminupdate";
    }

    @PostMapping("/update")
    public String updatedUser(@RequestParam("username") String _username, @RequestParam("email") String email) {
        Person p = new Person();
        p = userRep.findByEmail(email);
        p.setUsername(_username);
        userRep.save(p);

        return "cssandjs/admin";
    }

    @RequestMapping("/listUser")
    public String listUserwithTodo(Model model){
        List<Person> persons = (List<Person>) userRep.findAll();
        List<List<String>> elements = new ArrayList<List<String>>();
    
     
        for(int i = 0 ; i<persons.size();i++){
            for(int j = 0 ; j<persons.get(i).getTodo().size() ; j++){

                List<String> elem = new ArrayList<String>();
                elem.add(persons.get(i).getEmail());
                elem.add(persons.get(i).getUsername());
                elem.add(persons.get(i).getTodo().get(j).getTodo());
                elem.add(persons.get(i).getTodo().get(j).getStatus());
                elements.add(elem);
                
                      
            }
        }

        
        model.addAttribute("elements", elements);
        return "cssandjs/adminlistusertodo";
    }    
    @RequestMapping("/user")
    public String user(){
        return "cssandjs/user";
    }
    @RequestMapping("/addTodo")
    public String addTodo(@RequestParam("todo") String todo,
                            @RequestParam("status") String status){

        Todo t = new Todo();
        t.setTodo(todo);
        t.setStatus(status);
        Person p = new Person();
        p = userRep.findByEmail(e);
        t.setPersons(p);
        todoRep.save(t);

        return "cssandjs/user";
    }
    
    @GetMapping("/deleteTodo")
    public String deleteTodo(Model model){
        Person p = new Person();
        p = userRep.findByEmail(e);
        List<Todo> t = p.getTodo();
        List<Todo> todos = new ArrayList<Todo>();
    
        for(int i=0 ; i<t.size(); i++){
            Todo _todo = new Todo();        
             _todo.setId(t.get(i).getId());
             _todo.setTodo(t.get(i).getTodo());
             _todo.setStatus(t.get(i).getStatus());
             todos.add(_todo);
        }
        model.addAttribute("todos", todos);
        return "cssandjs/userdelete";
    }
    @PostMapping("/deleteTodo")
    public String deleteTodo(@RequestParam("todoId") Integer todoId){

        Todo t = new Todo();
        t = todoRep.findById(todoId);
       //System.out.println(todo);
        Person p = new Person();
        p = userRep.findByEmail(e);
        p.removeTodo(t);
        todoRep.delete(t);
        return "cssandjs/user";
    }
    @GetMapping("/updateTodo")
    public String updatetodo(Model model){
        Person p = new Person();
        p = userRep.findByEmail(e);
        List<Todo> t = p.getTodo();
        List<Todo> todos = new ArrayList<Todo>();
    
        for(int i=0 ; i<t.size(); i++){
            Todo _todo = new Todo();        
             _todo.setId(t.get(i).getId());
             _todo.setTodo(t.get(i).getTodo());
             _todo.setStatus(t.get(i).getStatus());
             todos.add(_todo);
        }
        model.addAttribute("todos", todos);
        return "cssandjs/userupdate";
    }

    @PostMapping("/updateTodo")
    public String updateTodo(@RequestParam("todo") String _todo,
                            @RequestParam("status") String _status,
                            @RequestParam("todoid") Integer _id){

        Todo t = new Todo();
        t = todoRep.findById(_id);
        t.setTodo(_todo);
        t.setStatus(_status);
        todoRep.save(t);
        return "cssandjs/user";
    }
    @GetMapping("/listTodo")
    public String listTodo(Model model){
        Person p = new Person();
        p = userRep.findByEmail(e);
        List<Todo> t = p.getTodo();
        List<Todo> todos = new ArrayList<Todo>();
    
        for(int i=0 ; i<t.size(); i++){
            Todo _todo = new Todo();        
             _todo.setId(t.get(i).getId());
             _todo.setTodo(t.get(i).getTodo());
             _todo.setStatus(t.get(i).getStatus());
             todos.add(_todo);
        }
        model.addAttribute("todos", todos);
        return "cssandjs/userlist";
    }
}
