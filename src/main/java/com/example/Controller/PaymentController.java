package com.example.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Entities.Users;
import com.example.Repositories.usersRepository;
import com.example.Service.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PaymentController {
	
	@Autowired
	UsersService userserv;
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_o7GeQPJXcngHjw", "uJxgzaak3d0EcvvruDhObUDU");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",50000);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);

			order = razorpay.orders.create(orderRequest);
		} catch (Exception e) {
			System.out.println("Exception while create Order");
		}
		return order.toString();
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean postMethodName(@RequestParam String orderId,String paymentId,String signature) {
		 try {
		        // Initialize Razorpay client with your API key and secret
		        @SuppressWarnings("unused")
				RazorpayClient razorpayClient = new RazorpayClient("rzp_test_o7GeQPJXcngHjw", "uJxgzaak3d0EcvvruDhObUDU");
		        // Create a signature verification data string
		        String verificationData = orderId + "|" + paymentId;

		        // Use Razorpay's utility function to verify the signature
		        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "uJxgzaak3d0EcvvruDhObUDU");

		        return isValidSignature;
		    } catch (RazorpayException e) {
		        e.printStackTrace();
		        return false;
		    }
	}
	
	@GetMapping("Payment-success")
	public String paymentSuccess(HttpSession session) {
		String email = (String)session.getAttribute("email");
		Users user = userserv.getUser(email);
		user.setPremium(true);
		userserv.updateUser(user);
		System.out.println(user.isPremium());
		System.out.println("payment successfull");
		return "customerHome";
	}
	
	@GetMapping("Payment-failure")
	public String paymentFailure() {
			System.out.println("payment failing");
		return "customerHome";
	}
	
	
	
}
