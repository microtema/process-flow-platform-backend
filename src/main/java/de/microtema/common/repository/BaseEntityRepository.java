package de.microtema.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

@NoRepositoryBean
public interface BaseEntityRepository<E extends BaseEntity> extends JpaRepository<E, UUID>, JpaSpecificationExecutor<E> , PagingAndSortingRepository<E, UUID> {

    Page<E> findAll(Specification<E> specification, Pageable pageable);
}
