
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Sonar Cloud
public class Creator {
    private String WebsiteName;
    private final static String ROBOTS = "/robots.txt";
    Pattern disallowPattern;
    String startRegex;
    private String starReplacement;
    public Creator(String websiteName,String starReplacement) {
        WebsiteName = websiteName;
        disallowPattern =Pattern.compile("Disallow*");
        startRegex="\\*";
        this.starReplacement=starReplacement;
    }

    public String CreateUrl() {
        final StringBuilder allinOne=new StringBuilder("");
        try {
            ArrayList<String> disallows = new ArrayList<>();
           // System.out.println("https://" + WebsiteName);
            URL scanUrl = new URL("https://" + WebsiteName + ROBOTS);
            HttpURLConnection urlConnection = (HttpURLConnection) scanUrl.openConnection();
            urlConnection.connect();
            Scanner s = new Scanner(urlConnection.getInputStream());
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Matcher matcher= disallowPattern.matcher(line);

                //System.out.println(line);
                if(matcher.find()) {
                    disallows.add(line);
                }
            }
            disallows.forEach(elo->{
                String deneme=elo.replaceAll(startRegex,starReplacement);
                allinOne.append(WebsiteName+deleteDisAllow(deneme)+"\n");
               // System.out.println(deleteDisAllow(deneme));

            });
        } catch (MalformedURLException e) {
            System.err.println("Bad Url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allinOne.toString();
    }
    private String deleteDisAllow(String withDissallow){
        return withDissallow.replaceAll("Disallow:(\\s*)","");
    }
}
