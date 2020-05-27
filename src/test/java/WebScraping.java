import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.commons.lang3.time.StopWatch;

import javax.swing.text.html.HTML;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraping {
  public static void main(String[] args){

    int totalSongs = 0;
    //create webscraping object
    webscrpObj obj = new webscrpObj();

    //creat stopwatch
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    int i = 0;


    String SearchURL = "https://www.lyricsplanet.com/search.php?field=title&value=A&p=1";
    obj.setSearchURL(SearchURL);



    //repeat for all letters
    do{
      String[] alph = new String[]
              {"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
                      "O","P","Q","R","S","T","U","V","W","X","Y","Z"};
      int num = 1;
      //create a pattern that detects structure of page number out of given..
      //Pattern page = Pattern.compile()
      //Pattern page = Pattern.compile("</strong> of <strong>(/d{1}?)</strong> with <strong>");

      /**       of <strong>.\d</strong>         */
      //Matcher matcher = page.matcher();
      String searchURLLetter = "https://www.lyricsplanet.com/search.php?field=title&value=" + alph[i] + "&p=" + num;
      String htmlLetter = HTMLtoString(searchURLLetter);
      //get number of pages for current letter
      Pattern page = Pattern.compile("of <strong>(\\d+)</strong> with");
      Matcher match = page.matcher(htmlLetter);
      //repeat until all pages of current letter are done

      while(match.find()){
        String codeGroup = match.group(1);
        System.out.println(codeGroup);
        System.out.println("___________________________________________________________________________");
        obj.setPageNumber(Integer.parseInt(codeGroup));
      }
      do{
        totalSongs++;
        String searchURLPage = "https://www.lyricsplanet.com/search.php?field=title&value=" + alph[i] + "&p=" + num;



        String htmlPage = HTMLtoString(searchURLPage);

        //find pattern for each song title and author
        Pattern p = Pattern.compile("\\d\">" + alph[i] + "(.*?)</a>");
        Matcher m = p.matcher(htmlPage);

        //find pattern for each page number
        //of <strong>\d+</strong> with
        //Pattern page = Pattern.compile("of <strong>(\\d+)</strong> with");
        //Matcher matcher = page.matcher(html);
       // int pageNumber = Integer.parseInt(matcher.group(1));
        //obj.setPageNumber(pageNumber);

        //print data
        while(m.find()){
          String codeGroup = m.group(1);
          System.out.println(alph[i] + ""+ codeGroup);
        }

        num++;
      } while(num<obj.getPageNumber());

      i++;
    } while(i<26);

    //stop stopwatch and print time
    stopWatch.stop();
    long time = stopWatch.getTime();
    System.out.println(time/3600 + " mins...I think");

    System.out.println(totalSongs);
  }

  public static String HTMLtoString(String searchURL){
    //get HTML as string
    String htm = null;
    URLConnection connection = null;

    //convert web page HTML into string
    try{
      connection = new URL(searchURL).openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      scanner.useDelimiter("\\Z");
      htm = scanner.next();
      scanner.close();
    } catch (Exception e){e.printStackTrace();}
    return htm;

  }

  //create method that creates file and print to file.

}
