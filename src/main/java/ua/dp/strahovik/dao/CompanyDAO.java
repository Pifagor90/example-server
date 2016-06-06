package ua.dp.strahovik.dao;


import ua.dp.strahovik.entities.Company;
import ua.dp.strahovik.entities.Event;

import java.util.List;

public interface CompanyDAO {
    public void addCompany(Company company);

    public List<Company> getCompaniesWhereNameLike(String companyName);

    public Company getCompanyByName(String companyName);

    public boolean companyExistsByName(String companyName);
}
