package ua.dp.strahovik.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.dp.strahovik.dao.CompanyDAO;
import ua.dp.strahovik.entities.Company;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Service
@ManagedBean(name="companyService")
@ViewScoped
public class CompanyServiceImpl implements CompanyService{
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    private CompanyDAO companyDAO;

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<String> getCompanyNamesWhereNameLike(String companyName) {
        List<Company> companies = companyDAO.getCompaniesWhereNameLike(companyName);
        List<String> names = new ArrayList<>(companies.size());
        for(Company company : companies){
            names.add(company.getName());
        }
        return names;
    }
}
