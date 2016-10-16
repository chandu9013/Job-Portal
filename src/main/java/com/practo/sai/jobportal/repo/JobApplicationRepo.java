package com.practo.sai.jobportal.repo;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.practo.sai.jobportal.model.JobApplication;

@Transactional
public interface JobApplicationRepo extends PagingAndSortingRepository<JobApplication, Integer> {

	@Query("from JobApplication ja where ja.job.jId=:jobId")
	public Page<JobApplication> findByJobId(@Param("jobId") int jobId, Pageable pageable);
}
