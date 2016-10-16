package com.practo.sai.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.model.Category;
import com.practo.sai.jobportal.model.CloseJob;
import com.practo.sai.jobportal.model.Job;
import com.practo.sai.jobportal.model.JobApplication;
import com.practo.sai.jobportal.model.Response;
import com.practo.sai.jobportal.service.AdminService;

@Controller
public class DataController {

	@Autowired
	AdminService adminService;

	@RequestMapping("/jobs")
	@ResponseBody
	public Page<Job> getJobs(Pageable pageable) {
		Page<Job> jobs = null;
		jobs = adminService.getJobs(pageable);
		return jobs;
	}

	@RequestMapping("/job/{jobId}")
	@ResponseBody
	public Page<JobApplication> getJobApplications(@PathVariable int jobId, Pageable pageable) {
		Page<JobApplication> jobApplications = null;
		jobApplications = adminService.getJobApplications(jobId, pageable);
		return jobApplications;

	}

	@RequestMapping("/categories")
	@ResponseBody
	public List<Category> getCategories() {
		List<Category> categories = null;
		categories = adminService.getCategories();
		return categories;
	}

	// @RequestMapping("/employee")
	// @ResponseBody
	// public Employee getEmployee(){
	//
	// }

	@RequestMapping(value = "/addJob", method = RequestMethod.POST)
	public Response addJob(@RequestBody Job job) {
		try {
			adminService.addJob(job);
			return sendResponse(Constants.ADD_JOB_SUCCESS, Constants.ADD_JOB_SUCCESS_MESSAGE);
		} catch (Exception e) {

		}
		return sendResponse(Constants.SERVER_ERROR, null);
	}

	@RequestMapping(value = "/addJobApplication", method = RequestMethod.POST)
	@ResponseBody
	public Response addJobApplication(@RequestBody JobApplication jobApplication) {
		try {
			adminService.addJobApplication(jobApplication);
			return sendResponse(Constants.ADD_JOB_APP_SUCCESS, Constants.ADD_JOB_APP_SUCCESS_MESSAGE);
		} catch (Exception e) {

		}
		return sendResponse(Constants.SERVER_ERROR, null);
	}

	@RequestMapping(value = "/closeJob", method = RequestMethod.POST)
	@ResponseBody
	public Response closeJob(@RequestBody CloseJob closeJob) {
		try {
			adminService.closeJob(closeJob);
			return sendResponse(Constants.CLOSE_JOB_SUCCESS, Constants.CLOSE_JOB_SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendResponse(Constants.SERVER_ERROR, null);
	}

	private Response sendResponse(int code, String message) {
		Response response = new Response();
		response.setResponseCode(code);
		response.setMessage(message);
		return response;

	}

}
