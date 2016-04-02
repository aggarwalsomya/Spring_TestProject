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
	 * get data request for some id using the HTTP Get will be mapped here
	 * @return It will return the required view
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

	
	/**
	 * It will generate the Random Id, if the id exists, it will generate a new one.
	 * @return the unique id
	 */
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
	 * Request for add the profile using http post will be mapped here.
	 * @param request
	 * @return view
	 */
	@RequestMapping(value = "/addProfile", method = RequestMethod.POST)
	public String addProfile(HttpServletRequest request) {
		int id = this.getNextNonExistingNumber();
		personSvc.add(setParams(request, id /* generate some random id */
		));
		return "redirect:/" + id;
	}

	
	/**
	 * It will set the params in the profile object from the servlet request object
	 * @param request
	 * @param id
	 * @return the profile object will all the parameters
	 */
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

	/**
	 * It is used to update the profile of the user
	 * @param request
	 * @return the view
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(HttpServletRequest request) {
		personSvc.update(setParams(request, Integer.parseInt(request.getParameter("id"))));
		return "redirect:/" + request.getParameter("id");
	}
	
	/**
	 *  It will update the data for the user with the specific id and details in the request params
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param address
	 * @param organization
	 * @param about_myself
	 * @param model
	 * @return view
	 */

	@RequestMapping(value = "/{someID}", method = RequestMethod.POST)
	public String updateData_Params(@PathVariable(value = "someID") String id,
			@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "organization", required = false) String organization,
			@RequestParam(value = "about_myself", required = false) String about_myself, Model model) {
		
		Profile p = new Profile();
		p.setId(Integer.parseInt(id));
		p.setFname(firstname);
		p.setLname(lastname);
		p.setEmail(email);
		p.setAddress(address);
		p.setOrg(organization);
		p.setAbout(about_myself);

		personSvc.update(p);
		return "redirect:/" + id;
	}
	
	/**
	 * It will delete the profile of the user with a userId in the url
	 * @param id
	 * @param model
	 * @return view
	 */

	@RequestMapping(value = "/{someID}", method = RequestMethod.DELETE)
	public String deleteProfile(@PathVariable(value = "someID") String id, Model model) {
		System.out.println("ProfileController::deleteProfile: " + id);
		int idInt = 0;
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
			model.addAttribute("id",id);
			return "error";
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	
	/**
	 * returns the home page
	 * @return
	 */
	public String home() {
		return "home";
	}
}