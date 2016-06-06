package ua.dp.strahovik.service;



import java.util.List;

public interface CompanyService {
    public List<String> getCompanyNamesWhereNameLike(String companyName);

}
