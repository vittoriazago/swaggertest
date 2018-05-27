package br.com.automation.service.tools;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import br.com.automation.service.IAutomationTool;

@Component
public class HtmlElementTool implements IAutomationTool<HtmlPage, HtmlElement> {

	HtmlPage page;

	public HtmlElementTool() {
	}

	@Override
	public HtmlPage openPage(String url) {
		WebClient wu = new WebClient(new BrowserVersion("browser", "fakeBrowser", "", 1));
		wu.setAjaxController(new NicelyResynchronizingAjaxController());
		wu.getOptions().setThrowExceptionOnFailingStatusCode(false);
		wu.getOptions().setThrowExceptionOnScriptError(false);

		wu.getOptions().setJavaScriptEnabled(true);
		wu.getOptions().setRedirectEnabled(true);
		wu.getOptions().setUseInsecureSSL(true);
		wu.getOptions().setCssEnabled(true);
		wu.addRequestHeader("Accept-Language", "pt-br");

		try {
			page = wu.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public HtmlElement getFirstByXpath(String xpath) {
		return page.getFirstByXPath(xpath);
	}

	@Override
	public List<HtmlElement> getByXPath(String xpath) {
		return (List<HtmlElement>) page.getByXPath(xpath);
	}

	@Override
	public HtmlPage executeJavaScript(String javaScript) {
		ScriptResult result = page.executeJavaScript(javaScript);
		page = (HtmlPage) result.getNewPage();
		return page;
	}

	@Override
	public HtmlPage click(HtmlElement element) {
		try {
			page = element.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public HtmlPage setValue(HtmlElement element, String value) {
		element.setAttribute("value", value);
		return page;
	}

	@Override
	public HtmlElement getInternalElement(HtmlElement element, String xpath) {
		return element.getFirstByXPath(xpath);
	}


	@Override
	public List<HtmlElement> getInternalElements(HtmlElement element, String xpath) {
		return (List<HtmlElement>)element.getByXPath(xpath);
	}
	
	@Override
	public String getAttributeValue(HtmlElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	@Override
	public String getElementContent(HtmlElement element) {
		return element.getTextContent();
	}

	@Override
	public String getElementTagName(HtmlElement element) {
		return element.getTagName();
	}

	@Override
	public void wait(long seconds, String xpath) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}