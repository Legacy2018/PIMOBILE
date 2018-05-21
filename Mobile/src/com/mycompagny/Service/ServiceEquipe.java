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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Fos_User;
import com.mycompany.Entite.Joueur;
import com.mycompany.gui.detailEquipe;
import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Emel
 */
public class ServiceEquipe {

    public void ajouterEquipe(Equipe eq) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/emel/PiWeb1/web/app_dev.php/api/addEq?"
                + "pays=" + eq.getPays()
                + "&etat=" + eq.getEtat()
                + "&groupe=" + eq.getGroupe()
                + "&phase=" + eq.getPhase()
                + "&point=" + eq.getPoint()
                + "&selecteur=" + eq.getSelecteur();

        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void UpdateEquipe(Equipe eq) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/emel/PiWeb1/web/app_dev.php/api/EdittEq/" + eq.getIdEquipe()
                + "?pays=" + eq.getPays()
                + "&etat=" + eq.getEtat()
                + "&groupe=" + eq.getGroupe()
                + "&phase=" + eq.getPhase()
                + "&point=" + eq.getPoint()
                + "&selecteur=" + eq.getSelecteur();

        con.setUrl(Url);

        System.out.println("tt");

        /* con.addResponseListener((e) -> {
         String str = new String(con.getResponseData());
         System.out.println(str);
            
         });*/
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void DeleteEquipe(Equipe eq) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/emel/PiWeb1/web/app_dev.php/api/DeleteEq/"
                + eq.getIdEquipe();

        con.setUrl(Url);

        System.out.println("equipe id " + eq.getIdEquipe());

        /* con.addResponseListener((e) -> {
         String str = new String(con.getResponseData());
         System.out.println(str);
            
         });*/
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    ArrayList<Equipe> listEquipe = new ArrayList<>();
    Map<String, Integer> listechart = new HashMap<>();

