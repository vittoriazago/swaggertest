package br.com.automation.service.swagger;

public class Resources {

	public static final String xpathControllers = "//h2//a[contains(@href,'#')]";
	public static final String xpathMethods = "//ul[contains(@style,'block') or @style='']//span[contains(@class,'path')]/a[contains(@class,'Operation')]";
	public static final String xpathTypeOfEmptyAndRequiredField = "//ul[contains(@style,'block') or @style='']//tr[td/*[contains(@class,'required') and not(@value!='')] and not(descendant::a)]//td[5]";
	public static final String xpathInputOfEmptyAndRequiredField = "//ul[contains(@style,'block') or @style='']//tr[not(descendant::a)]/td/*[contains(@class,'required') and not(@value!='')]";
	public static final String xpathRequiredBodys = "//ul[contains(@style,'block') or @style='']//table[@class='fullwidth parameters']//div[@class='snippet_json']";
	public static final String xpathTryOutButtons = "//ul[contains(@style,'block') or @style='']//input[@class='submit']";

	public static final String xpathRequestedMethod = "//ul[contains(@style,'block') or @style='']//span[@class='http_method']";
	public static final String xpathRequestedUrl = "//ul[contains(@style,'block') or @style='']//div[@style='']/div[contains(@class,'request_url')]";
	public static final String xpathResponseBody = "//ul[contains(@style,'block') or @style='']//div[@style='']/div[contains(@class,'response_body')]";
	public static final String xpathResponseCode = "//ul[contains(@style,'block') or @style='']//div[@style='']/div[contains(@class,'response_code')]";

	public static final String scriptSelectAnyOption = "";
	public static final String scriptSetDefaultObject = "";
	public static final String scriptSetInnerBodysEmpty = "var bodys = document.getElementsByClassName('snippet_json');"
			+ "for (var i=0;i<bodys.length; i++) {    bodys[i].innerHTML  = ''}";
	public static final String scriptSetInnerDescriptionsEmpty = "var bodys = document.getElementsByClassName('markdown');"
			+ "for (var i=0;i<bodys.length; i++) {    bodys[i].innerHTML  = ''}";

	public static final String defaultValueForString = "c9d459ee-daaf-43da-b74a-24113cfb62c6";
	public static final String defaultValueForBoolean = "true";
	public static final String defaultValueForFile = System.getProperty("user.dir") + "\\testeUpload.txt";
	public static final String defaultValueForInteger = "1";
	public static final String defaultValueForDouble = "1";
	public static final String defaultValueForLong = "1";
	public static final String defaultValueForDateTime = "2018-01-01";
}
