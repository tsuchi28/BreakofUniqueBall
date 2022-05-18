package breakblock;

import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block extends ImageView{
	private boolean life = true;
	
	static Image img1 = new Image(Paths.get(Screen.BlockImg1).toUri().toString());
	static Image img2 = new Image(Paths.get(Screen.BlockImg2).toUri().toString());

	//コンストラクタ
	Block(int x,int y){
		//画像セット
		setImage(img1);
		
		//初期位置
		setX(x);
		setY(y);
	}
	
	//ブロック画像を不可視に変更
	void setlconBreak() {
		setImage(img2);
	}
	
	//lifeセット
	void setLife(Boolean l) {
		life = l;
	}
	
	boolean getlife() {
		return life;
	}
	
	//void reset() {
		//setImage(img1);
	//}
}
