package com.flyhigh;

public class TicketInfo {
	private int ticketId;
	private String passengername;
	private String email;
	private int noe;
	
	private int flightId;
	private String airlinename;
	private String sourceairport;
	private String sourcecountry;
	private String destairport;
	private String destcountry;
	private String aircraft;
	
	private Double cost;
	private String size;
	private String seat;
	private int hours;
	private int minutes;
	private String date;
	private String sourceairportid;
	private String destairportid;
	
	
	
	@Override
	public String toString() {
		return "TicketInfo [ticketId=" + ticketId + ", passengername=" + passengername + ", email=" + email + ", noe="
				+ noe + ", flightId=" + flightId + ", airlinename=" + airlinename + ", sourceairport=" + sourceairport
				+ ", sourcecountry=" + sourcecountry + ", destairport=" + destairport + ", destcountry=" + destcountry
				+ ", aircraft=" + aircraft + ", cost=" + cost + ", size=" + size + ", seat=" + seat + ", hours=" + hours
				+ ", minutes=" + minutes + ", date=" + date + ", sourceairportid=" + sourceairportid
				+ ", destairportid=" + destairportid + "]";
	}

	public TicketInfo(){
		
	}

	public TicketInfo(int ticketId, String passengername, String email, int noe, int flightId, String airlinename,
			String sourceairport, String sourcecountry, String destairport, String destcountry, String aircraft,
			Double cost, String size, String seat, int hours, int minutes, String date, String sourceairportid,
			String destairportid) {
		super();
		this.ticketId = ticketId;
		this.passengername = passengername;
		this.email = email;
		this.noe = noe;
		this.flightId = flightId;
		this.airlinename = airlinename;
		this.sourceairport = sourceairport;
		this.sourcecountry = sourcecountry;
		this.destairport = destairport;
		this.destcountry = destcountry;
		this.aircraft = aircraft;
		this.cost = cost;
		this.size = size;
		this.seat = seat;
		this.hours = hours;
		this.minutes = minutes;
		this.date = date;
		this.sourceairportid = sourceairportid;
		this.destairportid = destairportid;
	}


	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getPassengername() {
		return passengername;
	}
	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNoe() {
		return noe;
	}
	public void setNoe(int noe) {
		this.noe = noe;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getAirlinename() {
		return airlinename;
	}
	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}
	public String getSourceairport() {
		return sourceairport;
	}
	public void setSourceairport(String sourceairport) {
		this.sourceairport = sourceairport;
	}
	public String getSourcecountry() {
		return sourcecountry;
	}
	public void setSourcecountry(String sourcecountry) {
		this.sourcecountry = sourcecountry;
	}
	public String getDestairport() {
		return destairport;
	}
	public void setDestairport(String destairport) {
		this.destairport = destairport;
	}
	public String getDestcountry() {
		return destcountry;
	}
	public void setDestcountry(String destcountry) {
		this.destcountry = destcountry;
	}
	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSourceairportid() {
		return sourceairportid;
	}
	public void setSourceairportid(String sourceairportid) {
		this.sourceairportid = sourceairportid;
	}
	public String getDestairportid() {
		return destairportid;
	}
	public void setDestairportid(String destairportid) {
		this.destairportid = destairportid;
	}	
}
