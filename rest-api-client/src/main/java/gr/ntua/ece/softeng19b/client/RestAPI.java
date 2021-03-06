package gr.ntua.ece.softeng19b.client;

//import gr.ntua.ece.softeng19b.data.model.User;
import gr.ntua.ece.softeng19b.data.model.*;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class RestAPI {

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String BASE_URL = "/energy/api";
    public static final String CUSTOM_HEADER = "X-OBSERVATORY-AUTH";

    static {
        System.setProperty("jdk.internal.httpclient.disableHostnameVerification", "true");
    }

    private final String urlPrefix;
    private final HttpClient client;

    private String token = null; //user is not logged in

    public RestAPI() throws RuntimeException {
        this("localhost", 8765);
    }
    public void setToken(String token){
        this.token = token ;
    }
    public RestAPI(String host, int port) throws RuntimeException {
        try {
            this.client = newHttpClient();
        }
        catch(NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e.getMessage());
        }
        this.urlPrefix = "https://" + host + ":" + port + BASE_URL;
    }

    public String retrieveTokenFromFile(){
        try {
            BufferedReader brTest = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\2.0TL19-25-our-master\\cli-client\\src\\main\\java\\gr\\ntua\\ece\\softeng19b\\cli\\softeng19bAPI.token"));
            String temp = brTest.readLine().replace("\n","");
            brTest.close();
            return temp;
        }
        catch (Exception e){
            return "-1";
        }
    }

    String urlForActualDataLoad(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForActualDataLoadForMonth(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/month/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForActualDataLoadForYear(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/year/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }



    String urlForAggregatedGenerationPerType(String areaName, String resolutionCode, LocalDate date, String productionType, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        String encProdType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
        return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProdType + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForAggregatedGenerationPerTypeForMonth(String areaName, String resolutionCode, LocalDate date, String productionType, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        String encProdType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
        return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProdType + "/" + encResCode + "/month/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForAggregatedGenerationPerTypeForYear(String areaName, String resolutionCode, LocalDate date, String productionType, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        String encProdType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
        return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProdType + "/" + encResCode + "/year/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForDayAheadTotalLoad(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForDayAheadTotalLoadForMonth(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/month/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForDayAheadTotalLoadForYear(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/year/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForActualvsForecast(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForActualvsForecastForMonth(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/month/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForActualvsForecastForYear(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/year/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }
    String urlForHealthCheck() {
        return urlPrefix + "/HealthCheck";
    }

    String urlForReset() { return urlPrefix + "/Reset"; }

    String urlForLogin() { return urlPrefix + "/Login"; }

    String urlForLogout() { return urlPrefix + "/Logout"; }

    String urlForAddUser() { return urlPrefix + "/Admin/users"; }

    String urlForUpdateUser(String username) {
        //System.out.println("AAAAAAA");
        return urlPrefix + "/Admin/users/" + URLEncoder.encode(username, StandardCharsets.UTF_8);
    }

    String urlForGetUser(String username) {
        return urlForUpdateUser(username);
    }

    String urlForImport(String dataSet) {
        return urlPrefix + "/Admin/" + URLEncoder.encode(dataSet, StandardCharsets.UTF_8);
    }

    private HttpRequest newGetRequest(String url) {
        return newRequest("GET", url, URL_ENCODED, HttpRequest.BodyPublishers.noBody());
    }

    private HttpRequest newPostRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        return newRequest("POST", url, contentType, bodyPublisher);
    }

    private HttpRequest newPutRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        
        //System.out.println("AAAAAAA");
        return newRequest("PUT", url, contentType, bodyPublisher);
    }

    private HttpRequest newRequest(String method,
                                   String url,
                                   String contentType,
                                   HttpRequest.BodyPublisher bodyPublisher) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        if (token != null) {
            builder.header(CUSTOM_HEADER, token);
        }
        return builder.
                method(method, bodyPublisher).
                header(CONTENT_TYPE_HEADER, contentType).
                uri(URI.create(url)).
                build();
    }

    private <T> T sendRequestAndParseResponseBodyAsUTF8Text(Supplier<HttpRequest> requestSupplier,
                                                            Function<Reader, T> bodyProcessor) {
        HttpRequest request = requestSupplier.get();
        try {
            System.out.println("Sending " + request.method() + " to " + request.uri());
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                try {
                    if (bodyProcessor != null) {
                        return bodyProcessor.apply(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
                    }
                    else {
                        return null;
                    }
                }
                catch(Exception e) {
                    throw new ResponseProcessingException(e.getMessage(), e);
                }
            }
            else {
                throw new ServerResponseException(statusCode, ClientHelper.readContents(response.body()));
            }
        }
        catch(IOException | InterruptedException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    public boolean isLoggedIn() {
        return token != null;
    }

    public String healthCheck() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForHealthCheck()),
            ClientHelper::parseJsonStatus
        );
    }

    public String resetDatabase() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForReset(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
            ClientHelper::parseJsonStatus
        );
    }

    public String login(String username, String password) {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", username);
        if (username.equals("admin")){
            formData.put("password", password);
        }
        else {
            try{
                TrippleDes encrypt = new TrippleDes();
                String encryptedPassword  = encrypt.encrypt(password);
                formData.put("password", encryptedPassword);
            }
            catch(Exception e) {
            }        
        }
        token = sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForLogin(), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonToken
        );
        return token;
        
    }

    public void logout() {
        sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForLogout(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
            null
        );
        token = null;
    }

    public User addUser(String username, String email, String password, int quota) {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", username);
        formData.put("email", email);
        try{
            TrippleDes encrypt = new TrippleDes();
            String encryptedPassword  = encrypt.encrypt(password);
            formData.put("password", encryptedPassword);
        }
        catch(Exception e) {
        }        
        formData.put("requestsPerDayQuota", String.valueOf(quota));
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForAddUser(), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonUser
        );
    }

    public User updateUser(User upUser) {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("email", upUser.getEmail());
        formData.put("requestsPerDayQuota", String.valueOf(upUser.getRequestsPerDayQuota()));
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPutRequest(urlForUpdateUser(upUser.getUsername()), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonUser
        );
    }
        

    public User getUser(String username) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForGetUser(username)),
            ClientHelper::parseJsonUser
        );
    }

    public ImportResult importFile(String dataSet, Path dataFilePath) throws IOException {
        String boundary = new BigInteger(256, new Random()).toString();
        Map<String, Object> formData = Map.of("file", dataFilePath);
        HttpRequest.BodyPublisher bodyPublisher = ofMultipartFormData(formData, boundary);
        String contentType = MULTIPART_FORM_DATA + ";boundary=" + boundary;
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForImport(dataSet), contentType, bodyPublisher),
            ClientHelper::parseJsonImportResult
        );
    }
    public List<ATLRecordForSpecificDay> getActualTotalLoad(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {                                                        
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualDataLoad(areaName, resolutionCode, date, format)),
            format::consumeActualTotalLoadRecordsForSpecificDay
        );
    }

    public List<ATLRecordForSpecificMonth> getActualTotalLoadForMonth(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualDataLoadForMonth(areaName, resolutionCode, date, format)),
            format::consumeActualTotalLoadRecordsForSpecificMonth
        );
    }

    public List<ATLRecordForSpecificYear> getActualTotalLoadForYear(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualDataLoadForYear(areaName, resolutionCode, date, format)),
            format::consumeActualTotalLoadRecordsForSpecificYear
        );
    }



    public List<AGPTRecordForSpecificDay> getAggregatedGenerationPerType(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            String productionType,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForAggregatedGenerationPerType(areaName, resolutionCode, date,productionType, format)),
            format::consumeAggregatedGenerationPerTypeRecordsForSpecificDay
        );
    }

    public List<AGPTRecordForSpecificMonth> getAggregatedGenerationPerTypeForMonth(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            String productionType,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForAggregatedGenerationPerTypeForMonth(areaName, resolutionCode, date,productionType, format)),
            format::consumeAggregatedGenerationPerTypeRecordsForSpecificMonth
        );
    }

    public List<AGPTRecordForSpecificYear> getAggregatedGenerationPerTypeForYear(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            String productionType,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForAggregatedGenerationPerTypeForYear(areaName, resolutionCode, date,productionType, format)),
            format::consumeAggregatedGenerationPerTypeRecordsForSpecificYear
        );
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    
    public List<DATLFRecordForSpecificDay> getDayAheadTotalLoad(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForDayAheadTotalLoad(areaName, resolutionCode, date, format)),
            format::consumeDayAheadTotalLoadRecordsForSpecificDay
        );
    }

    public List<DATLFRecordForSpecificMonth> getDayAheadTotalLoadForMonth(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForDayAheadTotalLoadForMonth(areaName, resolutionCode, date, format)),
            format::consumeDayAheadTotalLoadRecordsForSpecificMonth
        );
    }

    public List<DATLFRecordForSpecificYear> getDayAheadTotalLoadForYear(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForDayAheadTotalLoadForYear(areaName, resolutionCode, date, format)),
            format::consumeDayAheadTotalLoadRecordsForSpecificYear
        );
    }
    /////////////////////////////////////////////////////////////////////////////
    public List<AvFRecordForSpecificDay> getActualvsForecast(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualvsForecast(areaName, resolutionCode, date, format)),
            format::consumeActualvsForecastRecordsForSpecificDay
        );
    }

    public List<AvFRecordForSpecificMonth> getActualvsForecastForMonth(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualvsForecastForMonth(areaName, resolutionCode, date, format)),
            format::consumeActualvsForecastRecordsForSpecificMonth
        );
    }

    public List<AvFRecordForSpecificYear> getActualvsForecastForYear(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualvsForecastForYear(areaName, resolutionCode, date, format)),
            format::consumeActualvsForecastRecordsForSpecificYear
        );
    }

    //Helper method to create a new http client that can tolerate self-signed or improper ssl certificates
    private static HttpClient newHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        return HttpClient.newBuilder().sslContext(sslContext).build();
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
        }
    };

    private static HttpRequest.BodyPublisher ofUrlEncodedFormData(Map<String, String> data) {
        return HttpRequest.BodyPublishers.ofString(ClientHelper.encode(data));
    }

    private static HttpRequest.BodyPublisher ofMultipartFormData(Map<String, Object> data, String boundary)
            throws IOException {
        var byteArrays = new ArrayList<byte[]>();
        String separator = "--" + boundary + "\r\nContent-Disposition: form-data; name=";
        byte[] separatorBytes = separator.getBytes(StandardCharsets.UTF_8);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            byteArrays.add(separatorBytes);

            if (entry.getValue() instanceof Path) {
                var path = (Path) entry.getValue();
                String mimeType = Files.probeContentType(path);
                byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
                        + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                byteArrays.add(Base64.getMimeEncoder().encode(Files.readAllBytes(path)));
                byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
            } else {
                byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue() + "\r\n")
                        .getBytes(StandardCharsets.UTF_8));
            }
        }
        byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

}
