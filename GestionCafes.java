import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class GestionCafes {
    public static void main(String[] args) throws IOException {
        Scanner clav = new Scanner(System.in);
        try {
              // ici j'instancifie la connection avec une properties
              InputStream inputDe = new FileInputStream("base.properties");
                
              Properties prop = new Properties();
              //je charge les proprietes

              prop.load(inputDe);


              //les variables qui porteront les proprietes
              String url = prop.getProperty("Url");
              String user = prop.getProperty("User");
              String passe = prop.getProperty("Passe");
              String driver = prop.getProperty("Driver");
              


            try {
              



                Class.forName(driver);
                Connection con = DriverManager.getConnection( url, user, passe);
                System.out.println("connection est passée avec succès !");
                Statement stat = con.createStatement();
                // Menu

                System.out.println("1. Etudiant avec consommation de café MAX de en une semaine et de cette semaine ");
                System.out.println("2. Le nombre total  de consommation de tasses de café  par:");
                System.out.println("3. Liste des étudiants et leurs nombre de consommation");
                System.out.println("4. Insérer les infos  de l'étudiant avec le numéro de semaine et son nbr de tasses");

                System.out.print("CHOIX: ");
                int choix;
                choix = clav.nextInt();
                switch (choix) {
                    case 1:
                        ResultSet resultat = stat.executeQuery("SELECT ETUDIANT, PRENOM, NOM, NB_TASSES, NO_SEMAINE FROM CONSOS_CAFE\r\n" + //
                                                        "c JOIN ETUDIANTS p ON p.ID=c.ETUDIANT WHERE c.NB_TASSES=(SELECT\r\n" + //
                                                        "MAX(NB_TASSES) FROM CONSOS_CAFE )");
                        while (resultat.next()) {
                            System.out.println("Prenom : "+resultat.getString("PRENOM"));
                            System.out.println("Nom: "+resultat.getString("NOM"));
                            System.out.println("Nbr tasses: " + resultat.getInt("NB_TASSES"));
                            System.out.println("Numéro Semaine: "+resultat.getString("NO_SEMAINE"));
                        }

                        break;
                    case 2:
                            System.out.println("Mettez le numéro de l'étudiant:");
                            int num=clav.nextInt();
                            ResultSet res1=stat.executeQuery("SELECT SUM(NB_TASSES) as total FROM CONSOS_CAFE WHERE ETUDIANT="+num+"");
                            while(res1.next()){
                                System.out.println("Son nbr total tasses est : "+res1.getInt("total"));
                            }
                                break;


                    case 3:
                            System.out.print("Metter le numéro de la semaine:");
                           int numeroDeSemaine=clav.nextInt();
                           ResultSet result=stat.executeQuery("SELECT ETUDIANT, PRENOM, NOM, NB_TASSES FROM CONSOS_CAFE c JOIN ETUDIANTS p ON c.ETUDIANT = p.ID WHERE c.NO_SEMAINE ="+ numeroDeSemaine+" ORDER BY NB_TASSES DESC");
                           while (result.next()) {
                            System.out.println("Prénom "+result.getString("PRENOM"));
                            System.out.println("NOm "+result.getString("NOM"));
                            System.out.println("Nombre tasses: "+result.getInt("NB_TASSES"));
                           }
                            break;
                
                    case 4:
                           System.out.print("Mettez le numéro de la semaine:");
                            int numSem=clav.nextInt();
                            System.out.print("\n");
                           System.out.print("Mettez le numéro de l'étudiant:");
                            int idProg=clav.nextInt();
                            System.out.print("\n");

                           System.out.print("Mettez le nombre de tasses consommées:");
                            int nbTasses=clav.nextInt();
                            stat.execute("INSERT INTO CONSOS_CAFE (NO_SEMAINE, ETUDIANT, NB_TASSES) VALUES ("+numSem+", "+idProg+","+ nbTasses+")");
                           System.out.println("Votre achat est bien enregistré");
                           break;
                    default:
                    System.out.print("LE Numéro donné ne figure pas sur la liste !");
                        break;
                }// je ferm ma connexion
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            
        }
    
    }
}