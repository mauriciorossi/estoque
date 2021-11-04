package com.targettrust.imob.util;

import java.util.Date;
import java.util.Calendar;

public class Util {

    public static long pegaDataParaSistema() throws Exception {
        try {
            Date d = new Date(0);
            long dataHoje = d.getTime();
            return dataHoje;
        } catch (Exception e) {
            return 0;
        }
    }

    public static long dataToLong(String param) {
        try {
            int dia, mes, ano = 0;

            if ((param.indexOf("/") > 3) || (param.indexOf("-") > 3)) {
                param = dateFormatPortuguese(param);

            }
            if ((param.indexOf("/") < 0) && (param.indexOf("-") < 0)) { // digitado sem barras ou traços
                dia = Integer.parseInt(param.substring(0, 2));
                mes = Integer.parseInt(param.substring(2, 4)) - 1;
                ano = Integer.parseInt(param.substring(4));

            } else { // digitado com barras
                dia = Integer.parseInt(param.substring(0, 2));
                mes = Integer.parseInt(param.substring(3, 5)) - 1;
                ano = Integer.parseInt(param.substring(6));
            }
            if (ano < 99) { // caso digitou dois digitos só
                if (ano < 50) {
                    ano += 2000;
                } else {
                    ano += 1900;
                }
            }
            Calendar dataFormat = Calendar.getInstance();
            dataFormat.set(ano, mes, dia);
            Date novaData = dataFormat.getTime();
            return novaData.getTime();
        } catch (Exception e) {
            return 0;
        }

    }

    public static String dateFormatPortuguese(String param) {
        try {
            String data, dia, mes, ano = "";
            ano = param.substring(0, 4);
            mes = param.substring(5, 7);
            dia = param.substring(8, 10);
            data = dia + "/" + mes + "/" + ano;
            return data;
        } catch (Exception e) {
            return "";
        }

    }

    public static String formataData(Date data) {
        if (data == null) {
            return "";
        }
        String dt = java.text.DateFormat.getDateInstance().format(data);
        if ("31/12/1969".equals(dt)) {
            dt = "";
        }
        return dt;
    }

}
