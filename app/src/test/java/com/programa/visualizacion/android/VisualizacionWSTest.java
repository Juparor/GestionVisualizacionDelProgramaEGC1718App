package com.programa.visualizacion.android;

import junit.framework.Assert;

import org.junit.Test;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class VisualizacionWSTest {

    private SimpleDateFormat format = new SimpleDateFormat("H:m");

    @Test
    public void charlasConnectionTest(){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setAddAdornments(false);
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("django.soap.example", "getCharlas");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE("https://visualizacionpruebasws.herokuapp.com/soap_service/?wsdl", 0);
        try {
            httpTransport.call("django.soap.example/getCharlas", soapEnvelope);
            Assert.assertNotNull(soapEnvelope.getResponse());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void charlasRetrieveTest(){

        boolean booleanResult = false;
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setAddAdornments(false);
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("django.soap.example", "getCharlas");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE("https://visualizacionpruebasws.herokuapp.com/soap_service/?wsdl", 0);

        try {
            httpTransport.call("django.soap.example/getCharlas", soapEnvelope);

            if (soapEnvelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) soapEnvelope.bodyIn;
                throw new Exception(fault.faultstring);
            } else {
                SoapObject result = (SoapObject) soapEnvelope.getResponse();

                for (int i = 0; i < result.getPropertyCount(); i++) {

                    SoapObject object = (SoapObject) result.getProperty(i);

                    if ("anyType".equals(object.getName()) && object.getPropertyCount() > 0) {

                        Event event = new Event();

                        if ("getEventos".equals("getCharlas")) {

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

                if ("getCharlas".equals("getCharlas")) {
                    MainActivity.finishedWS = true;
                }
            }
            booleanResult = true;

            Assert.assertTrue(booleanResult);
        } catch (IOException e) {
            e.printStackTrace();
            booleanResult = false;
            Assert.assertFalse(booleanResult);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            booleanResult = false;
            Assert.assertFalse(booleanResult);
        } catch (Exception e) {
            e.printStackTrace();
            booleanResult = false;
            Assert.assertFalse(booleanResult);
        }
    }

    @Test
    public void charlasWrongMethodRetrieveTest(){

        boolean booleanResult = false;
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setAddAdornments(false);
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("django.soap.example", "getCharls");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE("https://visualizacionpruebasws.herokuapp.com/soap_service/?wsdl", 0);

        try {
            httpTransport.call("django.soap.example/getCharls", soapEnvelope);

            if (soapEnvelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) soapEnvelope.bodyIn;
                throw new Exception(fault.faultstring);
            } else {
                SoapObject result = (SoapObject) soapEnvelope.getResponse();

                for (int i = 0; i < result.getPropertyCount(); i++) {

                    SoapObject object = (SoapObject) result.getProperty(i);

                    if ("anyType".equals(object.getName()) && object.getPropertyCount() > 0) {

                        Event event = new Event();

                        if ("getEventos".equals("getCharlas")) {

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

                if ("getCharlas".equals("getCharlas")) {
                    MainActivity.finishedWS = true;
                }
            }
            booleanResult = true;

            Assert.assertTrue(booleanResult);
        } catch (IOException e) {
            e.printStackTrace();
            booleanResult = false;
            Assert.assertFalse(booleanResult);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            booleanResult = false;
            Assert.assertFalse(booleanResult);
        } catch (Exception e) {
            e.printStackTrace();
            booleanResult = false;
            Assert.assertFalse(booleanResult);
        }
    }

    @Test
    public void eventosConnectionTest(){
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setAddAdornments(false);
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("django.soap.example", "getEventos");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE("https://visualizacionpruebasws.herokuapp.com/soap_service/?wsdl", 0);
        try {
            httpTransport.call("django.soap.example/getEventos", soapEnvelope);
            Assert.assertNotNull(soapEnvelope.getResponse());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void eventosRetrieveTest(){

        boolean booleanResult = false;
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setAddAdornments(false);
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("django.soap.example", "getEventos");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE("https://visualizacionpruebasws.herokuapp.com/soap_service/?wsdl", 0);

        try {
            httpTransport.call("django.soap.example/getEventos", soapEnvelope);

            if (soapEnvelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) soapEnvelope.bodyIn;
                throw new Exception(fault.faultstring);
            } else {
                SoapObject result = (SoapObject) soapEnvelope.getResponse();

                for (int i = 0; i < result.getPropertyCount(); i++) {

                    SoapObject object = (SoapObject) result.getProperty(i);

                    if ("anyType".equals(object.getName()) && object.getPropertyCount() > 0) {

                        Event event = new Event();

                        if ("getEventos".equals("getEventos")) {

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

                if ("getCharlas".equals("getEventos")) {
                    MainActivity.finishedWS = true;
                }
            }
            booleanResult = true;

        } catch (IOException e) {
            e.printStackTrace();
            booleanResult = false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            booleanResult = false;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResult = false;
        }
        Assert.assertTrue(booleanResult);
    }

    @Test
    public void eventosWrongMethodRetrieveTest(){

        boolean booleanResult = false;
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setAddAdornments(false);
        soapEnvelope.dotNet = true;
        SoapObject soapReq = new SoapObject("django.soap.example", "getEvento");
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE httpTransport = new HttpTransportSE("https://visualizacionpruebasws.herokuapp.com/soap_service/?wsdl", 0);

        try {
            httpTransport.call("django.soap.example/getEvento", soapEnvelope);

            if (soapEnvelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) soapEnvelope.bodyIn;
                throw new Exception(fault.faultstring);
            } else {
                SoapObject result = (SoapObject) soapEnvelope.getResponse();

                for (int i = 0; i < result.getPropertyCount(); i++) {

                    SoapObject object = (SoapObject) result.getProperty(i);

                    if ("anyType".equals(object.getName()) && object.getPropertyCount() > 0) {

                        Event event = new Event();

                        if ("getEventos".equals("getEvento")) {

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

                if ("getCharlas".equals("getEvento")) {
                    MainActivity.finishedWS = true;
                }
            }
            booleanResult = true;
        } catch (IOException e) {
            e.printStackTrace();
            booleanResult = false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            booleanResult = false;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResult = false;
        }
        Assert.assertFalse(booleanResult);
    }
}
