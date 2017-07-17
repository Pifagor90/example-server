package ua.dp.strahovik.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.net.MalformedURLException;
import java.net.URL;

@FacesValidator(value = "ua.dp.strahovik.validator.UrlValidator")
public class UrlValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext,UIComponent component, Object value) throws ValidatorException {
        if (value.toString().equals("")){
            return;
        }
        StringBuilder url = new StringBuilder();
        String urlValue = value.toString();

        if (!urlValue.startsWith("http://", 0)) {
            url.append("http://");
        }
        url.append(urlValue);

        try {
            new URL(url.toString());
        } catch (MalformedURLException e) {
            FacesMessage msg = new FacesMessage("URL validation failed", "Invalid URL format");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
