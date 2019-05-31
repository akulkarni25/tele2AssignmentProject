package com.tele2.assignment.subscriptionapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.tele2.assignment.subscriptionapi.domain.Subscription;

/**
 *
 * @author Apoorva
 *
 */

@Service("subscriptionApiService")
public class SubscriptionApiService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Subscription> subscriptions;

	static {
		subscriptions = populateDummySubscription();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Subscription findById(long id) {
		for (Subscription subscription : subscriptions) {
			if (subscription.getId() == id) {
				return subscription;
			}
		}
		return null;
	}

	public List<Subscription> findAllSubscriptions() {
		//fetch all subscriptions 
		return subscriptions;
	}

	public Subscription findByName(String name) {
		for (Subscription subscription : subscriptions) {
			if (subscription.getName().equalsIgnoreCase(name)) {
				return subscription;
			}
		}
		return null;
	}

	public long saveSubscription(Subscription subscription) {
		long id = counter.incrementAndGet();
		subscription.setId(id);
		subscriptions.add(subscription);
		return id;
	}

	public void updateSubscription(Subscription subscription) {
		subscription.setLastUpdate();
		int index = subscriptions.indexOf(subscription);
		subscriptions.set(index, subscription);
	}



	public boolean isSubscriptionExist(Subscription subscription) {
		return findById(subscription.getId()) != null;
	}



	private static List<Subscription> populateDummySubscription() {
		List<Subscription> subscriptions = new ArrayList<Subscription>();
		subscriptions.add(new Subscription(counter.incrementAndGet(), "International Voice Subscription",new BigDecimal("200.00")));
		subscriptions.add(new Subscription(counter.incrementAndGet(), "International Voice Subscription",new BigDecimal("100.00")));
		subscriptions.add(new Subscription(counter.incrementAndGet(), "Data Bundle Subscription",new BigDecimal("10.00")));
		subscriptions.add(new Subscription(counter.incrementAndGet(), "Voice & Data Bundle Subscription",new BigDecimal("20.00")));
	
		return subscriptions;
	}

}
