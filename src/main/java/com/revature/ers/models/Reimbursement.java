package com.revature.ers.models;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Reimbursement {
    private int id;
    private double amount;
    private Date submitted;
    private Date resolved;
    private User author;
    private User resolver;
    private Status status;
    private Type type;

    public Reimbursement() {
    }

    public Reimbursement(User author, double amount ,String type) {
        this.amount = amount;
        this.author = author;
        setType(type);
        this.status = Status.PENDING;
    }

    public Reimbursement(int id, double amount, Date submitted, Date resolved, User author, User resolver, Status status, Type type) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public void setStatus(String status){
        if(status.equals("Pending")){
            this.status = Status.PENDING;
        } else if (status.equals("Approved")){
            this.status = Status.APPROVED;
        } else  if (status.equals("Denied")){
            this.status = Status.DENIED;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setType(String type){
        if(type.equals("Food")){
            this.type = Type.FOOD;
        } else if (type.equals("Lodging")){
            this.type = Type.LODGING;
        } else if (type.equals("Travel")) {
            this.type = Type.TRAVEL;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && status == that.status && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, author, resolver, amount);
    }

    @Override
    public String toString() {
        if(resolver == null) {
            return "{" +
                    "id:" + id +
                    ", date submitted:" + submitted +
                    ", date resolved:" + resolved +
                    ", author:" + author.getUsername() +
                    ", resolver:" + resolver +
                    ", amount:" + amount +
                    ", status:" + status +
                    ", type:" + type +
                    '}';
        } else {
            return "Reimbursement{" +
                    "id=" + id +
                    ", date submitted=" + submitted +
                    ", date resolved=" + resolved +
                    ", author=" + author.getUsername() +
                    ", resolver=" + resolver.getUsername() +
                    ", amount=" + amount +
                    ", status=" + status +
                    ", type=" + type +
                    '}';
        }
    }
}
