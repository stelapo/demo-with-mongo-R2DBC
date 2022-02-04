package com.example.demo.repository.specification;

import com.example.demo.model.SearchCriteria;
import com.example.demo.model.User;
import org.springframework.data.mongodb.core.query.Criteria;

public class UserCriteriaQuery extends Criteria {
    private SearchCriteria criteria;

    public UserCriteriaQuery(SearchCriteria criteria) {
        super(criteria.getKey());
        this.criteria = criteria;
        this.buildCriteria();
    }

    public String getKey(){
        return this.criteria.getKey();
    }

    private void buildCriteria(){
        if (criteria.getValue() != null) {
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                /*if (user.get(criteria.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            criteriaBuilder.upper(user.get(criteria.getKey())), "%" + criteria.getValue().toString().toUpperCase() + "%");
                } else { //per eventuali futuri parametri interi
                    return criteriaBuilder.equal(user.get(criteria.getKey()), criteria.getValue());
                }*/
                this.regex(criteria.getValue().toString(), "i");
            }
        }
    }

}
