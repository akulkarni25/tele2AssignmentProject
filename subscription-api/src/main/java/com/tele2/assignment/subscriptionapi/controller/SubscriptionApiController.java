package com.tele2.assignment.subscriptionapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tele2.assignment.subscriptionapi.domain.Subscription;
import com.tele2.assignment.subscriptionapi.exception.*;
import com.tele2.assignment.subscriptionapi.service.SubscriptionApiService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionApiController {
	public static final Logger logger = LoggerFactory.getLogger(SubscriptionApiController.class);

	@Autowired
	private SubscriptionApiService subscriptionApiService;
	// Chnage annotation to GetMapping (specific http operation) - done
	// add basic auth using spring security basic auth - implement filter - not done
	// externalise - use cache
	// create separate packages - done
	// validation on request use json schema - done
	// javax validation framework - done

	@GetMapping("/{id}")
	// path variable with parameter
	public ResponseEntity<Subscription> getSubscription(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {

		logger.info("Fetching Subscription with id {}", id);
		Subscription subscription = subscriptionApiService.findById(id);
		if (subscription == null) {
			logger.error("Subscription with id {} not found.", id);
			throw new ResourceNotFoundException("Subscription not found for this id :: " + id);
		}
		return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllSubscriptions() throws ResourceNotFoundException {

		logger.info("Fetching all Subscriptions");
		List<Subscription> subscriptions = subscriptionApiService.findAllSubscriptions();
		if (subscriptions.isEmpty()) {
			throw new ResourceNotFoundException("No subscriptions found");
		}
		return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSubscription(@PathVariable(value = "id") long id,
			@Valid @RequestBody Subscription subscription) throws ResourceNotFoundException {
		logger.info("Updating Subscription with id {}", id);

		Subscription currentSubscription = subscriptionApiService.findById(id);

		if (currentSubscription == null) {
			logger.error("Unable to update. Subscription with id {} not found.", id);
			throw new ResourceNotFoundException("Subscription not found for this id :: " + id);
		}

		currentSubscription.setName(subscription.getName());
		currentSubscription.setMonthlyPrice(subscription.getMonthlyPrice());
		if (currentSubscription.getLastUpdate() == null) {
			currentSubscription.setLastUpdate();
		} else {
			currentSubscription.setLastUpdate(subscription.getLastUpdate());
		}
		subscriptionApiService.updateSubscription(currentSubscription);
		return new ResponseEntity<Subscription>(currentSubscription, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription) {
		logger.info("Creating Subscription : {}", subscription);
			
		if (subscription.getLastUpdate() == null) {
			subscription.setLastUpdate();
		}
		long currentId = subscriptionApiService.saveSubscription(subscription);
		Subscription currentSubscription = subscriptionApiService.findById(currentId);
		// HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Subscription>(currentSubscription, HttpStatus.CREATED);

	}

}
