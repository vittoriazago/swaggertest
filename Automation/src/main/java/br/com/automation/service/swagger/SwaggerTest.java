package br.com.automation.service.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.automation.service.IAutomationTool;

@Component
public class SwaggerTest {

	@Autowired
	public IAutomationTool<?, Object> tool;

	private final String xpathControllers = "//h2//a[contains(@href,'#')]";
	private final String xpathMethods = "//ul[contains(@style,'block') or @style='']//span[contains(@class,'path')]/a[contains(@class,'Operation')]";
	private final String xpathTypeOfEmptyAndRequiredField = "//ul[contains(@style,'block') or @style='']//tr[td/*[contains(@class,'required') and not(@value!='')] and not(descendant::a)]//td[5]";
	private final String xpathInputOfEmptyAndRequiredField = "//ul[contains(@style,'block') or @style='']//tr[not(descendant::a)]/td/*[contains(@class,'required') and not(@value!='')]";
	private final String xpathRequiredBodys = "//ul[contains(@style,'block') or @style='']//table[@class='fullwidth parameters']//div[@class='snippet_json']";
	private final String xpathTryOutButtons = "//ul[contains(@style,'block') or @style='']//input[@class='submit']";

	private final String xpathRequestedMethod = "//ul[contains(@style,'block') or @style='']//span[@class='http_method']";
	private final String xpathRequestedUrl = "//ul[contains(@style,'block') or @style='']//div[@style='']/div[contains(@class,'request_url')]";
	private final String xpathResponseBody = "//ul[contains(@style,'block') or @style='']//div[@style='']/div[contains(@class,'response_body')]";
	private final String xpathResponseCode = "//ul[contains(@style,'block') or @style='']//div[@style='']/div[contains(@class,'response_code')]";

	private final String scriptSelectAnyOption = "";
	private final String scriptSetDefaultObject = "";
	private final String scriptSetInnerBodysEmpty = "var bodys = document.getElementsByClassName('snippet_json');"
			+ "for (var i=0;i<bodys.length; i++) {    bodys[i].innerHTML  = ''}";
	private final String scriptSetInnerDescriptionsEmpty = "var bodys = document.getElementsByClassName('markdown');"
			+ "for (var i=0;i<bodys.length; i++) {    bodys[i].innerHTML  = ''}";

	private final String defaultValueForString = "c9d459ee-daaf-43da-b74a-24113cfb62c6";
	private final String defaultValueForBoolean = "true";
	private final String defaultValueForFile = "C:\\testeUpload.txt";
	private final String defaultValueForInteger = "1";
	private final String defaultValueForDouble = "1";
	private final String defaultValueForLong = "1";
	private final String defaultValueForDateTime = "2018-01-01";

	public List<ResultDTO> executaFluxo(String url) throws Exception {
		
		try {
			tool.openPage(url);

			tool.wait(1000, "");

			// select the links to open controllers, and click each one of them
			tool.getByXPath(xpathControllers).forEach(controller -> {
				tool.click(controller);
				tool.wait(1000, "");
			});

			tool.getByXPath(xpathMethods).forEach(method -> {
				tool.click(method);
				tool.wait(1000, "");
			});

			tool.getByXPath(xpathRequiredBodys).forEach(body -> {
				tool.click(body);
				tool.wait(500, "");
			});

			// by clicking on body samples, js can deslocate tryOutButton
			tool.executeJavaScript(scriptSetInnerBodysEmpty);

			tool.executeJavaScript(scriptSetInnerDescriptionsEmpty);

			List<Object> typesOfEmptyFields = tool.getByXPath(xpathTypeOfEmptyAndRequiredField);
			List<Object> emptyFields = tool.getByXPath(xpathInputOfEmptyAndRequiredField);
			for (int i = 0; i < emptyFields.size(); i++) {
				String dataTypeString = tool.getElementContent(typesOfEmptyFields.get(i)).replace("Array", "")
						.replaceAll("\\W", "");
				DataTypeEnum dataType = DataTypeEnum.valueOf(dataTypeString.toUpperCase());

				setRequiredField(emptyFields.get(i), dataType);

				tool.wait(500, "");
			}

			// click on all the buttons with 'Try Out' message
			tool.getByXPath(xpathTryOutButtons).forEach(tryoutbutton -> {
				tool.click(tryoutbutton);
				tool.wait(500, "");
			});

			tool.wait(800, "");
			List<Object> divMethods = tool.getByXPath(xpathRequestedMethod);
			List<Object> divResultUrl = tool.getByXPath(xpathRequestedUrl);
			List<Object> divResultBody = tool.getByXPath(xpathResponseBody);
			List<Object> divResultCode = tool.getByXPath(xpathResponseCode);
			List<ResultDTO> results = new ArrayList<ResultDTO>();

			for (int i = 0; i < divResultUrl.size(); i++) {

				ResultDTO result = new ResultDTO(tool.getElementContent(divMethods.get(i)).trim().toUpperCase(),
						tool.getElementContent(divResultUrl.get(i)), tool.getElementContent(divResultCode.get(i)),
						tool.getElementContent(divResultBody.get(i)));

				results.add(result);
				System.out.println(result.toString());
			}
			return results;
		} catch (Exception ex) {
			throw ex;

		} finally {
			tool.close();
		}
	}

	public void setRequiredField(Object field, DataTypeEnum dataType) {
		String fieldTag = tool.getElementTagName(field).toUpperCase();
		TagNameEnum fieldType = TagNameEnum.valueOf(fieldTag);
		String fieldId = tool.getAttributeValue(field, "id");

		// swtich commented because setValue will work
		// switch (fieldType) {
		// case INPUT:
		if (dataType == DataTypeEnum.INTEGER)
			tool.setValue(field, defaultValueForInteger);
		else if (dataType == DataTypeEnum.DOUBLE)
			tool.setValue(field, defaultValueForDouble);
		else if (dataType == DataTypeEnum.LONG)
			tool.setValue(field, defaultValueForLong);
		else if (dataType == DataTypeEnum.DATETIME)
			tool.setValue(field, defaultValueForDateTime);
		else if (dataType == DataTypeEnum.BOOLEAN)
			tool.setValue(field, defaultValueForBoolean);
		else if (dataType == DataTypeEnum.FILE)
			tool.setValue(field, defaultValueForFile);
		else
			tool.setValue(field, defaultValueForString);
		// break;
		// case SELECT:
		// tool.executeJavaScript(String.format(scriptSelectAnyOption, fieldId));
		// break;
		// case TEXTAREA:
		// tool.executeJavaScript(String.format(scriptSetDefaultObject, fieldId));
		// break;
		// }
	}

}