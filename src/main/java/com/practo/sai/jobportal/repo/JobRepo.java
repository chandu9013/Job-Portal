package com.practo.sai.jobportal.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.practo.sai.jobportal.model.Job;

public interface JobRepo extends PagingAndSortingRepository<Job, Integer> {

}
