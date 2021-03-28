//package formFilling;
//
//public class AutoFilling {
//
//	public static void auto() {
//		 WebClient client = new WebClient(BrowserVersion.CHROME);
//	        client.setHTMLParserListener(HTMLParserListener.LOG_REPORTER);
//	        client.setJavaScriptEngine(new JavaScriptEngine(client));
//	        client.getOptions().setJavaScriptEnabled(true);
//	        client.getCookieManager().setCookiesEnabled(true);
//	        client.getOptions().setThrowExceptionOnScriptError(false);
//	        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
//	        client.setAjaxController(new NicelyResynchronizingAjaxController());
//	        client.getCache().setMaxSize(0);
//	        client.getOptions().setRedirectEnabled(true);
//
//	        String url = "https://accounts.google.com/login?hl=en#identifier";
//	        HtmlPage loginPage = client.getPage(url);
//	        client.waitForBackgroundJavaScript(1000000);
//
//	        HtmlForm loginForm = loginPage.getFirstByXPath("//form[@id='gaia_loginform']");
//	        List<HtmlInput> buttonInputs = loginForm.getInputsByValue("signIn");
//	        HtmlInput nextButton = Iterables.getFirst(buttonInputs, null);
//	        HtmlInput loginButton = Iterables.getLast(buttonInputs);
//	        Thread.sleep(2000);
//
//	        //setup email
//	        HtmlInput emailInput = loginForm.getInputByName("Email");
//	        emailInput.setValueAttribute(emailAddress);
//	        Thread.sleep(2000);
//
//	        //click next button
//	        nextButton.click();
//	        client.waitForBackgroundJavaScript(1000000);
//	        Thread.sleep(2000);
//
//	        //setup password
//	        HtmlInput passwordInput = loginForm.getInputByName("Passwd");
//	        passwordInput.setValueAttribute(password);
//
//	        //click login button
//	        loginButton.click();
//	        client.waitForBackgroundJavaScript(1000000);
//	        Thread.sleep(2000);
//
//	        HtmlPage gmailPage = client.getPage("https://mail.google.com/mail/u/0/#inbox");
//	        log.info(gmailPage.asText());
//	}
//}
