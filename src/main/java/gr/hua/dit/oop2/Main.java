package gr.hua.dit.oop2;

import java.io.*;
import java.net.URISyntaxException;

import gr.hua.dit.oop2.musicplayer.Player;
import gr.hua.dit.oop2.musicplayer.PlayerException;
import gr.hua.dit.oop2.musicplayer.PlayerFactory;
import gr.hua.dit.oop2.musicplayer.PlayerFactory;



public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

      Audio audio = new Audio();
      InputStream song = null;
      M3uPlaylist m3u = null;


      if (args.length > 2) {
        System.out.println("Please provide until 2 arguments");
      }
      else{

        try {

          if(args.length==1 && args[0].endsWith(".mp3") ){
            audio.playMp3UntilEnd(song,args);
          }
          else if(args[0].endsWith(".m3u") && args.length==1){
            audio.playM3uAllList(m3u,song,args[0]);
          }
          else if(args[1].equals("order")){
            audio.playM3uAllList(m3u,song,args[0]);
          }
          else if(args[0].endsWith(".m3u") && args[1].equals("random")){
            audio.playM3uRandom(m3u,song,args[0]);

          }
          else if(args.length==2 && args[1].equals("loop")){
            audio.playMp3Loop(song,args);
          }

        } catch (FileNotFoundException e) {
          System.err.println("File not found");
        } catch (PlayerException e) {
          System.err.println("Something's wrong with the player: " + e.getMessage());
        } finally {
          if (audio.getP() != null)
            audio.getP().close();
        }

      }
    }
}
