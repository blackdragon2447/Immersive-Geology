/*
 * Author: Muddykat
 * Date: 
 */

package com.igteam.immersivegeology.common.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import com.igteam.immersivegeology.ImmersiveGeology;
import com.igteam.immersivegeology.api.materials.Material;
import com.igteam.immersivegeology.api.materials.MaterialUseType;


public class JsonGenerator {
	
	public static JsonGenerator INSTANCE = new JsonGenerator();
	
	public void generateDefaultItem(Material material, MaterialUseType type)
	{
		generateDefaultItem("item_"+type.getName()+"_"+material.getName(), material.getMaterialType().toString(), type.getName());
	}

	//NOTE: this generate runs at start up, minecraft looks for item and block models BEFORE this runs, 
	//which means you need to start minecraft up to generate the json, then close and start minecraft again to see it in game.
	public void generateDefaultItem(String registryName, String itemType, String itemBaseType)
	{
		try
		{
			File file = new File("../src/main/resources/assets/immersivegeology/models/item/"+registryName+".json");
			if(!file.exists())
			{
				JsonWriter jsonWriter = new JsonWriter(new FileWriter(file));
				jsonWriter.setIndent(" "); //this makes it more readable for humans!
				jsonWriter.beginObject();
				jsonWriter.name("parent").value("immersivegeology:item/base/"+itemBaseType);
				jsonWriter.endObject();
				jsonWriter.close();
			}
		} catch(IOException e)
		{

		}

	}
	
	public void generateDefaultBlock(String registryName) throws IOException {
		File state = new File("../src/main/resources/assets/"+ ImmersiveGeology.MODID +"/blockstates/" + registryName + ".json");
		if(!state.exists()) {
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(state)); 
			jsonWriter.setIndent(" "); //this makes it more readable for humans!
			jsonWriter.beginObject();
				jsonWriter.name("variants");
				jsonWriter.beginObject();
					jsonWriter.name("");
					jsonWriter.beginObject();
						jsonWriter.name("model").value(ImmersiveGeology.MODID +":block/" + registryName);
					jsonWriter.endObject();
				jsonWriter.endObject();
			jsonWriter.endObject();
		    jsonWriter.close();
		}
		
		File block = new File("../src/main/resources/assets/"+ ImmersiveGeology.MODID + "/models/block/" + registryName + ".json");
		if(!block.exists()) {
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(block)); 
			jsonWriter.setIndent(" "); //this makes it more readable for humans!
			jsonWriter.beginObject();
				jsonWriter.name("parent").value("block/cube_all");
				jsonWriter.name("textures");
				jsonWriter.beginObject();
					jsonWriter.name("all");
					jsonWriter.value(ImmersiveGeology.MODID +":blocks/" + registryName);
				jsonWriter.endObject();
			jsonWriter.endObject();
		    jsonWriter.close();
		}
		
		File item = new File("../src/main/resources/assets/"+ ImmersiveGeology.MODID + "/models/item/" + registryName + ".json");
		if(!item.exists()) {
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(item)); 
			jsonWriter.setIndent(" "); //this makes it more readable for humans!
			jsonWriter.beginObject();
				jsonWriter.name("parent").value(ImmersiveGeology.MODID + ":block/" + registryName);
			jsonWriter.endObject();
		    jsonWriter.close();
		}
	}
	
}