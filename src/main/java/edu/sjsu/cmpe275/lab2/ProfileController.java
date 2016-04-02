package edu.sjsu.cmpe275.lab2;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ProfileController {

	@Autowired
	private ProfileService personSvc;

	/**
	 * Requests to http://localhost:8080/hello will be mapped here. Everytime
	 * invoked, we pass list of all persons to view
	 */
	@RequestMapping(value = "/{someID}", method = RequestMethod.GET)
	public String getData(@PathVariable(value = "someID") String id,
			@RequestParam(value = "brief", required = false) String brief, Model model) {
		System.out.println("the path url id is " + id);
		Profile p = personSvc.getSpecificUser(id);
		model.addAttribute("id", id);

		if (p == null) {
			return "error";
		}

		model.addAttribute("fname", p.getFname());
		model.addAttribute("lname", p.getLname());
		model.addAttribute("email", p.getEmail());
		model.addAttribute("address", p.getAddress());
		model.addAttribute("org", p.getOrg());
		model.addAttribute("amy", p.getAbout());
		System.out.println("brief is" + brief);
		if (brief == null)
			return "GetData";
		else
			return "GetPlainData";
	}
	
	private int getNextNonExistingNumber() {
		Random rn = new Random();
		rn.setSeed(System.currentTimeMillis());
		while (true) {
			int rand_id = rn.nextInt(Integer.SIZE - 1) % 10000;
			if (!personSvc.exists(rand_id)) {
				return rand_id;
			}
		}
	}

	/**
	 * POST requests to http://localhost:8080/hello/addPerson goes here. The new
	 * person data is passed from HTML from and bound into the Person object.
	 */
	@RequestMapping(value = "/addProfile", method = RequestMethod.POST)
	public String addProfile(HttpServletRequest request) {
		int id = this.getNextNonExistingNumber();
		personSvc.add(
			setParams(
				request, 
				id 		/* generate some random id */ 
			)
		);
		return "redirect:/" + id;
	}

	private Profile setParams(HttpServletRequest request, int id) {
		Profile profile = new Profile();
		profile.setId(id);
		profile.setFname(request.getParameter("fname"));
		profile.setLname(request.getParameter("lname"));
		profile.setEmail(request.getParameter("email"));
		profile.setAddress(request.getParameter("address"));
		profile.setOrg(request.getParameter("org"));
		profile.setAbout(request.getParameter("amy"));
		return profile;
	}
	
	@RequestMapping(value = "/updateProfile1", method = RequestMethod.POST)
	public String updateProfile1(HttpServletRequest request) {
		System.out.println(request.getAttribute("regist"));
		/*
		 * if(request.getAttribute("regist") == "Update") { //do the update
		 * stuff here } else { //invoke the delete operation from here }
		 */

		// Updating the profile object here
		personSvc.update1(setParams(request, Integer.parseInt(request.getParameter("id"))));
		return "redirect:/" + request.getParameter("id");
	}

//	@RequestMapping(value = "/{someID}", method = RequestMethod.POST)
//	public String updateProfile(HttpServletRequest request) {
//		System.out.println(request.getAttribute("regist"));
//		/*
//		 * if(request.getAttribute("regist") == "Update") { //do the update
//		 * stuff here } else { //invoke the delete operation from here }
//		 */
//
//		// Updating the profile object here
//		personSvc.update(setParams(request), request.getParameter("id"));
//		return "redirect:/" + request.getParameter("id");
//	}

	@RequestMapping(value = "/{someID}", method = RequestMethod.DELETE)
	public String deleteProfile(@PathVariable(value = "someID") String id, Model model) {
		System.out.println("ProfileController::deleteProfile: " + id);
		int idInt  = 0;
		try {
			idInt = Integer.parseInt(id);
		} catch (Exception e) {
			System.out.println("exception is:" + e.getMessage());
		}
		if (personSvc.exists(idInt)) {
			System.out.println("row exist for " + id);
			personSvc.delete(idInt);
			return "home";
		} else {
			System.out.println("row doesn't exist for " + id);
			return "error";
		}
//		System.out.println(rowsAffected + "rows has been deleted");
//		//it will return error page in case no rows are affected, means the id could not be found in the database
//		if(rowsAffected == 1) {
//			System.out.println("redirecting to home....");
//			return "home";
//		} else {
//			return "error";
//		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
}