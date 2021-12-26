package com.flyhigh;

public class DisplayOp {
		public int flightId;
		public Double costf;
		public String name;
		public int hours;
		public int mins;
		
		public DisplayOp(){
			this.flightId =0;
			this.costf =0.0;
		}
		
		public DisplayOp(int flightId, Double costf, String name,int hours, int mins){
			this.flightId = flightId;
			this.costf = costf;
			this.name = name;
			this.hours = hours;
			this.mins = mins;
		}
		
		public String toString() {
			return flightId+"\t"+costf+"\t"+name+"\t"+hours+"\t"+mins;
		}
}
