package gr.hua.dit.oop2;

import gr.hua.dit.oop2.musicplayer.Player;
import gr.hua.dit.oop2.musicplayer.PlayerException;
import gr.hua.dit.oop2.musicplayer.PlayerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


public class Audio {
  //encapsulation with private declaration
  private Player p;
  private String[] args;
  private InputStream song;

  //default constructor
  public Audio(){

  }


  //getters
  public Player getP() {
    return p = PlayerFactory.getPlayer();
  }

  public String[] getArgs() {
    return args;
  }


  //method for playing a mp3 file until the end
  public void playMp3UntilEnd(InputStream song,String[] args) throws FileNotFoundException, PlayerException {
      //Play the first argument that  user inserted.
      song = new FileInputStream(args[0]);
      System.out.println("Song "+songsNames(args[0])+" just begin");
      getP().play(song);
      System.out.println("Song "+songsNames(args[0])+" just end");
  }

  //method for playing a mp3 file in loop
  public void playMp3Loop(InputStream song,String[] args) throws FileNotFoundException, PlayerException {
    boolean loop = true;
    //We create a do...while loop and we are playing the song until getStatus()==Player.Status.ERROR
    do{
      System.out.println("Song "+songsNames(args[0])+" just begin");
      song = new FileInputStream(args[0]);
      getP().play(song);
      System.out.println("Song "+songsNames(args[0])+" just end");

      if(getP().getStatus() == Player.Status.ERROR){
        loop = false;
      }

    }while(loop);

   }
   //method for playing random mp3 files from m3u playlist
  public void playM3uRandom(M3uPlaylist m3u,InputStream song,String args) throws PlayerException, FileNotFoundException {
    m3u = new M3uPlaylist(new File(args));

      //We create a table with Integers and set size m3u.mp3.size().
      Integer[] arr = new Integer[m3u.mp3.size()];
      //Insert in table numbers from 0 to arr.length that is equal with m3u.mp3.size().
      for (int j = 0; j < arr.length; j++) {
        arr[j] = j;
      }
      //The shuffle() method is used to work by randomly reorders the specified list elements using a default randomness.
      Collections.shuffle(Arrays.asList(arr));
         //Now, the numbers in array is mixed up, so we can play random mp3 files from a specific list without duplicates.
         for(int i=0; i<arr.length; i++){
            if (m3u.mp3.get(arr[i]).startsWith("\\") == false) {
               System.out.println("Song "+songsNames(m3u.mp3.get(arr[i]))+" just begin");
               song = new FileInputStream(relativePaths(args) + m3u.mp3.get(arr[i]).replace("\\", "/"));
               getP().play(song);
               System.out.println("Song "+songsNames(m3u.mp3.get(arr[i]))+" just end");
            }
            else {

               System.out.println("Song "+songsNames(m3u.mp3.get(arr[i]))+" just begin");
               song = new FileInputStream(m3u.mp3.get(arr[i]).replace("\\", "/"));
               getP().play(song);
               System.out.println("Song "+songsNames(m3u.mp3.get(arr[i]))+" just end");

           }
         }

        }


  //Method for play all mp3 files in m3u list in order
  public void playM3uAllList(M3uPlaylist m3u,InputStream song,String args) throws PlayerException, FileNotFoundException{
    m3u = new M3uPlaylist(new File(args));

    //We through the whole list
    for(int i=0; i<m3u.mp3.size(); i++){
       //If the reference is with relative path, case the file is in sub-directories that this playlist is stored in.
       //or Case the file is in the same directory that the playlist is stored in.
       if(m3u.mp3.get(i).startsWith("\\")==false){
         //we use the replace method to replace '\' with '/'. Now, we have created the full path from relative path.
         System.out.println("Song "+songsNames(m3u.mp3.get(i))+" just begin");
         song = new FileInputStream(relativePaths(args)+m3u.mp3.get(i).replace("\\","/"));
         getP().play(song);
         System.out.println("Song "+songsNames(m3u.mp3.get(i))+" just end");
       }
       else{//if the reference is an absolute path.
         System.out.println("Song "+songsNames(m3u.mp3.get(i))+" just begin");
         song = new FileInputStream(m3u.mp3.get(i).replace("\\","/"));
         getP().play(song);
         System.out.println("Song "+songsNames(m3u.mp3.get(i))+" just end");
       }
    }
  }

  //Method for take only the name of the song
  public String songsNames(String args){

    //if playing songs from m3u list
    if(args.contains("\\")){
      return (String) args.subSequence(args.lastIndexOf("\\")+1,args.lastIndexOf(".mp3"));
    }
    //We find where is the last index of char '/' .
    //We find where is the last index of char '.mp3'.
    //return the name of the song
    return (String) args.subSequence(args.lastIndexOf("/")+1,args.lastIndexOf(".mp3"));
  }

  //Method for relative paths.
  public String relativePaths(String args){
    //We find where is the last index of char '/' , because we want to keep the parent folder.
    //With subSequence, we take the substring from 0 to last index of '/', because we want to take the parent folder of m3u list.
    return (String) args.subSequence(0,args.lastIndexOf("/")+1);
  }





}

