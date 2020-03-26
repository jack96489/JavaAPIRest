package javaapirest;


import java.awt.image.ImageProducer;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;


class WSConsumer {

    private String result;

    private String prefix;
    private String parameters;

    WSConsumer() {
        reset();
    }

    void reset() {
        result = "";
        parameters = "";
        prefix = "";
    }

    WSConsumer(String str) {
        result = "";
        parameters = "";
        prefix = str;
    }

    public String getResult() {
        return result;
    }

    public void addParameter(String paramater, String value) throws UnsupportedEncodingException {
        if (!paramater.isEmpty() && !value.isEmpty()) {
            if (parameters.isEmpty())
                parameters = URLEncoder.encode(paramater, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
            else
                parameters += "&" + URLEncoder.encode(paramater, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
        }
    }


    public int get(String host) {
        int status = 0;
        try {
            String url;
            if (parameters.isEmpty())
                url = prefix;
            else
                url = prefix + "?" + parameters;

            System.out.println("GET " + url);

            final URL serverURL = new URL(url);

            final HttpURLConnection service;
            if (prefix.contains("https"))
                service = (HttpsURLConnection) serverURL.openConnection();
            else
                service = (HttpURLConnection) serverURL.openConnection();

            // impostazione header richiesta
            service.setRequestProperty("Host", host);
            service.setRequestProperty("Accept", "application/text");   // elenco dati accettabili dal client https://en.wikipedia.org/wiki/Media_type
            service.setRequestProperty("Accept", "application/pdf");
            service.setRequestProperty("Accept-Charset", "UTF-8");
            // impostazione metodo di richiesta GET
            service.setRequestMethod("GET");
            // attivazione ricezione
            service.setDoInput(true);
            // connessione al web-service
            service.connect();
            // verifica stato risposta
            status = service.getResponseCode();
            // apertura stream di ricezione da risorsa web
            final BufferedReader input = new BufferedReader(new InputStreamReader(service.getInputStream(), StandardCharsets.UTF_8));
            // lettura da web e scrittura in result
            result = input.lines().collect(Collectors.joining("\n"));
            input.close();

            if (status != 200)
                throw new RuntimeException(service.getResponseCode() + ": " + service.getResponseMessage());

        } catch (IOException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        parameters = "";
        return status;
    }

    public int post(String host) {
        int status = 0;
        result = "";

        System.out.println("POST " + prefix);

        try {
            final URL serverURL = new URL(prefix);   // i parametri non vengono aggiunti all'url, ma vengono inviati tramite stream
            final HttpURLConnection service;
            if (prefix.contains("https"))
                service = (HttpsURLConnection) serverURL.openConnection();
            else
                service = (HttpURLConnection) serverURL.openConnection();

            // impostazione header richiesta
            service.setRequestProperty("Host", host);
            service.setRequestProperty("Accept", "application/text");   // elenco dati accettabili dal client https://en.wikipedia.org/wiki/Media_type
            service.setRequestProperty("Accept", "application/pdf");
            service.setRequestProperty("Accept-Charset", "UTF-8");
            // impostazione metodo di richiesta POST
            service.setRequestMethod("POST");

            // attivazione ricezione e trasmissione
            service.setDoInput(true);
            service.setDoOutput(true);

            // i parametri vengono inviati sullo stream
            final OutputStream os = service.getOutputStream();
            os.write(parameters.getBytes());
            os.flush();
            os.close();
            // fine invio


            // connessione al web-service
            service.connect();
            // verifica stato risposta
            status = service.getResponseCode();

            if (status != 200)
                throw new RuntimeException(status + ": " + service.getResponseMessage());    //non ok

            // apertura stream di ricezione da risorsa web
            final BufferedReader input = new BufferedReader(new InputStreamReader(service.getInputStream(), StandardCharsets.UTF_8));

            //lettura da web e scrittura in result
            result = input.lines().collect(Collectors.joining("\n"));
            input.close();

        } catch (IOException ex) {
            Logger.getLogger(WSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        parameters = "";
        return status;
    }


}