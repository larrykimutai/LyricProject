public class webscrpObj {
  private String SearchURL;
  private String HTML;
  private int PageNumber;

  public webscrpObj(){
    SearchURL = null;
    HTML = null;
    PageNumber = 0;
  }

  //setters
  public void setSearchURL(String searchURL){
    SearchURL = searchURL;
  }

  public void setHTML(String html){
    HTML = html;
  }

  public void setPageNumber(int pageNumber){
    PageNumber = pageNumber;
  }

  //getters
  public String getSearchURL(){
    return SearchURL;
  }

  public String getHTML(){
    return HTML;
  }

  public int getPageNumber(){
    return PageNumber;
  }
}
