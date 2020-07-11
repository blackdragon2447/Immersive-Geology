package com.muddykat.noise;

import static com.igteam.immersivegeology.common.world.gen.config.ImmersiveGenerationSettings.SEA_LEVEL;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.igteam.immersivegeology.common.world.noise.INoise2D;
import com.igteam.immersivegeology.common.world.noise.SimplexNoise2D;

public class NoiseGenTester {
	 public static void greyWriteImage(double[][] data, double[][] data2, double[][] data3){
	        //this takes an array of doubles between 0 and 1 and generates a grey scale image from them

	        BufferedImage image = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);

	        for (int y = 0; y < data[0].length; y++)
	        {
	          for (int x = 0; x < data.length; x++)
	          {
	            if (data[x][y]>1){
	                data[x][y]=1;
	            }
	            if (data[x][y]<0){
	                data[x][y]=0;
	            }
	            if (data2[x][y]>1){
	                data2[x][y]=1;
	            }
	            if (data2[x][y]<0){
	                data2[x][y]=0;
	            }
	            if (data3[x][y]>1){
	                data3[x][y]=1;
	            }
	            if (data3[x][y]<0){
	                data3[x][y]=0;
	            }
	              Color col=new Color((float)data[x][y],(float)data2[x][y],(float)data3[x][y]); 
	            image.setRGB(x, y, col.getRGB());
	          }
	        }

	        try {
	            // retrieve image
	            File outputfile = new File("saved.png");
	            outputfile.createNewFile();

	            ImageIO.write(image, "png", outputfile);
	        } catch (IOException e) {
	            //o no!
	        }
	    }


	    public static void main(String args[]){
	    	double iStart=0;
	        double iEnd=500;
	        double jStart=0;
	        double jEnd=500;
	        int chunkAmount = 30;
	        long seed = 1020;
	        //OpenSimplexNoise noise = new OpenSimplexNoise(seed);
	        
	        final INoise2D warpX = new SimplexNoise2D(seed).octaves(4).spread(0.1f).scaled(-30, 30);
	        final INoise2D warpZ = new SimplexNoise2D(seed + 1).octaves(4).spread(0.1f).scaled(-30, 30);
	        INoise2D noise = new SimplexNoise2D(seed).octaves(4).spread(0.2f).warped(warpX, warpZ).map(x -> x > 0.4 ? x - 0.8f : -x).scaled(-0.4f, 0.8f, 0f, 1f).spread(0.3f);
	       
	        double[][] result=new double[16 * chunkAmount][16 * chunkAmount];

	        for(int i=0;i< (16 * chunkAmount);i++){
	            for(int j=0;j<(16 * chunkAmount);j++){
	                int xp=(int)(iStart+i*((iEnd-iStart)/(16 * chunkAmount)));
	                int yp=(int)(jStart+j*((jEnd-jStart)/(16 * chunkAmount)));
	                
	                result[i][j]=noise.noise(xp, yp);
	                
	            } 
	        }

	        //OpenSimplexNoise noise = new OpenSimplexNoise(seed);
	        INoise2D noise2 = new SimplexNoise2D(seed).octaves(4).scaled(0, 0.8f).flattened(0, 500).spread(0.2f).terraces(8);
	        double[][] result2=new double[16 * chunkAmount][16 * chunkAmount];

	        for(int i=0;i< (16 * chunkAmount);i++){
	            for(int j=0;j<(16 * chunkAmount);j++){
	                int xp=(int)(iStart+i*((iEnd-iStart)/(16 * chunkAmount)));
	                int yp=(int)(jStart+j*((jEnd-jStart)/(16 * chunkAmount)));
	                
	                result2[i][j]=noise.terraces(5).noise(xp, yp);
	                
	            } 
	        }
	        
	        INoise2D noise3 = new SimplexNoise2D(seed).octaves(4).scaled(0.2f, 0.8f).flattened(0, 500).spread(0.02f);
	        double[][] result3=new double[(16 * chunkAmount)][(16 * chunkAmount)];
	         
	
	        for(int i=0;i< (16 * chunkAmount);i++){
	            for(int j=0;j<(16 * chunkAmount);j++){
	            	result3[i][j] = noise.flattened(0.2f, 0.6f).noise(i, j);
	            }
	        }
	        
	            
	        greyWriteImage(result,result2,result3);
	    }
}