    public ArrayList<Equipe> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/emel/PiWeb1/web/app_dev.php/api/showall");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceEquipe ser = new ServiceEquipe();
                listEquipe = ser.getList(new String(con.getResponseData()));
                System.out.println("lise        !!" + listEquipe);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEquipe;
    }
    String pourcentage;
    Map<String, Integer> stat;

    public Map<String, Integer> getChar(Equipe e) {
        // String  pourcentage = null;
        ConnectionRequest con = new ConnectionRequest();
        stat = new HashMap<>();
        con.setUrl("http://localhost/emel/PiWeb1/web/app_dev.php/api/chartEq/" + e.getIdEquipe());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceEquipe ser = new ServiceEquipe();
                //     listechart = ser.getChart(new String(con.getResponseData()));
                try {
                    String pourcentage = new String(con.getResponseData(), "utf-8");
                    System.out.println("pour !!" + pourcentage);
                    int ind = pourcentage.indexOf("totr");
                    int ind1 = pourcentage.indexOf(",");

                    String id = pourcentage.toString().substring(ind + 6, ind1);
                    System.out.println("tot r !" + id);
                    stat.put("totr", Integer.parseInt(id));
                    //
                    int ind2 = pourcentage.indexOf("totj");
                    int ind21 = pourcentage.indexOf(",\"r");

                    String id2 = pourcentage.toString().substring(ind2 + 6, ind21);
                    System.out.println("tot j !" + id2);
                    stat.put("totj", Integer.parseInt(id2));
                    //
                    int ind3 = pourcentage.indexOf("\"rouge\":");
                    int ind31 = pourcentage.indexOf(",\"j");

                    String id3 = pourcentage.toString().substring(ind3 + 8, ind31);
                    System.out.println("rouge !" + id3);
                    stat.put("rouge", Integer.parseInt(id3));
                    //
                    int ind4 = pourcentage.indexOf("\"jaune\":");
                    int ind41 = pourcentage.indexOf("}");

                    String id4 = pourcentage.toString().substring(ind4 + 8, ind41);
                    System.out.println("jaune !" + id4);
                    stat.put("jaune", Integer.parseInt(id4));
                    //

                } catch (UnsupportedEncodingException ex) {
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return stat;
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
                String id = obj.get("idEquipe").toString().substring(0, ind);
                int ind1 = obj.get("etat").toString().indexOf("etat");
                String et = obj.get("etat").toString().substring(ind1 + 1, ind1 + 2);
                System.err.println("etat    :" + et);
                System.out.println(" id eqyupe  :" + id);
                e.setIdEquipe(Integer.valueOf(id));
                e.setPays(obj.get("pays").toString());
                e.setPhase(obj.get("phase").toString());
                int ind3 = obj.get("point").toString().indexOf("point");
                int ind4 = obj.get("point").toString().length();
                String pt = obj.get("point").toString().substring(ind3 + 1, ind4 - 2);
                e.setPoint(Integer.valueOf(pt));

                System.out.print("point +" + pt);

                e.setEtat(Integer.valueOf(et));
                e.setGroupe(obj.get("groupe").toString());
                e.setSelecteur(obj.get("selecteur").toString());
                e.setDrapeau((String) obj.get("liendrapeau"));
                System.out.println(e);
                listEquipe.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEquipe);
        return listEquipe;
    }

    public Map<String, Integer> getChart(String json) {

        ArrayList<Equipe> listEquipe = new ArrayList<>();
        Map<String, Integer> liste = new HashMap<String, Integer>();
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> ch = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("aaa" + ch);
            List<Map<String, Object>> list = (List<Map<String, Object>>) ch.get("root");

            //  for (Map<String, Object> obj : list) {
            System.out.println("lise        !!");

            int totr = (int) list.get(0).get("totr");
            int totj = (int) list.get(0).get("totj");

            int ro = (int) list.get(0).get("rouge");
            int jo = (int) list.get(0).get("jaune");

            liste.put("totr", totr);
            liste.put("totj", totj);
            liste.put("rouge", ro);
            liste.put("jaune", jo);

            // }
        } catch (IOException ex) {
        }
        System.out.println(listEquipe);
        return liste;
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
            series.add("Carton " + ++k, value);
        }

        return series;
    }

    public Form createPieChartForm( //List<Joueur> l

            Equipe e
    ) {
        // Generate the values

        /*int totr = 0 ;
         int totj = 0;
         for (Joueur j : l)
         {
         totr = j.getCartr()+totr;
         totj=j.getCartj()+totj;
         }*/
        Map<String, Integer> st = new HashMap<>();
        st = getChar(e);
        int totr = st.get("totr");
        int totj = st.get("totj");
        int rouge = st.get("rouge");
        int jaune = st.get("jaune");
        double[] values = new double[]{totr, totj, rouge, jaune};

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
        PieChart chart = new PieChart(buildCategoryDataset("Carton rouge et jaune", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Carton", new BorderLayout());
                  f.setUIID("AbonnementsForm");

        f.add(BorderLayout.CENTER, c);
         Toolbar tb = f.getToolbar();
            tb.addCommandToLeftBar("Retour", null, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    detailEquipe af;
                    af = new detailEquipe(e);
                    af.getF().show();

                }
            }); 
        return f;

    }

    public void fav(Equipe e, Fos_User u) {
        Database db;
        //   System.out.println("id eq "+fu.getId());

        try {
            db = Database.openOrCreate("dbRussia2018");

            db.execute("insert into  favoris   (id ,usr , eq ) values (" + u.getId() + "," + u.getId() + "," + e.getIdEquipe() + ");");
            System.out.println("ok fav");
            Cursor c = db.executeQuery("Select * from favoris ;");
            while (c.next()) {
                Row r = c.getRow();
                String a = r.getString(0);

                String n = r.getString(1);
                String pre = r.getString(2);

                System.out.println("id user :" + n + "ideq :" + pre + "id :" + a);

            }

        } catch (IOException ex) {

        }
    }

    public void nofav(Equipe e, Fos_User u) {
        Database db;
        //   System.out.println("id eq "+fu.getId());

        try {
            db = Database.openOrCreate("dbRussia2018");

            db.execute("delete from favoris   where usr=" + u.getId() + " and eq=" + e.getIdEquipe() + ";");
            System.out.println("ok nofav");
            Cursor c = db.executeQuery("Select * from favoris ;");
            while (c.next()) {
                Row r = c.getRow();
                String a = r.getString(0);

                String n = r.getString(1);
                String pre = r.getString(2);

                System.out.println("no more  : id user :" + n + "ideq :" + pre + "id :" + a);

            }

        } catch (IOException ex) {

        }
    }
}
    //return true ;

