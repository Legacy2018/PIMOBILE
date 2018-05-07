/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import com.mycompagny.Service.AnnonceCovoiturageService;
//import static com.mycompany.gui.AfficherAnnoncesCovoiturage.AnnonceCovoiturage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author medma
 */
public class AfficherAnnonce {

    Form f;
    Database db;
    private Resources theme;
    static String Distance = "0";
    Style s = UIManager.getInstance().getComponentStyle("Title");

    public AfficherAnnonce() {
        System.out.println("faouziiii2"+AfficherAnnoncesCovoiturage.AnnonceCovoiturage);
        try {
            theme = UIManager.initFirstTheme("/theme2");

            // Enable Toolbar on all Forms by default
            Toolbar.setGlobalToolbar(true);
            Button Modifier = new Button("Modifier");
            Button Supprimer = new Button("Supprimer");
            Button Reserver = new Button("Résesrver");
            MapContainer cnt = new MapContainer("AIzaSyBoVSG3FplX2Ckbl672m4HWjnKK4WS5j2w");
            Button CancelFavoris = new Button("Supprimer Des Favoris");
            Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            System.out.println("bibii" + AfficherAnnoncesCovoiturage.AnnonceCovoiturage);
            FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_MONETIZATION_ON, s);
            System.out.println("m");
            Label Prix = new Label("Prix En $: " + String.valueOf(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getTarif()), prx);
            Label Tarif = new Label("1");
            Tarif.setVisible(false);
            System.out.println("m");
            FontImage dep = FontImage.createMaterial(FontImage.MATERIAL_FLAG, s);
            FontImage arr = FontImage.createMaterial(FontImage.MATERIAL_FLAG, s);
            SpanLabel AdresseDepart = new SpanLabel(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getAdresse_depart());
            Label AdresseDepart1 = new Label("De :", dep);
            SpanLabel AdresseArrivee = new SpanLabel(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getAdresse_arrivee());
            Label AdresseArrivee1 = new Label("à :", arr);
            FontImage plc = FontImage.createMaterial(FontImage.MATERIAL_EVENT_SEAT, s);
            Label places = new Label("Nombre de palces disponibles:  " + Tarif.getText(), plc);
            Slider sldr = new Slider();
            System.out.println("m");
            if (AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getNbrePlaces() == 0) {
                sldr.setEditable(false);
            } else {
                sldr.setEditable(true);
            }
            sldr.setMaxValue(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getNbrePlaces());
            sldr.setMinValue(1);
            sldr.setVertical(false);
            System.out.println("m");
            CancelFavoris.setVisible(false);
            Label coast1 = new Label("Cout Total:    ");
            Label coast = new Label("" + sldr.getProgress() * AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getTarif());
            Tarif.setText((AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getNbrePlaces() - sldr.getProgress()) + "");
            System.out.println("m");
            sldr.addDataChangedListener(new DataChangedListener() {

                @Override
                public void dataChanged(int type, int index) {
                    Tarif.setText(String.valueOf(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getNbrePlaces() - sldr.getProgress()));//To change body of generated methods, choose Tools | Templates.
                    places.setText("Nombre de palces disponibles:  " + Tarif.getText());
                    coast.setText("" + sldr.getProgress() * AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getTarif());
                }
            });
            Reserver.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    AnnonceCovoiturageService Ann = new AnnonceCovoiturageService();
                    Ann.ReserverAnnonce(sldr.getProgress(), AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getId_annonce());
                    boolean mybasereturn = Database.exists("Reservation");
                    Message m = new Message("bonjour monsieur je veux reserver pour votre annonce de covoiturage");
                    Display.getInstance().sendMessage(new String[]{"mohamedmalek.jouini@esprit.tn"}, "demande de covoiturage", m);
                }
            });
            System.out.println("m");
            Button res = new Button("Ajouter aux favoris");
            db = Database.openOrCreate("Fav");
            List<String> l = new ArrayList();
            boolean mybasereturn = Database.exists("Fav");
            if (mybasereturn == false) {
                db.execute("create table Favoris (idannonce TEXT, utilisateurid TEXT);");
            }
            System.out.println("faouzi"+Login.u.getId());
            //changer par id annonceur connecté
            Cursor c = db.executeQuery("select * FROM Favoris where utilisateurid=" + Login.u.getId());
            while (c.next()) {System.out.println("la");
                System.out.println("samiiiirrrr");
                Row r = c.getRow();
                String idannonce = r.getString(0);
                String idannonceur = r.getString(1);

                if (String.valueOf(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getId_annonce()).equals(idannonce)) {
                    System.out.println("blablabla");
                    res.setVisible(false);
                    CancelFavoris.setVisible(true);
                }

            }

            res.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    AnnonceCovoiturageService Ann = new AnnonceCovoiturageService();
                    Ann.ReserverAnnonce(sldr.getProgress(), AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getId_annonce());
                    boolean mybasereturn = Database.exists("Fav");
                    try {
                        db = Database.openOrCreate("Fav");

                        if (mybasereturn == false) {
                            db.execute("create table Favoris (idannonce TEXT, utilisateurid TEXT);");
                        }

                    } catch (IOException ex) {
                        System.out.println("mawjoudaaaaaa!!!");
                    }
                    try {

                        db.execute("insert into Favoris (idannonce , utilisateurid) values ('" + AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getId_annonce() + "','" + Login.u.getId_user() + "');");

                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            });

            CancelFavoris.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("samira1");
                    boolean mybasereturn = Database.exists("Fav");
                    try {
                        db = Database.openOrCreate("Fav");
                        System.out.println("samira2");

                    } catch (IOException ex) {
                        System.out.println("mawjoudaaaaaa!!!");
                    }
                    try {
                        System.out.println("samira3");
                        db.execute("delete from Favoris where idannonce='" + AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getId_annonce() + "'and utilisateurid='" + Login.u.getId_user() + "'");
                        System.out.println("samira");
                        AfficherAnnoncesCovoiturage a = new AfficherAnnoncesCovoiturage();
                        a.getF().show();

                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            });
