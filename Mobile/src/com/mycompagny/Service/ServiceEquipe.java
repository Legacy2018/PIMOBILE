/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Fos_User;
import com.mycompany.Entite.Joueur;
import java.io.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Emel
 */
public class ServiceEquipe {
        
    

    public void ajouterEquipe(Equipe eq) {
        ConnectionRequest con = new ConnectionRequest();
        
        String Url = "http://localhost/PiWeb1/web/app_dev.php/api/addEq?"+
                "pays="+eq.getPays()+
                "&etat="+eq.getEtat()+
                "&groupe="+eq.getGroupe()+
                "&phase="+eq.getPhase()+
                "&point="+eq.getPoint()+
                "&selecteur="+eq.getSelecteur() ;
        
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            else{
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }


    ArrayList<Equipe> listEquipe = new ArrayList<>();
    
    public ArrayList<Equipe> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiWeb1/web/app_dev.php/api/showall");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                ServiceEquipe ser = new ServiceEquipe();
                listEquipe  = ser.getList(new String(con.getResponseData()));
                   System.out.println("lise        !!"+listEquipe);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEquipe ;
    }

    public ArrayList<Equipe> getList(String json) {

        ArrayList<Equipe> listEquipe = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> equipe = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(equipe);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) equipe.get("root");

            for (Map<String, Object> obj : list) {
                  System.out.println("lise        !!");
                Equipe e = new Equipe();
       //         e.setIdEquipe(Integer.parseInt(obj.get("idEquipe").toString().trim()));
              //  e.setIdEquipe((int) obj.get("idEquipe"));
           
                int ind = obj.get("idEquipe").toString().indexOf(".");
                String id= obj.get("idEquipe").toString().substring(0, ind );
                System.out.println(" id eqyupe  :"+id);
               e.setIdEquipe(Integer.valueOf(id));
                e.setPays(obj.get("pays").toString());
                e.setPhase(obj.get("phase").toString());
         //       e.setEtat((int) obj.get("etat"));
                e.setPhase(obj.get("phase").toString());
                e.setDrapeau((String) obj.get("liendrapeau"));
                System.out.println(e);
                listEquipe.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEquipe);
        return listEquipe;
    }
   
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        series.add("Project " + ++k, value);
    }

    return series;
}

    public Form createPieChartForm(
            //List<Joueur> l
    ) 
            {
    // Generate the values
        
        /*int totr = 0 ;
        int totj = 0;
        for (Joueur j : l)
        {
        totr = j.getCartr()+totr;
        totj=j.getCartj()+totj;
        }*/
        
    double[] values = new double[]{12, 14, 11, 10, 19};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Budget", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    
    return f;

}
    public void fav(Equipe e , Fos_User u)
    {   Database db ;
        boolean created =Database.exists("Russie");
        try {
            db= Database.openOrCreate("Russie");
            if (created ==false)
            {
           
                    db.execute("insert into  fav (usr , eq ) values ("+u.getId()+","+e.getIdEquipe()+");");
                    System.out.println("ok fav");
                     Cursor c= db.executeQuery("Select * fav ;");
                    while ( c.next())
                    {
                        Row r = c.getRow();
                        String n =r.getString(1);
                        String pre = r.getString(2);
                      
                                            System.out.println("id user :"+n +"ideq :"+pre);

                      
                    }
            }
            
        } catch (IOException ex) {
         
        }
    //return true ;
    }

  
    
}
