package com.spacey.insta.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Followers {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="from_user_fk")
    private Userr from;

    @ManyToOne
    @JoinColumn(name="to_user_fk")
    private Userr to;

    public Followers() {};

    public Followers(Userr from, Userr to) {
        this.from = from;
        this.to = to;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Userr getFrom() {
		return from;
	}

	public void setFrom(Userr from) {
		this.from = from;
	}

	public Userr getTo() {
		return to;
	}

	public void setTo(Userr to) {
		this.to = to;
	}
    
    
}