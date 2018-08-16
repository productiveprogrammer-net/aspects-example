package net.productiveprogrammer.repository;

import net.productiveprogrammer.dao.RequestLogDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLogDao, Long> {
    // TODO Work out how to do Today!
    List<RequestLogDao> findAllByCreatedDateTimeBetween(final Date start, final Date end);
}
