package com.unicloud.model;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by dzkan on 2016/3/8.
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    private String number;
    private String name;
    private Collection<BlogEntity> blogs;


    @Id
    @Column(name = "number", nullable = false, length = 45)
    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }


    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result =  31  + (number != null ? number.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @Where(clause = "deleted=0")
    public Collection<BlogEntity> getblogs() {
        return blogs;
    }

    public void setblogs(Collection<BlogEntity> blogs) {
        this.blogs = blogs;
    }
}
