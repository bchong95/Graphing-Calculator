import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
Manages all the sound for my project
*/

public class Sound 
{
	/**
	Plays the minecraft button noise whenever someone uses a JButton
	*/
	public static void playFX()
    {
    	try 
    	{
    		File file = new File("click.wav");
    		AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
    		Clip clip = AudioSystem.getClip();
    		clip.open(audioIn);
    		clip.start();
    		} 
    	catch (UnsupportedAudioFileException e) {
    		//Controller.error("Unsupported Audio, please reload program");
    		System.exit(0);
    		} 
    	catch (IOException e) {
    		//Controller.error("Audio file not read, please reload program");
    		System.exit(0);
    		} 
    	catch (LineUnavailableException e) {
    		e.printStackTrace();
    		}
    }
	/**
	Plays the over 9000 meme whenever someone evaluates something bigger than the memory alloted
	Link: http://www.youtube.com/watch?v=SiMHTK15Pik
	*/
	public static void play9000()
	{
		try 
    	{
    		File file = new File("9000.wav");
    		AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
    		Clip clip = AudioSystem.getClip();
    		clip.open(audioIn);
    		clip.start();
    		} 
    	catch (UnsupportedAudioFileException e) {
    		//Controller.error("Unsupported Audio, please reload program");
    		System.exit(0);
    		} 
    	catch (IOException e) {
    		//Controller.error("Audio file not read, please reload program");
    		System.exit(0);
    		} 
    	catch (LineUnavailableException e) {
    		e.printStackTrace();
    		}
	}
	/**
	Plays a beep to let the user know that they did something wrong
	*/
	public static void error()
	{
		try 
    	{
    		File file = new File("error.wav");
    		AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
    		Clip clip = AudioSystem.getClip();
    		clip.open(audioIn);
    		clip.start();
    		} 
    	catch (UnsupportedAudioFileException e) {
    		//Controller.error("Unsupported Audio, please reload program");
    		System.exit(0);
    		} 
    	catch (IOException e) {
    		//Controller.error("Audio file not read, please reload program");
    		System.exit(0);
    		} 
    	catch (LineUnavailableException e) {
    		e.printStackTrace();
    		}
	} 
}
