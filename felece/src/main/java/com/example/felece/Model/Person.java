package com.example.felece.Model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
@Table(name = "person", schema = "public")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;


    @ManyToMany
    @JoinTable(
        name = "todo_persons", 
        joinColumns = @JoinColumn(name = "persons_id" ,referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "todo_id",referencedColumnName = "id"))
    private List<Todo> todos;
    //private Set<Todo> todos;
    @ManyToOne(optional = false)
    @JoinColumn(name="roleid",referencedColumnName="id")
    private Role role;

 //   @OneToMany(mappedBy = "person")
 //   Set<PersonTodo> personTodos;

    public Person(){}
    public void setId(Integer _id){this.id = _id;}
    public Integer getId(){return id;}
    public void setEmail(String _email){this.email = _email;}
    public String getEmail(){return email;}
    public void setUsername(String _username){this.username = _username;}
    public String getUsername(){return username;}
    public void setPassword(String _password){this.password = _password;}
    public String getPassword(){return password;}
    public void setRole(Role _role){this.role = _role;}
    public Role getRole(){return role;}
    //public void setTodo(Todo _todo){this.todos = Stream.of(_todo).collect(Collectors.toSet());}
   // public Set<Todo> getTodo(){return todos;}
    public void setTodo(Todo _todo){this.todos = Stream.of(_todo).collect(Collectors.toList());}
    public List<Todo> getTodo(){return todos;}
    
    public void removeTodo(Todo t) {
        this.todos.remove(t);
        t.getPersons().remove(this);
    }


}
