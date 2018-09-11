package com.programa.visualizacion.android.ws;

import android.os.AsyncTask;

import com.programa.visualizacion.android.Event;
import com.programa.visualizacion.android.MainActivity;

import org.apache.poi.util.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class VisualizacionWS {

    private static String nameSpace = "django.soap.example";
    private static String wsdlUrl = "https://visualizacionpruebasws.herokuapp.com/soap_service/?wsdl";
    private static int timeOut = 0;
    private static String methodName = "";
    private static String soapAction = "";
    private static SimpleDateFormat format = new SimpleDateFormat("H:m");

    public static boolean getCharlas() {

        soapAction = "django.soap.example/getCharlas";
        methodName = "getCharlas";

        return makeRequest();
    }

    public static boolean getEventos() {

        soapAction = "django.soap.example/getEventos";
        methodName = "getEventos";

        return makeRequest();
    }

    private static boolean makeRequest() {

        boolean result;

        WebServiceTask webServiceTask = new WebServiceTask();
        try {
           result = (boolean) webServiceTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            result = false;
        } catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    private static class WebServiceTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.setAddAdornments(false);
            soapEnvelope.dotNet = true;
            SoapObject soapReq = new SoapObject(nameSpace, methodName);
            soapEnvelope.setOutputSoapObject(soapReq);
            HttpTransportSE httpTransport = new HttpTransportSE(wsdlUrl, timeOut);

            try {
                httpTransport.call(soapAction, soapEnvelope);

                if (soapEnvelope.bodyIn instanceof SoapFault) {
                    SoapFault fault = (SoapFault) soapEnvelope.bodyIn;
                    throw new Exception(fault.faultstring);
                } else {
                    SoapObject result = (SoapObject) soapEnvelope.getResponse();

                    for (int i = 0; i < result.getPropertyCount(); i++) {

                        SoapObject object = (SoapObject) result.getProperty(i);

                        if ("anyType".equals(object.getName()) && object.getPropertyCount() > 0) {

                            Event event = new Event();

                            if ("getEventos".equals(methodName)) {

                                for (int j = 1; j < object.getPropertyCount(); j++) {

                                    SoapPrimitive soapPrimitive = (SoapPrimitive) object.getProperty(j);
                                    String soapPrimitiveValue = "";

                                    if (soapPrimitive != null){
                                        soapPrimitiveValue = soapPrimitive.getValue().toString().trim();
                                    }

                                    switch (j) {
                                        case 1:
                                            if (soapPrimitiveValue.contains("25")){
                                                event.setDay(25);
                                            } else if (soapPrimitiveValue.contains("26")){
                                                event.setDay(26);

                                            } else if (soapPrimitiveValue.contains("27")){
                                                event.setDay(27);

                                            } else if (soapPrimitiveValue.contains("28")){
                                                event.setDay(28);

                                            } else if (soapPrimitiveValue.contains("29")){
                                                event.setDay(29);
                                            }
                                            break;
                                        case 2:
                                            event.setBeginningHour(format.parse(soapPrimitiveValue));
                                            break;
                                        case 3:
                                            event.setEndingHour(format.parse(soapPrimitiveValue));
                                            break;
                                        case 4:
                                            event.setTitle(soapPrimitiveValue);
                                            break;
                                        case 5:
                                            event.setCode(soapPrimitiveValue);
                                            break;
                                    }
                                }
                                MainActivity.allEvents.add(event);

                            } else {

                                Event aux = null;

                                for (int j = object.getPropertyCount(); j > 0; j--) {

                                    SoapPrimitive soapPrimitive = (SoapPrimitive) object.getProperty(j - 1);

                                    String soapPrimitiveValue = null;

                                    if (soapPrimitive != null){
                                        soapPrimitiveValue = soapPrimitive.getValue().toString().trim();
                                    }

                                    if (j == object.getPropertyCount()) {
                                        for (Event e : MainActivity.allEvents) {

                                            if (soapPrimitiveValue != null && e.getCode() != null && e.getCode().equals(soapPrimitiveValue)) {

                                                event = e;
                                                aux = e;
                                                break;
                                            }
                                        }
                                    }

                                    if (aux != null) {

                                        switch (j) {
                                            case 1:
                                                aux.setTitle(soapPrimitiveValue);
                                                break;
                                            case 2:
                                                aux.setSpeakers(soapPrimitiveValue);
                                                break;
                                            case 3:
                                                aux.setDescription(soapPrimitiveValue);
                                                break;
                                            case 4:
                                                break;
                                        }
                                    }
                                }

                                if (aux != null){
                                    MainActivity.allEvents.remove(event);
                                    MainActivity.allEvents.add(aux);
                                }
                            }
                        }
                    }

                    if ("getCharlas".equals(methodName)) {
                        MainActivity.finishedWS = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}
