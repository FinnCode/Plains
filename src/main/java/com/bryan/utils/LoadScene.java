package com.bryan.utils;

import java.util.ArrayList;
import java.util.List;

import com.bryan.scene.Scene;
import com.bryan.scene.impl.Scene_1;
import com.bryan.scene.impl.Scene_2;
import com.bryan.scene.impl.Scene_Over;

public class LoadScene {
	public static List<Scene> load() {
		List<Scene> scenes = new ArrayList<Scene>();
		Scene scene_1 = new Scene_1();
		scenes.add(scene_1);
		Scene scene_2 = new Scene_2();
		scenes.add(scene_2);
		// Class.forName("add-on/*");
		Scene scene_over = new Scene_Over();
		scenes.add(scene_over);
		return scenes;
	}
}
