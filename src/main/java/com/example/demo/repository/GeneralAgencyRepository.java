package com.example.demo.repository;

import static com.example.demo.model.QGeneralAgency.generalAgency;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.Location;
import com.example.demo.model.GeneralAgency;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class GeneralAgencyRepository {

	private final JPAQueryFactory queryFactory;

	public List<GeneralAgency> findAll() {
		return queryFactory.selectFrom(generalAgency).fetch();
	}
	
	public List<GeneralAgency> getGaList1(Location ne, Location sw) {
		return queryFactory.selectFrom(generalAgency)
				.where(generalAgency.x.loe(ne.getX())
						.and(generalAgency.y.loe(ne.getY())).and(generalAgency.x.goe(sw.getX()).and(generalAgency.y.goe(sw.getY()))))
				.fetch();
	}
}
