package com.dekapx.apps.contact.specification;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.core.search.SearchCriteria;
import com.dekapx.apps.core.search.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ContactSpecification implements Specification<Contact> {
    private List<SearchCriteria> searchCriterias;

    public ContactSpecification() {
        this.searchCriterias = new ArrayList<>();
    }

    public void addSearchCriteria(final SearchCriteria searchCriteria) {
        this.searchCriterias.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(final Root<Contact> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria searchCriteria : searchCriterias) {
            if (searchCriteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
