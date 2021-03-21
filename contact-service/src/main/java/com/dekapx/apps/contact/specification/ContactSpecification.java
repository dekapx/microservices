package com.dekapx.apps.contact.specification;

import com.dekapx.apps.contact.domain.Contact;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification implements Specification<Contact> {
    private Contact contact;

    public ContactSpecification(final Contact contact) {
        super();
        this.contact = contact;
    }

    @Override
    public Predicate toPredicate(final Root<Contact> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final Predicate predicate = builder.disjunction();

        if (contact.getFirstName() != null) {
            predicate.getExpressions().add(builder.like(root.get("firstName"), "%" + contact.getFirstName() + "%"));
        }

        if (contact.getPhone() != null) {
            predicate.getExpressions().add(builder.like(root.get("phone"), "%" + contact.getPhone() + "%"));
        }
        return predicate;
    }
}
