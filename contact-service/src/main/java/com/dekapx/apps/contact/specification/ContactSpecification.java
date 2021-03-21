package com.dekapx.apps.contact.specification;

import com.dekapx.apps.contact.domain.Contact;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ContactSpecification implements Specification<Contact> {
    private Contact contact;

    public ContactSpecification(final Contact contact) {
        super();
        this.contact = contact;
    }

    @Override
    public Predicate toPredicate(final Root<Contact> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final Predicate predicate = builder.disjunction();

        Optional.ofNullable(contact.getFirstName())
                .ifPresent(firstName -> predicate.getExpressions()
                        .add(builder.like(root.get("firstName"), "%" + firstName + "%")));

        Optional.ofNullable(contact.getPhone())
                .ifPresent(phone -> predicate.getExpressions()
                        .add(builder.like(root.get("phone"), "%" + phone + "%")));

        return predicate;
    }
}
