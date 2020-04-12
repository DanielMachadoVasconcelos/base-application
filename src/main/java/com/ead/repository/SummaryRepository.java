package com.ead.repository;

import com.ead.model.Summary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "summary", path = "summary")
public interface SummaryRepository extends PagingAndSortingRepository<Summary, String> {

    List<Summary> findAllByStatus(@Param("status") String status);



}
