package br.com.automation.service.swagger;

import static br.com.automation.service.swagger.Resources.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.automation.service.IAutomationTool;

/**
 * Responsible for the automated test
 * @author Vittoria Zago
 * @since 2018-05-28
 */
@Component
public class SwaggerTest {

	@Autowired
	public IAutomationTool<?, Object> tool;

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

			// by clicking on body samples, js can move the tryOut button
			//so let's remove the possible causes of displacement
			tool.executeJavaScript(scriptSetInnerBodysEmpty);
			tool.executeJavaScript(scriptSetInnerDescriptionsEmpty);

			List<Object> typesOfEmptyFields = tool.getByXPath(xpathTypeOfEmptyAndRequiredField);
			List<Object> emptyFields = tool.getByXPath(xpathInputOfEmptyAndRequiredField);
			
			for (int i = 0; i < emptyFields.size(); i++) {
				String dataTypeString = tool.getElementContent(typesOfEmptyFields.get(i))
						.replace("Array", "")
						.replaceAll("\\W", "");
				DataTypeEnum dataType = DataTypeEnum.valueOf(dataTypeString.toUpperCase());

				setRequiredField(emptyFields.get(i), dataType);

				tool.wait(200, "");
			}

			// click on all the buttons with 'Try Out' message
			tool.getByXPath(xpathTryOutButtons).forEach(tryoutbutton -> {
				tool.click(tryoutbutton);
				tool.wait(500, "");
			});

			tool.wait(800, "");
			
			return GetResponses();
			
		} catch (Exception ex) {
			throw ex;

		} finally {
			tool.close();
		}
	}

	public void setRequiredField(Object field, DataTypeEnum dataType) {

		switch (dataType) {
		case INTEGER:
			tool.setValue(field, defaultValueForInteger);
			break;
		case DOUBLE:
			tool.setValue(field, defaultValueForDouble);
			break;
		case LONG:
			tool.setValue(field, defaultValueForLong);
			break;
		case DATETIME:
			tool.setValue(field, defaultValueForDateTime);
			break;
		case BOOLEAN:
			tool.setValue(field, defaultValueForBoolean);
			break;
		case FILE:
			tool.setValue(field, defaultValueForFile);
			break;
		default:
			tool.setValue(field, defaultValueForString);
			break;
		}
	}

	public List<ResultDTO> GetResponses(){
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
	}
	
}