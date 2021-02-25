package com.example.handlerconhilo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MiRunnable implements Runnable {
    Handler callback;
    MiRunnable(Handler callback){
        this.callback=callback;
    }
    @Override
    public void run() {
        Document document=null;
        ArrayList<Cuadro> lista_cuadros=new ArrayList();
        try {
            document= Jsoup.connect("https://www.museodelprado.es/coleccion/artista/tiziano-vecellio-di-gregorio/d5a82a70-aa3f-4355-b733-97c04d9690ab").get();
            Elements elementos_cuadro=document.select("div.mostrable.miniaturas>figure");
            for(Element e: elementos_cuadro){
                Element elemento_imagen=e.selectFirst("img");
                Element elemento_enlace=e.selectFirst("figcaption a");
                String ruta_cuadro=elemento_imagen.absUrl("src");
                String nombre_cuadro=elemento_enlace.text();
                Cuadro c=new Cuadro(nombre_cuadro, ruta_cuadro);
                lista_cuadros.add(c);
            }
            Message mensaje=new Message();
            mensaje.obj=lista_cuadros;
            callback.sendMessage(mensaje);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface IDevuelveDatos {
    }
}
