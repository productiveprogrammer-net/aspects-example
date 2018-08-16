package net.productiveprogrammer.dao;

import javax.persistence.*;

@Entity
@Table(name = "request_log_arguments")
public class ArgumentDao {

    @Id @GeneratedValue
    @Column(name = "argument_id")
    private Long id;

    @Column(name = "parameter")
    private String parameter;

    @Column(name = "value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
