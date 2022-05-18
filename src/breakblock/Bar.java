package breakblock;

import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Bar extends ImageView implements Observer{
	
	//砲台画像生成
	static private Image Barimg = new Image(Paths.get(Screen.BarImg).toUri().toString());
	Ball ball;
	
	//コンストラクタ
	Bar(Ball b){
		super(Barimg);
		this.ball = b;
		
		//位置設定
		this.setX(0);//初期値x=0
		this.setY(Screen.Bar_Y);//y=500固定
	}
	
	public void update(Ball s) {
		
		//バーの当たり判定
		int bx = (int)(s).getX();
		int by = (int)(s).getY();
		
		//左端に当たったら(速度2)
		if(Screen.hitCheck(bx, by, Screen.Ball_W, Screen.Ball_H, (int)getX(), (int)getY(), 10, Screen.Bar_H)) {
			//遅延スキルを使っていたら
			if(ball.getSklFlg() == 1) {
				ball.setVx(-1);
				ball.setVy(-0.5);
			}
			//スキルを使っていないor遅延スキル以外だったら
			else {
				ball.setVx(-2); //Ball x左加速
				ball.setVy(-1); //Ball y反転
			}
		}
		
		//右端に当たったら(速度2)
		else if(Screen.hitCheck(bx, by, Screen.Ball_W, Screen.Ball_H, (int)getX()+60, (int)getY(), 10, Screen.Bar_H)) {
			//遅延スキルを使っていたら
			if(ball.getSklFlg() == 1) {
				ball.setVx(1); //Ball x右加速
				ball.setVy(-0.5); //Ball y反転
			}
			//スキルを使っていないor遅延スキル以外だったら
			else {
				ball.setVx(2); //Ball x右加速
				ball.setVy(-1); //Ball y反転
			}
		}
		
		//左寄りに当たったら(速度1.5)
		else if(Screen.hitCheck(bx, by, Screen.Ball_W, Screen.Ball_H, (int)getX()+10, (int)getY(), 15, Screen.Bar_H)) {
			//遅延スキルを使っていたら
			if(ball.getSklFlg() == 1) {
				ball.setVx(-0.75); //Ball x左加速
				ball.setVy(-0.5); //Ball y反転
			}
			//スキルを使っていないor遅延スキル以外だったら
			else {
				ball.setVx(-1.5); //Ball x左加速
				ball.setVy(-1); //Ball y反転
			}
		}
		
		//右寄りに当たったら(速度1.5)
		else if(Screen.hitCheck(bx, by, Screen.Ball_W, Screen.Ball_H, (int)getX()+45, (int)getY(), 15, Screen.Bar_H)) {
			//遅延スキルを使っていたら
			if(ball.getSklFlg() == 1) {
				ball.setVx(0.75); //Ball x右加速
				ball.setVy(0.5); //Ball y反転
			}
			//スキルを使っていないor遅延スキル以外だったら
			else {
				ball.setVx(1.5); //Ball x右加速
				ball.setVy(-1); //Ball y反転
			}
		}
		
		//バーの中央に当たったら(速度1)
		else if(Screen.hitCheck(bx, by,Screen.Ball_W,Screen.Ball_H, (int)getX()+25, (int)getY(), 20, Screen.Bar_H)) {
			//遅延スキルを使っていたら
			if(ball.getSklFlg() == 1) {
				ball.setVy(-0.5); //Ball y反転
			}
			//スキルを使っていないor遅延スキル以外だったら
			else {
				ball.setVy(-1); //Ball y反転
			}
		}
	}
	
	//砲台移動

	void move(KeyCode kc) {
		
		if(kc == KeyCode.RIGHT) { //→を押した
			if(this.getX()+Screen.Bar_W < Screen.Right) { //右端でなかったら
				setX(getX() + 30);
				if(this.getX()+Screen.Bar_W > Screen.Right) { //移動後右端を超えたら右端へ
					this.setX(Screen.Right);
				}
			}
		}
		
		if(kc == KeyCode.LEFT) { //←を押した
			if(this.getX() > Screen.Left) { //左端でなかったら
				setX(getX() - 30);
				if(this.getX() < Screen.Left) { //移動後左端を超えたら左端へ
					this.setX(Screen.Left);
				}
			}
		}
		
		System.out.printf("【砲台位置】(%d,%d)\n", (int)getX(), (int)getY());
	}
	
	void reset() {
		setX(0);
		setY(Screen.Bar_Y);
	}	
}
