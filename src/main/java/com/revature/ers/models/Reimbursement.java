package com.revature.ers.models;

import java.util.Objects;

public class Reimbursement {
    private int id;
    private Status status;
    private User author;
    private User resolver;
    private double amount;

    public Reimbursement() {
    }

    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() { return resolver; }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
            return "Reimbursement{" +
                    "id=" + id +
                    ", status=" + status +
                    ", author=" + author.getUsername() +
                    ", resolver=" + resolver +
                    ", amount=" + amount +
                    '}';
        } else {
            return "Reimbursement{" +
                    "id=" + id +
                    ", status=" + status +
                    ", author=" + author.getUsername() +
                    ", resolver=" + resolver.getUsername() +
                    ", amount=" + amount +
                    '}';
        }
    }
}