System.out.println("m");
            f = new Form();
            System.out.println("m");
            String encoded = getRoutesEncoded("Paris", "Dijon");
            // decode the routes in an arry of coords
            Coord[] coords = decode(encoded);
            cnt.setCameraPosition(coords[0]);
            System.out.println("latlong " + coords[0]);

            System.out.println("cords " + coords);

            cnt.addPath(coords);
            FontImage dis = FontImage.createMaterial(FontImage.MATERIAL_SWAP_HORIZ, s);
            Label Dist = new Label("Distance:   " + Distance, dis);
System.out.println("m");
            cnt.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1000));
            C0.add(Tarif);

            Button titre = new Button("Détails");

            f.setUIID("AnnoncesForm");
            Toolbar tb = f.getToolbar();
            Image icon = theme.getImage("icon.png");
            Container topBar = BorderLayout.east(new Label(icon));

            tb.addMaterialCommandToSideMenu("Annonces de Covoiturage", FontImage.MATERIAL_LIST, e -> {
                AfficherAnnoncesCovoiturage a = new AfficherAnnoncesCovoiturage();
                a.getF().show();
            });
            tb.addMaterialCommandToSideMenu("Favoris", FontImage.MATERIAL_STAR, e -> {
                AnnoncesFavorites a;
                try {
                    a = new AnnoncesFavorites();
                    a.getF().show();
                } catch (IOException ex) {

                }

            });
            tb.addMaterialCommandToSideMenu("Mes Annonces", FontImage.MATERIAL_VIEW_LIST, e -> {
                //mes Annonces
                MesAnnoncesCovoiturage a = new MesAnnoncesCovoiturage();
                a.getF().show();
            });

            tb.addMaterialCommandToSideMenu("Ajouter Votre Trajet", FontImage.MATERIAL_PLACE, e -> {
                AjouterAnnonceCovoiturage a = new AjouterAnnonceCovoiturage();
                a.getF().show();
            });
            f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
                HomeRussie2018 h = new HomeRussie2018();
                h.getF().show();
            });
            C1.add(AdresseDepart1);
            C1.add(AdresseDepart);
            C2.add(AdresseArrivee1);
            C2.add(AdresseArrivee);
            C3.add(coast1);
            C3.add(coast);
            C0.add(titre);
            C0.add(C1);
            C0.add(C2);
            C0.add(Dist);
            C0.add(Prix);
            System.out.println("ouhmennek");
            C0.add(places);
            C0.add(C3);
            C0.add(sldr);
          if(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getId_annonceur()==Login.u.getId())  
          {C0.add(Supprimer);
            C0.add(Modifier);}
