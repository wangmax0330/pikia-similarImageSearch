package com.pikia.z.test;


import java.util.ArrayList;
import java.util.List;

import com.pikia.app.util.ImageHelper;


public class SimilarImageTestTest {
	public static void main(String[] args) {
		List<String> hashCodes = new ArrayList<String>();
	    
	    String filename = ImageHelper.path + "/images/";
	    System.out.println( filename);
	    String hashCode = null;
		
	    for (int i = 0; i < 6; i++){
		    hashCode = SimilarImageSearch.produceFingerPrint(filename + "example" + (i + 1) + ".jpg");
		    hashCodes.add(hashCode);
        }
	    System.out.println("Resources: ");
	    System.out.println(hashCodes);
	    
		String sourceHashCode = SimilarImageSearch.produceFingerPrint(filename + "source.jpg");
		System.out.println("Source: ");
		System.out.println(sourceHashCode);
		
		for (int i = 0; i < hashCodes.size(); i++){
		    int difference = SimilarImageSearch.hammingDistance(sourceHashCode, hashCodes.get(i));
		    System.out.print("汉明距离:"+difference+"     ");
		    if(difference==0){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg一样");
		    }else if(difference<=5){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg非常相似");
		    }else if(difference<=10){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg有点相似");
		    }else if(difference>10){
		    	System.out.println("source.jpg图片跟example"+(i+1)+".jpg完全不一样");
		    }
        }
	}
}