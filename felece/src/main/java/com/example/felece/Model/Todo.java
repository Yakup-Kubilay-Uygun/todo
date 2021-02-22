package com.example.felece.Model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "todo", schema = "public")
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "todo")
    private String todo;
    @Column(name = "status")
    private String status;
    @ManyToMany
    //private Set<Person> persons;
    private List<Person> persons;
/*	
    @OneToMany(mappedBy = "todo")
    Set<PersonTodo> personTodos;
*/
    public void setId(Integer _id){this.id = _id;}
    public Integer getId(){return id;}
    public void setTodo(String _todo){this.todo = _todo;}
    public String getTodo(){return todo;}
    //public void setPersons(Person _person){this.persons= Stream.of(_person).collect(Collectors.toSet());}
    //public Set<Person> getPersons(){return persons;}
    public void setPersons(Person _person){this.persons= Stream.of(_person).collect(Collectors.toList());}
    public List<Person> getPersons(){return persons;}
    public void setStatus(String _status){status = _status;}
    public String getStatus(){return status;}
    

}
