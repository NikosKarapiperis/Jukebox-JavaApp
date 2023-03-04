package gr.hua.dit.oop2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Class for parsing a m3u list.
public class M3uPlaylist {
  List<String> mp3;
  int next;
  public M3uPlaylist(File f){
    //We create an ArrayList with Strings
    mp3=new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
      String line;
      while ((line = br.readLine()) != null) {
        //check for extended m3u file which contains comments
        if(line.startsWith("#")==false){
          addMP3(line);
        }

      }
    } catch (IOException ex) {
      System.out.println("error is reading the file");
    }
    next=-1;
  }

  private void addMP3(String line) {
    //We check if line ends with .mp3 because we want to add only mp3 files in our list
    if (line.endsWith(".mp3")) {
      //We insert in the ArrayList only the lines that contains mp3 files
      mp3.add(line);
    }
  }

  public String getNext(){
    next++;
    if(mp3.size()<=next)
      next=0;
    return mp3.get(next);
  }
}


