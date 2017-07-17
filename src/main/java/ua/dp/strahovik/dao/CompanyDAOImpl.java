package ua.dp.strahovik.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.dp.strahovik.entities.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {
    private static final Logger logger = LoggerFactory.getLogger(CompanyDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addCompany(Company company) {
        logger.debug("invoked addCompany(Company company) company=" + company);
        entityManager.persist(company);
        logger.info("inside addCompany(Company company) company=" + company + " company saved succesfully");
    }

    @Override
    public List<Company> getCompaniesWhereNameLike(String companyName) {
        logger.debug("invoked List<Company> getCompaniesWhereNameLike(String companyName) companyName=" + companyName);
        TypedQuery<Company> query = entityManager.createQuery("FROM Company company WHERE company.name LIKE :companyName",
                Company.class);
        query.setParameter("companyName", "%" + companyName + "%");
        List<Company> companies = query.getResultList();
        logger.debug("inside List<Company> getCompaniesWhereNameLike(String companyName) companyName=" + companyName
                + "/n companies detail=" + companies);
        return companies;
    }

    @Override
    public Company getCompanyByName(String companyName) {
        logger.debug("invoked Company getCompanyByName(String companyName) companyName=" + companyName);
        TypedQuery<Company> query = entityManager.createQuery("FROM Company company WHERE company.name = :companyName",
                Company.class);
        query.setParameter("companyName", companyName);
        return query.getSingleResult();
    }

    @Override
    public boolean companyExistsByName(String companyName) {
        logger.debug("invoked boolean companyExistsByName(String companyName) companyName=" + companyName);
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(company.name) FROM Company company WHERE " +
                "company.name = :companyName", Long.class);
        query.setParameter("companyName", companyName);
        return query.getSingleResult() == 1;
    }


    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
