package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static GuitarString guitarString[] = new GuitarString[37];
    public static final double CONCERT_A = 440.0;
    public static void main(String[] args) {
        for(int i = 0;i < 37;i++){
            guitarString[i] = new GuitarString(440 * Math.pow(2,(i - 24) / 12));
        }
        while(true){
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int t = keyboard.indexOf(key);
                if(t == -1){
                    continue;
                }
                else guitarString[t].pluck();
            }
            double sample = 0;
            for(int i = 0;i < 37;i++) sample += guitarString[i].sample();
            StdAudio.play(sample);
            for(int i = 0;i < 37;i++) guitarString[i].tic();
        }
    }
}
