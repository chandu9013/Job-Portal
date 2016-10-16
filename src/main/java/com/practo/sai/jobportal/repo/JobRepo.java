package com.practo.sai.jobportal.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.practo.sai.jobportal.model.Employee;
import com.practo.sai.jobportal.model.Job;

@Transactional
public interface JobRepo extends PagingAndSortingRepository<Job, Integer> {

	@Modifying
	@Query("update Job set isClosed = true where jId =:jobId and recruit=:recruit")
	public void closeJob(@Param("jobId") int jobId, @Param("recruit") Employee recruit);

}
