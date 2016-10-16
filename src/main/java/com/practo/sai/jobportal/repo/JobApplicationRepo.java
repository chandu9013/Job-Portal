package com.practo.sai.jobportal.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.practo.sai.jobportal.model.JobApplication;

public interface JobApplicationRepo extends PagingAndSortingRepository<JobApplication, Integer> {

}