if(AfficherAnnoncesCovoiturage.AnnonceCovoiturage.getId_annonceur()!=Login.u.getId())
{ C0.add(Reserver);

            C0.add(res);
            C0.add(CancelFavoris);}
           // C0.add(cnt);

            //
            f.add(C0);
            f.refreshTheme();

            f.show();
            Supprimer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    AnnonceCovoiturageService AnnCovoiturage = new AnnonceCovoiturageService();
                    AnnCovoiturage.removeAnnonce(AfficherAnnoncesCovoiturage.AnnonceCovoiturage);
                }
            });

            Modifier.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    ModifierAnnonce AffAnnonce = new ModifierAnnonce();
                    AffAnnonce.getF().show();
                }
            });
        } catch (IOException ex) {

        }
    }

    public static String getDistance() {
        return Distance;
    }

    public static void setDistance(String Distance) {
        AfficherAnnonce.Distance = Distance;
    }

    public Form getF() {

        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public static String getRoutesEncoded(String src, String dest) {

        String ret = "";

        try {

            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false);

            request.addArgument("key", "AIzaSyBoVSG3FplX2Ckbl672m4HWjnKK4WS5j2w");

            request.addArgument("origin", src);

            request.addArgument("destination", dest);

            NetworkManager.getInstance().addToQueueAndWait(request);

            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
//            System.out.println("response "+response);
            if (response.get("routes") != null) {

                ArrayList routes = (ArrayList) response.get("routes");

                if (routes.size() > 0) {
                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                }

                System.out.println("distance " + ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("legs")).get(0)).get("distance")).get("text").toString());
                Distance = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("legs")).get(0)).get("distance")).get("text").toString();
                System.out.println("Diatanceeeee" + Distance);
                //  String d = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("distance")).get("text").toString();
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return ret;

    }

    public static void getRoutesEncodedAsync(Coord src, Coord dest, Callback callback) {

        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false) {

            @Override

            protected void readResponse(InputStream input) throws IOException {

                String ret = "";

                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));

                if (response.get("routes") != null) {

                    ArrayList routes = (ArrayList) response.get("routes");

                    if (routes.size() > 0) {
                        ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                    }

                }

                callback.onSucess(ret);

            }

        };

        request.addArgument("key", "AIzaSyBoVSG3FplX2Ckbl672m4HWjnKK4WS5j2w");

        request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());

        request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

        NetworkManager.getInstance().addToQueue(request);

    }

    public static Coord[] decode(final String encodedPath) {

        int len = encodedPath.length();

        final ArrayList<Coord> path = new ArrayList<Coord>();

        int index = 0;

        int lat = 0;

        int lng = 0;

        while (index < len) {

            int result = 1;

            int shift = 0;

            int b;

            do {

                b = encodedPath.charAt(index++) - 63 - 1;

                result += b << shift;

                shift += 5;

            } while (b >= 0x1f);

            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;

            shift = 0;

            do {

                b = encodedPath.charAt(index++) - 63 - 1;

                result += b << shift;

                shift += 5;

            } while (b >= 0x1f);

            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new Coord(lat * 1e-5, lng * 1e-5));

        }

        Coord[] p = new Coord[path.size()];

        for (int i = 0; i < path.size(); i++) {

            p[i] = path.get(i);

        }

        return p;

    }

}
