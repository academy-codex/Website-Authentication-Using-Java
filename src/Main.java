import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLFormElement;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.w3c.dom.html.HTMLInputElement;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        // Login URL link of the form
        String loginURL = "https://news.ycombinator.com/login?goto=news";
        HtmlPage page = client.getPage(loginURL);

        // Selecting the username and password field.
        HtmlInput password = page.getFirstByXPath("//input[@type='password']");
        HtmlInput username = page.getFirstByXPath(".//preceding::input[not(@type='hidden')]");

        // Setting the values of the username and password field.
        username.setValueAttribute("sid21");
        password.setValueAttribute("123321123");

        // Submitting the form.
        HtmlForm form = password.getEnclosingForm();
        page = client.getPage(form.getWebRequest(null));

        HtmlAnchor logoutLink = page.getFirstByXPath(String.format("//a[@href='user?id=%s']", "sid21"));

        if (logoutLink!=null){
            System.out.println("Logged In!\n");

            // Printing out the resultant content in case of successful login

            System.out.print(page.asXml());

            // Incase you want to fetch the cookies

//            java.util.Set<Cookie> cookies = client.getCookieManager().getCookies();
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName() + " = " + cookie.getValue());
//            }
        } else{
            System.out.println("Could not login man!");
        }

    }
}
