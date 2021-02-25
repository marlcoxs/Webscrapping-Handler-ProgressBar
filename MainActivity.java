package com.example.handlerconhilo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
Button boton;
WebView wv;
Handler manejador;
ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress_bar=findViewById(R.id.id_progressbar);
        manejador=new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                 //Aqui recibo los mensajes del otro hilo
                super.handleMessage(msg);
               ArrayList<Cuadro> lista_cuadros=(ArrayList<Cuadro>) msg.obj;//Aqui va la lista de cuadros
                rellenarTabla(lista_cuadros);



            }
        };
        boton=findViewById(R.id.id_btn);
        wv=findViewById(R.id.id_webview);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);
                MiRunnable runable=new MiRunnable(manejador);
                Thread hilo=new Thread(runable);
                hilo.start();


            }
        });
    }
    public void rellenarTabla(ArrayList<Cuadro> cuadros){
        String html=crearHTML(cuadros);
        wv.loadData(html, "text/html", "UTF-8");
        progress_bar.setVisibility(View.INVISIBLE);
    }
    public String crearHTML(ArrayList<Cuadro> cuadros){
        String html_tabla="<html><head></head><body><table border=1px>";
        for (Cuadro c: cuadros){
            html_tabla+="<tr><td><img width=40px src='"+c.getRuta_imagen()+"'></td>"+
                    "<td>"+c.getNombre()+"</td><tr>";
                }
        html_tabla+="</table></body></html>";
        return html_tabla;

    }
}