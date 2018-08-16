package net.productiveprogrammer.dao;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="request_logs")
public class RequestLogDao {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "execution_millis")
    private long executionMillis;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date_time")
    @CreatedDate
    private Date createdDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date_time")
    @LastModifiedDate
    private Date updatedDateTime;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ArgumentDao> arguments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getExecutionMillis() {
        return executionMillis;
    }

    public void setExecutionMillis(long executionMillis) {
        this.executionMillis = executionMillis;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public List<ArgumentDao> getArguments() {
        return arguments;
    }

    public void setArguments(List<ArgumentDao> arguments) {
        this.arguments = arguments;
    }
}
