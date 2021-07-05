package by.gourianova.monitorsensors.util;

import by.gourianova.monitorsensors.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class UserInfoTag extends TagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write( " " + user.getFirstName());;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
