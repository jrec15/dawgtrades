package edu.uga.dawgtrades.model.impl;

import java.util.Date;

import edu.uga.dawgtrades.model.Auction;
import edu.uga.dawgtrades.model.Bid;
import edu.uga.dawgtrades.model.DTException;
import edu.uga.dawgtrades.model.Item;
import edu.uga.dawgtrades.model.RegisteredUser;

/**
 * Implementation of Bid
 * @author Justin
 *
 */

public class BidImpl extends Persistent implements Bid {
	
	private float amount;
	private Date date;
	private Auction auction;
	private RegisteredUser registeredUser;
	
	public BidImpl (float amount, Date date, Auction auction, RegisteredUser registeredUser) throws DTException {
		super(-1);
		this.amount = amount;
		this.date = date;
		this.auction = auction;
		this.registeredUser = registeredUser;
	}

	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isWinning() {
		float highBid = auction.getSellingPrice();
		return (amount == highBid);
	}
	public Auction getAuction() {
		return auction;
	}
	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}
	
	public String toString() {
		String result = "Bid[" + getId() + "]: Amount[" + getAmount() + "] Date[" + getDate() + "] This bid is ";
		if (!isWinning())
			result += " not";
		result +="winning. \n     " + getAuction().toString() + "\n      " + getRegisteredUser().toString();
		return result;
	}
	
	public boolean equals(Object bid) {
		if (bid == null)
			return false;
		if (bid instanceof Bid) {
			boolean result = getAuction().equals((((Bid)bid).getAuction()));
			if (result == true)
				result = getRegisteredUser().equals((((Bid)bid).getRegisteredUser()));
			return result;
		}
		return false;		
	}
}
