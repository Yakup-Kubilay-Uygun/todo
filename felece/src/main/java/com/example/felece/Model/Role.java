package com.example.felece.Model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role", schema = "public")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="role")
    private Set<Person> persons;

    public void setId(Integer _id){this.id = _id;}
    public Integer getId(){return id;}
    public void setName(String _name){this.name = _name;}
    public String getName(){return name;}

}
