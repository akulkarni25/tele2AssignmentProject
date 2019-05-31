package com.tele2.assignment.subscriptionapi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Subscription {
	
		@Id
		@GeneratedValue
		private long id;
	     
		@NotNull
	    @Size(min = 2, message = "First Name should have atleast 2 characters")
	    private String name;
	    
		@NotNull
	    private BigDecimal monthlyPrice;
	   
	    private LocalDateTime lastUpdate;
	
	    public LocalDateTime getLastUpdate() {
	    	return lastUpdate;
		}

		public void setLastUpdate() {			
			this.lastUpdate = this.currentTimeStamp();
		}
		
		private LocalDateTime currentTimeStamp()
		{
			LocalDateTime currentDateTime = LocalDateTime.now();
			return currentDateTime;
		}

		public BigDecimal getMonthlyPrice() {
			return monthlyPrice;
		}

		public void setMonthlyPrice(BigDecimal monthlyPrice) {
			this.monthlyPrice = monthlyPrice;
		}

		public Subscription(){
	        id=0;
	    }
	     
	    /**
		 * @param id
		 * @param name
		 * @param amount
		 * @param lastUpdate
		 */
		public Subscription(long id, String name, BigDecimal monthlyPrice) {
			this.id = id;
			this.name = name;
			this.monthlyPrice = monthlyPrice;
			this.lastUpdate = LocalDateTime.now();
		}
		
		public long getId() {
	        return id;
	    }
	 
	    public void setId(long id) {
	        this.id = id;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	        this.name = name;
	    }
	 
	 	 
	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + (int) (id ^ (id >>> 32));
	        return result;
	    }
	 
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Subscription other = (Subscription) obj;
	        if (id != other.id)
	            return false;
	        return true;
	    }
	 
	    @Override
	    public String toString() {
	        return "Subscription [id=" + id + ", name=" + name+"]";
	    }

		public void setLastUpdate(LocalDateTime lastUpdate) {
			this.lastUpdate = lastUpdate;
			
		}
	
	
}
