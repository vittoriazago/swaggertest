package br.com.automation.service;

import java.util.List;

public interface IAutomationTool<P, E> {

	public P openPage(String url);

	public E getFirstByXpath(String xpath);

	public List<E> getByXPath(String xpath);

	public P executeJavaScript(String javaScript);

	public P click(E element);

	public P setValue(E element, String value);

	public E getInternalElement(E element, String xpath);

	public List<E> getInternalElements(E element, String xpath);

	public String getAttributeValue(E element, String attribute);

	public String getElementContent(E element);

	public String getElementTagName(E element);

	void wait(long seconds, String xpath);

	public void close();
}