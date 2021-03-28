import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SignedUrl {

	public static void getSignedUrl2() throws IOException {
		

		OkHttpClient client = new OkHttpClient();



		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
		.addFormDataPart("filename", "image.jpg")
		.addFormDataPart("filetype", "image/jpg")
		.addFormDataPart("filesize", "23")
		.addFormDataPart("transferid", "VDFGSF")
		.build();
		 Request request = new Request.Builder().url("https://trango.io/Trango")
		.post(requestBody).build();
		okhttp3.Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) {throw new IOException("Unexpected code " + response);}
		System.out.println(response.body().string());}
	
	
	public static void main(String args[]) throws IOException {
	getSignedUrl2();
	}
	public static void getSignedUrl() throws IOException {
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://trango.io/Trango?filename=image.jpg&filetype=image/jpg&filesize=23.3&transferid=XCFDSF")
		  .post(null)
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Host", "trango.io")
		  .addHeader("accept-encoding", "gzip, deflate")
		  .addHeader("content-length", "")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("cache-control", "no-cache")
		  .build();

		okhttp3.Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
	}
	
}
